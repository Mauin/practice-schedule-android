package de.codekenner.practiceschedule.extensions

import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

fun ZonedDateTime.format() : String {
		val formatter: DateTimeFormatter
		when {
			ZonedDateTime.now().plusDays(7).isBefore(this) -> formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT)
			ZonedDateTime.now().plusDays(1).isBefore(this) -> // TODO THIS WEEK (Mon, Tue, Wed, ...)
				formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT)
			else -> // TODO TODAY
				formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT)
		}

		return this.format(formatter)
}


fun ZonedDateTime.isBeforeOrEqual(other: ZonedDateTime) : Boolean {
	return this.isBefore(other) || this.isEqual(other)
}

fun ZonedDateTime.isAfterOrEqual(other: ZonedDateTime) : Boolean {
	return this.isAfter(other) || this.isEqual(other)
}
