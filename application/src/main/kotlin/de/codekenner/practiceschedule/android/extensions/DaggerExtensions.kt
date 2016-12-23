package de.codekenner.practiceschedule.extensions

import android.app.Activity
import de.codekenner.practiceschedule.android.Application
import de.codekenner.practiceschedule.android.inject.activity.ActivityComponent
import de.codekenner.practiceschedule.android.inject.activity.ActivityModule
import de.codekenner.practiceschedule.android.inject.activity.DaggerActivityComponent
import de.codekenner.practiceschedule.android.inject.application.ApplicationComponent
import de.codekenner.practiceschedule.android.inject.application.DaggerApplicationComponent

fun Application.createComponent(): ApplicationComponent {
	return DaggerApplicationComponent.builder()
			.applicationModule(de.codekenner.practiceschedule.android.inject.application.ApplicationModule(this))
			.build()
}

fun Activity.createComponent(): ActivityComponent {
	return DaggerActivityComponent.builder()
			.applicationComponent(this.applicationComponent())
			.activityModule(ActivityModule(this))
			.build()
}
