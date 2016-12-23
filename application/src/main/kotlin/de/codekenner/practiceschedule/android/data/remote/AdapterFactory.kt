package de.codekenner.practiceschedule.data.remote

import com.ryanharter.auto.value.moshi.MoshiAdapterFactory
import com.squareup.moshi.JsonAdapter

@MoshiAdapterFactory
abstract class AdapterFactory : JsonAdapter.Factory {
	companion object {
		fun create(): JsonAdapter.Factory {
			return AutoValueMoshi_AdapterFactory()
		}
	}
}
