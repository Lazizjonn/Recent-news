package uz.gita.recentnews.domain.repository.impl

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.mynewsapp.utils.isAvailableNetwork
import uz.gita.recentnews.data.model.responce.AllNewsResponse
import uz.gita.recentnews.data.source.remote.Api
import uz.gita.recentnews.domain.repository.NewsRepository
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor(
    private val api: Api,
    @ApplicationContext private val context: Context
) : NewsRepository {

    override fun getAllNewsFromNet(query: String) = flow<Result<AllNewsResponse>> {


        if (!context.isAvailableNetwork()) {
            emit(Result.failure(Exception("Internet failure")))
            return@flow
        }

        val response = api.getAllNews(query)

        if (response.isSuccessful) {
            response.body()?.let { emit(Result.success(it)) }
        } else {
            emit(Result.failure(Exception("Response is unsuccessful")))
        }

    }.catch { emit(Result.failure(it)) }
        .flowOn(Dispatchers.IO)

    override fun getAllNewsFromRoom(query: String) {


    }
}