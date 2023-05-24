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

    /**
     * Override method called when the activity is created.
     * Initializes the activity and sets up the user interface.
     *
     * @param savedInstanceState The saved instance state bundle.
     */
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

    /**
     * Handles the click event of the clicked view.
     * Retrieves first and last names from the inputs and
     * calls the addUser() function inside a separate thread.
     *
     * @param p0 The view that was clicked (can be null).
     */
    override fun onClick(p0: View?) {
        val firstNameString : String = firstNameInput.text.toString()
        val lastNameString : String = lastNameInput.text.toString()

        // Check if user given inputs are too short.
        if (firstNameString.length < 2 || lastNameString.length < 2) {
            Toast.makeText(
                this,
                "Name length must be at least two characters.",
                Toast.LENGTH_LONG
            ).show()
            println("New user not added. Length too short.")

        } else {
            // If lengths are good, addUser() gets invoked and
            // a new user is added.
            thread {
                addUser(firstNameString, lastNameString)
                runOnUiThread {
                    Toast.makeText(
                        this, "New user added", Toast.LENGTH_SHORT
                    ).show()
                }
            }
            // Clearing the input fields.
            firstNameInput.setText("")
            lastNameInput.setText("")
        }
    }
}