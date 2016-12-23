package de.codekenner.practiceschedule.android.ui.home

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import de.codekenner.practiceschedule.android.R
import de.codekenner.practiceschedule.data.remote.receive.Schedule
import de.codekenner.practiceschedule.extensions.format
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.view_schedule.view.*

internal class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

	private val onItemClick = PublishSubject.create<Schedule>()
	private var schedule: Schedule? = null

	init {
		itemView.setOnClickListener { v -> onItemClick.onNext(schedule) }
	}

	fun setSchedule(schedule: Schedule) {
		this.schedule = schedule

		itemView.schedule_ribbon.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.accent))
		itemView.schedule_group.text = schedule.group().name()
		itemView.schedule_time.text = schedule.startTime().format()
	}

	fun onItemClick(): Observable<Schedule> = onItemClick
}
