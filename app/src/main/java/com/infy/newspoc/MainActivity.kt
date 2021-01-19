package com.infy.newspoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.infy.infypoc.adapter.CountryAdapter
import com.infy.infypoc.model.CountryDetails
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var countryDetailsList: ArrayList<CountryDetails> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null)
            countryDetailsList = savedInstanceState.getParcelableArrayList<CountryDetails>("COUNTRY_DETAILS") as ArrayList<CountryDetails>
        recyclerView = findViewById(R.id.rcvDummy)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        loadJSONItems()
        recyclerView.adapter = CountryAdapter(countryDetailsList, this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("COUNTRY_DETAILS", countryDetailsList)
    }

    private fun loadJSONItems() {
        if (countryDetailsList.isEmpty()) {
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
                    val countryDetails = CountryDetails(title, description, imageRef)
                    countryDetailsList.add(countryDetails)
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
            inputStream = resources.openRawResource(R.raw.countrydetails)
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