package de.codekenner.practiceschedule.data.local.database.model

import com.google.auto.value.AutoValue
import com.squareup.sqldelight.RowMapper

import de.codekenner.practiceschedule.ScheduleNotesModel

@AutoValue
abstract class DbScheduleNote : ScheduleNotesModel {
	companion object {
		val FACTORY: ScheduleNotesModel.Factory<DbScheduleNote> = ScheduleNotesModel.Factory<DbScheduleNote>(::AutoValue_DbScheduleNote)

		val SELECT_FOR_SCHEDULE_MAPPER: RowMapper<DbScheduleNote> = FACTORY.select_for_scheduleMapper()
	}
}
