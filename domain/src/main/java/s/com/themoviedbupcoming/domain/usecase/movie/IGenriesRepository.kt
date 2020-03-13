package s.com.themoviedbupcoming.domain.usecase.movie

import io.reactivex.Single
import s.com.themoviedbupcoming.domain.model.Genre
import s.com.themoviedbupcoming.domain.model.MovieInList

interface IGenriesRepository {
    fun loadGenries(): Single<List<Genre>>
}