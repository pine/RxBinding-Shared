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
fun SeekBar.sharedChanges(): Observable<Int> {
    return CachedObservable.getOrCreate(this, ObservableType.SeekBarChanges) {
        RxSeekBar.changes(this)
    }
}

fun SeekBar.sharedUserChanges(): Observable<Int> {
    return CachedObservable.getOrCreate(this, ObservableType.SeekBarUserChanges) {
        RxSeekBar.userChanges(this)
    }
}

fun SeekBar.sharedSystemChanges(): Observable<Int> {
    return CachedObservable.getOrCreate(this, ObservableType.SeekBarSystemChanges) {
        RxSeekBar.systemChanges(this)
    }
}

fun SeekBar.sharedChangeEvents(): Observable<SeekBarChangeEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.SeekBarChangeEvents) {
        RxSeekBar.changeEvents(this)
    }
}