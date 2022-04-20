package uz.gita.recentnews.presentation.impl

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.gita.recentnews.data.model.responce.AllNewsResponse
import uz.gita.recentnews.domain.repository.NewsRepository
import uz.gita.recentnews.presentation.NewsViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModelImpl @Inject constructor(val newsRepository: NewsRepository) : ViewModel(), NewsViewModel {

    override var noNetConnectionLivedata = MediatorLiveData<Unit>()
    override var errorLivedata = MutableLiveData<String>()
    override var progressLivedata = MutableLiveData<Boolean>()
    override var loadNewsLivedata = MutableLiveData<AllNewsResponse>()


    init {
        allNews("all")
    }

    override fun allNews(query: String) {

        viewModelScope.launch {

            noNetConnectionLivedata.addSource(newsRepository.getAllNewsFromNet(query)){
                loadNewsLivedata.postValue(it)
            }

            noNetConnectionLivedata.addSource(newsRepository.errorLivedata){
                errorLivedata.postValue(it)
            }
            noNetConnectionLivedata.addSource(newsRepository.progressLivedata){
                progressLivedata.postValue(it)
            }

            noNetConnectionLivedata.addSource(newsRepository.noNetConnectionLivedata ){
                noNetConnectionLivedata.postValue(it)
            }
        }

    }

}