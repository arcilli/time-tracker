package org.arrnaux.timetracker.model.internal

import com.expediagroup.graphql.annotations.GraphQLIgnore
import org.arrnaux.timetracker.model.AbstractTag
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tag")
data class Tag constructor(
    @Id
    override val id: String = UUID.randomUUID().toString(),

    @Column(name = "name")
    override var name: String = ""
) : AbstractTag() {
    @GraphQLIgnore
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tag

        if (name != other.name) return false

        return true
    }

    @GraphQLIgnore
    override fun hashCode(): Int {
        return name.hashCode()
    }
}