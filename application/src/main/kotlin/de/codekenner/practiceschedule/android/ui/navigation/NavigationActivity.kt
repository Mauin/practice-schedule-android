package de.codekenner.practiceschedule.android.ui.navigation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import de.codekenner.practiceschedule.extensions.createComponent
import de.codekenner.practiceschedule.android.ui.home.HomeActivity
import de.codekenner.practiceschedule.android.ui.login.LoginActivity
import javax.inject.Inject

class NavigationActivity : AppCompatActivity(), NavigationContract.NavigationScreen {

	@Inject lateinit internal var presenter: NavigationPresenter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		createComponent().inject(this)
	}

	override fun onStart() {
		super.onStart()
		presenter.bind(this)
	}

	override fun onStop() {
		presenter.unbind()
		super.onStop()
	}

	override fun launchLogin() {
		startActivity(LoginActivity.createIntent(this))
		finish()
	}

	override fun launchHome() {
		startActivity(HomeActivity.createIntent(this))
		finish()
	}
}
