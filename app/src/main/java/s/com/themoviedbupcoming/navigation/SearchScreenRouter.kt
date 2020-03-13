package s.com.themoviedbupcoming.navigation

import org.jetbrains.anko.startActivity
import org.koin.standalone.KoinComponent
import s.com.themoviedbupcoming.R
import s.com.themoviedbupcoming.domain.model.SearchModel
import s.com.themoviedbupcoming.presentation.KEY_MOVIE_ID
import s.com.themoviedbupcoming.presentation.KEY_SEARCH_MODEL
import s.com.themoviedbupcoming.presentation.router.IMainScreenRouter
import s.com.themoviedbupcoming.presentation.router.ISearchScreenRouter
import s.com.themoviedbupcoming.ui.*
import s.com.themoviedbupcoming.utils.log

class SearchScreenRouter(private val activityBase: SearchActivity) : ISearchScreenRouter, KoinComponent {

    companion object {
    }

    override fun openSearchFragment() {
        log("openSearchFragment")
        activityBase.supportFragmentManager
            .beginTransaction()
            .replace(R.id.framelayoutMain, SearchFragment())
            .commit()
    }

    override fun openMainActivity(searchModel: SearchModel) {
        activityBase.startActivity<MainActivity>(KEY_SEARCH_MODEL to searchModel)
    }


}