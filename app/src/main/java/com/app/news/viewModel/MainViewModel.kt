package com.app.news.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.news.model.Repository
import com.app.news.model.entities.RemoteResponse

class MainViewModel : ViewModel() {

    private var repository = Repository()


    fun loadViewData(): LiveData<List<RemoteResponse.Article>> {
        return repository.getNewsData()
    }

}