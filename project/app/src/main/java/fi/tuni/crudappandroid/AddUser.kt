package fi.tuni.crudappandroid

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

/**
 * A Function that handles adding new users.
 *
 * This function makes a POST request to the server
 * with given user information.
 *
 * @param firstName The first name of the new user.
 * @param lastName The last name of the new user.
 */
fun addUser(firstName: String, lastName: String) {
    val client = OkHttpClient()
    val newUser = Person(firstName, lastName)
    val json = ObjectMapper().writeValueAsString(newUser)

    val mediaType = "application/json; charset=utf-8".toMediaType()
    val requestBody = json.toRequestBody(mediaType)

    val request = Request.Builder()
        .url("https://dummyjson.com/users/add")
        .post(requestBody)
        .build()

    val response = client.newCall(request).execute()
    if (response.isSuccessful) {
        val responseBody = response.body?.string()
        println(responseBody)
        println(response)
    } else {
        throw IOException("An error occurred")
    }
}