package de.codekenner.practiceschedule.data.local.database

import android.database.sqlite.SQLiteDatabase
import com.squareup.sqlbrite.BriteDatabase
import de.codekenner.practiceschedule.GroupModel
import de.codekenner.practiceschedule.ScheduleGroupRelationsModel
import de.codekenner.practiceschedule.ScheduleModel
import de.codekenner.practiceschedule.data.local.database.model.DbGroup
import de.codekenner.practiceschedule.data.local.database.model.DbSchedule
import de.codekenner.practiceschedule.data.local.database.model.DbScheduleGroupRelations
import de.codekenner.practiceschedule.data.remote.receive.Schedule
import de.codekenner.practiceschedule.android.util.RxUtils.listMapper
import hu.akarnokd.rxjava.interop.RxJavaInterop
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

class SchedulesStorage
@Inject
constructor(private val database: BriteDatabase) {

	fun getSchedules(): Observable<List<Schedule>> {
		return RxJavaInterop.toV2Observable(database.createQuery(ScheduleModel.TABLE_NAME, ScheduleModel.SELECT_ALL)
				.mapToList{ DbSchedule.SELECT_ALL_INFO.map(it) })
				.compose(listMapper<DbSchedule.AllInfo, Schedule>(Function{ Schedule.fromDb(it.schedules(), it.groups()) }))
	}

	fun storeSchedules(schedules: List<Schedule>) {
		for (schedule in schedules) {
			storeBaseSchedule(schedule)
		}
	}

	fun storeBaseSchedule(schedule: Schedule) {
		database.insert(ScheduleModel.TABLE_NAME, DbSchedule.insert(schedule), SQLiteDatabase.CONFLICT_REPLACE)
		database.insert(GroupModel.TABLE_NAME, DbGroup.insert(schedule.group()), SQLiteDatabase.CONFLICT_REPLACE)
		database.insert(ScheduleGroupRelationsModel.TABLE_NAME, DbScheduleGroupRelations.insert(schedule.id(), schedule.group().id()), SQLiteDatabase.CONFLICT_REPLACE)
	}
}
