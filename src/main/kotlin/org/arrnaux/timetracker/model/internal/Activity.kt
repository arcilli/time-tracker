package org.arrnaux.timetracker.model.internal

import org.arrnaux.timetracker.model.AbstractActivity
import java.util.*

data class Activity(
    // TODO: initialize string with some random number, based on the current time.
    override val id: String = "",

    override var startDate: Date? = Date(),
) : AbstractActivity() {

    var name : String = ""

    var description: String = ""

    var tags : List<Tag> = emptyList()
}
