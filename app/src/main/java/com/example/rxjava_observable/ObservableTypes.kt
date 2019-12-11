package com.example.rxjava_observable

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.*
import io.reactivex.disposables.Disposable

class ObservableTypes : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createObservable()
        createSingleObservable()
        createFlowableObserver()
        createMaybeObservable()
        createCompletableObservable()
    }

    private fun createObservable() {
        var observable = Observable.create(ObservableOnSubscribe<String> { emitter ->
            emitter.onNext("RxAndroid - Observable")
        })

        observable.run {

            subscribe(object : Observer<String> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: String) {
                    Log.e("LOG", t)
                }

                override fun onError(e: Throwable) {
                }
            })
        }
    }

    private fun createSingleObservable() {
        var observable = Single.create(SingleOnSubscribe<String>
        { emitter ->
            emitter.onSuccess("Single Observer")
        })
        observable.subscribe(object : SingleObserver<String> {
            override fun onSuccess(t: String) {

                Log.e("LOG", t)
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
            }
        })
    }

    private fun createFlowableObserver() {
        var observable = Flowable.create(object : FlowableOnSubscribe<String> {
            override fun subscribe(emitter: FlowableEmitter<String>) {
                emitter.onNext("FlowableObserver")

            }

        }, BackpressureStrategy.LATEST)

        observable.subscribe {
            Log.e("LOG", it)
        }
    }

    private fun createMaybeObservable() {

        var observable = Maybe.create(object : MaybeOnSubscribe<String> {
            override fun subscribe(emitter: MaybeEmitter<String>) {
                emitter.onSuccess("MaybeObservable")
            }

        })

        observable.subscribe(object : MaybeObserver<String> {
            override fun onSuccess(t: String) {
                Log.e("TAG", t)

            }

            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
            }
        })
    }

    private fun createCompletableObservable() {
        var observable = Completable.create(object : CompletableOnSubscribe {
            override fun subscribe(emitter: CompletableEmitter) {

                Thread.sleep(1000)
                emitter.onComplete()
            }
        })

        observable.subscribe(object : CompletableObserver {
            override fun onComplete() {
                Log.e("TAG", "onComplete()")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
            }
        })
    }
}
