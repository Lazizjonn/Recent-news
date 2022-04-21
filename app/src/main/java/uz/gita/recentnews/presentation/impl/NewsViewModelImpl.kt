package uz.gita.recentnews.presentation.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import uz.gita.recentnews.data.model.responce.AllNewsResponse
import uz.gita.recentnews.domain.repository.NewsRepository
import uz.gita.recentnews.presentation.NewsViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModelImpl @Inject constructor(private val repository: NewsRepository) : ViewModel(), NewsViewModel {

    override var errorLivedata = MutableLiveData<String>()
    override var progressLivedata = MutableLiveData<Boolean>()
    override var loadNewsLivedata = MutableLiveData<AllNewsResponse>()

    init {
        allNews("all")
    }

    override fun allNews(query: String) {


/*        viewModelScope.launch(Dispatchers.IO) {

            flow<Int> {
                emit(1)
                delay(1000)
                emit(2)
            }.flowOn(Dispatchers.Default)
                .map { it * 10 }
                .filter { it > 10 }
                .debounce(1000)
                .flowOn(Dispatchers.IO)
                .catch { }
                .onEach { }
                .launchIn(viewModelScope)
        }*/

        progressLivedata.value = true

        repository.getAllNewsFromNet(query)

            .onEach {
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

    }

}