package s.com.themoviedbupcoming.domain.usecase

import io.reactivex.Completable
import io.reactivex.CompletableTransformer
import io.reactivex.Scheduler
import io.reactivex.annotations.NonNull
import io.reactivex.annotations.Nullable
import io.reactivex.observers.DisposableCompletableObserver

abstract class AbsUseCaseCompl<Params>(val postExecutionThread: Scheduler) : AbsBaseUseCase() {

    abstract fun buildUseCaseObservable(@Nullable params: Params): Completable

    fun execute(
        observer: DisposableCompletableObserver, params: Params,
        onSubscribe: (() -> Unit)? = null
    ) {

        val completable = this.scheduledUC(params)
            .doOnSubscribe { onSubscribe?.invoke() }

        addDisposable(completable.subscribeWith(observer))
    }

    fun execute(@NonNull observer: DisposableCompletableObserver, @NonNull transformer: CompletableTransformer, @Nullable params: Params) {
        val completable = this.scheduledUC(params)
            .compose(transformer)
        addDisposable(completable.subscribeWith(observer))
    }

    fun scheduledUC(params: Params) =
        buildUseCaseObservable(params)
            .observeOn(postExecutionThread)
}
