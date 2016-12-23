package de.codekenner.practiceschedule.data.remote.receive

import android.os.Parcelable
import android.support.annotation.Nullable
import com.google.auto.value.AutoValue
import com.ryanharter.auto.value.parcel.ParcelAdapter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import de.codekenner.practiceschedule.data.local.database.model.DbGroup
import de.codekenner.practiceschedule.data.local.database.model.DbSchedule
import de.codekenner.practiceschedule.data.remote.ZonedDateTimeAdapter
import org.threeten.bp.ZonedDateTime

@AutoValue
abstract class Schedule : Parcelable {
	abstract fun id(): Long
	@ParcelAdapter(ZonedDateTimeAdapter::class) @Json(name = "start_date") abstract fun startTime(): ZonedDateTime
	abstract fun duration(): Long
	abstract fun group(): Group
	@Nullable abstract fun periods(): List<Period>?

	companion object {
		@JvmStatic
		open fun jsonAdapter(moshi: Moshi): JsonAdapter<Schedule> {
			return AutoValue_Schedule.jsonAdapter(moshi)
		}

		fun fromDb(schedule: DbSchedule, group: DbGroup): Schedule {
			return AutoValue_Schedule(schedule._schedule_id(), schedule.start_time(), schedule.duration(), Group.fromDb(group), emptyList<Period>())
		}
	}
}
