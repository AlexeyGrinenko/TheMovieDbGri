package s.com.themoviedbupcoming.remote.model

import com.google.gson.annotations.SerializedName

data class MoviesListHttpResponse (
    @SerializedName("results") val result: List<MovieInListGson>,
    @SerializedName("page") val page: Int,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int
)