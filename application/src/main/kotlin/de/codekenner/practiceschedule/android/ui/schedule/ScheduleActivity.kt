package de.codekenner.practiceschedule.android.ui.schedule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import de.codekenner.practiceschedule.android.R
import de.codekenner.practiceschedule.android.util.CardItemDecoration
import de.codekenner.practiceschedule.android.util.TimelineLayoutManager
import de.codekenner.practiceschedule.android.util.TimetableLayouter
import de.codekenner.practiceschedule.data.remote.receive.Period
import de.codekenner.practiceschedule.data.remote.receive.Schedule
import de.codekenner.practiceschedule.extensions.createComponent
import de.codekenner.practiceschedule.extensions.log
import javax.inject.Inject

class ScheduleActivity : AppCompatActivity(), ScheduleContract.ScheduleScreen {

	@Inject lateinit internal var presenter: SchedulePresenter
	@Inject lateinit internal var adapter: PeriodAdapter
	val layouter = TimetableLayouter()

	companion object {

		val EXTRA_SCHEDULE = "EXTRA_SCHEDULE"

		fun createIntent(context: Activity, schedule: Schedule): Intent {
			val intent = Intent(context, ScheduleActivity::class.java)
			intent.putExtra(EXTRA_SCHEDULE, schedule)
			return intent
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_schedule)

		createComponent().inject(this)

		setupRecyclerView()
	}

	private fun setupRecyclerView() {
		val recyclerView = findViewById(android.R.id.list) as RecyclerView

		recyclerView.layoutManager = TimelineLayoutManager(layouter)
		recyclerView.adapter = adapter
		recyclerView.addItemDecoration(CardItemDecoration(resources.getDimensionPixelSize(R.dimen.sixteen), resources.getDimensionPixelSize(R.dimen.sixteen)))
	}

	override fun onStart() {
		super.onStart()
		presenter.bind(this, intent.getParcelableExtra<Schedule>(EXTRA_SCHEDULE))
	}

	override fun onStop() {
		presenter.unbind()
		super.onStop()
	}

	override fun showSchedule(schedule: Schedule) {
		title = schedule.group().name()
		layouter.setSchedule(schedule)
	}

	override fun showPeriods(periods: List<Period>) {
		log("Showing ${periods.size} periods")
		layouter.setPeriods(periods)
		adapter.showPeriods(periods)
	}
}
