
package com.example.studentconnect

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AnnouncementsActivity : AppCompatActivity() {

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

        // Blue header
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
        title.text = "Announcements"
        title.textSize = 28f
        title.setTextColor(Color.WHITE)
        title.setTypeface(null, Typeface.BOLD)
        title.setPadding(0, 20, 0, 8)
        header.addView(title)

        val subtitle = TextView(this)
        subtitle.text = "Stay informed. Stay connected."
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

        // Introductory text
        val intro = TextView(this)
        intro.text = "University updates"
        intro.textSize = 22f
        intro.setTextColor(navyBlue)
        intro.setTypeface(null, Typeface.BOLD)
        layout.addView(intro)

        val description = TextView(this)
        description.text =
            "Check this section for important notices and information for students."
        description.textSize = 15f
        description.setTextColor(greyText)
        description.setPadding(0, 8, 0, 24)
        layout.addView(description)

        // Notice card
        val card = LinearLayout(this)
        card.orientation = LinearLayout.VERTICAL
        card.setPadding(22, 22, 22, 22)
        card.setBackgroundColor(Color.WHITE)

        val noticeTitle = TextView(this)
        noticeTitle.text = "📢  Important notices"
        noticeTitle.textSize = 19f
        noticeTitle.setTextColor(navyBlue)
        noticeTitle.setTypeface(null, Typeface.BOLD)
        card.addView(noticeTitle)

        val noticeMessage = TextView(this)
        noticeMessage.text =
            "There are no announcements available in this section yet.\n\nPlease check with your university or lecturers for the latest official updates."
        noticeMessage.textSize = 15f
        noticeMessage.setTextColor(darkText)
        noticeMessage.setLineSpacing(4f, 1f)
        noticeMessage.setPadding(0, 14, 0, 0)
        card.addView(noticeMessage)

        layout.addView(
            card,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = 20
            }
        )

        val footer = TextView(this)
        footer.text = "Student support, all in one place."
        footer.textSize = 13f
        footer.setTextColor(greyText)
        footer.gravity = android.view.Gravity.CENTER
        footer.setPadding(0, 12, 0, 12)
        layout.addView(footer)

        scrollView.addView(layout)
        setContentView(scrollView)
    }
}
