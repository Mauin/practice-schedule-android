package de.codekenner.practiceschedule.data.remote.receive

import com.google.auto.value.AutoValue
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

@AutoValue
abstract class Auth {
	abstract fun jwt(): String

	companion object {
		@JvmStatic
		open fun jsonAdapter(moshi: Moshi): JsonAdapter<Auth> {
			return AutoValue_Auth.jsonAdapter(moshi)
		}
	}
}
