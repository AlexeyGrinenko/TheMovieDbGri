package s.com.themoviedbupcoming.remote.ds

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import s.com.themoviedbupcoming.data.repository.source.main.IMoviesDataSource
import s.com.themoviedbupcoming.domain.model.MoviesListResponse
import s.com.themoviedbupcoming.domain.model.SearchModel
import s.com.themoviedbupcoming.remote.http.MoviesService
import s.com.themoviedbupcoming.remote.utils.MoviesListtoDomainMoviesResponse
import java.text.SimpleDateFormat
import java.util.*

class MoviesDataSource(private val moviesService: MoviesService) : IMoviesDataSource {
    override fun loadMovies(currentPage: Int, searchModel: SearchModel): Single<MoviesListResponse> {
        return moviesService.downloadShows(currentPage,
            if(searchModel.year.isEmpty())null else searchModel.year.toInt(),
            if(searchModel.genresId.isEmpty())null else searchModel.genresId,
            if(searchModel.actors.isEmpty())null else searchModel.actors,
            searchModel.includeAdults
            )
            .map { MoviesListtoDomainMoviesResponse(it) }
            .observeOn(AndroidSchedulers.mainThread())
    }
}