package fi.tuni.crudappandroid

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.*
import java.io.IOException
import kotlin.concurrent.thread

@JsonIgnoreProperties(ignoreUnknown = true)
data class Person(var firstName: String? = null, val lastName: String? = null) {
    override fun toString(): String {
        return "name: $firstName $lastName"
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class PersonJsonObject(var users: MutableList<Person>? = null)


class MainActivity : AppCompatActivity(), View.OnClickListener {
    /*
    // Button, TextView and EditTexts for when i implement
    // the search by name option.

    lateinit var searchText : TextView
    lateinit var firstNameEditText : EditText
    lateinit var lastNameEditText: EditText
    lateinit var searchByNameButton : Button
     */

    private lateinit var searchAllButton : Button
    private lateinit var lv : ListView
    private lateinit var adapter: ArrayAdapter<Person>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Kaisa's Android Project"

        this.searchAllButton = findViewById(R.id.searchAllButton)
        searchAllButton.setOnClickListener(this)

        this.lv = findViewById(R.id.listView)
        this.adapter = ArrayAdapter<Person>(this,
            R.layout.item, R.id.textViewInsideList, mutableListOf<Person>())
        lv.adapter = adapter
    }

    override fun onClick(view: View) {
        val client = OkHttpClient()

        thread {
            run(client) { json ->
                if(json != null) {
                    val mp = ObjectMapper()
                    val myObject : PersonJsonObject =
                        mp.readValue(json, PersonJsonObject::class.java)
                    val persons : MutableList<Person>? = myObject.users

                    runOnUiThread() {
                        persons?.forEach { adapter.add(it) }
                    }
                }
            }
        }
    }

    private fun run(client: OkHttpClient, callback: (String?) -> Unit) {
        val request = Request.Builder()
            .url("https://dummyjson.com/users")
            .build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if(!response.isSuccessful) throw IOException("Error: $response")

                    for ((firstName, lastName) in response.headers) {
                        println("$firstName $lastName")
                    }

                    callback(response.body!!.string())
                }
            }
        })

    }
}

