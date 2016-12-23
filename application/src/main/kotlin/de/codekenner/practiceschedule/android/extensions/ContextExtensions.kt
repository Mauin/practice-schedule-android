package de.codekenner.practiceschedule.extensions

import android.content.Context
import de.codekenner.practiceschedule.android.Application

fun Context.applicationComponent() = (applicationContext as Application).component()
