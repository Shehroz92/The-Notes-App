package eu.practice.notesapp.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import eu.practice.notesapp.R

class LockScreenActivity : AppCompatActivity() {

    private lateinit var passwordEditText: EditText
    private lateinit var enterButton: Button
    private var isFirstTimeSetup = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock_screen)

        // Initialize UI components
        passwordEditText = findViewById(R.id.passwordEditText)
        enterButton = findViewById(R.id.enterButton)

        // Get shared preferences
        val sharedPref = getSharedPreferences("AppPreferences", MODE_PRIVATE)
        val savedPassword = sharedPref.getString("app_password", null)

        // Determine if this is the first time setup
        isFirstTimeSetup = savedPassword == null

        // Set button text based on setup state
        enterButton.text = if (isFirstTimeSetup) "Set Password" else "Enter"

        enterButton.setOnClickListener {
            val enteredPassword = passwordEditText.text.toString()

            // Ensure password field is not empty
            if (enteredPassword.isEmpty()) {
                Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (isFirstTimeSetup) {
                // Save the new password for the first time
                savePassword(sharedPref, enteredPassword)
                Toast.makeText(this, "Password set successfully!", Toast.LENGTH_SHORT).show()
                openNotesActivity()
            } else {
                // Verify the password for returning users
                if (checkPassword(sharedPref, enteredPassword)) {
                    Toast.makeText(this, "Access granted!", Toast.LENGTH_SHORT).show()
                    openNotesActivity()
                } else {
                    Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun savePassword(sharedPref: SharedPreferences, password: String) {
        with(sharedPref.edit()) {
            putString("app_password", password)
            apply()
        }
    }

    private fun checkPassword(sharedPref: SharedPreferences, enteredPassword: String): Boolean {
        val savedPassword = sharedPref.getString("app_password", null)
        return enteredPassword == savedPassword
    }

    private fun openNotesActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish() // Close LockScreenActivity
    }
}
