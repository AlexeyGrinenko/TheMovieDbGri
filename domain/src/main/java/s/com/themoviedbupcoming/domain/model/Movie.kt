package s.com.themoviedbupcoming.domain.model

data class Movie(
    var adult: Boolean?,
    var backdropPath: String?,
    var belongsToCollection: String?,
    var budget: Int?,
    var genres: List<Genre> = ArrayList(),
    var homepage: String?,
    var id: Int?,
    var imdbId: String?,
    var originalLanguage: String?,
    var originalTitle: String?,
    var overview: String?,
    var popularity: Double?,
    var posterPath: String?,
    var productionCompanies: String,
    var productionCountries: String,
    var releaseDate: String?,
    var revenue: Int?,
    var runtime: Int?,
    var status: String?,
    var tagline: String?,
    var title: String?,
    var video: Boolean?,
    var voteCount: Int?,
    var voteAverage: Double?
)