package de.codekenner.practiceschedule.data.remote.receive

import com.google.auto.value.AutoValue
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

@AutoValue
abstract class Note {
	abstract fun id(): Long
	abstract fun description(): String

	companion object {
		@JvmStatic
		fun moshiJsonAdapter(moshi: Moshi): JsonAdapter<Note> {
			return AutoValue_Note.jsonAdapter(moshi)
		}
	}

}
