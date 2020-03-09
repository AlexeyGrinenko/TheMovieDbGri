package s.com.themoviedbupcoming.presentation.main

import s.com.themoviedbupcoming.presentation.IBasePresenter

interface MovieScreenContract {

    interface MovieScreenView {
    }

    interface Presenter : IBasePresenter<MovieScreenView> {
        fun loadMovieDetailFragment(id: Int)
    }
}