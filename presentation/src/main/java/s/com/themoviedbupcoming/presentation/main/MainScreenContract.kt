package s.com.themoviedbupcoming.presentation.main

import s.com.themoviedbupcoming.domain.model.SearchModel
import s.com.themoviedbupcoming.presentation.IBasePresenter

interface MainScreenContract {

    interface MainScreenView {
    }

    interface Presenter : IBasePresenter<MainScreenView> {
        fun onShowsHomeClicked(searchModel:SearchModel)
        fun onShowsClicked(showId: String)
    }
}