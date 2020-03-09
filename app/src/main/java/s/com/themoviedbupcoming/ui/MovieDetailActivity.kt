package s.com.themoviedbupcoming.ui

import android.os.Bundle
import android.view.MenuItem
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.setProperty
import org.koin.core.parameter.parametersOf
import s.com.themoviedbupcoming.BaseActivity
import s.com.themoviedbupcoming.R
import s.com.themoviedbupcoming.di.KOIN_KEY_SCOPE_MOVIE_ACTIVITY
import s.com.themoviedbupcoming.presentation.KEY_ACTIVITY
import s.com.themoviedbupcoming.presentation.KEY_MOVIE_ID
import s.com.themoviedbupcoming.presentation.main.MovieScreenContract

class MovieDetailActivity : BaseActivity(), MovieScreenContract.MovieScreenView  {
    private val scopeKoin = getKoin().getOrCreateScope(KOIN_KEY_SCOPE_MOVIE_ACTIVITY)

    private val presenter: MovieScreenContract.Presenter by inject { parametersOf(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setProperty(KEY_ACTIVITY, this)
        presenter.attachView(this)
        val movieId = intent.getIntExtra(KEY_MOVIE_ID, -1)
        presenter.loadMovieDetailFragment(movieId)
        // add back arrow to toolbar
        supportActionBar?.let{
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
    }

    override fun onDestroy() {
        presenter.detachView()
        presenter.onViewHidden()
        scopeKoin.close()
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
