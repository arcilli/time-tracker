package org.arrnaux.timetracker.mutations

import com.expediagroup.graphql.annotations.GraphQLDescription
import com.expediagroup.graphql.spring.operations.Mutation
import org.arrnaux.timetracker.model.internal.Activity
import org.springframework.stereotype.Component
import org.arrnaux.timetracker.model.internal.Tag

@GraphQLDescription("adds a processActivity to the store")
@Component
class ProcessActivity : Mutation {
    /***
     * Store an activity in the data store & retrieve the persisted one.
     */
    fun storeActivity(name: String?, description: String?): Activity {
        return Activity()
    }

    fun updateActivity(activity: Activity): Activity {


        return activity
    }

    fun doSomething(name: String?): String = "ete pl";
}