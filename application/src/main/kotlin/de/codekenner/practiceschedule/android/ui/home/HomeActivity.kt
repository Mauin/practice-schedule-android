package de.codekenner.practiceschedule.android.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL
import de.codekenner.practiceschedule.android.R
import de.codekenner.practiceschedule.android.ui.schedule.ScheduleActivity
import de.codekenner.practiceschedule.android.util.CardItemDecoration
import de.codekenner.practiceschedule.data.remote.receive.Schedule
import de.codekenner.practiceschedule.extensions.createComponent
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeContract.HomeScreen {

	@Inject lateinit internal var presenter: SchedulePresenter
	@Inject lateinit internal var adapter: ScheduleAdapter

	companion object {
		fun createIntent(context: Context): Intent {
			return Intent(context, HomeActivity::class.java)
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_home)

		createComponent().inject(this)

		setupRecyclerView()
	}

	private fun setupRecyclerView() {
		val recyclerView = findViewById(android.R.id.list) as RecyclerView
		recyclerView.layoutManager = LinearLayoutManager(this, VERTICAL, false)
		recyclerView.adapter = adapter
		recyclerView.addItemDecoration(CardItemDecoration(resources.getDimensionPixelSize(R.dimen.sixteen), resources.getDimensionPixelSize(R.dimen.sixteen)))
	}

	override fun onStart() {
		super.onStart()
		presenter.bind(this, adapter)
	}

	override fun onStop() {
		presenter.unbind()
		super.onStop()
	}

	override fun showSchedules(schedules: List<Schedule>) {
		adapter.showSchedules(schedules)
	}

	override fun showScheduleDetails(schedule: Schedule) {
		startActivity(ScheduleActivity.createIntent(this, schedule))
	}
}
