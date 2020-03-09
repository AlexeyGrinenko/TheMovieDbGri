package s.com.themoviedbupcoming.data.repository

import io.reactivex.Single
import s.com.themoviedbupcoming.data.repository.source.main.IMoviesDataSource
import s.com.themoviedbupcoming.domain.model.MovieInList
import s.com.themoviedbupcoming.domain.usecase.movie.IMoviesRepository

class MoviesRepository(val moviesDataSource: IMoviesDataSource) : IMoviesRepository {
    private var currentPage = 0
    override fun loadShows(isRefreshing: Boolean): Single<List<MovieInList>> {
        if (isRefreshing) {
            currentPage = 0
        }
        currentPage++
        return moviesDataSource.loadMovies(currentPage).map { it.result }
    }
}