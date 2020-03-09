package s.com.themoviedbupcoming.data.repository.source.main

import io.reactivex.Single
import s.com.themoviedbupcoming.domain.model.MoviesListResponse

interface IMoviesDataSource {
    fun loadMovies(currentPage: Int): Single<MoviesListResponse>

}