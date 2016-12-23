package de.codekenner.practiceschedule.android.ui.schedule

import de.codekenner.practiceschedule.android.BasePresenter
import de.codekenner.practiceschedule.android.Screen
import de.codekenner.practiceschedule.data.remote.receive.Period
import de.codekenner.practiceschedule.data.remote.receive.Schedule

internal interface ScheduleContract {
	interface ScheduleScreen : Screen {
		fun showSchedule(schedule: Schedule)
		fun showPeriods(periods: List<Period>)
	}

	interface SchedulePresenter : BasePresenter
}
