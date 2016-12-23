package de.codekenner.practiceschedule.data.local.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import de.codekenner.practiceschedule.*

class DatabaseOpenHelper private constructor(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

	companion object {
		private val VERSION = 1
		private val NAME = "PracticeScheduleDB"

		fun create(context: Context): DatabaseOpenHelper {
			return DatabaseOpenHelper(context, NAME, null, VERSION)
		}
	}

	override fun onCreate(db: SQLiteDatabase) {
		db.execSQL(GroupModel.CREATE_TABLE)
		db.execSQL(PeriodNotesModel.CREATE_TABLE)
		db.execSQL(PeriodModel.CREATE_TABLE)
		db.execSQL(ScheduleModel.CREATE_TABLE)
		db.execSQL(ScheduleNotesModel.CREATE_TABLE)
		db.execSQL(PeriodScheduleRelationsModel.CREATE_TABLE)
		db.execSQL(ScheduleGroupRelationsModel.CREATE_TABLE)
	}

	override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
		Log.e("DatabaseOpenHelper", "onUpgrade: $oldVersion to $newVersion")
	}

}
