package com.app.news.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.app.news.model.Repository
import com.app.news.model.entities.RemoteResponse

class MainViewModel : AndroidViewModel {

    private var context: Context
    private var repository: Repository
    private lateinit var articles: LiveData<List<RemoteResponse.Article>>


    constructor(application: Application) : super(application) {
        context = getApplication<Application>().applicationContext
        repository = Repository()
    }

    fun loadViewData(): LiveData<List<RemoteResponse.Article>> {
        return repository.getNewsData()
    }

}