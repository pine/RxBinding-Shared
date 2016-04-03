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