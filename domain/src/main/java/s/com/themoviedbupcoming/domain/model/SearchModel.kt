package s.com.themoviedbupcoming.domain.model

import java.io.Serializable

class SearchModel(
    var title: String,
    var genresId: String,
    var year: String,
    var actors: String,
    var includeAdults: Boolean
) : Serializable {
}