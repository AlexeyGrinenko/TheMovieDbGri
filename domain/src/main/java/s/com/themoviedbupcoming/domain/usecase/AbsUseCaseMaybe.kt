package s.com.themoviedbupcoming.domain.usecase

import io.reactivex.Maybe
import io.reactivex.MaybeTransformer
import io.reactivex.Scheduler
import io.reactivex.annotations.NonNull
import io.reactivex.annotations.Nullable
import io.reactivex.observers.DisposableMaybeObserver

abstract class AbsUseCaseMaybe<Result, Params>(val postExecutionThread: Scheduler) : AbsBaseUseCase() {

    protected abstract fun buildUseCaseObservable(@Nullable params: Params): Maybe<Result>

    fun execute(
        @NonNull observer: DisposableMaybeObserver<Result>, @Nullable params: Params,
        onSubscribe: (() -> Unit)? = null
    ) {

        val observable = this.scheduledUC(params)
            .doOnSubscribe { onSubscribe?.invoke() }

        addDisposable(observable.subscribeWith(observer))
    }

    fun execute(
        @NonNull observer: DisposableMaybeObserver<Result>,
        @NonNull transformer: MaybeTransformer<Result, Result>,
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
