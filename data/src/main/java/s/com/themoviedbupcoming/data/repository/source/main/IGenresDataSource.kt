package s.com.themoviedbupcoming.data.repository.source.main

import io.reactivex.Single
import s.com.themoviedbupcoming.domain.model.GenresListResponse

interface IGenresDataSource {
    fun loadGenres(): Single<GenresListResponse>

}