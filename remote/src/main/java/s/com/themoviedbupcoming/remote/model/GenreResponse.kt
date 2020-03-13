package s.com.themoviedbupcoming.remote.model

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("genres")
    var genresList: List<GenreGson>
)