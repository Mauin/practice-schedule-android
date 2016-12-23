package de.codekenner.practiceschedule.data.remote

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import de.codekenner.practiceschedule.data.local.account.AccountController
import de.codekenner.practiceschedule.data.remote.receive.Auth
import de.codekenner.practiceschedule.data.remote.receive.Period
import de.codekenner.practiceschedule.data.remote.receive.Schedule
import de.codekenner.practiceschedule.data.remote.receive.User
import de.codekenner.practiceschedule.data.remote.send.AuthRequest
import de.codekenner.practiceschedule.data.remote.send.UserRequest
import de.codekenner.practiceschedule.extensions.log
import de.codekenner.practiceschedule.android.inject.annotation.ApplicationScope
import de.codekenner.practiceschedule.android.util.Notification
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

@ApplicationScope
class ApiClient
@Inject
internal constructor(private val api: PracticeScheduleApi, private val accountController: AccountController) {

	private fun <T> authSafeRequest(request: Single<T>): Single<T> {
		return request.retryWhen({ throwableFlowable ->
			throwableFlowable.flatMap({ throwable ->
				val result: Flowable<Notification>
				if (throwable is HttpException && throwable.code() == 401) {
					log("Retrying for authentication")
					result = authenticate().map { tick -> Notification.BEEP }.toFlowable()
				} else {
					result = Flowable.error(throwable)
				}

				result
			})
		})
	}

	fun createUser(email: String, name: String, password: String): Single<User> {
		return api.createUser(UserRequest.create(email, name, password))
	}

	private fun authenticate(): Single<Notification> {
		return authenticate(accountController.email, accountController.password)
				.map(Auth::jwt)
				.doOnSuccess { accountController.setJwtForAccount(it) }
				.map { tick -> Notification.BEEP }
	}

	fun authenticate(email: String, password: String): Single<Auth> = api.authenticate(AuthRequest.create(email, password))
	fun getOwnUser(): Single<User> = authSafeRequest(api.getUser())
	fun getSchedules(): Single<List<Schedule>> = authSafeRequest(api.getSchedules())
	fun getPeriods(id: Long): Single<List<Period>> = authSafeRequest(api.getSchedule(id)).map { it.periods() }
}
