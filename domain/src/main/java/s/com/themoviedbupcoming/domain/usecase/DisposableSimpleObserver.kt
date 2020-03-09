package s.com.themoviedbupcoming.domain.usecase

import io.reactivex.observers.DisposableObserver

open class DisposableSimpleObserver<T> : DisposableObserver<T>() {

    override fun onComplete() {

    }

    override fun onNext(t: T) {

    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
    }

}