package org.arrnaux.timetracker.model.internal

import org.arrnaux.timetracker.model.AbstractActivity
import java.time.LocalDateTime
import java.util.*

data class Activity(
        // TODO: generate this as a timestamp?
        override val id: String = UUID.randomUUID().toString(),

        override var startDate: String? = LocalDateTime.now().toString()
) : AbstractActivity() {

    var name: String = ""

    var description: String = ""

    var tags: List<Tag> = emptyList()
}
