package de.codekenner.practiceschedule.data.remote.send

import com.google.auto.value.AutoValue
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

@AutoValue
abstract class User {
	@Json(name = "email") internal abstract fun email(): String
	@Json(name = "name") internal abstract fun name(): String
	@Json(name = "password") internal abstract fun password(): String

	companion object {

		internal fun create(email: String, name: String, password: String): User {
			return AutoValue_User(email, name, password)
		}

		@JvmStatic
		open fun jsonAdapter(moshi: Moshi): JsonAdapter<User> {
			return AutoValue_User.jsonAdapter(moshi)
		}
	}
}
