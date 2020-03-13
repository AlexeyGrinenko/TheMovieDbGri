package s.com.themoviedbupcoming.domain.usecase.movie

import io.reactivex.Single
import s.com.themoviedbupcoming.domain.model.MovieInList
import s.com.themoviedbupcoming.domain.model.SearchModel

interface IMoviesRepository {
    fun loadShows(isRefreshing: Boolean, searchModel: SearchModel): Single<List<MovieInList>>
}