package moe.pine.rxbinding.shared.view

import android.app.Activity
import android.os.Build
import android.view.View
import moe.pine.rxbinding.shared.BuildConfig
import moe.pine.rxbinding.shared.CachedObservable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
import rx.observers.TestSubscriber

/**
 * RxSharedViewTest
 * Created by pine on 2016/04/06.
 */
@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(Build.VERSION_CODES.LOLLIPOP))
class RxSharedViewTest {
    @Before
    fun setup() {
        CachedObservable.unsafeClear()
    }

    @Test
    fun sharedClicks() {
        val view = View(Robolectric.buildActivity(Activity::class.java).create().get())
        val subscriber = TestSubscriber<Unit>()
        view.sharedClicks().subscribe(subscriber)

        subscriber.assertValueCount(0)
        subscriber.assertNoErrors()
        subscriber.assertNotCompleted()

        view.performClick()

        subscriber.assertValueCount(1)
        subscriber.assertValue(Unit)
        subscriber.assertNoErrors()
        subscriber.assertNotCompleted()
    }

    @Test
    fun sharedClicks_multiSubscribers() {
        val view = View(Robolectric.buildActivity(Activity::class.java).create().get())
        val subscribers = (0..2).map { TestSubscriber<Unit>() }
        val subscriptions = subscribers.map { view.sharedClicks().subscribe(it) }

        view.performClick()

        subscribers.forEach {
            it.assertValueCount(1)
            it.assertValue(Unit)
            it.assertNoErrors()
            it.assertNotCompleted()
        }

        subscriptions.first().unsubscribe()
        view.performClick()

        subscribers.last().let {
            it.assertValueCount(2)
            it.assertValues(Unit, Unit)
            it.assertNoErrors()
            it.assertNotCompleted()
        }
    }
}