package moe.pine.rxbinding.shared

import rx.Observable
import java.util.*

/**
 * Cache for Observable
 * Created by matsushita on 2016/01/20.
 */
object CachedObservable {
    private val cachedObservables by lazy { WeakHashMap<CacheKey, Observable<*>>() }

    fun <T> getOrCreate(
            view: Any,
            type: ObservableType,
            defaultValue: () -> Observable<T>
    ): Observable<T> {
        val key = CacheKey(view, type)

        @Suppress("UNCHECKED_CAST")
        return synchronized(this.cachedObservables) {
            this.cachedObservables.getOrPut(key) {
                defaultValue()
                        .doOnUnsubscribe { this.cachedObservables.remove(key) }
                        .share()
            }
        } as Observable<T>
    }

    fun unsafeClear() {
        synchronized(this.cachedObservables) {
            this.cachedObservables.clear()
        }
    }
}