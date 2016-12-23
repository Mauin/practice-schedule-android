package de.codekenner.practiceschedule.android.inject.application

import android.accounts.AccountManager
import com.squareup.sqlbrite.BriteDatabase
import dagger.Component
import de.codekenner.practiceschedule.data.local.account.Authenticator
import de.codekenner.practiceschedule.data.remote.ApiClient
import de.codekenner.practiceschedule.android.inject.annotation.ApplicationScope
import javax.inject.Named

@ApplicationScope
@Component(
		modules = arrayOf(ApplicationModule::class, NetworkModule::class, DatabaseModule::class, ResourceModule::class)
)
interface ApplicationComponent {
	fun inject(authenticator: Authenticator)

	fun apiClient(): ApiClient
	fun accountManager(): AccountManager
	@Named("AccountType") fun accountType(): String
	fun briteDatabase(): BriteDatabase
}
