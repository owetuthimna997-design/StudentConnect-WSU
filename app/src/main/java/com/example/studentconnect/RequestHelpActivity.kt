
package com.example.studentconnect

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RequestHelpActivity : AppCompatActivity() {

    private val navyBlue = Color.rgb(23, 70, 162)
    private val backgroundBlue = Color.rgb(234, 242, 255)
    private val darkText = Color.rgb(45, 55, 72)
    private val greyText = Color.rgb(89, 101, 121)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val scrollView = ScrollView(this)
        scrollView.setBackgroundColor(backgroundBlue)
        scrollView.isFillViewport = true

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(24, 28, 24, 28)

        // Simple white header
        val header = LinearLayout(this)
        header.orientation = LinearLayout.VERTICAL
        header.setPadding(0, 10, 0, 20)
        header.setBackgroundColor(Color.WHITE)

        // Back button
        val backButton = TextView(this)
        backButton.text = "← Back"
        backButton.textSize = 16f
        backButton.setTextColor(navyBlue)
        backButton.setPadding(0, 0, 0, 20)
        header.addView(backButton)

        backButton.setOnClickListener {
            finish()
        }

        // App name
        val appName = TextView(this)
        appName.text = "STUDENTCONNECT"
        appName.textSize = 14f
        appName.setTextColor(navyBlue)
        appName.setTypeface(null, Typeface.BOLD)
        header.addView(appName)

        // Screen title
        val title = TextView(this)
        title.text = "Request Help"
        title.textSize = 26f
        title.setTextColor(darkText)
        title.setTypeface(null, Typeface.BOLD)
        title.setPadding(0, 12, 0, 8)
        header.addView(title)

        // Subtitle
        val subtitle = TextView(this)
        subtitle.text = "We're here to help you."
        subtitle.textSize = 15f
        subtitle.setTextColor(greyText)
        header.addView(subtitle)

        layout.addView(
            header,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = 22
            }
        )

        // Introduction
        val intro = TextView(this)
        intro.text = "Tell us what you need"
        intro.textSize = 21f
        intro.setTextColor(navyBlue)
        intro.setTypeface(null, Typeface.BOLD)
        layout.addView(intro)

        val instructions = TextView(this)
        instructions.text =
            "Complete the form below so you can keep a record of your help request."
        instructions.textSize = 15f
        instructions.setTextColor(greyText)
        instructions.setPadding(0, 8, 0, 20)
        layout.addView(instructions)

        // White form card
        val formCard = LinearLayout(this)
        formCard.orientation = LinearLayout.VERTICAL
        formCard.setPadding(20, 22, 20, 22)
        formCard.setBackgroundColor(Color.WHITE)

        // Name field
        val nameLabel = makeLabel("Your name")
        formCard.addView(nameLabel)

        val nameInput = makeInput("Enter your full name")
        nameInput.setSingleLine(true)
        formCard.addView(nameInput, fieldParams())

        // Email field
        val emailLabel = makeLabel("Email address")
        formCard.addView(emailLabel)

        val emailInput = makeInput("Enter your email address")
        emailInput.inputType = 33
        emailInput.setSingleLine(true)
        formCard.addView(emailInput, fieldParams())

        // Help description field
        val helpLabel = makeLabel("How can we help?")
        formCard.addView(helpLabel)

        val helpInput = makeInput("Describe the help you need")
        helpInput.minLines = 4
        helpInput.gravity = Gravity.TOP
        helpInput.setPadding(14, 14, 14, 14)

        formCard.addView(
            helpInput,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = 20
            }
        )

        // Submit button
        val submitButton = Button(this)
        submitButton.text = "SUBMIT REQUEST"
        submitButton.setTextColor(Color.WHITE)
        submitButton.setBackgroundTintList(
            android.content.res.ColorStateList.valueOf(navyBlue)
        )
        submitButton.textSize = 15f
        submitButton.setTypeface(null, Typeface.BOLD)

        formCard.addView(
            submitButton,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )

        // Confirmation message
        val confirmation = TextView(this)
        confirmation.textSize = 15f
        confirmation.setTextColor(Color.rgb(23, 100, 60))
        confirmation.setPadding(0, 18, 0, 0)
        formCard.addView(confirmation)

        layout.addView(
            formCard,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = 20
            }
        )

        // Footer
        val footer = TextView(this)
        footer.text = "Your request is saved on this device."
        footer.textSize = 13f
        footer.setTextColor(greyText)
        footer.gravity = Gravity.CENTER
        footer.setPadding(0, 10, 0, 12)
        layout.addView(footer)

        // Submit request functionality
        submitButton.setOnClickListener {

            val name = nameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val help = helpInput.text.toString().trim()

            when {

                name.isEmpty() -> {
                    nameInput.error = "Please enter your name"
                    nameInput.requestFocus()
                }

                email.isEmpty() -> {
                    emailInput.error = "Please enter your email"
                    emailInput.requestFocus()
                }

                !android.util.Patterns.EMAIL_ADDRESS
                    .matcher(email).matches() -> {
                    emailInput.error = "Enter a valid email address"
                    emailInput.requestFocus()
                }

                help.isEmpty() -> {
                    helpInput.error = "Please describe the help you need"
                    helpInput.requestFocus()
                }

                else -> {

                    val request = HelpRequest(
                        name = name,
                        email = email,
                        description = help
                    )

                    // Save request on the device
                    HelpRequestStorage(this).saveRequest(request)

                    confirmation.text =
                        "Thank you, $name!\n\nYour help request has been saved on this device."

                    Toast.makeText(
                        this,
                        "Help request saved successfully!",
                        Toast.LENGTH_LONG
                    ).show()

                    submitButton.isEnabled = false
                    submitButton.text = "REQUEST SAVED"
                }
            }
        }

        scrollView.addView(layout)
        setContentView(scrollView)
    }

    // Create form labels
    private fun makeLabel(text: String): TextView {
        return TextView(this).apply {
            this.text = text
            textSize = 15f
            setTextColor(darkText)
            setTypeface(null, Typeface.BOLD)
            setPadding(0, 0, 0, 8)
        }
    }

    // Create input fields
    private fun makeInput(hintText: String): EditText {
        return EditText(this).apply {
            hint = hintText
            textSize = 15f
            setTextColor(darkText)
            setHintTextColor(greyText)
            setPadding(14, 12, 14, 12)

            backgroundTintList =
                android.content.res.ColorStateList.valueOf(
                    Color.rgb(190, 204, 228)
                )
        }
    }

    // Input field spacing
    private fun fieldParams(): LinearLayout.LayoutParams {
        return LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            bottomMargin = 18
        }
    }
}
