package de.codekenner.practiceschedule.android.ui.schedule

import android.util.Log
import de.codekenner.practiceschedule.android.Screen
import de.codekenner.practiceschedule.data.remote.receive.Schedule
import de.codekenner.practiceschedule.data.repo.PeriodsRepo
import de.codekenner.practiceschedule.android.inject.annotation.ActivityScope
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@ActivityScope
class SchedulePresenter
@Inject
constructor(private val periodsRepo: PeriodsRepo) : ScheduleContract.SchedulePresenter {

	private val disposables = CompositeDisposable()

	override fun bind(screen: Screen) {

	}

	internal fun bind(screen: ScheduleContract.ScheduleScreen, schedule: Schedule) {
		screen.showSchedule(schedule)

		disposables.add(
				periodsRepo.getPeriods(schedule.id())
						.observeOn(mainThread())
						.subscribe(
								{ screen.showPeriods(it) },
								{ throwable -> Log.e("API", throwable.message, throwable) }
						)
		)
	}

	override fun unbind() {
		disposables.clear()
	}
}
