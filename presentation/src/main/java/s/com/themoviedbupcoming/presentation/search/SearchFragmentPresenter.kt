package s.com.themoviedbupcoming.presentation.search

import s.com.themoviedbupcoming.domain.model.Genre
import s.com.themoviedbupcoming.domain.model.SearchModel
import s.com.themoviedbupcoming.domain.usecase.DisposableSimpleSingleObserver
import s.com.themoviedbupcoming.domain.usecase.movie.GenresUseCase
import s.com.themoviedbupcoming.presentation.router.ISearchScreenRouter

class SearchFragmentPresenter(
    private val genresUseCase: GenresUseCase,
    private val searchScreenRouter: ISearchScreenRouter
) :
    SearchFragmentContract.Presenter {
    private var view: SearchFragmentContract.View? = null
    private var year = "2020"

    override fun attachView(view: SearchFragmentContract.View) {
        this@SearchFragmentPresenter.view = view
    }

    override fun loadGenres() {
        view?.showProgressDialog()
        genresUseCase.execute(object : DisposableSimpleSingleObserver<List<Genre>>() {
            override fun onSuccess(movies: List<Genre>) {
                view?.hideProgressDialog()
                view?.loadGenresList(movies)
            }

            override fun onError(e: Throwable) {
                view?.hideProgressDialog()
                view?.showErrorMessage(e.localizedMessage)
            }
        }, GenresUseCase.Params())

    }

    override fun saveYear(year: String) {
        this.year = year
    }

    override fun selectedYear(): String {
        return this.year
    }


    override fun detachView() {
        view = null
        genresUseCase.dispose()
    }

    override fun onViewHidden() {
        genresUseCase.clear()
    }

    override fun startSearch(title: String, genres: MutableList<Genre>, includeAdult: Boolean) {
        val genresId = genres.filter { it.isSelected }.map{it.id}.joinToString(",")
      val searchModel =  SearchModel(title,genresId,year,"",includeAdult)
        searchScreenRouter.openMainActivity(searchModel)
    }

}