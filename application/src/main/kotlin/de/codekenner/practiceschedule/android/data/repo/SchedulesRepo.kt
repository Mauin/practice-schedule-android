package de.codekenner.practiceschedule.data.repo

import de.codekenner.practiceschedule.data.local.database.SchedulesStorage
import de.codekenner.practiceschedule.data.remote.ApiClient
import de.codekenner.practiceschedule.data.remote.receive.Schedule
import de.codekenner.practiceschedule.extensions.log
import de.codekenner.practiceschedule.android.util.RxUtils.remoteLocalObservable
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import javax.inject.Inject

class SchedulesRepo
@Inject
internal constructor(private val apiClient: ApiClient, private val schedulesStorage: SchedulesStorage) {

	fun getAllSchedules(): Observable<List<Schedule>> {
		return remoteLocalObservable(apiClient.getSchedules(), Consumer { schedulesStorage.storeSchedules(it) }, schedulesStorage.getSchedules())
	}
}
