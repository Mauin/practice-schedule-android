package de.codekenner.practiceschedule.data.remote

import android.os.Parcel

import com.ryanharter.auto.value.parcel.TypeAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.sqldelight.ColumnAdapter

import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

import java.io.IOException

import javax.inject.Inject

class ZonedDateTimeAdapter
@Inject
constructor() : JsonAdapter<ZonedDateTime>(), TypeAdapter<ZonedDateTime>, ColumnAdapter<ZonedDateTime, String> {
	private val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME

	private fun `in`(value: String): ZonedDateTime {
		return ZonedDateTime.parse(value, formatter)
	}

	private fun out(value: ZonedDateTime): String {
		return value.format(formatter)
	}

	@Throws(IOException::class)
	override fun fromJson(reader: JsonReader): ZonedDateTime {
		return `in`(reader.nextString())
	}

	@Throws(IOException::class)
	override fun toJson(writer: JsonWriter, value: ZonedDateTime) {
		writer.value(out(value))
	}

	override fun fromParcel(parcel: Parcel): ZonedDateTime {
		return `in`(parcel.readString())
	}

	override fun toParcel(zonedDateTime: ZonedDateTime, parcel: Parcel) {
		parcel.writeString(out(zonedDateTime))
	}

	override fun decode(databaseValue: String): ZonedDateTime {
		return `in`(databaseValue)
	}

	override fun encode(value: ZonedDateTime): String {
		return out(value)
	}
}
