package s.com.themoviedbupcoming.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.fragment_search.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope
import s.com.themoviedbupcoming.BaseFragment
import s.com.themoviedbupcoming.R
import s.com.themoviedbupcoming.di.KOIN_KEY_SCOPE_SEARCH_FRAGMENT
import s.com.themoviedbupcoming.domain.model.Genre
import s.com.themoviedbupcoming.presentation.SEARCH_SCREEN
import s.com.themoviedbupcoming.presentation.search.SearchFragmentContract
import s.com.themoviedbupcoming.utils.gone
import s.com.themoviedbupcoming.utils.visible

class SearchFragment : BaseFragment(), SearchFragmentContract.View {

    private lateinit var scopeKoin: Scope

    override val contextName = SEARCH_SCREEN

    private val presenter: SearchFragmentContract.Presenter by inject { parametersOf(this.activity as SearchActivity) }
    private val genres= mutableListOf<Genre>()
    private val years =  mutableListOf<String>()
//    private lateinit var showsListAdapter: GenreAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scopeKoin = getKoin().getOrCreateScope(KOIN_KEY_SCOPE_SEARCH_FRAGMENT)
        presenter.attachView(this)
        if (isNetworkAvailable()) presenter.loadGenres()

        for(year in 2020 downTo 1920){
            years.add(year.toString())
        }

        btGenres.onClick { showGenresDialog() }
        btYears.onClick { showYearsDialog() }
        btSearch.onClick { presenter.startSearch(etTitle.text.toString(), genres, cbAdult.isChecked) }
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

    override fun loadGenresList(genres: List<Genre>) {
        if (this@SearchFragment.isAdded) {
            this.genres.addAll(genres)
//            showsListAdapter.notifyDataSetChanged()
        }
    }

    private fun showGenresDialog() {
        context?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(it.getString(R.string.select_genres))

            val checkedItems = genres.map { it.isSelected }
            builder.setMultiChoiceItems(
                genres.map { it.name ?: "" }.toTypedArray(),
                genres.map { it.isSelected }.toBooleanArray()
            ) { dialog, which, isChecked ->
                genres[which].isSelected = isChecked
            }

            builder.setPositiveButton(it.getString(R.string.ok)) { dialog, which ->

            }
            builder.setNegativeButton(it.getString(R.string.cancel)) { dialog, which ->
                for(index in 0 until checkedItems.size){
                    genres[index].isSelected = checkedItems[index]
                }
            }

            val dialog = builder.create()
            dialog.show()
        }
    }

    private fun showYearsDialog(){
        context?.let {
            // setup the alert builder
            val builder = AlertDialog.Builder(it)
            builder.setTitle(it.getString(R.string.select_year))

            val checkedItem = years.indexOf(presenter.selectedYear())
            builder.setSingleChoiceItems(years.toTypedArray(), checkedItem) { dialog, which ->
                presenter.saveYear(years[which])
            }
            builder.setPositiveButton(it.getString(R.string.ok)) { dialog, which ->

            }
            builder.setNegativeButton(it.getString(R.string.cancel)){ dialog, which ->
                presenter.saveYear(years[checkedItem])
            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    override fun showErrorMessage(message: String) {
        if (this@SearchFragment.isAdded) {
            context?.let { Toast.makeText(context, message, Toast.LENGTH_SHORT).show() }
        }
    }

    override fun showProgressDialog() {
        progressSearch.visible()
    }

    override fun hideProgressDialog() {
        progressSearch.gone()
    }
//
//    private fun onShowClickListener(movie: MovieInList) {
//        movie.id?.let { presenter.onMovieSelected(it) }
//    }

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