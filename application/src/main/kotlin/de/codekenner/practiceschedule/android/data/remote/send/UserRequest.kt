package de.codekenner.practiceschedule.data.remote.send

import com.google.auto.value.AutoValue
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

@AutoValue
abstract class UserRequest {
	@Json(name = "user") internal abstract fun user(): User

	companion object {

		fun create(email: String, name: String, password: String): UserRequest {
			return AutoValue_UserRequest(User.create(email, name, password))
		}

		@JvmStatic
		open fun jsonAdapter(moshi: Moshi): JsonAdapter<UserRequest> {
			return AutoValue_UserRequest.jsonAdapter(moshi)
		}
	}
}
