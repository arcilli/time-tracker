package org.arrnaux.timetracker.graphql.queries

import com.expediagroup.graphql.spring.operations.Query
import org.arrnaux.timetracker.model.internal.Tag
import org.arrnaux.timetracker.store.ActivitiesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Queries : Query {

    @Autowired
    private lateinit var repository: ActivitiesRepository

    fun tag() = Tag(name = "simple tag")

//    fun activity() = Activity(id = "1")
}
