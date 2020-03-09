package s.com.themoviedbupcoming.remote.http

import io.reactivex.Single
import retrofit2.http.*
import s.com.themoviedbupcoming.remote.model.MovieGson
import s.com.themoviedbupcoming.remote.model.MoviesListHttpResponse

interface MoviesService {

    @GET("discover/movie")
    fun downloadShows(
        @Query("page") currentPage: Int,
        @Query("primary_release_date.gte") releaseDate: String
    ): Single<MoviesListHttpResponse>

    @GET("movie/{id}")
    fun getMovie(@Path("id") movieId: Int): Single<MovieGson>


}