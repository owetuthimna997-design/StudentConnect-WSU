
package com.example.studentconnect

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.text.method.HideReturnsTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val studentNumber = findViewById<EditText>(R.id.etEmail)
        val password = findViewById<EditText>(R.id.etPassword)

        val loginButton = findViewById<Button>(R.id.btnLogin)
        val registerLink = findViewById<TextView>(R.id.txtRegister)
        val forgotPassword = findViewById<TextView>(R.id.txtForgotPassword)
        val togglePassword = findViewById<ImageButton>(R.id.btnTogglePassword)
        val backButton = findViewById<TextView>(R.id.btnBack)

        // Open Registration
        registerLink.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Open Forgot Password
        forgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        // Show or hide password
        var passwordVisible = false

        togglePassword.setOnClickListener {
            passwordVisible = !passwordVisible

            if (passwordVisible) {
                password.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()

                togglePassword.setImageResource(R.drawable.ic_visibility_off)
                togglePassword.contentDescription = "Hide password"

            } else {
                password.transformationMethod =
                    PasswordTransformationMethod.getInstance()

                togglePassword.setImageResource(R.drawable.ic_visibility)
                togglePassword.contentDescription = "Show password"
            }

            password.setSelection(password.text.length)
        }

        // Back button
        backButton.setOnClickListener {
            finish()
        }

        // Login
        loginButton.setOnClickListener {

            val studentID = studentNumber.text.toString().trim()
            val userPassword = password.text.toString()

            if (studentID.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(
                    this,
                    "Enter your Student Number and password",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val preferences = getSharedPreferences(
                "student_accounts",
                MODE_PRIVATE
            )

            val accounts = org.json.JSONArray(
                preferences.getString("accounts", "[]")
            )

            var loginSuccessful = false

            for (i in 0 until accounts.length()) {

                val account = accounts.getJSONObject(i)

                if (account.getString("studentNumber") == studentID &&
                    account.getString("password") == userPassword
                ) {
                    loginSuccessful = true
                    break
                }
            }

            if (loginSuccessful) {

                Toast.makeText(
                    this,
                    "Login successful!",
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(Intent(this, HomeActivity::class.java))
                finish()

            } else {

                Toast.makeText(
                    this,
                    "Invalid Student Number or password.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
