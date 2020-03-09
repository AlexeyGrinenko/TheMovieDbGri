package s.com.themoviedbupcoming.data.repository.source.main

import io.reactivex.Single
import s.com.themoviedbupcoming.domain.model.Movie

interface IMovieDetailDataSource {
    fun loadMovie(id: Int): Single<Movie>

}