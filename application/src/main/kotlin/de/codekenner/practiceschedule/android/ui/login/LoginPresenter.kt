package de.codekenner.practiceschedule.android.ui.login

import de.codekenner.practiceschedule.android.Screen
import de.codekenner.practiceschedule.data.repo.UserRepo
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginPresenter
@Inject
constructor(private val userRepo: UserRepo) : LoginContract.Presenter {
	private val disposables = CompositeDisposable()

	override fun bind(screen: Screen) {
		val loginScreen = screen as LoginContract.LoginScreen

		disposables.add(
				loginScreen.signUpPressed()
						.flatMapSingle { tick -> userRepo.signUp(loginScreen.getEmail(), loginScreen.getName(), loginScreen.getPassword()) }
						.subscribe { tick -> loginScreen.launchHome() }
		)

		disposables.add(
				loginScreen.loginPressed()
						.flatMapSingle { tick -> userRepo.authenticate(loginScreen.getEmail(), loginScreen.getPassword()) }
						.subscribe { tick -> loginScreen.launchHome() }
		)
	}

	override fun unbind() {
		disposables.clear()
	}
}
