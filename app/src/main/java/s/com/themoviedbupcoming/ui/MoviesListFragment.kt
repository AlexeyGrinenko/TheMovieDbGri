package s.com.themoviedbupcoming.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.seatsbooking.adapters.InfiniteScrollListener
import kotlinx.android.synthetic.main.fragment_movies_list.*
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope
import s.com.themoviedbupcoming.BaseFragment
import s.com.themoviedbupcoming.R
import s.com.themoviedbupcoming.adapters.MoviesListAdapter
import s.com.themoviedbupcoming.di.KOIN_KEY_SCOPE_MOVIES_LIST_FRAGMENT
import s.com.themoviedbupcoming.domain.model.MovieInList
import s.com.themoviedbupcoming.domain.model.SearchModel
import s.com.themoviedbupcoming.presentation.KEY_SEARCH_MODEL
import s.com.themoviedbupcoming.presentation.MOVIES_LIST_SCREEN
import s.com.themoviedbupcoming.presentation.main.MoviesListContract
import s.com.themoviedbupcoming.utils.gone
import s.com.themoviedbupcoming.utils.visible

class MoviesListFragment : BaseFragment(), MoviesListContract.MoviesListView,
    SwipeRefreshLayout.OnRefreshListener {

    private lateinit var scopeKoin: Scope

    override val contextName = MOVIES_LIST_SCREEN

    private val presenter: MoviesListContract.MoviesListPresenter by inject { parametersOf(this.activity as MainActivity) }
    private val movies: ArrayList<MovieInList> = ArrayList()
    private lateinit var showsListAdapter: MoviesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scopeKoin = getKoin().getOrCreateScope(KOIN_KEY_SCOPE_MOVIES_LIST_FRAGMENT)
        presenter.attachView(this)
        val searchModel = this.arguments?.getSerializable(KEY_SEARCH_MODEL) as SearchModel
        presenter.setSearchModel(searchModel)
        swipMovies.setOnRefreshListener(this@MoviesListFragment)
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerMovies.layoutManager = linearLayoutManager
        showsListAdapter =
            MoviesListAdapter(movies) { movie: MovieInList -> onShowClickListener(movie) }
        recyclerMovies.adapter = showsListAdapter
        if (isNetworkAvailable()) presenter.loadMovies(true)
        recyclerMovies.clearOnScrollListeners()
        recyclerMovies.addOnScrollListener(
            InfiniteScrollListener({ presenter.loadMovies(false) }, linearLayoutManager)
        )
        recyclerMovies.addItemDecoration(
            DividerItemDecoration(
                context!!,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun loadShowsList(showsList: List<MovieInList>, isFromRefresh: Boolean) {
        if (this@MoviesListFragment.isAdded) {
//            if (isFromRefresh) shows.clear()
            movies.addAll(showsList)
            showsListAdapter.notifyDataSetChanged()
            swipMovies.isRefreshing = false
        }
    }

    override fun onRefresh() {
        presenter.refreshShowsList()
        swipMovies.isRefreshing = false
    }

    override fun onDestroy() {
        presenter.detachView()
        scopeKoin.close()
        super.onDestroy()
    }

    override fun onDestroyView() {
        presenter.onViewHidden()
        hideProgressDialog()
        super.onDestroyView()
    }

    override fun showErrorMessage(message: String) {
        if (this@MoviesListFragment.isAdded) {
            context?.let { Toast.makeText(context, message, Toast.LENGTH_SHORT).show() }
        }
    }

    override fun showProgressDialog() {
        progressMovies.visible()
    }

    override fun hideProgressDialog() {
        progressMovies.gone()
    }

    override fun moviesLoaded(): Int {
        return movies.size
    }

    private fun onShowClickListener(movie: MovieInList) {
        movie.id?.let { presenter.onMovieSelected(it) }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
            true
        } else {
            context?.let { showErrorMessage(it.getString(R.string.toast_no_internet)) }
            false
        }
    }
}