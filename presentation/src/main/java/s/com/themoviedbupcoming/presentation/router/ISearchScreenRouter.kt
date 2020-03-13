package s.com.themoviedbupcoming.presentation.router

import s.com.themoviedbupcoming.domain.model.SearchModel

interface ISearchScreenRouter {
    fun openSearchFragment()
    fun openMainActivity(searchModel: SearchModel)
}