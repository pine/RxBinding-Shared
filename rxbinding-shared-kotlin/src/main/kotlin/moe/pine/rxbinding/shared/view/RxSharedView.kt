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
fun View.sharedAttaches(): Observable<Unit> {
    return CachedObservable.getOrCreate(this, ObservableType.ViewAttaches) {
        RxView.attaches(this).map { Unit }
    }
}

fun View.sharedAttachEvents(): Observable<ViewAttachEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.ViewAttachEvents) {
        RxView.attachEvents(this)
    }
}

fun View.sharedDetaches(): Observable<Unit> {
    return CachedObservable.getOrCreate(this, ObservableType.ViewDetaches) {
        RxView.detaches(this).map { Unit }
    }
}

fun View.sharedClicks(): Observable<Unit> {
    return CachedObservable.getOrCreate(this, ObservableType.ViewClicks) {
        RxView.clicks(this).map { Unit }
    }
}

fun View.sharedDrags(): Observable<DragEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.ViewDrags) {
        RxView.drags(this)
    }
}

fun View.sharedDrags(handled: Func1<in DragEvent, Boolean>): Observable<DragEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.ViewDrags) {
        RxView.drags(this, handled)
    }
}

fun View.sharedDraws(): Observable<Unit> {
    return CachedObservable.getOrCreate(this, ObservableType.ViewDraws) {
        RxView.draws(this).map { Unit }
    }
}

fun View.sharedFocusChanges(): Observable<Boolean> {
    return CachedObservable.getOrCreate(this, ObservableType.ViewFocusChanges) {
        RxView.focusChanges(this)
    }
}

fun View.sharedGlobalLayouts(): Observable<Unit> {
    return CachedObservable.getOrCreate(this, ObservableType.ViewGlobalLayouts) {
        RxView.globalLayouts(this).map { Unit }
    }
}

fun View.sharedHovers(): Observable<MotionEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.ViewHovers) {
        RxView.hovers(this)
    }
}

fun View.sharedHovers(handled: Func1<in MotionEvent, Boolean>): Observable<MotionEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.ViewHovers) {
        RxView.hovers(this, handled)
    }
}

fun View.sharedLayoutChanges(): Observable<Unit> {
    return CachedObservable.getOrCreate(this, ObservableType.ViewLayoutChanges) {
        RxView.layoutChanges(this).map { Unit }
    }
}

fun View.sharedLayoutChangeEvents(): Observable<ViewLayoutChangeEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.ViewLayoutChangeEvents) {
        RxView.layoutChangeEvents(this)
    }
}

fun View.sharedLongClicks(): Observable<Unit> {
    return CachedObservable.getOrCreate(this, ObservableType.ViewLongClicks) {
        RxView.longClicks(this).map { Unit }
    }
}

fun View.sharedLongClicks(handled: Func0<Boolean>): Observable<Unit> {
    return CachedObservable.getOrCreate(this, ObservableType.ViewLongClicks) {
        RxView.longClicks(this, handled).map { Unit }
    }
}

fun View.sharedPreDraws(proceedDrawingPass: Func0<Boolean>): Observable<Unit> {
    return CachedObservable.getOrCreate(this, ObservableType.ViewPreDraws) {
        RxView.preDraws(this, proceedDrawingPass).map { Unit }
    }
}

fun View.sharedScrollChangeEvents(): Observable<ViewScrollChangeEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.ViewScrollChangeEvents) {
        RxView.scrollChangeEvents(this)
    }
}

fun View.sharedSystemUiVisibilityChanges(): Observable<Int> {
    return CachedObservable.getOrCreate(this, ObservableType.ViewSystemUiVisibilityChanges) {
        RxView.systemUiVisibilityChanges(this)
    }
}

fun View.sharedTouches(): Observable<MotionEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.ViewTouches) {
        RxView.touches(this)
    }
}

fun View.sharedTouches(handled: Func1<in MotionEvent, Boolean>): Observable<MotionEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.ViewTouches) {
        RxView.touches(this, handled)
    }
}