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

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var searchAllButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Kaisa's Android Project"

        this.searchAllButton = findViewById(R.id.searchAllButton)
        searchAllButton.setOnClickListener {
            val myIntent = Intent(this, AllUsersActivity::class.java)
            startActivity(myIntent)
        }
    }

    override fun onClick(view: View) {

    }

}

