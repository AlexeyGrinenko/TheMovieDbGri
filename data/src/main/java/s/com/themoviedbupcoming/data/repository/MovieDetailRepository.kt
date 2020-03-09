package s.com.themoviedbupcoming.data.repository
//
import s.com.themoviedbupcoming.data.repository.source.main.IMovieDetailDataSource
import io.reactivex.Single
import s.com.themoviedbupcoming.domain.model.Movie
import s.com.themoviedbupcoming.domain.usecase.movie.IMovieDetailRepository

class MovieDetailRepository(val movieDetailDataSource: IMovieDetailDataSource) :
    IMovieDetailRepository {

    override fun loadMovie(movieId: Int): Single<Movie> {
        return movieDetailDataSource.loadMovie(movieId)
    }

}