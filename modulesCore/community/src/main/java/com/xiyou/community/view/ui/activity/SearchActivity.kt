package com.xiyou.community.view.ui.activity

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xiyou.community.R

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        if(Intent.ACTION_SEARCH == intent.action)
        {
            intent.getStringExtra(SearchManager.QUERY)?.also {
                query ->

            }
        }
    }

}