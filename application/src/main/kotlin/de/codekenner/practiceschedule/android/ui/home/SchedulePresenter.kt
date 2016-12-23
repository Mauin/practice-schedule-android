package de.codekenner.practiceschedule.android.ui.home

import android.util.Log
import de.codekenner.practiceschedule.android.Screen
import de.codekenner.practiceschedule.data.repo.SchedulesRepo
import de.codekenner.practiceschedule.android.inject.annotation.ActivityScope
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@ActivityScope
internal class SchedulePresenter
@Inject
constructor(private val schedulesRepo: SchedulesRepo) : HomeContract.HomePresenter {
	private val disposables = CompositeDisposable()

	override fun bind(screen: Screen) {

	}

	fun bind(screen: HomeContract.HomeScreen, adapter: ScheduleAdapter) {
		disposables.add(
				schedulesRepo.getAllSchedules()
						.observeOn(mainThread())
						.subscribe(
								{ screen.showSchedules(it) },
								{ throwable -> Log.e("API", throwable.message, throwable) }
						)
		)

		disposables.add(
				adapter.onScheduleClicked()
						.subscribe { screen.showScheduleDetails(it) }
		)
	}

	override fun unbind() {
		disposables.clear()
	}
}
