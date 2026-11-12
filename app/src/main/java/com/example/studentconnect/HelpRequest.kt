
package com.example.studentconnect

data class HelpRequest(
    val name: String,
    val email: String,
    val description: String,
    val dateSubmitted: Long = System.currentTimeMillis()
)
