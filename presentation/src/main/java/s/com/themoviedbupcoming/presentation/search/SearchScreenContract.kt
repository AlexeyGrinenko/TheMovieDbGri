package s.com.themoviedbupcoming.presentation.search

import s.com.themoviedbupcoming.presentation.IBasePresenter

interface SearchScreenContract {

    interface SearchScreenView {
    }

    interface Presenter : IBasePresenter<SearchScreenView> {
        fun loadSearchFragment()
    }
}