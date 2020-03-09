package s.com.themoviedbupcoming.navigation

import org.jetbrains.anko.support.v4.withArguments
import org.koin.standalone.KoinComponent
import s.com.themoviedbupcoming.R
import s.com.themoviedbupcoming.presentation.KEY_MOVIE_ID
import s.com.themoviedbupcoming.presentation.router.IMovieScreenRouter
import s.com.themoviedbupcoming.ui.MovieDetailActivity
import s.com.themoviedbupcoming.ui.MovieDetailFragment

class MovieScreenRouter(private val activityBase: MovieDetailActivity) : IMovieScreenRouter,
    KoinComponent {

    override fun openMovieDetailFragment(id: Int) {
        activityBase.supportFragmentManager
            .beginTransaction()
            .replace(R.id.framelayoutMain, MovieDetailFragment().withArguments(KEY_MOVIE_ID to id))
            .commit()
    }
}