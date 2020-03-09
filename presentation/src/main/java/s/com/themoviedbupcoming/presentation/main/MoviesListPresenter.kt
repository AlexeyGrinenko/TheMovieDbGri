package s.com.themoviedbupcoming.presentation.main

import s.com.themoviedbupcoming.domain.model.MovieInList
import s.com.themoviedbupcoming.domain.usecase.DisposableSimpleSingleObserver
import s.com.themoviedbupcoming.presentation.router.IMainScreenRouter
import s.com.themoviedbupcoming.domain.usecase.movie.ShowsUseCase

class MoviesListPresenter(private val showsUseCase: ShowsUseCase, private val mainScreenRouter: IMainScreenRouter) :
    MoviesListContract.MoviesListPresenter {
    private var view: MoviesListContract.MoviesListView? = null

    override fun attachView(view: MoviesListContract.MoviesListView) {
        this@MoviesListPresenter.view = view
    }

    override fun refreshShowsList() {
        loadMovies(true)
    }

    override fun loadMovies(isRefreshing: Boolean) {
        view?.showProgressDialog()
        showsUseCase.execute(object : DisposableSimpleSingleObserver<List<MovieInList>>() {
            override fun onSuccess(movies: List<MovieInList>) {
                view?.hideProgressDialog()
                view?.loadShowsList(movies,isRefreshing)
            }

            override fun onError(e: Throwable) {
                view?.hideProgressDialog()
                view?.showErrorMessage(e.localizedMessage)
            }
        }, ShowsUseCase.Params(isRefreshing))
    }

    override fun onMovieSelected(id: Int) {
        mainScreenRouter.openMovieDetailActivity(id)
    }


    override fun detachView() {
        view = null
        showsUseCase.dispose()
    }

    override fun onViewHidden() {
        showsUseCase.clear()
    }

}