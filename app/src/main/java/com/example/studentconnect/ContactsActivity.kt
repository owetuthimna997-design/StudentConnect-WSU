
package com.example.studentconnect

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ContactsActivity : AppCompatActivity() {

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

        // Back button
        val backButton = TextView(this)
        backButton.text = "← Back"
        backButton.textSize = 16f
        backButton.setTextColor(navyBlue)
        backButton.setPadding(0, 0, 0, 18)
        layout.addView(backButton)

        backButton.setOnClickListener {
            finish()
        }

        // Header
        val header = LinearLayout(this)
        header.orientation = LinearLayout.VERTICAL
        header.setPadding(24, 28, 24, 28)
        header.setBackgroundColor(navyBlue)

        val appName = TextView(this)
        appName.text = "STUDENTCONNECT"
        appName.textSize = 14f
        appName.setTextColor(Color.rgb(220, 232, 255))
        appName.setTypeface(null, Typeface.BOLD)
        header.addView(appName)

        val title = TextView(this)
        title.text = "Important Contacts"
        title.textSize = 27f
        title.setTextColor(Color.WHITE)
        title.setTypeface(null, Typeface.BOLD)
        title.setPadding(0, 20, 0, 8)
        header.addView(title)

        val subtitle = TextView(this)
        subtitle.text = "Find the right support for your needs."
        subtitle.textSize = 15f
        subtitle.setTextColor(Color.WHITE)
        header.addView(subtitle)

        layout.addView(
            header,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = 24
            }
        )

        addText(
            layout,
            "Who do you need to contact?",
            21f,
            navyBlue,
            true
        )

        addText(
            layout,
            "Choose the relevant support service below for guidance on who to contact.",
            15f,
            greyText,
            false
        ).setPadding(0, 8, 0, 22)

        // Student Support
        addContactCard(
            layout,
            "🎓  Student Support",
            "For student assistance, counselling and general student enquiries.",
            "078 298 7374",
            "student.support@studentconnect.com"
        )

        // Academic Enquiries
        addContactCard(
            layout,
            "📚  Academic Enquiries",
            "For questions about your studies, courses and academic administration.",
            "063 456 1980",
            "academic.enquiries@studentconnect.com"
        )

        // IT Support
        addContactCard(
            layout,
            "💻  IT Support",
            "For technical problems involving student accounts, computers and online platforms.",
            "078 839 9860",
            "itsupport@studentconnect.com"
        )

        // Emergency Assistance
        addContactCard(
            layout,
            "🆘  Emergency Assistance",
            "For urgent safety concerns and emergency assistance. Contact verified local emergency services when help is needed.",
            "081 039 1277",
            "emergency@studentconnect.com"
        )

        val footer = TextView(this)
        footer.text =
            "Sample contact details for demonstration purposes only."
        footer.textSize = 12f
        footer.setTextColor(greyText)
        footer.gravity = Gravity.CENTER
        footer.setPadding(0, 18, 0, 12)
        layout.addView(footer)

        scrollView.addView(layout)
        setContentView(scrollView)
    }

    private fun addText(
        parent: LinearLayout,
        text: String,
        size: Float,
        color: Int,
        bold: Boolean
    ): TextView {
        val textView = TextView(this)
        textView.text = text
        textView.textSize = size
        textView.setTextColor(color)

        if (bold) {
            textView.setTypeface(null, Typeface.BOLD)
        }

        parent.addView(
            textView,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )

        return textView
    }

    private fun addContactCard(
        parent: LinearLayout,
        heading: String,
        description: String,
        phone: String,
        email: String
    ) {
        val card = LinearLayout(this)
        card.orientation = LinearLayout.VERTICAL
        card.setPadding(20, 20, 20, 20)
        card.setBackgroundColor(Color.WHITE)

        val headingView = TextView(this)
        headingView.text = heading
        headingView.textSize = 18f
        headingView.setTextColor(navyBlue)
        headingView.setTypeface(null, Typeface.BOLD)

        val descriptionView = TextView(this)
        descriptionView.text = description
        descriptionView.textSize = 14f
        descriptionView.setTextColor(darkText)
        descriptionView.setLineSpacing(4f, 1f)
        descriptionView.setPadding(0, 10, 0, 12)

        val phoneView = TextView(this)
        phoneView.text = "Phone: $phone"
        phoneView.textSize = 14f
        phoneView.setTextColor(darkText)
        phoneView.setPadding(0, 4, 0, 4)

        val emailView = TextView(this)
        emailView.text = "Email: $email"
        emailView.textSize = 14f
        emailView.setTextColor(navyBlue)
        emailView.setPadding(0, 4, 0, 0)

        card.addView(headingView)
        card.addView(descriptionView)
        card.addView(phoneView)
        card.addView(emailView)

        parent.addView(
            card,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = 22
            }
        )
    }
}
