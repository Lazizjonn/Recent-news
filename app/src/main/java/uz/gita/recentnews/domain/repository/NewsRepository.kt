package uz.gita.recentnews.domain.repository

import androidx.lifecycle.LiveData
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import uz.gita.recentnews.data.model.responce.AllNewsResponse
import uz.gita.recentnews.data.source.local.room.entity.NewsEntity


interface NewsRepository {

    fun getAllNewsFromNet(query: String): Flow<Result<List<NewsEntity>>>
    fun getAllNewsFromRoom(query: String) : List<NewsEntity>

}