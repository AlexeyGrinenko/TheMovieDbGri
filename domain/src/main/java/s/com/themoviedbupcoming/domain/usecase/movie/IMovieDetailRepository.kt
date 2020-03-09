package s.com.themoviedbupcoming.domain.usecase.movie

import io.reactivex.Single
import s.com.themoviedbupcoming.domain.model.Movie

interface IMovieDetailRepository {
    fun loadMovie(showId: Int): Single<Movie>
}