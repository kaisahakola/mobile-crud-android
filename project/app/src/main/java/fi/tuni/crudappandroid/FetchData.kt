package fi.tuni.crudappandroid

import okhttp3.*
import java.io.IOException

fun fetchData(client: OkHttpClient, callback: (String?) -> Unit) {
    val request = Request.Builder()
        .url("https://dummyjson.com/users")
        .build()

    client.newCall(request).enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            response.use {
                if(!response.isSuccessful) {
                    throw IOException("Error: $response")
                }

                for ((firstName, lastName) in response.headers) {
                    println("$firstName $lastName")
                }

                callback(response.body!!.string())
            }
        }
    })
}