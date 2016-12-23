package de.codekenner.practiceschedule.debug

import android.content.Context
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

object AnalyticsTools {

	fun initialize(context: Context) {
		Fabric.with(context, Crashlytics())
	}
}
