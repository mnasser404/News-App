package com.app.news.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.news.App
import com.app.news.model.Repository
import com.app.news.model.entities.RemoteResponse
import javax.inject.Inject

class MainViewModel : ViewModel() {

    @Inject
    lateinit var repository: Repository

    init {
        App.getAppComponent().inject(this)
    }

    fun loadViewData(): LiveData<List<RemoteResponse.Article>> {
        return repository.getNewsData()
    }

}