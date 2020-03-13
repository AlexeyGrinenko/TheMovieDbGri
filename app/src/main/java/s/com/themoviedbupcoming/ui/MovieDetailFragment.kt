package s.com.themoviedbupcoming.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import s.com.themoviedbupcoming.BaseFragment
import s.com.themoviedbupcoming.R
import s.com.themoviedbupcoming.adapters.MovieAdapter
import s.com.themoviedbupcoming.di.KOIN_KEY_SCOPE_MOVIE_FRAGMENT
import s.com.themoviedbupcoming.domain.model.Movie
import s.com.themoviedbupcoming.presentation.BASE_IMAGE_URL
import s.com.themoviedbupcoming.presentation.KEY_MOVIE_ID
import s.com.themoviedbupcoming.presentation.MOVIE_DETAIL_SCREEN
import s.com.themoviedbupcoming.presentation.main.MovieDetailContract


class MovieDetailFragment : BaseFragment(), MovieDetailContract.MovieDetailView {


    private val scopeKoin = getKoin().getOrCreateScope(KOIN_KEY_SCOPE_MOVIE_FRAGMENT)

    override val contextName = MOVIE_DETAIL_SCREEN

    private val presenter: MovieDetailContract.Presenter by inject { parametersOf(this.activity as MovieDetailActivity) }
    private lateinit var movieAdapter: MovieAdapter
    private val movieFields: ArrayList<String> = ArrayList()

//    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val showId = this.arguments?.getInt(KEY_MOVIE_ID, -1)

        presenter.attachView(this)
//        progressDialog = indeterminateProgressDialog(getString(R.string.please_wait))
        showId?.let { presenter.getMovieData(it) }
        val linearLayoutManager = LinearLayoutManager(context)
        recycleMovie.layoutManager = linearLayoutManager
        movieAdapter = MovieAdapter(movieFields)
        recycleMovie.adapter = movieAdapter
        super.onViewCreated(view, savedInstanceState)
    }

    override fun loadMovieData(movie: Movie) {
        if (this@MovieDetailFragment.isVisible) {
            tvMovieTitle?.text = movie.title

            Glide.with(this)
                .load(BASE_IMAGE_URL + movie.posterPath)
                .apply(RequestOptions().placeholder(R.drawable.ic_the_movie_logo))
                .into(ivImage)
            Glide.with(this)
                .load(BASE_IMAGE_URL + movie.backdropPath)
                .apply(RequestOptions().placeholder(R.drawable.ic_the_movie_logo))
                .into(ivImageBackdrop)
            context?.let { cnt ->
                movieFields.add("${movie.originalLanguage}: ${movie.originalTitle}")
                movieFields.add("${cnt.getString(R.string.release_on)} ${movie.releaseDate}")
                movieFields.add("${cnt.getString(R.string.popularity)} ${movie.popularity}")
                movieFields.add(
                    "${cnt.getString(R.string.votes)} ${movie.voteAverage ?: 0} / ${movie.voteCount
                        ?: 0}"
                )
                movie.homepage?.let { movieFields.add("${movie.homepage}") }
                movie.overview?.let { movieFields.add("${movie.overview}") }
                if (movie.adult == true) movieFields.add(cnt.getString(R.string.is_adult))
                movie.status?.let { movieFields.add("${cnt.getString(R.string.status)} ${movie.status}") }
                movieFields.add("${cnt.getString(R.string.budget)} ${movie.budget}")

                movie.belongsToCollection?.let { movieFields.add("${cnt.getString(R.string.belongs_to_collection)} ${movie.belongsToCollection}") }
                movie.imdbId?.let { movieFields.add("${cnt.getString(R.string.imdbId)} ${movie.imdbId}") }

                movieFields.add("${cnt.getString(R.string.genres)}: ${movie.genres.map { it.name }.joinToString()}")

                movie.productionCompanies?.let {
                    movieFields.add("${cnt.getString(R.string.production_companies)} ${movie.productionCompanies}")
                }
                movie.productionCountries?.let {
                    movieFields.add("${cnt.getString(R.string.production_countries)} ${movie.productionCountries}")
                }
                movie.revenue?.let { movieFields.add("${cnt.getString(R.string.revenue)} ${movie.revenue}") }
                movie.runtime?.let { movieFields.add("${cnt.getString(R.string.runtime)} ${movie.runtime}") }
                movie.tagline?.let { movieFields.add("${cnt.getString(R.string.tagline)} ${movie.tagline}") }

                movieAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun showErrorMessage(message: String) {
        context?.let { Toast.makeText(context, message, Toast.LENGTH_SHORT).show() }
    }

    override fun onDestroy() {
        hideProgressDialog()
        presenter.detachView()
        scopeKoin.close()
        super.onDestroy()
    }

    override fun onDestroyView() {
        presenter.onViewHidden()
        super.onDestroyView()
    }


    override fun showProgressDialog() {
//        progressDialog.show()
    }

    override fun hideProgressDialog() {
//        progressDialog.dismiss()
    }

}