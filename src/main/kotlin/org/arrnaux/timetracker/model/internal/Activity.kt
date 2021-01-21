package org.arrnaux.timetracker.model.internal

import com.expediagroup.graphql.annotations.GraphQLIgnore
import org.arrnaux.timetracker.model.AbstractActivity
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*
import javax.persistence.*
import kotlin.streams.toList

@Entity
@Table(name = "activity")
data class Activity constructor(
    // TODO: generate this as a timestamp?
    @Id
    override var id: String = UUID.randomUUID().toString(),
    @Column(name = "startDate")
    override var startDate: String? = LocalDateTime.now().toString(),

    @Column(name = "endDate")
    override var endDate: String? = null,

    @Column(name = "name")
    var name: String = "",

    @Column(name = "description")
    var description: String = "",

    /**
     * This field is not actually stored in the database. We're gonna set a table Activity2Tags, which will join activity
     * id with corresponding tag ids.
     *
     * TODO: can this be replaced with a Set<> and still be correctly serialized by GraphQL? (#5)
     * FetchType.Eager since the list of the tags is not containing so many elements, so it's not impacting the perfomance.
     */
    @ManyToMany(cascade = [CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH], fetch = FetchType.EAGER)
    @JoinTable(
        name = "activity2tag",
        joinColumns =
        [JoinColumn(name = "activityId", referencedColumnName = "id")],
        inverseJoinColumns =
        [JoinColumn(name = "tagId", referencedColumnName = "id")]
    )
    /**
     * Mutable list since this list will be populated by Hibernate.
     */
    var tags: List<Tag> = mutableListOf()
) : AbstractActivity() {
    fun containAtLeastOneTagFromList(tagsList: List<String>): Boolean {
        val tagNames = tags.stream().map { k -> k.name }.toList();
        for (value in tagsList) {
            if (tagNames.contains(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the spend time on the current activity, in minutes, as difference between endDate and startDate. If the
     * endDate is not set, the current time will be considered.
     */
    fun spentTime(): Long {
        if (null != startDate) {
            val startTime = LocalDateTime.parse(startDate);
            val endTime = LocalDateTime.parse(endDate ?: LocalDateTime.now().toString());
            return ChronoUnit.MINUTES.between(startTime, endTime);
        }
        return -1;
    }

    /**
     * This is used when a mutation is made in order to modify a subset of the activities fields (ie. a PATCH in REST)
     */
    @GraphQLIgnore
    fun copyFromObject(activity: Activity) {
        id = activity.id;
        if (activity.containsValidTags()) {
            tags = activity.tags
        }
        if (activity.name.isNotBlank()) {
            name = activity.name
        }
        if (activity.description.isNotBlank()) {
            description = activity.description
        }
        startDate = activity.startDate ?: startDate
        endDate = activity.endDate ?: endDate
    }

    /**
     * Checks if the current activity contains tags or not. A valid tag is characterized by the fact that its name is
     * not empty.
     */
    @GraphQLIgnore
    fun containsValidTags(): Boolean {
        return tags.stream()
            .allMatch { it.name.isNotBlank() };
    }
}