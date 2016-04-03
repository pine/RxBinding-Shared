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
fun TextView.sharedEditorActions(): Observable<Int> {
    return CachedObservable.getOrCreate(this, ObservableType.TextViewEditorActions) {
        RxTextView.editorActions(this)
    }
}

fun TextView.sharedEditorActions(handled: Func1<in Int, Boolean>): Observable<Int> {
    return CachedObservable.getOrCreate(this, ObservableType.TextViewEditorActions) {
        RxTextView.editorActions(this, handled)
    }
}

fun TextView.sharedEditorActionEvents(): Observable<TextViewEditorActionEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.TextViewEditorActionEvents) {
        RxTextView.editorActionEvents(this)
    }
}

fun TextView.sharedEditorActionEvents(handled: Func1<in TextViewEditorActionEvent, Boolean>): Observable<TextViewEditorActionEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.TextViewEditorActionEvents) {
        RxTextView.editorActionEvents(this, handled)
    }
}

fun TextView.sharedTextChanges(): Observable<CharSequence> {
    return CachedObservable.getOrCreate(this, ObservableType.TextViewTextChanges) {
        RxTextView.textChanges(this)
    }
}

fun TextView.sharedTextChangeEvents(): Observable<TextViewTextChangeEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.TextViewTextChangeEvents) {
        RxTextView.textChangeEvents(this)
    }
}

fun TextView.sharedBeforeTextChangeEvents(): Observable<TextViewBeforeTextChangeEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.TextViewBeforeTextChangeEvents) {
        RxTextView.beforeTextChangeEvents(this)
    }
}

fun TextView.sharedAfterTextChangeEvents(): Observable<TextViewAfterTextChangeEvent> {
    return CachedObservable.getOrCreate(this, ObservableType.TextViewAfterTextChangeEvents) {
        RxTextView.afterTextChangeEvents(this)
    }
}