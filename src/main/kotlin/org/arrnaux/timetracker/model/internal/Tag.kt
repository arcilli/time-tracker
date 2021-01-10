package org.arrnaux.timetracker.model.internal

import org.arrnaux.timetracker.model.AbstractTag

data class Tag(
    override val id: String = ""
) : AbstractTag() {

}
