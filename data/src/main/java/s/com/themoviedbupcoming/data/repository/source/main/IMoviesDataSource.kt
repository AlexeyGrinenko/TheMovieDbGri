package s.com.themoviedbupcoming.data.repository.source.main

import io.reactivex.Single
import s.com.themoviedbupcoming.domain.model.MoviesListResponse
import s.com.themoviedbupcoming.domain.model.SearchModel

interface IMoviesDataSource {
    fun loadMovies(currentPage: Int, searchModel: SearchModel): Single<MoviesListResponse>

}