package de.codekenner.practiceschedule.android.ui.schedule

import android.support.v7.widget.RecyclerView
import android.view.View
import de.codekenner.practiceschedule.data.remote.receive.Period
import kotlinx.android.synthetic.main.view_period.view.*

internal class PeriodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

	fun setPeriod(period: Period) {
		itemView.period_name.text = period.name()
		itemView.period_start.text = period.offset().toString()
		itemView.period_duration.text = period.duration().toString()
	}
}
