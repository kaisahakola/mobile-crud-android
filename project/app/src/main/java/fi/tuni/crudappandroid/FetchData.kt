package fi.tuni.crudappandroid

import okhttp3.*
import java.io.IOException

/**
 * This function implements the fetching of all data from the backend.
 *
 * @param client The OkHttpClient instance used to make the network request.
 * @param callback A callback function that receives the fetched data as a string.
 */
fun fetchData(client: OkHttpClient, callback: (String?) -> Unit) {
    val request = Request.Builder()
        .url("https://dummyjson.com/users")
        .build()

    /**
     * Making the network request using the OkHttpClient instance.
     *
     * @param object A callback function that receives the fetched
     * data as a string.
     */
    client.newCall(request).enqueue(object: Callback {
        /**
         * Called if the request is not successful.
         *
         * @throws IOException Thrown if an error occurs during the request.
         */
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        /**
         * Called when a response is received from the server after making the
         * network request.
         *
         * @param call The original network request tha was made.
         * @param response The response that is received from the request.
         * @throws IOException Thrown if the response is not successful.
         */
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