package s.com.themoviedbupcoming.domain.usecase

import io.reactivex.observers.DisposableSingleObserver

open class DisposableSimpleSingleObserver<T> : DisposableSingleObserver<T>() {

    override fun onSuccess(t: T) {
        // Empty
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
    }
}