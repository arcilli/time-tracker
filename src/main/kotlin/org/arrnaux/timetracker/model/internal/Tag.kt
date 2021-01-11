package org.arrnaux.timetracker.model.internal

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
    override var name: String
) : AbstractTag() {
    constructor() : this(name = "")
}