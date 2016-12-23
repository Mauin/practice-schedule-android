package de.codekenner.practiceschedule.android.inject.application

import android.accounts.AccountManager
import android.content.Context

import dagger.Module
import dagger.Provides
import de.codekenner.practiceschedule.android.inject.annotation.ApplicationScope

@Module
class ApplicationModule(private val application: Context) {

	@Provides
	@ApplicationScope
	internal fun provideApplicationContext() = application

	@Provides
	internal fun provideAccountManager() = AccountManager.get(application)
}
