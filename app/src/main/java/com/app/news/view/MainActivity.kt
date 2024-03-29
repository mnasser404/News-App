package com.app.news.view

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.news.App
import com.app.news.R
import com.app.news.di.components.ActivitySubComponent
import com.app.news.di.modules.ActivityModule
import com.app.news.model.entities.RemoteResponse
import com.app.news.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var newsList: RecyclerView
    private lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var newsAdapter: NewsAdapter
    @Inject
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        App.getAppComponent().plus(ActivityModule()).inject(this)
        newsList = mainList
        newsList.layoutManager = layoutManager
        newsList.adapter = newsAdapter
        observeLiveData()
    }

    private fun setupToolbar(){
        toolbar = mainToolbar
        toolbar.setTitle(R.string.toolbar_title)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu);
        return true
    }


    private fun observeLiveData() {
        val observer = Observer<List<RemoteResponse.Article>> {
            newsAdapter.provideAdapterList(it)
        }
        mainViewModel.loadViewData().observe(this, observer)
    }


}
