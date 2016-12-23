package de.codekenner.practiceschedule.data.local.database.model

import android.content.ContentValues

import com.google.auto.value.AutoValue
import com.squareup.sqldelight.RowMapper

import de.codekenner.practiceschedule.GroupModel
import de.codekenner.practiceschedule.data.remote.receive.Group

@AutoValue
abstract class DbGroup : GroupModel {
	companion object {
		val FACTORY: GroupModel.Factory<DbGroup> = GroupModel.Factory<DbGroup>(::AutoValue_DbGroup)

		val SELECT_ALL_MAPPER: RowMapper<DbGroup> = FACTORY.select_allMapper()

		fun insert(group: Group): ContentValues {
			return FACTORY.marshal()
					._group_id(group.id())
					.name(group.name())
					.asContentValues()
		}
	}
}
