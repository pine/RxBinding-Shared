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
 * Created by CodeGenerator.kt on Apr 3, 2016 10:47:33 AM.
 */
fun RatingBar.sharedRatingChanges(): Observable<Float> {
    return CachedObservable.getOrCreate(this, ObservableType.RatingBarRatingChanges) {
        RxRatingBar.ratingChanges(this)
    }
}

fun RatingBar.sharedRatingChangeEvents(): Observable<RatingBarChangeEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.RatingBarRatingChangeEvents) {
        RxRatingBar.ratingChangeEvents(this)
    }
}