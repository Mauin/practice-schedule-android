package de.codekenner.practiceschedule.data.remote.receive

import android.os.Parcelable
import android.support.annotation.Nullable
import com.google.auto.value.AutoValue
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import de.codekenner.practiceschedule.data.local.database.model.DbPeriod

@AutoValue
abstract class Period : Parcelable {
	abstract fun id(): Long
	abstract fun name(): String
	abstract fun offset(): Long
	abstract fun duration(): Long
	@Nullable @Json(name = "personal_notes") abstract fun notes(): List<Note>?

	companion object {
		@JvmStatic
		fun moshiJsonAdapter(moshi: Moshi): JsonAdapter<Period> {
			return AutoValue_Period.jsonAdapter(moshi)
		}

		fun fromDb(periods: DbPeriod): Period {
			return AutoValue_Period(periods._period_id(), periods.name(), periods.start_offset(), periods.duration(), emptyList<Note>())
		}
	}
}
