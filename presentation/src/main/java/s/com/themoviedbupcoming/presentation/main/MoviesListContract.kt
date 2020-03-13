package s.com.themoviedbupcoming.presentation.main

import s.com.themoviedbupcoming.domain.model.MovieInList
import s.com.themoviedbupcoming.domain.model.SearchModel
import s.com.themoviedbupcoming.presentation.IBasePresenter

interface MoviesListContract {

    interface MoviesListView {
        fun loadShowsList(showsList: List<MovieInList>, isFromRefresh:Boolean)
        fun showErrorMessage(message: String)
        fun showProgressDialog()
        fun hideProgressDialog()
        fun moviesLoaded():Int
    }

    interface MoviesListPresenter : IBasePresenter<MoviesListView> {
        fun onMovieSelected(id: Int)
        fun refreshShowsList()
        fun loadMovies(isRefreshing: Boolean)
        fun setSearchModel(searchModel: SearchModel)
    }
}
