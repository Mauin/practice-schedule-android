package de.codekenner.practiceschedule.data.remote.send

import com.google.auto.value.AutoValue
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

@AutoValue
abstract class AuthRequest {
	@Json(name = "auth") internal abstract fun auth(): Auth

	companion object {

		fun create(email: String, password: String): AuthRequest {
			return AutoValue_AuthRequest(Auth.create(email, password))
		}

		@JvmStatic
		open fun jsonAdapter(moshi: Moshi): JsonAdapter<AuthRequest> {
			return AutoValue_AuthRequest.jsonAdapter(moshi)
		}
	}
}
