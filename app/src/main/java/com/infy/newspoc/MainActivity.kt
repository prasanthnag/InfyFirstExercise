package com.infy.newspoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.infy.newspoc.adapter.NewsAdapter
import com.infy.newspoc.model.NewsDetails
import com.infy.newspoc.utils.Constants
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var newsDetailsList: ArrayList<NewsDetails> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null)
            newsDetailsList = savedInstanceState.getParcelableArrayList<NewsDetails>(Constants.KEY_NEWS_DETAILS) as ArrayList<NewsDetails>
        recyclerView = findViewById(R.id.rcvDummy)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        loadJSONItems()
        recyclerView.adapter = NewsAdapter(newsDetailsList, this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(Constants.KEY_NEWS_DETAILS, newsDetailsList)
    }

    private fun loadJSONItems() {
        if (newsDetailsList.isEmpty()) {
            try {
                val jsonObject = JSONObject(readJSON())
                val jsonArray = jsonObject.getJSONArray("rows")
                for (i in 0 until jsonArray.length()) {
                    val itemObj = jsonArray.getJSONObject(i)
                    val title = if (!itemObj.isNull("title")) itemObj.getString("title") else null
                    val description =
                            if (!itemObj.isNull("description")) itemObj.getString("description") else null
                    val imageRef =
                            if (!itemObj.isNull("imageHref")) itemObj.getString("imageHref") else null
                    val countryDetails = NewsDetails(title, description, imageRef)
                    newsDetailsList.add(countryDetails)
                }
            } catch (e: JSONException) {
                Log.d("", "loadJSONItems: ", e)
            }
        }

    }

    private fun readJSON(): String {
        var jsonString: String? = null
        try {
            val inputStream: InputStream?
            inputStream = resources.openRawResource(R.raw.newsdetails)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            jsonString = String(buffer, Charset.defaultCharset())
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return jsonString ?: ""
    }

}