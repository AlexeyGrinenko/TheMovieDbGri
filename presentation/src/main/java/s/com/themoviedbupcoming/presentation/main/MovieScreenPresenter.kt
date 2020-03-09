package s.com.themoviedbupcoming.presentation.main

import s.com.themoviedbupcoming.presentation.router.IMovieScreenRouter

class MovieScreenPresenter(
    private val movieScreenRouter: IMovieScreenRouter
) : MovieScreenContract.Presenter {
    private var view: MovieScreenContract.MovieScreenView? = null

    override fun attachView(view: MovieScreenContract.MovieScreenView) {
        super.attachView(view)
        this@MovieScreenPresenter.view = view
    }

    override fun loadMovieDetailFragment(id: Int) {
        movieScreenRouter.openMovieDetailFragment(id)
    }

    override fun detachView() {
        view = null
    }

    override fun onViewHidden() {
    }


}