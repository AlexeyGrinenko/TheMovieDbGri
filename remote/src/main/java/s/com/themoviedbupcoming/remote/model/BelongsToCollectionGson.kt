package s.com.themoviedbupcoming.remote.model

import com.google.gson.annotations.SerializedName

data class BelongsToCollectionGson(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String?,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("backdrop_path")
    var backdropPath: String?
)