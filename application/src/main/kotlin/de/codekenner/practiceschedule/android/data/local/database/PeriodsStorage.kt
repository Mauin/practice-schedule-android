package de.codekenner.practiceschedule.data.local.database

import android.database.sqlite.SQLiteDatabase
import com.squareup.sqlbrite.BriteDatabase
import de.codekenner.practiceschedule.PeriodModel
import de.codekenner.practiceschedule.PeriodNotesModel
import de.codekenner.practiceschedule.PeriodScheduleRelationsModel
import de.codekenner.practiceschedule.data.local.database.model.DbPeriod
import de.codekenner.practiceschedule.data.local.database.model.DbPeriodNote
import de.codekenner.practiceschedule.data.local.database.model.DbPeriodScheduleRelations
import de.codekenner.practiceschedule.data.remote.receive.Period
import de.codekenner.practiceschedule.android.util.RxUtils.listMapper
import hu.akarnokd.rxjava.interop.RxJavaInterop
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

class PeriodsStorage
@Inject
constructor(private val database: BriteDatabase) {

	fun storePeriods(scheduleId: Long, periods: List<Period>) {
		for (period in periods) {
			storePeriod(scheduleId, period)
		}
	}

	fun storePeriod(scheduleId: Long, period: Period) {
		database.insert(PeriodScheduleRelationsModel.TABLE_NAME, DbPeriodScheduleRelations.insert(scheduleId, period.id()), SQLiteDatabase.CONFLICT_REPLACE)
		database.insert(PeriodModel.TABLE_NAME, DbPeriod.insert(period), SQLiteDatabase.CONFLICT_REPLACE)

		period.notes()?.forEach{
			database.insert(PeriodNotesModel.TABLE_NAME, DbPeriodNote.insert(period.id(), it), SQLiteDatabase.CONFLICT_REPLACE)
		}
	}

	fun getPeriodsForSchedule(id: Long): Observable<List<Period>> {
		return RxJavaInterop.toV2Observable(database.createQuery(PeriodModel.TABLE_NAME, DbPeriod.FACTORY.select_for_schedule(id).statement)
				.mapToList<DbPeriod.AllInfo> { DbPeriod.SELECT_FOR_SCHEDULE_MAPPER.map(it) })
				.compose(listMapper<DbPeriod.AllInfo, Period> (Function { Period.fromDb(it.periods()) }))
	}
}
