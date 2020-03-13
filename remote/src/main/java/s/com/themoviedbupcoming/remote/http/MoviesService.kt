package s.com.themoviedbupcoming.remote.http

import io.reactivex.Single
import retrofit2.http.*
import s.com.themoviedbupcoming.remote.model.GenreResponse
import s.com.themoviedbupcoming.remote.model.MovieGson
import s.com.themoviedbupcoming.remote.model.MoviesListHttpResponse

interface MoviesService {

    @GET("discover/movie")
    fun downloadShows(
        @Query("page") currentPage: Int,
        @Query("year") year: Int?,
        @Query("with_genres") genresId: String?,
        @Query("with_people") actors: String?,
        @Query("include_adult") includeAdult: Boolean

        ): Single<MoviesListHttpResponse>

    @GET("movie/{id}")
    fun getMovie(@Path("id") movieId: Int): Single<MovieGson>

    @GET("genre/movie/list")
    fun getGenres(): Single<GenreResponse>


//    https://api.themoviedb.org/3/search/movie?api_key=8123971648da4ff0ca36b9fed8f72ebd&query=Scarlett
//    "known_for_department": "Directing",
//    "known_for_department": "Acting",
//    https://api.themoviedb.org/3/person/popular?api_key=8123971648da4ff0ca36b9fed8f72ebd&language=en-US&page=1

}