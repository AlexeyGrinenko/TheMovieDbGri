package s.com.themoviedbupcoming.presentation.main

import s.com.themoviedbupcoming.domain.model.SearchModel
import s.com.themoviedbupcoming.presentation.router.IMainScreenRouter

class MainScreenPresenter(
    private val mainScreenRouter: IMainScreenRouter
) : MainScreenContract.Presenter {
    private var view: MainScreenContract.MainScreenView? = null

    override fun attachView(view: MainScreenContract.MainScreenView) {
        super.attachView(view)
        this@MainScreenPresenter.view = view
    }

    override fun onShowsHomeClicked(searchModel:SearchModel) {
        mainScreenRouter.openMovieListFragment(searchModel)
    }

    override fun onShowsClicked(showId: String) {
//        mainScreenRouter.openShowsDetailFragment(showId)
    }


    override fun detachView() {
        view = null
    }

    override fun onViewHidden() {
    }


}