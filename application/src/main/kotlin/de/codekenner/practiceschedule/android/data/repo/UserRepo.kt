package de.codekenner.practiceschedule.data.repo

import de.codekenner.practiceschedule.data.local.account.AccountController
import de.codekenner.practiceschedule.data.remote.ApiClient
import de.codekenner.practiceschedule.data.remote.receive.Auth
import de.codekenner.practiceschedule.android.util.Notification
import de.codekenner.practiceschedule.android.util.Notification.BEEP
import io.reactivex.Single
import javax.inject.Inject

class UserRepo
@Inject
constructor(private val apiClient: ApiClient, private val accountController: AccountController) {

	fun signUp(email: String, name: String, password: String): Single<Notification> {
		return apiClient.createUser(email, name, password)
				.doOnSuccess { accountController.createAccount(it.email(), password) }
				.doOnSuccess { accountController.setUserData(it) }
				.flatMap { apiClient.authenticate(email, password) }
				.map(Auth::jwt)
				.doOnSuccess { accountController.setJwtForAccount(it) }
				.map { BEEP }
	}

	fun authenticate(email: String, password: String): Single<Notification> {
		return apiClient.authenticate(email, password)
				.map(Auth::jwt)
				.doOnSuccess { accountController.createAccount(email, password) }
				.doOnSuccess { accountController.setJwtForAccount(it) }
				.flatMap { apiClient.getOwnUser() }
				.doOnSuccess { accountController.setUserData(it) }
				.map { BEEP }
	}
}
