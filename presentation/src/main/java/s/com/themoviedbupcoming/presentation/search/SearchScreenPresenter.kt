package s.com.themoviedbupcoming.presentation.search

import s.com.themoviedbupcoming.presentation.router.ISearchScreenRouter

class SearchScreenPresenter(
    private val searchScreenRouter: ISearchScreenRouter
) : SearchScreenContract.Presenter {
    private var view: SearchScreenContract.SearchScreenView? = null

    override fun attachView(view: SearchScreenContract.SearchScreenView) {
        super.attachView(view)
        this@SearchScreenPresenter.view = view
    }

    override fun loadSearchFragment() {
        searchScreenRouter.openSearchFragment()
    }

    override fun detachView() {
        view = null
    }

    override fun onViewHidden() {
    }
}