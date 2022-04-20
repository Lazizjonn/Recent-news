package uz.gita.recentnews.presentation

import androidx.lifecycle.LiveData
import uz.gita.recentnews.data.model.responce.AllNewsResponse

interface NewsViewModel {

    val noNetConnectionLivedata: LiveData<Unit>
    val errorLivedata: LiveData<String>
    val progressLivedata: LiveData<Boolean>
    val loadNewsLivedata: LiveData<AllNewsResponse>

    fun allNews(query: String)
}