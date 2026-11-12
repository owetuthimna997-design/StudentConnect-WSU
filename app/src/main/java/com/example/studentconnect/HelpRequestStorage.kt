
package com.example.studentconnect

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

class HelpRequestStorage(context: Context) {

    private val preferences = context.getSharedPreferences(
        "studentconnect_help_requests",
        Context.MODE_PRIVATE
    )

    fun saveRequest(request: HelpRequest) {
        val requests = JSONArray(
            preferences.getString("requests", "[]")
        )

        val item = JSONObject().apply {
            put("name", request.name)
            put("email", request.email)
            put("description", request.description)
            put("dateSubmitted", request.dateSubmitted)
        }

        requests.put(item)

        preferences.edit()
            .putString("requests", requests.toString())
            .apply()
    }

    fun getRequests(): List<HelpRequest> {
        val requests = JSONArray(
            preferences.getString("requests", "[]")
        )

        val result = mutableListOf<HelpRequest>()

        for (i in 0 until requests.length()) {
            val item = requests.getJSONObject(i)

            result.add(
                HelpRequest(
                    name = item.getString("name"),
                    email = item.getString("email"),
                    description = item.getString("description"),
                    dateSubmitted = item.getLong("dateSubmitted")
                )
            )
        }

        return result
    }
}
