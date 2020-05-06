package com.picpay.desafio.android.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

// IO thread pool scheduler
fun io() = Schedulers.io()

// Main Thread scheduler
fun ui() = AndroidSchedulers.mainThread()

/**
 * Execute the operation on a new thread (from thread pool) and listen on the main thread.
 * It can be used for I/O operations
 *
 * @return the transformer properly configured
 */
fun <T> Single<T>.defaultSchedulers() = this.subscribeOn(io()).observeOn(ui())

fun <T> LiveData<T>.nonNullObserve(owner: LifecycleOwner, observer: (data: T) -> Unit) {
    observe(owner, Observer {
        it?.let(observer)
    })
}