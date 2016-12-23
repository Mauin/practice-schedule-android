package de.codekenner.practiceschedule.android.inject.application

import android.content.Context
import android.util.Log

import com.squareup.sqlbrite.BriteDatabase
import com.squareup.sqlbrite.SqlBrite

import dagger.Module
import dagger.Provides
import de.codekenner.practiceschedule.data.local.database.DatabaseOpenHelper

import rx.schedulers.Schedulers.io

@Module
class DatabaseModule {

	@Provides
	internal fun provideDatabaseOpenHelper(context: Context) = DatabaseOpenHelper.create(context)

	@Provides
	internal fun provideSqlBrite(): SqlBrite {
		return SqlBrite.Builder()
				.logger { message -> Log.e("SqlBrite", message) }
				.build()
	}

	@Provides
	internal fun provideBriteDatabase(sqlBrite: SqlBrite, databaseOpenHelper: DatabaseOpenHelper): BriteDatabase {
		return sqlBrite.wrapDatabaseHelper(databaseOpenHelper, io())
	}
}
