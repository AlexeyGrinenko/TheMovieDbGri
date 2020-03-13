package s.com.themoviedbupcoming.remote.utils

import s.com.themoviedbupcoming.domain.model.*
import s.com.themoviedbupcoming.remote.model.GenreResponse
import s.com.themoviedbupcoming.remote.model.MovieGson
import s.com.themoviedbupcoming.remote.model.MovieInListGson
import s.com.themoviedbupcoming.remote.model.MoviesListHttpResponse

fun MoviesListtoDomainMoviesResponse(response: MoviesListHttpResponse) =
    MoviesListResponse(
        convertMoviesListToDomainmoviesList(response.result),
        response.page,
        response.totalResults,
        response.totalPages
    )

private fun convertMoviesListToDomainmoviesList(shows: List<MovieInListGson>): List<MovieInList> {
    return shows.map {
        MovieInList(
            it.popularity,
            it.voteCount,
            it.video,
            it.posterPath,
            it.id,
            it.adult,
            it.backdropPath,
            it.originalLanguage,
            it.originalTitle,
            it.overview,
            it.releaseDate,
            it.genreIds,
            it.title,
            it.voteAverage
        )
    }
}

fun MovieGsontoDomain(movieGson: MovieGson) = Movie(
    movieGson.adult,
    movieGson.backdropPath,
    movieGson.belongsToCollection?.name,
    movieGson.budget,
    movieGson.genres.map { Genre(it.id, it.name) },
    movieGson.homepage,
    movieGson.id,
    movieGson.imdbId,
    movieGson.originalLanguage,
    movieGson.originalTitle,
    movieGson.overview,
    movieGson.popularity,
    movieGson.posterPath,
    movieGson.productionCompanies.map { it.name }.joinToString(),
    movieGson.productionCountries.map { it.name }.joinToString(),
    movieGson.releaseDate,
    movieGson.revenue,
    movieGson.runtime,
    movieGson.status,
    movieGson.tagline,
    movieGson.title,
    movieGson.video,
    movieGson.voteCount,
    movieGson.voteAverage
)

fun GenreListToDomainGenresResponse(response: GenreResponse) =
    GenresListResponse(response.genresList.map { Genre(it.id, it.name) }

    )

