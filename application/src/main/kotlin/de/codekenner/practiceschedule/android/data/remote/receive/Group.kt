package de.codekenner.practiceschedule.data.remote.receive

import android.os.Parcelable

import com.google.auto.value.AutoValue
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

import de.codekenner.practiceschedule.data.local.database.model.DbGroup

@AutoValue
abstract class Group : Parcelable {
	abstract fun id(): Long
	abstract fun name(): String

	companion object {
		@JvmStatic
		fun moshiJsonAdapter(moshi: Moshi): JsonAdapter<Group> {
			return AutoValue_Group.jsonAdapter(moshi)
		}

		fun fromDb(group: DbGroup): Group {
			return AutoValue_Group(group._group_id(), group.name())
		}
	}
}
