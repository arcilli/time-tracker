package org.arrnaux.timetracker.store

import org.arrnaux.timetracker.model.internal.Activity
import org.springframework.data.jpa.repository.JpaRepository

interface ActivitiesRepository : JpaRepository<Activity, String> {
    fun findActivityById(id: String): Activity?

    fun save(activity: Activity): Activity

    fun findActivitiesByStartDateAfter(startDate: String): List<Activity>?

    fun findActivitiesByStartDateAfterAndEndDateBeforeOrderByStartDateAsc(
        startDate: String,
        endDate: String = "",
    ): List<Activity>?;

    fun deleteActivityById(id: String)

    /**
     * TODO: find activities by startdate, endDate and by tag
     */
}