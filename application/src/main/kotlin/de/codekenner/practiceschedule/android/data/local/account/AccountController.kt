package de.codekenner.practiceschedule.data.local.account

import android.accounts.Account
import android.accounts.AccountManager
import android.os.Bundle
import android.util.Log
import de.codekenner.practiceschedule.data.remote.receive.User
import javax.inject.Inject
import javax.inject.Named

class AccountController
@Inject
constructor(private val accountManager: AccountManager, private @Named("AccountType") val accountType: String) {

	fun createAccount(email: String, password: String) {
		Log.e("AccountController", "Adding account: " + email)

		val account = Account(email, accountType)
		val bundle = Bundle()
		bundle.putString(USER_EMAIL, email)

		val success = accountManager.addAccountExplicitly(account, null, bundle)
		Log.e("AccountController", "Success: " + success)

		accountManager.setPassword(account, password)
	}

	fun setUserData(user: User) {
		accountManager.setUserData(account, USER_ID, user.id().toString())
		accountManager.setUserData(account, USER_NAME, user.name())
	}

	fun setJwtForAccount(jwt: String) {
		accountManager.setAuthToken(account, JWT, jwt)
	}

	val jwt: String
		get() {
			if (!hasAccount()) {
				return ""
			}
			return accountManager.peekAuthToken(account, JWT)
		}

	val email: String
		get() {
			if (!hasAccount()) {
				throw IllegalAccessError("No valid account")
			}
			return accountManager.getUserData(account, USER_EMAIL)
		}

	val password: String
		get() {
			if (!hasAccount()) {
				throw IllegalAccessError("No valid account")
			}
			return accountManager.getPassword(account)
		}

	private val account: Account
		get() {
			if (!hasAccount()) {
				throw IllegalStateException("No existing account!")
			}

			return accountManager.getAccountsByType(accountType)[0]
		}

	fun hasAccount() = accountManager.getAccountsByType(accountType).size == 1

	companion object {
		val USER_ID = "USER_ID"
		val USER_NAME = "USER_NAME"
		val USER_EMAIL = "USER_EMAIL"
		val JWT = "JWT"
	}
}
