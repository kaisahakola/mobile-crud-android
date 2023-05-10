package fi.tuni.crudappandroid

import android.widget.ArrayAdapter
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import kotlin.concurrent.thread

fun parseJson(activity: MainActivity, adapter: ArrayAdapter<Person>) {
    val client = OkHttpClient()

    thread {
        fetchData(client) { json ->
            if(json != null) {
                val mp = ObjectMapper()
                val myObject : PersonJsonObject =
                    mp.readValue(json, PersonJsonObject::class.java)
                val persons : MutableList<Person>? = myObject.users

                activity.runOnUiThread() {
                    persons?.forEach { adapter.add(it) }

                }
            }
        }
    }
}