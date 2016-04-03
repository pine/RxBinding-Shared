package moe.pine.rxbinding.shared.widget

import com.jakewharton.rxbinding.widget.*
import android.widget.*
import android.view.*
import moe.pine.rxbinding.shared.CachedObservable
import moe.pine.rxbinding.shared.ObservableType
import rx.Observable
import rx.functions.*

/**
 * Auto generated
 * Created by CodeGenerator.kt on Apr 3, 2016 10:47:34 AM.
 */
fun Toolbar.sharedItemClicks(): Observable<MenuItem> {
    return CachedObservable.getOrCreate(this, ObservableType.ToolbarItemClicks) {
        RxToolbar.itemClicks(this)
    }
}

fun Toolbar.sharedNavigationClicks(): Observable<Unit> {
    return CachedObservable.getOrCreate(this, ObservableType.ToolbarNavigationClicks) {
        RxToolbar.navigationClicks(this).map { Unit }
    }
}