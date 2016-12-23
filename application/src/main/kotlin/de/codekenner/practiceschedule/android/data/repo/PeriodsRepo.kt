package de.codekenner.practiceschedule.data.repo

import de.codekenner.practiceschedule.data.local.database.PeriodsStorage
import de.codekenner.practiceschedule.data.remote.ApiClient
import de.codekenner.practiceschedule.data.remote.receive.Period
import de.codekenner.practiceschedule.extensions.log
import de.codekenner.practiceschedule.android.util.RxUtils.remoteLocalObservable
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import javax.inject.Inject

class PeriodsRepo
@Inject
internal constructor(private val apiClient: ApiClient, private val periodsStorage: PeriodsStorage) {

	fun getPeriods(id: Long): Observable<List<Period>> {
		return remoteLocalObservable(apiClient.getPeriods(id), Consumer { periods -> periodsStorage.storePeriods(id, periods) }, periodsStorage.getPeriodsForSchedule(id))
				.doOnNext { log("have periods") }
				.doOnError { log(it) }
	}
}
