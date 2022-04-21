package uz.gita.recentnews.presentation

import androidx.lifecycle.LiveData
import uz.gita.recentnews.data.model.responce.AllNewsResponse
import uz.gita.recentnews.data.source.local.room.entity.NewsEntity

interface NewsViewModel {

    val errorLivedata: LiveData<String>
    val progressLivedata: LiveData<Boolean>
    val loadNewsLivedata: LiveData<List<NewsEntity>>



    fun allNews(query: String)

}