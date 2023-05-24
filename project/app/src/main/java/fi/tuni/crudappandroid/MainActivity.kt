package fi.tuni.crudappandroid

import android.content.Intent
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
import java.io.Serializable
import kotlin.concurrent.thread

/**
 * This is the main activity of the Android CRUD project.
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var addUserButton : Button
    private lateinit var lv : ListView
    private lateinit var adapter: ArrayAdapter<Person>

    /**
     * Override method called when the activity is created.
     * Initializes the activity and sets up the user interface.
     *
     * @param savedInstanceState The saved instance state bundle.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Kaisa's Android Project"

        this.lv = findViewById(R.id.listView)
        this.adapter = ArrayAdapter<Person>(this,
            R.layout.item, R.id.textViewInsideList, mutableListOf<Person>())
        lv.adapter = adapter

        this.addUserButton = findViewById(R.id.addUserButton)
        addUserButton.setOnClickListener(this)
    }

    /**
     * Override method called when the activity is resumed.
     *
     * This function calls the parseJson() function which makes
     * the connection to the server to parse and display user data.
     */
    override fun onResume() {
        super.onResume()
        parseJson(this, adapter)
    }

    /**
     * Override method that handles the click event, when a specific view is clicked.
     * Starts the AddUserActivity by creating and starting a new Intent.
     *
     * @param view The view that was clicked.
     */
    override fun onClick(view: View) {
        val myIntent = Intent(this, AddUserActivity::class.java)
        startActivity(myIntent)
    }
}

