package moe.pine.rxbinding.shared.widget

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
 * Created by CodeGenerator.kt on Apr 3, 2016 9:08:57 AM.
 */
fun <T: Adapter> AdapterView<T>.sharedItemSelections(): Observable<Int> {
    return CachedObservable.getOrCreate(this, ObservableType.AdapterViewItemSelections) {
        RxAdapterView.itemSelections(this)
    }
}

fun <T: Adapter> AdapterView<T>.sharedSelectionEvents(): Observable<AdapterViewSelectionEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.AdapterViewSelectionEvents) {
        RxAdapterView.selectionEvents(this)
    }
}

fun <T: Adapter> AdapterView<T>.sharedItemClicks(): Observable<Int> {
    return CachedObservable.getOrCreate(this, ObservableType.AdapterViewItemClicks) {
        RxAdapterView.itemClicks(this)
    }
}

fun <T: Adapter> AdapterView<T>.sharedItemClickEvents(): Observable<AdapterViewItemClickEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.AdapterViewItemClickEvents) {
        RxAdapterView.itemClickEvents(this)
    }
}

fun <T: Adapter> AdapterView<T>.sharedItemLongClicks(): Observable<Int> {
    return CachedObservable.getOrCreate(this, ObservableType.AdapterViewItemLongClicks) {
        RxAdapterView.itemLongClicks(this)
    }
}

fun <T: Adapter> AdapterView<T>.sharedItemLongClicks(handled: Func0<Boolean>): Observable<Int> {
    return CachedObservable.getOrCreate(this, ObservableType.AdapterViewItemLongClicks) {
        RxAdapterView.itemLongClicks(this, handled)
    }
}

fun <T: Adapter> AdapterView<T>.sharedItemLongClickEvents(): Observable<AdapterViewItemLongClickEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.AdapterViewItemLongClickEvents) {
        RxAdapterView.itemLongClickEvents(this)
    }
}

fun <T: Adapter> AdapterView<T>.sharedItemLongClickEvents(handled: Func1<in AdapterViewItemLongClickEvent, Boolean>): Observable<AdapterViewItemLongClickEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.AdapterViewItemLongClickEvents) {
        RxAdapterView.itemLongClickEvents(this, handled)
    }
}