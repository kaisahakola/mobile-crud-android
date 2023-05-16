package fi.tuni.crudappandroid

import android.widget.ArrayAdapter
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import kotlin.concurrent.thread

/**
 * This function implements the parsing of the json data.
 *
 * @param activity The activity where the runOnUiThread is ran.
 * @param adapter The ArrayAdapter where the Person json objects are sent.
 */
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