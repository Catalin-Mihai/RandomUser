package com.catalin.randomuser.ui.main.utils

import android.content.Context
import com.catalin.randomuser.R
import com.catalin.randomuser.data.repository.model.User
import java.util.Calendar

fun User.description(context: Context): String {
    return context.getString(R.string.user_description_template).format(age, location.country)
}

fun User.lastMessageAsDayTime(context: Context): String {
    val (hour, minute) = with(Calendar.getInstance()) {
        Pair(get(Calendar.HOUR_OF_DAY), get(Calendar.MINUTE))
    }
    return context.getString(R.string.day_time_template).format(hour, minute)
}