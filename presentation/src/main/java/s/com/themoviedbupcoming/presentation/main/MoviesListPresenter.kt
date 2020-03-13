package s.com.themoviedbupcoming.presentation.main

import s.com.themoviedbupcoming.domain.model.MovieInList
import s.com.themoviedbupcoming.domain.model.SearchModel
import s.com.themoviedbupcoming.domain.usecase.DisposableSimpleSingleObserver
import s.com.themoviedbupcoming.presentation.router.IMainScreenRouter
import s.com.themoviedbupcoming.domain.usecase.movie.MoviesUseCase

class MoviesListPresenter(private val moviesUseCase: MoviesUseCase, private val mainScreenRouter: IMainScreenRouter) :
    MoviesListContract.MoviesListPresenter {
    private var view: MoviesListContract.MoviesListView? = null
    private lateinit var  searchModel:SearchModel

    override fun attachView(view: MoviesListContract.MoviesListView) {
        this@MoviesListPresenter.view = view
    }

    override fun refreshShowsList() {
        loadMovies(true)
    }

    override fun loadMovies(isRefreshing: Boolean) {
        if(this::searchModel.isInitialized) {
            view?.showProgressDialog()
            moviesUseCase.execute(object : DisposableSimpleSingleObserver<List<MovieInList>>() {
                override fun onSuccess(movies: List<MovieInList>) {
                  val filtered = if(searchModel.title.isNotEmpty())  movies.filter { movie -> movie.title!!.contains(searchModel.title) }
                    else movies
                    view?.hideProgressDialog()
                    view?.loadShowsList(filtered, isRefreshing)
                    if(view!!.moviesLoaded() <20) (loadMovies(isRefreshing))
                }

                override fun onError(e: Throwable) {
                    view?.hideProgressDialog()
                    view?.showErrorMessage(e.localizedMessage)
                }
            }, MoviesUseCase.Params(isRefreshing, searchModel))
        }
    }

    override fun setSearchModel(searchModel: SearchModel) {
        this@MoviesListPresenter.searchModel =searchModel
    }

    override fun onMovieSelected(id: Int) {
        mainScreenRouter.openMovieDetailActivity(id)
    }


    override fun detachView() {
        view = null
        moviesUseCase.dispose()
    }

    override fun onViewHidden() {
        moviesUseCase.clear()
    }

}