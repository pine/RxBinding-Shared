package moe.pine.rxbinding.shared

import android.view.View
import org.junit.Test
import org.mockito.Mockito
import rx.observers.TestSubscriber
import rx.subjects.PublishSubject
import kotlin.test.assertTrue

/**
 * Test for CachedObservable
 * Created by pine on 2016/01/21.
 */
class CachedObservableTest {
    @Test
    fun getOrCreate() {
        val view = Mockito.mock(View::class.java)
        val type = ObservableType.values().first()

        // case 1. single observer
        run {
            CachedObservable.unsafeClear()

            val subject = PublishSubject.create<Int>()
            var unsubscribed = false
            val originalObservable = subject.doOnUnsubscribe { unsubscribed = true }
            val subscriber = TestSubscriber<Int>()
            val observable = CachedObservable.getOrCreate(view, type, { originalObservable })
            val subscription = observable.subscribe(subscriber)

            subject.onNext(0)

            subscriber.assertValueCount(1)
            subscriber.assertValue(0)
            subscriber.assertNoErrors()

            subject.onNext(10)

            subscriber.assertValueCount(2)
            subscriber.assertValues(0, 10)
            subscriber.assertNoErrors()

            subscription.unsubscribe()

            assertTrue(unsubscribed)
        }

        // case 2. multi observers
        run {
            CachedObservable.unsafeClear()

            val subject = PublishSubject.create<Int>()
            var unsubscribed = false
            val originalObservable = subject.doOnUnsubscribe { unsubscribed = true }
            val subscriber1 = TestSubscriber<Int>()
            val subscriber2 = TestSubscriber<Int>()
            val observable = CachedObservable.getOrCreate(view, type, { originalObservable })

            val subscription1 = observable.subscribe(subscriber1)
            val subscription2 = observable.subscribe(subscriber2)

            subject.onNext(0)

            subscriber1.assertValueCount(1)
            subscriber1.assertValue(0)
            subscriber1.assertNoErrors()

            subscriber2.assertValueCount(1)
            subscriber2.assertValue(0)
            subscriber2.assertNoErrors()

            subscription1.unsubscribe()

            assertTrue(!unsubscribed)

            subscription2.unsubscribe()

            assertTrue(unsubscribed)
        }
    }
}