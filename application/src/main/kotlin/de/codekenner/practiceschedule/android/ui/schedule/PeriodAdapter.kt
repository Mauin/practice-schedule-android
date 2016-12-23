package de.codekenner.practiceschedule.android.ui.schedule

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import de.codekenner.practiceschedule.android.R
import de.codekenner.practiceschedule.android.inject.annotation.ActivityScope
import de.codekenner.practiceschedule.data.remote.receive.Period
import java.util.*
import javax.inject.Inject

@ActivityScope
internal class PeriodAdapter
@Inject
constructor() : RecyclerView.Adapter<PeriodViewHolder>() {

	private val periods = ArrayList<Period>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeriodViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.view_period, parent, false)
		return PeriodViewHolder(view)
	}

	override fun onBindViewHolder(holder: PeriodViewHolder, position: Int) {
		holder.setPeriod(periods[position])
	}

	override fun getItemCount(): Int = periods.size

	fun showPeriods(periods: List<Period>) {
		this.periods.clear()
		this.periods.addAll(periods)
		notifyDataSetChanged()
	}
}
