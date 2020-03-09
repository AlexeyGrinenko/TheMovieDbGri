package s.com.themoviedbupcoming.remote.model

import com.google.gson.annotations.SerializedName

data class ProductionCountrieGson(
    @SerializedName("iso_3166_1")
    var nameIso: String,
    @SerializedName("name")
    var name: String?
)