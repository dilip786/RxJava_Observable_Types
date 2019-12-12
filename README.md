### RxJava Observable Types
A simple android project that demonstrates Types of observable patterns 

### Code Setup

```

Make sure you require Internet permission in your manifest file.

<uses-permission android:name="android.permission.INTERNET" />

We need following dependencies for this project. Add the lines below to your app/build.gradle in your app project under dependencies.

def rxjava_version ="2.1.8"
def rxandroid_version = "2.0.1"
def retrofit_version ="2.0.2"
def gson_version ="2.6.2"
def retrofit_adapter ="1.0.0"

dependencies 
{
	// Retrofit, Gson
	implementation "com.google.code.gson:gson:$gson_version"
	implementation "com.squareup.retrofit2:retrofit:$retrofit_version"
	implementation "com.squareup.retrofit2:converter-gson:$retrofit_version"
	implementation "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:$retrofit_adapter"

	// RxJava2
	implementation "io.reactivex.rxjava2:rxjava:$rxjava_version"
	implementation "io.reactivex.rxjava2:rxandroid:$rxandroid_version"
}

```


#### Types of Observables

- Single : Single is an Observable that always emit only one value or throws an error. A typical use case of Single observable would be when we make a network call in Android and receive a response
  
   ```
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
    ```
- Maybe: Maybe is an Observable that may or may not emit a value. For example, we would like to know if a particular user exists in our db. The user may or may not exist.

    ```
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
    ```
- Completable: Completable does not emit any data, but rather is focused on the status of execution — whether successful or failure.

    ```
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
    ```
- Flowable: Flowable is typically used when an Observable is emitting huge amounts of data but the Observer is not able to handle this data emission. This is known as Back Pressure.

    ```
    var observable = Flowable.create(object : FlowableOnSubscribe<String> {
            override fun subscribe(emitter: FlowableEmitter<String>) {
                emitter.onNext("FlowableObserver")

            }

        }, BackpressureStrategy.LATEST)

        observable.subscribe {
            Log.e("LOG", it)
        }
    ```


