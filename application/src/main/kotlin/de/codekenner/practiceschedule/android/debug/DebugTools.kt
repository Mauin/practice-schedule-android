package de.codekenner.practiceschedule.debug

import android.content.Context
import com.facebook.stetho.Stetho

object DebugTools {

	fun initialize(context: Context) {
		Stetho.initializeWithDefaults(context)
	}
}
