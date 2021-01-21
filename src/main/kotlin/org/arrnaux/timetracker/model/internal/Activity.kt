package org.arrnaux.timetracker.model.internal

import org.arrnaux.timetracker.model.AbstractActivity
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import kotlin.streams.toList

@Entity
@Table(name = "activity")
data class Activity constructor(
    // TODO: generate this as a timestamp?
    @Id
    override val id: String = UUID.randomUUID().toString(),
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
     */
    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
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
    var tags: MutableList<Tag> = mutableListOf()
) : AbstractActivity() {
    fun containAtLeastOneTag(tagsList: List<String>): Boolean {
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
    fun spentTime(): Double {
        // TODO
        return -1.0
    }
}