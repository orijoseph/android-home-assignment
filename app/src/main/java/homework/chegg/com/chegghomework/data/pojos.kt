package homework.chegg.com.chegghomework.data

import com.google.gson.annotations.SerializedName

data class ResponseA(@SerializedName("stories") var stories: List<Story>? = null)

data class ResponseB(@SerializedName("metadata") var metadata: MetaData? = null)

data class MetaData(@SerializedName("innerdata") var articles: List<Article>? = null)

data class Article(
        @SerializedName("aticleId") var aticleId: Int? = null,
        @SerializedName("picture") var picture: String? = null
) : IDetailsTiDisplay {
    override fun convertToDisplayObject(): DetailsTiDisplay {
        return DetailsTiDisplay(aticleId.toString(), "", picture)
    }
}

data class Story(
        @SerializedName("title") var title: String? = null,
        @SerializedName("subtitle") var subtitle: String? = null,
        @SerializedName("imageUrl") var imageUrl: String? = null
) : IDetailsTiDisplay {
    override fun convertToDisplayObject(): DetailsTiDisplay {
        return DetailsTiDisplay(title, subtitle, imageUrl)
    }
}

data class Article2(
        @SerializedName("topLine") var topLine: String? = null,
        @SerializedName("subLine1") var subLine1: String? = null,
        @SerializedName("subline2") var subline2: String? = null,
        @SerializedName("image") var image: String? = null
) : IDetailsTiDisplay {
    override fun convertToDisplayObject(): DetailsTiDisplay {
        return DetailsTiDisplay(topLine, subLine1, image)
    }
}

data class DetailsTiDisplay(
        var title: String? = null,
        var subtitle: String? = null,
        var imageUrl: String? = null
)

data class CahcedResponse(val lastEntery: Long = -1,
                          val cachedData: List<IDetailsTiDisplay>? = mutableListOf())