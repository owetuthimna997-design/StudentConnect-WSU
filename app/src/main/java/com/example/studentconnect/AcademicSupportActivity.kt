
package com.example.studentconnect

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AcademicSupportActivity : AppCompatActivity() {

    private val navyBlue = Color.rgb(23, 70, 162)
    private val backgroundBlue = Color.rgb(244, 247, 253)
    private val darkText = Color.rgb(45, 55, 72)
    private val greyText = Color.rgb(100, 110, 125)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val scrollView = ScrollView(this)
        scrollView.setBackgroundColor(backgroundBlue)
        scrollView.isFillViewport = true

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(20, 24, 20, 24)

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
        header.setPadding(22, 24, 22, 24)
        header.background = roundedBackground(navyBlue, 20)

        val appTitle = TextView(this)
        appTitle.text = "STUDENTCONNECT"
        appTitle.textSize = 13f
        appTitle.setTextColor(Color.rgb(210, 225, 255))
        appTitle.setTypeface(null, Typeface.BOLD)
        header.addView(appTitle)

        val title = TextView(this)
        title.text = "Academic Support"
        title.textSize = 27f
        title.setTextColor(Color.WHITE)
        title.setTypeface(null, Typeface.BOLD)
        title.setPadding(0, 16, 0, 8)
        header.addView(title)

        val subtitle = TextView(this)
        subtitle.text =
            "Simple guidance to help you succeed in your studies."
        subtitle.textSize = 14f
        subtitle.setTextColor(Color.WHITE)
        header.addView(subtitle)

        layout.addView(header)

        // Introduction
        val introTitle = TextView(this)
        introTitle.text = "How can we support you?"
        introTitle.textSize = 21f
        introTitle.setTextColor(navyBlue)
        introTitle.setTypeface(null, Typeface.BOLD)
        introTitle.setPadding(0, 26, 0, 8)
        layout.addView(introTitle)

        val intro = TextView(this)
        intro.text =
            "Explore helpful tips and resources to stay organised, improve your learning and reach your academic goals."
        intro.textSize = 14f
        intro.setTextColor(greyText)
        intro.setLineSpacing(3f, 1f)
        intro.setPadding(0, 0, 0, 20)
        layout.addView(intro)

        // Study resources
        addCard(
            layout,
            "📚",
            "Study Resources",
            "You can find learning materials from these sources:\n\n" +
                    "• WSU Moodle: Check your course pages for lecture notes, presentations, assignments and other uploaded materials.\n\n" +
                    "• WSU Library: Look for academic books, journals and research materials.\n\n" +
                    "• Lecturers: Ask for recommended textbooks, notes and additional learning materials.\n\n" +
                    "• Your Faculty or Department: Ask about course-specific resources and academic materials."
        )

        // Plan studies
        addCard(
            layout,
            "📅",
            "Plan Your Studies",
            "Create a weekly timetable, set study goals and divide large assignments into manageable tasks."
        )

        // E-learning platforms
        addCard(
            layout,
            "💻",
            "E-learning Platforms",
            "Check Moodle, Blackboard or other university platforms for notes, announcements and assignments."
        )

        // Academic help
        addCard(
            layout,
            "🙋",
            "Get Academic Help",
            "Contact your lecturer or tutor when you need assistance. Asking questions early can make learning easier."
        )

        val footer = TextView(this)
        footer.text = "Keep learning. Keep growing."
        footer.textSize = 15f
        footer.setTextColor(navyBlue)
        footer.setTypeface(null, Typeface.BOLD)
        footer.gravity = Gravity.CENTER
        footer.setPadding(0, 18, 0, 12)
        layout.addView(footer)

        scrollView.addView(layout)
        setContentView(scrollView)
    }

    private fun addCard(
        parent: LinearLayout,
        icon: String,
        heading: String,
        description: String
    ) {
        val card = LinearLayout(this)
        card.orientation = LinearLayout.VERTICAL
        card.setPadding(20, 18, 20, 18)
        card.background = roundedBackground(Color.WHITE, 16)

        val iconView = TextView(this)
        iconView.text = icon
        iconView.textSize = 27f
        card.addView(iconView)

        val headingView = TextView(this)
        headingView.text = heading
        headingView.textSize = 17f
        headingView.setTextColor(navyBlue)
        headingView.setTypeface(null, Typeface.BOLD)
        headingView.setPadding(0, 10, 0, 6)
        card.addView(headingView)

        val descriptionView = TextView(this)
        descriptionView.text = description
        descriptionView.textSize = 14f
        descriptionView.setTextColor(darkText)
        descriptionView.setLineSpacing(4f, 1f)
        card.addView(descriptionView)

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.bottomMargin = 24
        parent.addView(card, params)
    }

    private fun roundedBackground(
        color: Int,
        radius: Int
    ): GradientDrawable {
        return GradientDrawable().apply {
            setColor(color)
            cornerRadius = radius * resources.displayMetrics.density
        }
    }
}
