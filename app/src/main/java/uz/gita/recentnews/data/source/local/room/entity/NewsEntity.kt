package uz.gita.recentnews.data.source.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import uz.gita.recentnews.data.model.responce.ArticlesItem

@Entity(tableName = "news_entity")
data class NewsEntity(
    @PrimaryKey
    val title: String,
    val image: String,
    @SerializedName("read_more")
    val readMore: String,
    val author: String,
    val description: String,
    @SerializedName("inshorts_link")
    val inshortsLink: String,
    val timestamp: String,
    @SerializedName("category_name")
    val categoryName: String
)



