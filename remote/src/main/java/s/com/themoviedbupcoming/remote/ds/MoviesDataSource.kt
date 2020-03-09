package s.com.themoviedbupcoming.remote.ds

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import s.com.themoviedbupcoming.data.repository.source.main.IMoviesDataSource
import s.com.themoviedbupcoming.domain.model.MoviesListResponse
import s.com.themoviedbupcoming.remote.http.MoviesService
import s.com.themoviedbupcoming.remote.utils.MoviesListtoDomainMoviesResponse
import java.text.SimpleDateFormat
import java.util.*

class MoviesDataSource(private val moviesService: MoviesService) : IMoviesDataSource {
    private val formatter = SimpleDateFormat("yyyy-MM-dd")
    override fun loadMovies(currentPage: Int): Single<MoviesListResponse> {
        return moviesService.downloadShows(currentPage, formatter.format(Date()))
            .map { MoviesListtoDomainMoviesResponse(it) }
            .observeOn(AndroidSchedulers.mainThread())
    }
}