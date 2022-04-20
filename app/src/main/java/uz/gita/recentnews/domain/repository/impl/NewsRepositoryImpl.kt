package uz.gita.recentnews.domain.repository.impl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import uz.gita.mynewsapp.utils.isConnected
import uz.gita.recentnews.data.model.responce.AllNewsResponse
import uz.gita.recentnews.data.source.remote.Api
import uz.gita.recentnews.domain.repository.NewsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(val api: Api) :
    NewsRepository {

    override val noNetConnectionLivedata = MutableLiveData<Unit>()
    override val errorLivedata = MutableLiveData<String>()
    override val progressLivedata = MutableLiveData<Boolean>()


    override suspend fun getAllNewsFromNet(query: String): LiveData<AllNewsResponse> = liveData {
        try {
            if (!isConnected()) {
                // TODO """""''
//                noNetConnectionLivedata.postValue(Unit)

            } else {
                progressLivedata.postValue(true)
                val response = api.getAllNews(query)

                if (response.isSuccessful) {
                    response.body()?.let { emit(it) }
                    progressLivedata.postValue(false)

                } else {
                    errorLivedata.postValue("Responce is unsuccessful")
                    progressLivedata.postValue(false)
                }
            }
        } catch (e: Throwable){
            errorLivedata.postValue("Error occurred: $e")
        }

    }


    override fun getAllNewsFromRoom(query: String) {


    }
}