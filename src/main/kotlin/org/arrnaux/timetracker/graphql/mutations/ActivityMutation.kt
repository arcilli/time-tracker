package org.arrnaux.timetracker.graphql.mutations

import com.expediagroup.graphql.spring.operations.Mutation
import org.arrnaux.timetracker.model.internal.Activity
import org.arrnaux.timetracker.model.internal.Tag
import org.arrnaux.timetracker.store.ActivitiesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import kotlin.streams.toList

@Component
class ActivityMutation : Mutation {

    @Autowired
    private lateinit var repository: ActivitiesRepository

    /***
     * Store an activity in the data store & retrieve the persisted one.
     *
     * TODO: better handling of default parameters
     */
    fun storeActivity(
        name: String?,
        description: String?,
        startDate: String?,
        endDate: String? = "",
        tags: List<String>? = emptyList()
    ): Activity {
        val activity = Activity(
            name = name.orEmpty(), description = description.orEmpty(), startDate = startDate, endDate = endDate
        )
        if (null == startDate || startDate.isBlank()) {
            activity.startDate = LocalDateTime.now().toString();
        }
        if (null != tags) {
            activity.tags = tags.stream()
                .filter { k -> (null != k) }
                .map { k -> Tag(name = k) }
                .toList() as MutableList<Tag>
        };
        return repository.save(activity)
    }

    /**
     * Updates the activity with the given id. Only fields that are not null in the @param activity are updated.
     */
    fun updateActivity(id: String, activity: Activity): Activity? {
        val storedActivity = repository.findActivityById(id);
//        if (null != storedActivity) {
//            return copyNonNullMembers(storedActivity);
//        }
        return null;
    }

    fun doSomething(name: String?): String = "simple output";
}
