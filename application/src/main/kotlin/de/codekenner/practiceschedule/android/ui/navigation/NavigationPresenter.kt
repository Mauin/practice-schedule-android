package de.codekenner.practiceschedule.android.ui.navigation

import de.codekenner.practiceschedule.android.Screen
import de.codekenner.practiceschedule.data.local.account.AccountController
import javax.inject.Inject

internal class NavigationPresenter
@Inject
constructor(private val accountController: AccountController) : NavigationContract.Presenter {

	override fun bind(screen: Screen) {
		val view = screen as NavigationContract.NavigationScreen

		if (accountController.hasAccount()) {
			view.launchHome()
		} else {
			view.launchLogin()
		}
	}

	override fun unbind() {

	}
}
