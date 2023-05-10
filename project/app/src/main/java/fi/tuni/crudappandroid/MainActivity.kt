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

    private lateinit var addUserButton : Button
    private lateinit var lv : ListView
    private lateinit var adapter: ArrayAdapter<Person>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Kaisa's Android Project"

        this.lv = findViewById(R.id.listView)
        this.adapter = ArrayAdapter<Person>(this,
            R.layout.item, R.id.textViewInsideList, mutableListOf<Person>())
        lv.adapter = adapter

        this.addUserButton = findViewById(R.id.addUserButton)
        addUserButton.setOnClickListener {
            val myIntent = Intent(this, AllUsersActivity::class.java)
            startActivity(myIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        parseJson(this, adapter)
    }

    override fun onClick(view: View) {

    }
}

