package de.codekenner.practiceschedule.android.inject.activity

import dagger.Component
import de.codekenner.practiceschedule.android.inject.annotation.ActivityScope
import de.codekenner.practiceschedule.android.inject.application.ApplicationComponent
import de.codekenner.practiceschedule.android.ui.home.HomeActivity
import de.codekenner.practiceschedule.android.ui.login.LoginActivity
import de.codekenner.practiceschedule.android.ui.navigation.NavigationActivity
import de.codekenner.practiceschedule.android.ui.schedule.ScheduleActivity

@ActivityScope
@Component(
		modules = arrayOf(ActivityModule::class),
		dependencies = arrayOf(ApplicationComponent::class)
)
interface ActivityComponent {

	fun inject(navigationActivity: NavigationActivity)
	fun inject(loginActivity: LoginActivity)
	fun inject(homeActivity: HomeActivity)
	fun inject(scheduleActivity: ScheduleActivity)

}
