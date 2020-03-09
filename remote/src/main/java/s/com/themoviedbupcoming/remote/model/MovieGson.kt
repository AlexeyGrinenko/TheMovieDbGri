package s.com.themoviedbupcoming.remote.model

import com.google.gson.annotations.SerializedName

data class MovieGson(
    @SerializedName("adult")
    var adult: Boolean?,
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("belongs_to_collection")
    var belongsToCollection: BelongsToCollectionGson?,
    @SerializedName("budget")
    var budget: Int?,
    @SerializedName("genres")
    var genres: List<GenreGson> = ArrayList(),
    @SerializedName("homepage")
    var homepage: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("imdb_id")
    var imdbId: String?,
    @SerializedName("original_language")
    var originalLanguage: String?,
    @SerializedName("original_title")
    var originalTitle: String?,
    @SerializedName("overview")
    var overview: String?,
    @SerializedName("popularity")
    var popularity: Double?,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("production_companies")
    var productionCompanies: List<ProductionCompanieGson> = ArrayList(),
    @SerializedName("production_countries")
    var productionCountries: List<ProductionCountrieGson> = ArrayList(),
    @SerializedName("release_date")
    var releaseDate: String?,
    @SerializedName("revenue")
    var revenue: Int?,
    @SerializedName("runtime")
    var runtime: Int?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("tagline")
    var tagline: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("video")
    var video: Boolean?,
    @SerializedName("vote_count")
    var voteCount: Int?,
    @SerializedName("vote_average")
    var voteAverage: Double?
)