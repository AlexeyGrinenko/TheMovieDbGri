package s.com.themoviedbupcoming.presentation.search

import s.com.themoviedbupcoming.domain.model.Genre
import s.com.themoviedbupcoming.domain.model.MovieInList
import s.com.themoviedbupcoming.presentation.IBasePresenter

interface SearchFragmentContract {

    interface View {
        fun loadGenresList(genres: List<Genre>)
        fun showErrorMessage(message: String)
        fun showProgressDialog()
        fun hideProgressDialog()
    }

    interface Presenter : IBasePresenter<View> {
//        fun onMovieSelected(id: Int)
        fun loadGenres()
        fun saveYear(year:String)
        fun selectedYear():String
        fun startSearch(title: String, genres: MutableList<Genre>, includeAdult: Boolean)
    }
}
