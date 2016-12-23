package de.codekenner.practiceschedule.data.remote.receive

import com.google.auto.value.AutoValue
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

@AutoValue
abstract class User {
	abstract fun email(): String
	abstract fun id(): Long
	abstract fun name(): String

	companion object {
		@JvmStatic
		open fun jsonAdapter(moshi: Moshi): JsonAdapter<User> {
			return AutoValue_User.jsonAdapter(moshi)
		}
	}
}
