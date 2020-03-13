package s.com.themoviedbupcoming.domain.model


data class Genre(
    var id: Int,
    var name: String?,
    var isSelected:Boolean = false
)