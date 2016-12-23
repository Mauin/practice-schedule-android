package de.codekenner.practiceschedule.android.util

import de.codekenner.practiceschedule.extensions.log
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import java.util.*

object RxUtils {

	fun <T, R> MAP_LISTS(transformer: Function<T, R>): Function<List<T>, List<R>> {
		return Function { input ->
			val result = ArrayList<R>(input.size)
			for (element in input) {
				try {
					result.add(transformer.apply(element))
				} catch (ignored: Exception) {
				}

			}
			result
		}
	}

	fun <T, R> listMapper(transformer: Function<T, R>): ObservableTransformer<List<T>, List<R>> {
		return ObservableTransformer { upstream -> upstream.map(MAP_LISTS(transformer)) }
	}

	fun <T> remoteLocalObservable(remote: Single<T>, storeCommand: Consumer<T>, local: Observable<T>): Observable<T> {
		return Observable.merge(
			remote
					.doOnSuccess(storeCommand)
					.toObservable()
					.doOnError { log(it.message!!) }
					.onErrorResumeNext(local),
			local
		)
	}
}
