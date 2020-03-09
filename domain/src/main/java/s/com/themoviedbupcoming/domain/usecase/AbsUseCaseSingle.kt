package s.com.themoviedbupcoming.domain.usecase

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.annotations.NonNull
import io.reactivex.annotations.Nullable
import io.reactivex.observers.DisposableSingleObserver

abstract class AbsUseCaseSingle<Result, Params>(val postExecutionThread: Scheduler) : AbsBaseUseCase() {

    protected abstract fun buildUseCaseObservable(params: Params): Single<Result>

    fun execute(
        @NonNull observer: DisposableSingleObserver<Result>, @Nullable params: Params,
        onSubscribe: (() -> Unit)? = null
    ) {
        val observable = this.scheduledUC(params)
            .doOnSubscribe { onSubscribe?.invoke() }

        addDisposable(observable.subscribeWith(observer))
    }

    fun execute(
        @NonNull observer: DisposableSingleObserver<Result>,
        @NonNull transformer: SingleTransformer<Result, Result>,
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
