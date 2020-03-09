package s.com.themoviedbupcoming.domain.usecase

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.annotations.NonNull
import io.reactivex.annotations.Nullable
import io.reactivex.observers.DisposableObserver

abstract class AbsUseCaseObs<Result, Params>(val postExecutionThread: Scheduler) : AbsBaseUseCase() {

    abstract fun buildUseCaseObservable(params: Params): Observable<Result>

    fun execute(
        @NonNull observer: DisposableObserver<Result>, params: Params,
        onSubscribe: (() -> Unit)? = null
    ) {
        val observable = this.scheduledUC(params)
            .doOnSubscribe { onSubscribe?.invoke() }

        addDisposable(observable.subscribeWith(observer))
    }

    fun execute(
        @NonNull observer: DisposableObserver<Result>,
        @NonNull transformer: ObservableTransformer<Result, Result>,
        @Nullable params: Params
    ) {
        val observable = this.scheduledUC(params)
            .compose(transformer)
        addDisposable(observable.subscribeWith(observer))
    }

    fun scheduledUC(params: Params) =
        buildUseCaseObservable(params)
            .observeOn(postExecutionThread)

}
