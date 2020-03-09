package s.com.themoviedbupcoming.remote.model

import com.google.gson.annotations.SerializedName

data class GenreGson(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String?
)