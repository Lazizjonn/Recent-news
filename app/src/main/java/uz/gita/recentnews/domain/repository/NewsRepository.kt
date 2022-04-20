package uz.gita.recentnews.domain.repository

import androidx.lifecycle.LiveData
import dagger.Provides
import retrofit2.Response
import uz.gita.recentnews.data.model.responce.AllNewsResponse


interface NewsRepository {

    val noNetConnectionLivedata: LiveData<Unit>
    val errorLivedata: LiveData<String>
    val progressLivedata: LiveData<Boolean>

    suspend fun getAllNewsFromNet(query: String): LiveData<AllNewsResponse>

    fun getAllNewsFromRoom(query: String)
}