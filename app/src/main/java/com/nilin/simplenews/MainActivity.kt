package com.nilin.simplenews

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.google.gson.Gson
import com.nilin.simplenews.model.ApiGank
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var url = "http://gank.io/api/data/Android/10/1"

        var context: Context = this
        doAsync {
            var returnjsonstr = URL(url).readText()
            var returnjson = Gson().fromJson(returnjsonstr, ApiGank::class.java)
            Log.i("haha","haha"+returnjson.results)
            uiThread {

                recyclerview.layoutManager = GridLayoutManager(context, 1)
                recyclerview.adapter = NewsAdapter(returnjson.results)
//                recyclerview.adapter = NewsAdapter(returnjson.results) {
//                    toast("${it.title} Clicked")
//                }
            }
        }
    }
}

