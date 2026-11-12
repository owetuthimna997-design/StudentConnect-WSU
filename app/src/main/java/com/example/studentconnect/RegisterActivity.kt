
package com.example.studentconnect

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val fullName = findViewById<EditText>(R.id.etFullName)
        val studentNumber = findViewById<EditText>(R.id.etStudentNumber)
        val email = findViewById<EditText>(R.id.etRegisterEmail)
        val password = findViewById<EditText>(R.id.etRegisterPassword)
        val confirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val securityQuestion = findViewById<Spinner>(R.id.spSecurityQuestion)
        val securityAnswer = findViewById<EditText>(R.id.etSecurityAnswer)

        val registerButton = findViewById<Button>(R.id.btnRegister)
        val backToLogin = findViewById<TextView>(R.id.txtBackToLogin)
        val backButton = findViewById<TextView>(R.id.btnBack)

        // Security question options
        val questions = arrayOf(
            "Select a security question",
            "What was the name of your first school?",
            "What was the name of your first pet?",
            "What is your favourite childhood nickname?",
            "What is the name of your hometown?"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            questions
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        securityQuestion.adapter = adapter

        registerButton.setOnClickListener {

            val name = fullName.text.toString().trim()
            val studentID = studentNumber.text.toString().trim()
            val emailAddress = email.text.toString().trim()
            val userPassword = password.text.toString()
            val confirm = confirmPassword.text.toString()
            val selectedQuestion = securityQuestion.selectedItem.toString()
            val answer = securityAnswer.text.toString().trim()

            if (name.isEmpty() || studentID.isEmpty() ||
                emailAddress.isEmpty() || userPassword.isEmpty() ||
                confirm.isEmpty() || answer.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Please complete all fields",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (securityQuestion.selectedItemPosition == 0) {

                Toast.makeText(
                    this,
                    "Please select a security question",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (!android.util.Patterns.EMAIL_ADDRESS
                    .matcher(emailAddress).matches()
            ) {

                Toast.makeText(
                    this,
                    "Enter a valid email address",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (userPassword.length < 6) {

                Toast.makeText(
                    this,
                    "Password must have at least 6 characters",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (userPassword != confirm) {

                Toast.makeText(
                    this,
                    "Passwords do not match",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val preferences = getSharedPreferences(
                    "student_accounts",
                    MODE_PRIVATE
                )

                val accounts = JSONArray(
                    preferences.getString("accounts", "[]")
                )

                var accountExists = false

                for (i in 0 until accounts.length()) {
                    val account = accounts.getJSONObject(i)

                    if (account.getString("studentNumber") == studentID) {
                        accountExists = true
                        break
                    }
                }

                if (accountExists) {

                    Toast.makeText(
                        this,
                        "Student number already registered",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {

                    val newAccount = JSONObject()

                    newAccount.put("fullName", name)
                    newAccount.put("studentNumber", studentID)
                    newAccount.put("email", emailAddress)
                    newAccount.put("password", userPassword)
                    newAccount.put("securityQuestion", selectedQuestion)
                    newAccount.put("securityAnswer", answer.trim().lowercase())

                    accounts.put(newAccount)

                    preferences.edit()
                        .putString("accounts", accounts.toString())
                        .apply()

                    Toast.makeText(
                        this,
                        "Registration successful! Please log in.",
                        Toast.LENGTH_LONG
                    ).show()

                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }

        backToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}
