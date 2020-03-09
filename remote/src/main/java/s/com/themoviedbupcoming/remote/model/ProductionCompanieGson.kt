package s.com.themoviedbupcoming.remote.model

import com.google.gson.annotations.SerializedName

data class ProductionCompanieGson(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String?,
    @SerializedName("logo_path")
    var logoPath: String?,
    @SerializedName("origin_country")
    var country: String?
)