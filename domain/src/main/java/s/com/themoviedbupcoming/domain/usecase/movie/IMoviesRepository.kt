package s.com.themoviedbupcoming.domain.usecase.movie

import io.reactivex.Single
import s.com.themoviedbupcoming.domain.model.MovieInList

interface IMoviesRepository {
    fun loadShows(isRefreshing: Boolean): Single<List<MovieInList>>
}