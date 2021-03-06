package moe.pine.rxbinding.shared.view

import com.jakewharton.rxbinding.view.*
import android.view.*
import moe.pine.rxbinding.shared.CachedObservable
import moe.pine.rxbinding.shared.ObservableType
import rx.Observable
import rx.functions.*

/**
 * Auto generated
 * Created by CodeGenerator.kt on Apr 3, 2016 10:47:32 AM.
 */
fun ViewGroup.sharedChangeEvents(): Observable<ViewGroupHierarchyChangeEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.ViewGroupChangeEvents) {
        RxViewGroup.changeEvents(this)
    }
}