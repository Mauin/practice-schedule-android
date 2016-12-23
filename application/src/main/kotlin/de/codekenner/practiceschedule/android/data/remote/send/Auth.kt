package de.codekenner.practiceschedule.data.remote.send

import com.google.auto.value.AutoValue
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

@AutoValue
abstract class Auth {
	@Json(name = "email") internal abstract fun email(): String
	@Json(name = "password") internal abstract fun password(): String

	companion object {

		internal fun create(email: String, password: String): Auth {
			return AutoValue_Auth(email, password)
		}

		@JvmStatic
		open fun jsonAdapter(moshi: Moshi): JsonAdapter<Auth> {
			return AutoValue_Auth.jsonAdapter(moshi)
		}
	}
}
