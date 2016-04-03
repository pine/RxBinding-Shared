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
fun MenuItem.sharedClicks(): Observable<Unit> {
    return CachedObservable.getOrCreate(this, ObservableType.MenuItemClicks) {
        RxMenuItem.clicks(this).map { Unit }
    }
}

fun MenuItem.sharedClicks(handled: Func1<in MenuItem, Boolean>): Observable<Unit> {
    return CachedObservable.getOrCreate(this, ObservableType.MenuItemClicks) {
        RxMenuItem.clicks(this, handled).map { Unit }
    }
}

fun MenuItem.sharedActionViewEvents(): Observable<MenuItemActionViewEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.MenuItemActionViewEvents) {
        RxMenuItem.actionViewEvents(this)
    }
}

fun MenuItem.sharedActionViewEvents(handled: Func1<in MenuItemActionViewEvent, Boolean>): Observable<MenuItemActionViewEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.MenuItemActionViewEvents) {
        RxMenuItem.actionViewEvents(this, handled)
    }
}