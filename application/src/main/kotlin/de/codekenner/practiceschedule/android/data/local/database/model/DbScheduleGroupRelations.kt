package de.codekenner.practiceschedule.data.local.database.model

import android.content.ContentValues

import com.google.auto.value.AutoValue

import de.codekenner.practiceschedule.ScheduleGroupRelationsModel

@AutoValue
abstract class DbScheduleGroupRelations : ScheduleGroupRelationsModel {
	companion object {
		val FACTORY: ScheduleGroupRelationsModel.Factory<DbScheduleGroupRelations> = ScheduleGroupRelationsModel.Factory<DbScheduleGroupRelations>(::AutoValue_DbScheduleGroupRelations)

		fun insert(scheduleId: Long, groupId: Long): ContentValues {
			return FACTORY.marshal()
					._group_id(groupId)
					._schedule_id(scheduleId)
					.asContentValues()
		}
	}
}
