package s.com.themoviedbupcoming.presentation.router

import s.com.themoviedbupcoming.domain.model.SearchModel

interface IMainScreenRouter {
    fun openMovieListFragment(searchModel: SearchModel)
    fun openMovieDetailActivity(id: Int)
}