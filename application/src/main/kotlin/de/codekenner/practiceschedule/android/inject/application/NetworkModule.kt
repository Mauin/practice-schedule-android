package de.codekenner.practiceschedule.android.inject.application

import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import de.codekenner.practiceschedule.data.local.account.AccountController
import de.codekenner.practiceschedule.data.remote.AdapterFactory
import de.codekenner.practiceschedule.data.remote.PracticeScheduleApi
import de.codekenner.practiceschedule.data.remote.ZonedDateTimeAdapter
import de.codekenner.practiceschedule.android.inject.annotation.ApplicationScope
import io.reactivex.schedulers.Schedulers.io
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.threeten.bp.ZonedDateTime
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module
class NetworkModule {

	@Provides
	@ApplicationScope
	internal fun provideMoshi(zonedDateTimeAdapter: ZonedDateTimeAdapter): Moshi {
		return Moshi.Builder()
				.add(AdapterFactory.create())
				.add(ZonedDateTime::class.java, zonedDateTimeAdapter)
				.build()
	}

	@Provides
	@Named("LoggingInterceptor")
	internal fun provideLoggingInterceptor(): Interceptor {
		val httpLoggingInterceptor = HttpLoggingInterceptor()
		httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
		return httpLoggingInterceptor
	}

	@Provides
	@ApplicationScope
	@Named("AuthInterceptor")
	internal fun provideAuthInterceptor(accountController: AccountController): Interceptor {
		return Interceptor {
			chain ->
			val jwt = accountController.jwt
			if (jwt.isEmpty()) {
				Log.e("OkHttp", "Unauthenticated Request")
				chain.proceed(chain.request())
			} else {
				val authRequest = chain.request().newBuilder()
						.addHeader("Authorization", "Bearer: " + jwt)
						.build()

				chain.proceed(authRequest)
			}
		}
	}

	@ApplicationScope
	@Provides
	internal fun provideOkHttpClient(@Named("LoggingInterceptor") loggingInterceptor: Interceptor,
									 @Named("AuthInterceptor") authInterceptor: Interceptor): OkHttpClient {
		return OkHttpClient.Builder()
				.addInterceptor(authInterceptor)
				.addInterceptor(loggingInterceptor)
				.build()
	}

	@ApplicationScope
	@Provides
	internal fun provideApi(client: OkHttpClient, moshi: Moshi): PracticeScheduleApi {
		val retrofit = Retrofit.Builder()
				.baseUrl("https://practice-schedule.herokuapp.com")
				.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(io()))
				.addConverterFactory(MoshiConverterFactory.create(moshi))
				.client(client)
				.build()
		return retrofit.create(PracticeScheduleApi::class.java)
	}

}
