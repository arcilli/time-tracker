package org.arrnaux.timetracker.model.internal

import org.arrnaux.timetracker.model.AbstractActivity
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

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
     * TODO: can this be replaced with a Set<> and still be correctly serialized by GraphQL?
     */
    @ManyToMany
    @JoinTable(
        name = "activity2tag",
        joinColumns =
        [JoinColumn(name = "activityId", referencedColumnName = "id")],
        inverseJoinColumns =
        [JoinColumn(name = "tagId", referencedColumnName = "id")]
    )
    var tags: List<Tag> = emptyList()
) : AbstractActivity() {
}
