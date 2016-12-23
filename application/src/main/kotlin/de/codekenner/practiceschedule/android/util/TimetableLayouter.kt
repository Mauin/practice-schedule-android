package de.codekenner.practiceschedule.android.util

import android.view.View
import android.view.ViewParent
import de.codekenner.practiceschedule.data.remote.receive.Period
import de.codekenner.practiceschedule.data.remote.receive.Schedule
import de.codekenner.practiceschedule.extensions.log
import org.threeten.bp.ZonedDateTime

class TimetableLayouter {
	companion object {
		const val MINUTE_HEIGHT = 20
	}

	private var schedule: Schedule? = null
	private var periods: List<Period>? = null

	fun setSchedule(schedule: Schedule) {
		this.schedule = schedule
	}

	fun setPeriods(periods: List<Period>) {
		this.periods = periods
	}

	fun getHeight(position: Int) : Int {
		if (schedule == null || periods == null) {
			return 0
		}

		return (periods!![position].duration() * MINUTE_HEIGHT).toInt()
	}

	fun getWidth(position: Int, parent: ViewParent) : Int {
		val parallelPeriods = getParallelPeriods(position)

		return (parent as View).width / parallelPeriods
	}

	fun getTop(position: Int) : Int {
		val period = periods!![position]

		log("Offset: #${period.name()} Top: ${(period.offset() * MINUTE_HEIGHT)}")
		return (period.offset() * MINUTE_HEIGHT).toInt()
	}

	fun getLeft(position: Int, parent: ViewParent) : Int {
		val period = periods!![position]

		log("Offset: #${period.name()} Left: ${getWidth(position, parent) * positionInParallel(position)}")
		return getWidth(position, parent) * positionInParallel(position)
	}

	private fun getParallelPeriods(position: Int) : Int {
		val period = periods!![position]
		var count = 1

		var current = position - 1
		while(current > 0 && overlap(schedule!!.startTime(), period, periods!![current])) {
			count++
			current--
		}

		current = position + 1
		while(current < periods!!.size - 1 && overlap(schedule!!.startTime(), period, periods!![current])) {
			count++
			current++
		}

		log("Parallel at $position: $count")
		return count
	}

	private fun positionInParallel(position: Int) : Int {
		val period = periods!![position]
		var currentPosition = 0

		var current = position - 1
		while(current > 0 && overlap(schedule!!.startTime(), periods!![current], period)) {
			currentPosition++
			current--
		}

		return currentPosition
	}

	private fun overlap(scheduleStart: ZonedDateTime, first: Period, second: Period) : Boolean {
		val firstStart = scheduleStart.plusMinutes(first.offset())
		val firstEnd = scheduleStart.plusMinutes(first.offset()).plusMinutes(first.duration())
		val secondStart = scheduleStart.plusMinutes(second.offset())
		val secondEnd = scheduleStart.plusMinutes(second.offset()).plusMinutes(second.duration())

		return ((firstStart.isBefore(secondEnd)) && secondStart.isAfter(firstEnd))
		|| ((secondStart.isBefore(firstEnd)) && firstStart.isAfter(secondEnd))
		|| (firstStart.isEqual(secondStart) && firstEnd.isEqual(secondEnd))
	}

}


