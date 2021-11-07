package com.example.rssfeed


import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.net.HttpURLConnection
import java.net.URL
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    lateinit var MainRV: RecyclerView
    var RecentQuestions = mutableListOf<Questions>()
    lateinit var adapter: RcyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainRV = findViewById<RecyclerView>(R.id.MainRV)
        adapter = RcyclerViewAdapter(RecentQuestions)
        MainRV.layoutManager = LinearLayoutManager(this)
        FetchTopSongs().execute()
    }

    private inner class FetchTopSongs : AsyncTask<Void, Void, MutableList<Questions>>() {
        val parser = XmlParserHandler()
        override fun doInBackground(vararg params: Void?): MutableList<Questions> {
            val url = URL("https://stackoverflow.com/feeds")
            val urlConnection = url.openConnection() as HttpURLConnection
            RecentQuestions =
                urlConnection.getInputStream()?.let {
                    parser.parse(it)
                }
                        as MutableList<Questions>
            return RecentQuestions
        }

        override fun onPostExecute(result: MutableList<Questions>?) {
            super.onPostExecute(result)
            adapter = RcyclerViewAdapter(RecentQuestions)
            MainRV.adapter = adapter
            MainRV.visibility = View.VISIBLE
        }

    }
}