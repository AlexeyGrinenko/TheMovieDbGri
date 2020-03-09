package s.com.themoviedbupcoming.remote.ds

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import s.com.themoviedbupcoming.data.repository.source.main.IMovieDetailDataSource
import s.com.themoviedbupcoming.domain.model.Movie
import s.com.themoviedbupcoming.remote.http.MoviesService
import s.com.themoviedbupcoming.remote.utils.MovieGsontoDomain

class MovieDetailDataSource(private val moviesService: MoviesService) : IMovieDetailDataSource {
    override fun loadMovie(movieId: Int): Single<Movie> {
        return moviesService.getMovie(movieId)
            .map {  MovieGsontoDomain(it) }
            .observeOn(AndroidSchedulers.mainThread())
    }


}