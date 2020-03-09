package s.com.themoviedbupcoming.remote.model

import com.google.gson.annotations.SerializedName

data class MovieInListGson(
    @SerializedName("popularity")
    var popularity: Double?,
    @SerializedName("vote_count")
    var voteCount: Int?,
    @SerializedName("video")
    var video: Boolean?,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("adult")
    var adult: Boolean?,
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("original_language")
    var originalLanguage: String?,
    @SerializedName("original_title")
    var originalTitle: String?,
    @SerializedName("overview")
    var overview: String?,
    @SerializedName("release_date")
    var releaseDate: String?,
    @SerializedName("genre_ids")
    var genreIds: List<Int> = ArrayList(),
    @SerializedName("title")
    var title: String?,
    @SerializedName("vote_average")
    var voteAverage: Double?
)