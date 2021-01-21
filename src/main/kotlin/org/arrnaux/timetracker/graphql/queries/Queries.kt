package org.arrnaux.timetracker.graphql.queries

import com.expediagroup.graphql.annotations.GraphQLDescription
import com.expediagroup.graphql.spring.operations.Query
import org.arrnaux.timetracker.model.internal.Activity
import org.arrnaux.timetracker.model.internal.Tag
import org.arrnaux.timetracker.store.ActivitiesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import kotlin.streams.toList

@Component
class Queries : Query {

    @Autowired
    private lateinit var repository: ActivitiesRepository

    fun tag() = Tag(name = "simple tag")

    @GraphQLDescription("Find activities by startDate and endDate")
    fun findActivitiesWithAtLeastOneTag(
        startDate: String?,
        endDate: String?,
        tags: List<String>?
    ): List<Activity> {
        /**
         * TODO: try to add the tag filtering in the query, not filtering them by iterating (#6).
         */
        val activities: List<Activity> = repository.findActivitiesByStartDateAfterAndEndDateBeforeOrderByStartDateAsc(
            startDate = startDate.orEmpty(),
            endDate = endDate ?: LocalDateTime.now().toString()
        ).orEmpty()

        if (null != tags && tags.isNotEmpty()) {
            return activities.stream()
                .filter { k -> k.containAtLeastOneTagFromList(tags) }
                .toList()
        }
        return activities
    }
}
