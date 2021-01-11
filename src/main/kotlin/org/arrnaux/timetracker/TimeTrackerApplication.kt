package org.arrnaux.timetracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.arrnaux.timetracker.model.internal.Tag

@SpringBootApplication
class TimeTrackerApplication

fun main(args: Array<String>) {
    val tag: Tag = Tag(name = "gigel", id="safafsafasfas")
    print("fdasfdasfsda")
    print(Tag())
    runApplication<TimeTrackerApplication>(*args)
}
