package de.codekenner.practiceschedule.data.local.database.model

import android.content.ContentValues

import com.google.auto.value.AutoValue

import de.codekenner.practiceschedule.PeriodScheduleRelationsModel

@AutoValue
abstract class DbPeriodScheduleRelations : PeriodScheduleRelationsModel {
	companion object {
		val FACTORY: PeriodScheduleRelationsModel.Factory<DbPeriodScheduleRelations> = PeriodScheduleRelationsModel.Factory<DbPeriodScheduleRelations>(::AutoValue_DbPeriodScheduleRelations)

		fun insert(scheduleId: Long, periodId: Long): ContentValues {
			return FACTORY.marshal()
					._period_id(periodId)
					._schedule_id(scheduleId)
					.asContentValues()
		}
	}
}
