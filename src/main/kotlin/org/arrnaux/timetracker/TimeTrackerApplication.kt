package org.arrnaux.timetracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TimeTrackerApplication

fun main(args: Array<String>) {
    runApplication<TimeTrackerApplication>(*args)
}
