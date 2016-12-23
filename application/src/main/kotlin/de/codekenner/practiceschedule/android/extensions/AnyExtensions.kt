package de.codekenner.practiceschedule.extensions

import android.util.Log

fun Any.log(message: String) {
	Log.e(this.javaClass.simpleName, message)
}

fun Any.log(throwable: Throwable) {
	Log.e(this.javaClass.simpleName, throwable.message!!, throwable)
}
