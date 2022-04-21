package uz.gita.recentnews.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import uz.gita.recentnews.data.source.local.room.entity.NewsEntity

@Dao
interface NewsDao : BaseDao<NewsEntity> {

    @Query("SELECT * FROM news_entity WHERE categoryName = :category ")
    fun getAllNews(category: String): List<NewsEntity>

}