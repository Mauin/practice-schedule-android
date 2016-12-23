package de.codekenner.practiceschedule.android.ui.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import de.codekenner.practiceschedule.android.R
import de.codekenner.practiceschedule.android.inject.annotation.ActivityScope
import de.codekenner.practiceschedule.data.remote.receive.Schedule
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.*
import javax.inject.Inject

@ActivityScope
internal class ScheduleAdapter
@Inject
constructor() : RecyclerView.Adapter<ScheduleViewHolder>() {

	private val schedules = ArrayList<Schedule>()
	private val onScheduleClicked = PublishSubject.create<Schedule>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.view_schedule, parent, false)
		val scheduleViewHolder = ScheduleViewHolder(view)
		scheduleViewHolder.onItemClick().subscribe { onScheduleClicked.onNext(it) }
		return scheduleViewHolder
	}

	override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
		holder.setSchedule(schedules[position])
	}

	override fun getItemCount(): Int = schedules.size

	fun showSchedules(schedules: List<Schedule>) {
		this.schedules.clear()
		this.schedules.addAll(schedules)
		notifyDataSetChanged()
	}

	fun onScheduleClicked(): Observable<Schedule> = onScheduleClicked
}
