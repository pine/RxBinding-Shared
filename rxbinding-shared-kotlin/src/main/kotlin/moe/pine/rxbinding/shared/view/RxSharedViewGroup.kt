package moe.pine.rxbinding.shared.view

import moe.pine.rxbinding.shared.CachedObservable
import moe.pine.rxbinding.shared.ObservableType
import rx.Observable
import rx.functions.*
import com.jakewharton.rxbinding.view.*
import com.jakewharton.rxbinding.widget.*
import android.view.*
import android.widget.*

/**
 * Auto generated
 * Created by CodeGenerator.kt on Apr 3, 2016 9:08:56 AM.
 */
fun ViewGroup.sharedChangeEvents(): Observable<ViewGroupHierarchyChangeEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.ViewGroupChangeEvents) {
        RxViewGroup.changeEvents(this)
    }
}