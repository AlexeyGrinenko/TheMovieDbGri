package s.com.themoviedbupcoming.remote.ds

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import s.com.themoviedbupcoming.data.repository.source.main.IGenresDataSource
import s.com.themoviedbupcoming.domain.model.GenresListResponse
import s.com.themoviedbupcoming.remote.http.MoviesService
import s.com.themoviedbupcoming.remote.utils.GenreListToDomainGenresResponse

class GenresDataSource(private val moviesService: MoviesService) : IGenresDataSource {
    override fun loadGenres(): Single<GenresListResponse> {
        return moviesService.getGenres()
            .map { GenreListToDomainGenresResponse(it) }
            .observeOn(AndroidSchedulers.mainThread())
    }
}