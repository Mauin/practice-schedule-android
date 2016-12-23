package de.codekenner.practiceschedule.data.local.account

import android.accounts.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import de.codekenner.practiceschedule.extensions.applicationComponent
import de.codekenner.practiceschedule.android.ui.login.LoginActivity
import javax.inject.Inject
import javax.inject.Named

class Authenticator(private val context: Context) : AbstractAccountAuthenticator(context) {

	@Inject lateinit var accountController: AccountController
	@field:[Inject Named("AccountType")] lateinit var accountType: String

	init {
		context.applicationComponent().inject(this)
	}

	@Throws(NetworkErrorException::class)
	override fun addAccount(response: AccountAuthenticatorResponse, accountType: String, authTokenType: String, requiredFeatures: Array<String>?, options: Bundle): Bundle {
		val intent = Intent(context, LoginActivity::class.java)
		val bundle = Bundle()
		bundle.putParcelable(AccountManager.KEY_INTENT, intent)
		return bundle
	}

	@Throws(NetworkErrorException::class)
	override fun getAuthToken(response: AccountAuthenticatorResponse, account: Account, authTokenType: String, options: Bundle): Bundle {
		val jwt = accountController.jwt

		if (!TextUtils.isDigitsOnly(jwt)) {
			val bundle = Bundle()
			bundle.putString(AccountManager.KEY_ACCOUNT_NAME, account.name)
			bundle.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type)
			bundle.putString(AccountManager.KEY_AUTHTOKEN, jwt)
			return bundle
		}
		return addAccount(response, accountType, authTokenType, null, options)
	}

	override fun editProperties(response: AccountAuthenticatorResponse, accountType: String) = null

	@Throws(NetworkErrorException::class)
	override fun confirmCredentials(response: AccountAuthenticatorResponse, account: Account, options: Bundle) = null

	override fun getAuthTokenLabel(authTokenType: String) = null

	@Throws(NetworkErrorException::class)
	override fun updateCredentials(response: AccountAuthenticatorResponse, account: Account, authTokenType: String, options: Bundle) = null

	@Throws(NetworkErrorException::class)
	override fun hasFeatures(response: AccountAuthenticatorResponse, account: Account, features: Array<String>) = null
}
