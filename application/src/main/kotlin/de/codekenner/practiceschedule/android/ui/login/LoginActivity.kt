package de.codekenner.practiceschedule.android.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import de.codekenner.practiceschedule.android.R
import de.codekenner.practiceschedule.android.ui.home.HomeActivity
import de.codekenner.practiceschedule.android.util.Notification
import de.codekenner.practiceschedule.android.util.Notification.BEEP
import de.codekenner.practiceschedule.extensions.createComponent
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginContract.LoginScreen {

	@Inject lateinit internal var presenter: LoginPresenter

	private val loginButton = PublishSubject.create<Notification>()
	private val signUpButton = PublishSubject.create<Notification>()

	companion object {

		fun createIntent(context: Context): Intent {
			return Intent(context, LoginActivity::class.java)
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_login)

		createComponent().inject(this)

		login_login.setOnClickListener { v -> loginButton.onNext(BEEP) }
		login_signup.setOnClickListener { v -> signUpButton.onNext(BEEP) }
	}

	override fun onStart() {
		super.onStart()
		presenter.bind(this)
	}

	override fun onStop() {
		presenter.unbind()
		super.onStop()
	}

	override fun loginPressed(): Observable<Notification> = loginButton

	override fun signUpPressed(): Observable<Notification> = signUpButton

	override fun getName() = login_name.text.toString()

	override fun getEmail() = login_email.text.toString()

	override fun getPassword() = login_password.text.toString()

	override fun launchHome() {
		startActivity(HomeActivity.createIntent(this))
		finish()
	}
}
