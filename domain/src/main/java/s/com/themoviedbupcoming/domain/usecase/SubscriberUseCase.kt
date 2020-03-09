package s.com.themoviedbupcoming.domain.usecase

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

abstract class SubscriberUseCase<R, P> {

    var compositeSubscription: CompositeDisposable = CompositeDisposable()

    protected abstract fun buildSubscriptionUseCase(params: P): Observable<R>

    private fun getScheduledSubscription(params: P): Observable<R> {
        return buildSubscriptionUseCase(params)
    }

    fun subscribe(observer: DisposableObserver<R>, params: P) {
        compositeSubscription.clear()
        compositeSubscription.add(getScheduledSubscription(params).subscribeWith(observer))
    }

    fun dispose() {
        compositeSubscription.dispose()
    }
}