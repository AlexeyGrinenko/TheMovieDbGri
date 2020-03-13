package s.com.themoviedbupcoming.navigation

import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.withArguments
import org.koin.standalone.KoinComponent
import s.com.themoviedbupcoming.R
import s.com.themoviedbupcoming.domain.model.SearchModel
import s.com.themoviedbupcoming.presentation.KEY_MOVIE_ID
import s.com.themoviedbupcoming.presentation.KEY_SEARCH_MODEL
import s.com.themoviedbupcoming.presentation.router.IMainScreenRouter
import s.com.themoviedbupcoming.ui.MainActivity
import s.com.themoviedbupcoming.ui.MovieDetailActivity
import s.com.themoviedbupcoming.ui.MoviesListFragment
import s.com.themoviedbupcoming.utils.log

class MainScreenRouter(private val activityBase: MainActivity) : IMainScreenRouter, KoinComponent {

    companion object {
    }

    override fun openMovieListFragment(searchModel: SearchModel) {
        log("openShowsListFragment")
        activityBase.supportFragmentManager
            .beginTransaction()
            .replace(R.id.framelayoutMain, MoviesListFragment().withArguments(KEY_SEARCH_MODEL to searchModel))
            .commit()
    }


    override fun openMovieDetailActivity(id: Int) {
        activityBase.startActivity<MovieDetailActivity>(KEY_MOVIE_ID to id)
    }


}