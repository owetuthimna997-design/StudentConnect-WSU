
package com.example.studentconnect

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        findViewById<Button>(R.id.btnAcademicSupport)
            .setOnClickListener {
                startActivity(
                    Intent(this, AcademicSupportActivity::class.java)
                )
            }

        findViewById<Button>(R.id.btnAnnouncements)
            .setOnClickListener {
                startActivity(
                    Intent(this, AnnouncementsActivity::class.java)
                )
            }

        findViewById<Button>(R.id.btnContacts)
            .setOnClickListener {
                startActivity(
                    Intent(this, ContactsActivity::class.java)
                )
            }

        findViewById<Button>(R.id.btnHelp)
            .setOnClickListener {
                startActivity(
                    Intent(this, RequestHelpActivity::class.java)
                )
            }

        findViewById<Button>(R.id.btnViewRequests)
            .setOnClickListener {
                startActivity(
                    Intent(this, ViewHelpRequestsActivity::class.java)
                )
            }
    }
}
