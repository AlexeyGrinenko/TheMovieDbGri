package s.com.themoviedbupcoming.domain.usecase

import io.reactivex.observers.DisposableMaybeObserver

abstract class DisposableSimpleMaybeObserver<T> : DisposableMaybeObserver<T>() {
    override fun onSuccess(t: T) {
        // Empty
    }

    override fun onComplete() {
        // Empty
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
    }
}