package de.codekenner.practiceschedule.android.ui.home

import de.codekenner.practiceschedule.android.BasePresenter
import de.codekenner.practiceschedule.android.Screen
import de.codekenner.practiceschedule.data.remote.receive.Schedule

internal interface HomeContract {
	interface HomeScreen : Screen {
		fun showSchedules(schedules: List<Schedule>)
		fun showScheduleDetails(schedule: Schedule)
	}

	interface HomePresenter : BasePresenter
}
