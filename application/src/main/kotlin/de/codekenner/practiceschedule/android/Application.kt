package de.codekenner.practiceschedule.android

import com.jakewharton.threetenabp.AndroidThreeTen
import de.codekenner.practiceschedule.debug.AnalyticsTools
import de.codekenner.practiceschedule.debug.DebugTools
import de.codekenner.practiceschedule.extensions.createComponent
import de.codekenner.practiceschedule.android.inject.application.ApplicationComponent

class Application : android.app.Application() {

	private lateinit var component: ApplicationComponent

	override fun onCreate() {
		super.onCreate()

		AndroidThreeTen.init(this)
		component = createComponent()

		DebugTools.initialize(this)
		AnalyticsTools.initialize(this)
	}

	fun component(): ApplicationComponent = component
}
