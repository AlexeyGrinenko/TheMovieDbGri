package s.com.themoviedbupcoming.presentation.main

import s.com.themoviedbupcoming.domain.model.Movie
import s.com.themoviedbupcoming.presentation.IBasePresenter

interface MovieDetailContract {

    interface MovieDetailView {
        fun loadMovieData(movie: Movie)
        fun showErrorMessage(message: String)
        fun showProgressDialog()
        fun hideProgressDialog()
    }

    interface Presenter : IBasePresenter<MovieDetailView> {
        fun getMovieData(id: Int)
    }
}