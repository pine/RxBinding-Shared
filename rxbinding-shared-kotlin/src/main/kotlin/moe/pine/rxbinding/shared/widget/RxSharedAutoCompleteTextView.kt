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
fun AutoCompleteTextView.sharedItemClickEvents(): Observable<AdapterViewItemClickEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.AutoCompleteTextViewItemClickEvents) {
        RxAutoCompleteTextView.itemClickEvents(this)
    }
}