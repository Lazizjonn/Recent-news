package uz.gita.recentnews.domain.repository

import androidx.lifecycle.LiveData
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import uz.gita.recentnews.data.model.responce.AllNewsResponse


interface NewsRepository {

    fun getAllNewsFromNet(query: String): Flow<Result<AllNewsResponse>>

    fun getAllNewsFromRoom(query: String)
}