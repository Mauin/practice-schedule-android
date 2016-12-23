package de.codekenner.practiceschedule.data.local.database.model

import android.content.ContentValues

import com.google.auto.value.AutoValue
import com.squareup.sqldelight.RowMapper

import de.codekenner.practiceschedule.PeriodModel
import de.codekenner.practiceschedule.data.remote.receive.Period

@AutoValue
abstract class DbPeriod : PeriodModel {

	@AutoValue
	abstract class AllInfo : PeriodModel.Select_for_scheduleModel<DbPeriod, DbPeriodScheduleRelations>

	companion object {
		val FACTORY: PeriodModel.Factory<DbPeriod> = PeriodModel.Factory<DbPeriod>(::AutoValue_DbPeriod)

		val SELECT_FOR_SCHEDULE_MAPPER: RowMapper<AllInfo> = FACTORY.select_for_scheduleMapper<DbPeriodScheduleRelations, AllInfo>(PeriodModel.Select_for_scheduleCreator<DbPeriod, DbPeriodScheduleRelations, AllInfo> { periods, period_schedule_relation -> AutoValue_DbPeriod_AllInfo(periods, period_schedule_relation) }, DbPeriodScheduleRelations.FACTORY)

		fun insert(period: Period): ContentValues {
			return FACTORY.marshal()
					._period_id(period.id())
					.name(period.name())
					.start_offset(period.offset())
					.duration(period.duration())
					.asContentValues()
		}
	}
}
