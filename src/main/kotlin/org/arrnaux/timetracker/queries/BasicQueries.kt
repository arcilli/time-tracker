package org.arrnaux.timetracker.queries

import com.expediagroup.graphql.spring.operations.Query
import org.springframework.stereotype.Component
import org.arrnaux.timetracker.model.internal.Tag

@Component
class BasicQueries : Query {

    fun tag() = Tag(id = "211212")
}
