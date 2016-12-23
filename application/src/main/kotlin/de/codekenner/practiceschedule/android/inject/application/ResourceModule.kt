package de.codekenner.practiceschedule.android.inject.application

import android.content.Context
import dagger.Module
import dagger.Provides
import de.codekenner.practiceschedule.android.R
import javax.inject.Named

@Module
class ResourceModule {

	@Provides @Named("AccountType")
	fun provideAccountType(context: Context): String {
		return context.getString(R.string.account)
	}

}
