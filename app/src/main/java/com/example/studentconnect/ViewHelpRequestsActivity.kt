
package com.example.studentconnect

import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ViewHelpRequestsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainLayout = LinearLayout(this)
        mainLayout.orientation = LinearLayout.VERTICAL
        mainLayout.setPadding(32, 40, 32, 32)
        mainLayout.setBackgroundColor(Color.rgb(243, 247, 255))

        val title = TextView(this)
        title.text = "Saved Help Requests"
        title.textSize = 26f
        title.setTextColor(Color.rgb(23, 70, 162))
        title.setPadding(0, 0, 0, 24)

        mainLayout.addView(title)

        val scrollView = ScrollView(this)
        val requestsLayout = LinearLayout(this)
        requestsLayout.orientation = LinearLayout.VERTICAL

        val requests = HelpRequestStorage(this).getRequests()

        if (requests.isEmpty()) {
            val emptyMessage = TextView(this)
            emptyMessage.text =
                "No help requests have been saved yet."
            emptyMessage.textSize = 17f
            emptyMessage.setTextColor(Color.DKGRAY)
            requestsLayout.addView(emptyMessage)
        } else {
            val dateFormat = SimpleDateFormat(
                "dd MMM yyyy, HH:mm",
                Locale.getDefault()
            )

            for ((index, request) in requests.withIndex()) {
                val requestText = TextView(this)

                requestText.text =
                    "Request ${index + 1}\n\n" +
                            "Name: ${request.name}\n" +
                            "Email: ${request.email}\n" +
                            "Help needed: ${request.description}\n" +
                            "Date: ${
                                dateFormat.format(Date(request.dateSubmitted))
                            }"

                requestText.textSize = 16f
                requestText.setTextColor(Color.DKGRAY)
                requestText.setPadding(20, 20, 20, 20)
                requestText.setBackgroundColor(Color.WHITE)
                requestText.gravity = Gravity.START

                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.bottomMargin = 20

                requestsLayout.addView(requestText, params)
            }
        }

        scrollView.addView(requestsLayout)
        mainLayout.addView(
            scrollView,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        setContentView(mainLayout)
    }
}
