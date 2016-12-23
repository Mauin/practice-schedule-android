package de.codekenner.practiceschedule.android.ui.navigation

import de.codekenner.practiceschedule.android.BasePresenter
import de.codekenner.practiceschedule.android.Screen

internal interface NavigationContract {
	interface NavigationScreen : Screen {
		fun launchLogin()
		fun launchHome()
	}

	interface Presenter : BasePresenter
}
