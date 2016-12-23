package de.codekenner.practiceschedule.data.local.database.model

import android.content.ContentValues

import com.google.auto.value.AutoValue
import com.squareup.sqldelight.RowMapper

import de.codekenner.practiceschedule.ScheduleModel
import de.codekenner.practiceschedule.data.remote.ZonedDateTimeAdapter
import de.codekenner.practiceschedule.data.remote.receive.Schedule

@AutoValue
abstract class DbSchedule : ScheduleModel {

	@AutoValue
	abstract class AllInfo : ScheduleModel.Select_allModel<DbSchedule, DbScheduleGroupRelations, DbGroup>

	companion object {
		val FACTORY: ScheduleModel.Factory<DbSchedule> = ScheduleModel.Factory<DbSchedule>(::AutoValue_DbSchedule, ZonedDateTimeAdapter())

		val SELECT_ALL_INFO: RowMapper<AllInfo> = FACTORY.select_allMapper<DbScheduleGroupRelations, DbGroup, AllInfo>(ScheduleModel.Select_allCreator<DbSchedule, DbScheduleGroupRelations, DbGroup, AllInfo> { schedules, schedule_group_relation, groups -> AutoValue_DbSchedule_AllInfo(schedules, schedule_group_relation, groups) }, DbScheduleGroupRelations.FACTORY, DbGroup.FACTORY)

		fun insert(schedule: Schedule): ContentValues {
			return FACTORY.marshal()
					._schedule_id(schedule.id())
					.start_time(schedule.startTime())
					.duration(schedule.duration())
					.asContentValues()
		}
	}
}
