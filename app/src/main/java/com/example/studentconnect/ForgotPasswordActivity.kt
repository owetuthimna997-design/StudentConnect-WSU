
package com.example.studentconnect

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray

class ForgotPasswordActivity : AppCompatActivity() {

    private var verifiedStudentNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val studentNumber = findViewById<EditText>(R.id.etForgotStudentNumber)
        val verifyStudent = findViewById<Button>(R.id.btnVerifyStudent)
        val securityQuestion = findViewById<TextView>(R.id.txtSecurityQuestion)
        val securityAnswer = findViewById<EditText>(R.id.etForgotSecurityAnswer)
        val verifyAnswer = findViewById<Button>(R.id.btnVerifyAnswer)
        val newPassword = findViewById<EditText>(R.id.etNewPassword)
        val confirmPassword = findViewById<EditText>(R.id.etConfirmNewPassword)
        val resetPassword = findViewById<Button>(R.id.btnResetPassword)
        val backButton = findViewById<TextView>(R.id.btnBack)

        var savedAccounts = JSONArray()

        verifyStudent.setOnClickListener {

            val enteredNumber = studentNumber.text.toString().trim()

            if (enteredNumber.isEmpty()) {
                Toast.makeText(this, "Enter your Student Number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val preferences = getSharedPreferences("student_accounts", MODE_PRIVATE)
            savedAccounts = JSONArray(preferences.getString("accounts", "[]"))

            var foundAccount = false

            for (i in 0 until savedAccounts.length()) {

                val account = savedAccounts.getJSONObject(i)

                if (account.getString("studentNumber") == enteredNumber) {

                    if (!account.has("securityQuestion") ||
                        !account.has("securityAnswer")) {
                        Toast.makeText(
                            this,
                            "This account has no security question. Please contact support.",
                            Toast.LENGTH_LONG
                        ).show()
                        return@setOnClickListener
                    }

                    verifiedStudentNumber = enteredNumber

                    securityQuestion.text = account.getString("securityQuestion")

                    securityQuestion.visibility = View.VISIBLE
                    securityAnswer.visibility = View.VISIBLE
                    verifyAnswer.visibility = View.VISIBLE

                    foundAccount = true
                    break
                }
            }

            if (!foundAccount) {
                Toast.makeText(
                    this,
                    "Student account not found",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        verifyAnswer.setOnClickListener {

            val enteredAnswer = securityAnswer.text.toString().trim()

            if (enteredAnswer.isEmpty()) {
                Toast.makeText(this, "Enter your security answer", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var answerCorrect = false

            for (i in 0 until savedAccounts.length()) {

                val account = savedAccounts.getJSONObject(i)

                if (account.getString("studentNumber") == verifiedStudentNumber) {

                    val savedAnswer = account.getString("securityAnswer")

                    if (enteredAnswer.equals(savedAnswer, ignoreCase = true)) {
                        answerCorrect = true
                    }

                    break
                }
            }

            if (answerCorrect) {

                Toast.makeText(
                    this,
                    "Verification successful",
                    Toast.LENGTH_SHORT
                ).show()

                newPassword.visibility = View.VISIBLE
                confirmPassword.visibility = View.VISIBLE
                resetPassword.visibility = View.VISIBLE

            } else {

                Toast.makeText(
                    this,
                    "Incorrect security answer",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        resetPassword.setOnClickListener {

            val password = newPassword.text.toString()
            val confirm = confirmPassword.text.toString()

            if (password.isEmpty() || confirm.isEmpty()) {

                Toast.makeText(this, "Complete both password fields", Toast.LENGTH_SHORT).show()

            } else if (password.length < 6) {

                Toast.makeText(
                    this,
                    "Password must have at least 6 characters",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (password != confirm) {

                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()

            } else {

                val preferences = getSharedPreferences("student_accounts", MODE_PRIVATE)
                val accounts = JSONArray(preferences.getString("accounts", "[]"))

                for (i in 0 until accounts.length()) {

                    val account = accounts.getJSONObject(i)

                    if (account.getString("studentNumber") == verifiedStudentNumber) {

                        account.put("password", password)
                        break
                    }
                }

                preferences.edit()
                    .putString("accounts", accounts.toString())
                    .apply()

                Toast.makeText(
                    this,
                    "Password reset successful. Please log in.",
                    Toast.LENGTH_LONG
                ).show()

                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}
