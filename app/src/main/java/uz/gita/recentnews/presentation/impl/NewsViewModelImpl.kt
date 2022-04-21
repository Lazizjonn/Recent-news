package uz.gita.recentnews.presentation.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okio.IOException
import retrofit2.HttpException
import uz.gita.mynewsapp.utils.isConnected
import uz.gita.recentnews.data.source.local.room.entity.NewsEntity
import uz.gita.recentnews.domain.repository.NewsRepository
import uz.gita.recentnews.presentation.NewsViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModelImpl @Inject constructor(
    private val repository: NewsRepository
) : ViewModel(), NewsViewModel {

    override var errorLivedata = MutableLiveData<String>()
    override var progressLivedata = MutableLiveData<Boolean>()
    override var loadNewsLivedata = MutableLiveData<List<NewsEntity>>()

    init {
        allNews("all")
    }

    override fun allNews(query: String) {
        progressLivedata.value = true

        if (isConnected()) {
            repository.getAllNewsFromNet(query).onEach {
                progressLivedata.value = false
                it.onSuccess {
                    loadNewsLivedata.value = it
                }

                it.onFailure {
                    errorLivedata.value = when (it) {

                        is IOException -> "No internet connection"
                        is HttpException -> it.response()?.message()
                        else -> it.message
                    }
                }
            }.launchIn(viewModelScope)

        } else {
           loadNewsLivedata.value =  repository.getAllNewsFromRoom(query)
            progressLivedata.value = false
        }
    }
}