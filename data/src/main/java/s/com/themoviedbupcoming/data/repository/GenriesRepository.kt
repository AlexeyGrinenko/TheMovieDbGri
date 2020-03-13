package s.com.themoviedbupcoming.data.repository

import io.reactivex.Single
import s.com.themoviedbupcoming.data.repository.source.main.IGenresDataSource
import s.com.themoviedbupcoming.domain.model.Genre
import s.com.themoviedbupcoming.domain.usecase.movie.IGenriesRepository

class GenriesRepository(val genresDataSource: IGenresDataSource) : IGenriesRepository {
    override fun loadGenries(): Single<List<Genre>> {
        return genresDataSource.loadGenres().map { it.genres }

    }
}