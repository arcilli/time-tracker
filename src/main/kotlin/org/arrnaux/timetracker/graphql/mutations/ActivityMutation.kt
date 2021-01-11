package org.arrnaux.timetracker.graphql.mutations

import com.expediagroup.graphql.annotations.GraphQLDescription
import com.expediagroup.graphql.spring.operations.Mutation
import org.arrnaux.timetracker.model.internal.Activity
import org.arrnaux.timetracker.store.ActivitiesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@GraphQLDescription("adds a processActivity to the store")
@Component
class ActivityMutation : Mutation {

    @Autowired
    private lateinit var repository: ActivitiesRepository

    /***
     * Store an activity in the data store & retrieve the persisted one.
     */
    fun storeActivity(name: String?, description: String?): Activity {
        val activity: Activity = Activity()
        activity.name = name.orEmpty();
        activity.description = description.orEmpty()
        return repository.save(activity)
    }

    fun updateActivity(activity: Activity): Activity {


        return activity
    }

    fun doSomething(name: String?): String = "ete pl";
}