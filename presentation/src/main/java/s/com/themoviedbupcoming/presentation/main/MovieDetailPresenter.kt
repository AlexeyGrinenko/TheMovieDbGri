package s.com.themoviedbupcoming.presentation.main

import s.com.themoviedbupcoming.domain.model.Movie
import s.com.themoviedbupcoming.domain.usecase.DisposableSimpleSingleObserver
import s.com.themoviedbupcoming.domain.usecase.movie.MovieDetailUseCase

class MovieDetailPresenter(private val movieDetailUseCase: MovieDetailUseCase)
    : MovieDetailContract.Presenter {

    private var view: MovieDetailContract.MovieDetailView? = null

    override fun attachView(view: MovieDetailContract.MovieDetailView) {
        this@MovieDetailPresenter.view = view
    }

    override fun getMovieData(id: Int) {
        view?.showProgressDialog()
        movieDetailUseCase.execute(object : DisposableSimpleSingleObserver<Movie>() {
            override fun onSuccess(movie: Movie) {
                view?.hideProgressDialog()
                view?.loadMovieData(movie)
            }

            override fun onError(e: Throwable) {
                view?.hideProgressDialog()
                view?.showErrorMessage(e.localizedMessage)
            }
        }, MovieDetailUseCase.Params(id))
    }

    override fun detachView() {
        view = null
        movieDetailUseCase.dispose()
    }

    override fun onViewHidden() {
        movieDetailUseCase.clear()
    }
}