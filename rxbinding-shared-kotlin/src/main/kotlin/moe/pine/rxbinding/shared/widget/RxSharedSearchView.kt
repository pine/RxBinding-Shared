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
fun SearchView.sharedQueryTextChangeEvents(): Observable<SearchViewQueryTextEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.SearchViewQueryTextChangeEvents) {
        RxSearchView.queryTextChangeEvents(this)
    }
}

fun SearchView.sharedQueryTextChanges(): Observable<CharSequence> {
    return CachedObservable.getOrCreate(this, ObservableType.SearchViewQueryTextChanges) {
        RxSearchView.queryTextChanges(this)
    }
}