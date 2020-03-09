package s.com.themoviedbupcoming.domain.model

data class MovieInList(
    var popularity: Double?,
    var voteCount: Int?,
    var video: Boolean?,
    var posterPath: String?,
    var id: Int?,
    var adult: Boolean?,
    var backdropPath: String?,
    var originalLanguage: String?,
    var originalTitle: String?,
    var overview: String?,
    var releaseDate: String?,
    var genreIds: List<Int> = ArrayList(),
    var title: String?,
    var voteAverage: Double?
)