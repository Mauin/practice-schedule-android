package de.codekenner.practiceschedule.data.local.database.model

import android.content.ContentValues

import com.google.auto.value.AutoValue
import com.squareup.sqldelight.RowMapper

import de.codekenner.practiceschedule.PeriodNotesModel
import de.codekenner.practiceschedule.data.remote.receive.Note

@AutoValue
abstract class DbPeriodNote : PeriodNotesModel {
	companion object {
		val FACTORY: PeriodNotesModel.Factory<DbPeriodNote> = PeriodNotesModel.Factory<DbPeriodNote>(::AutoValue_DbPeriodNote)

		val SELECT_FOR_PERIOD_MAPPER: RowMapper<DbPeriodNote> = FACTORY.select_for_periodMapper()

		fun insert(periodId: Long, note: Note): ContentValues {
			return FACTORY.marshal()
					._id(note.id())
					._period_id(periodId)
					.note(note.description())
					.asContentValues()
		}
	}
}
