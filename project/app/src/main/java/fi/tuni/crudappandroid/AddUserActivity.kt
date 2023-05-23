package fi.tuni.crudappandroid

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread


/**
 * This is the activity for adding users.
 */
class AddUserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var firstNameText: TextView
    private lateinit var lastNameText: TextView
    private lateinit var firstNameInput: EditText
    private lateinit var lastNameInput: EditText
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        title = "Add users"

        this.firstNameText = findViewById(R.id.first_name_text_view)
        this.firstNameInput = findViewById(R.id.first_name_edit_text)
        this.lastNameText = findViewById(R.id.last_name_text_view)
        this.lastNameInput = findViewById(R.id.last_name_edit_text)
        this.addButton = findViewById(R.id.add_button)

        firstNameText.text = "First name"
        lastNameText.text = "Last name"

        addButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val firstNameString : String = firstNameInput.text.toString()
        val lastNameString : String = lastNameInput.text.toString()

        thread {
            addUser(firstNameString, lastNameString)
        }

        Toast.makeText(this, "New user added", Toast.LENGTH_SHORT).show();

        firstNameInput.setText("")
        lastNameInput.setText("")
    }
}