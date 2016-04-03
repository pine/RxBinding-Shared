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
 * Created by CodeGenerator.kt on Apr 3, 2016 9:08:58 AM.
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