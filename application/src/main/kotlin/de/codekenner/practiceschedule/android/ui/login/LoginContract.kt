package de.codekenner.practiceschedule.android.ui.login

import de.codekenner.practiceschedule.android.BasePresenter
import de.codekenner.practiceschedule.android.Screen
import de.codekenner.practiceschedule.android.util.Notification
import io.reactivex.Observable

internal interface LoginContract {

	interface LoginScreen : Screen {
		fun loginPressed(): Observable<Notification>
		fun signUpPressed(): Observable<Notification>
		fun getName(): String
		fun getEmail(): String
		fun getPassword(): String

		fun launchHome()
	}

	interface Presenter : BasePresenter
}
