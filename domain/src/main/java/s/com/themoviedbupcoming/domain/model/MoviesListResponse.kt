package s.com.themoviedbupcoming.domain.model

data class MoviesListResponse (
    val result: List<MovieInList>,
    val page: Int,
    val totalResults: Int,
     val totalPages: Int
)