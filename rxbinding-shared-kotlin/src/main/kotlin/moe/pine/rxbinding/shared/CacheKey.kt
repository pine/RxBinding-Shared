package moe.pine.rxbinding.shared

import android.view.View

/**
 * Key for CachedObservable
 * Created by pine on 2016/04/03.
 */
data class CacheKey(val view: Any, val type: ObservableType)