package org.arrnaux.timetracker.model

import java.time.LocalDateTime
import java.util.*

open class AbstractActivity {
    open val id: String? = null

    // TODO: replace this with a Date when you can properly serialize a Date in GraphQL
    open var startDate: String? = null

    // TODO: replace this with a Date when you can properly serialize a Date in GraphQL
    open var endDate: String? = null
}