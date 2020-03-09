package s.com.themoviedbupcoming.domain.usecase

import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class AbsBaseUseCase {

    private val disposables = CompositeDisposable()

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

    fun clear() {
        if (!disposables.isDisposed) disposables.clear()
    }

    internal fun addDisposable(@NonNull disposable: Disposable) {
        disposables.add(disposable)
    }
}
