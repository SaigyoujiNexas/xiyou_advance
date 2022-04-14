package com.xiyou.advance.modulespublic.common.utils

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import java.util.*

object StringUtil {
    fun timeInMillisToString(millis: Long): String = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
    {
        val format = SimpleDateFormat("yyyy-MM-dd")
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis
        format.format(calendar)
    }
    else{
        val date = Date(millis)
        val str = "${date.year}-" +
                if(date.month < 10) "0${date.month}" else "${date.month}-"+
                        if(date.day < 10) "0${date.day}" else "${date.day}"
        str
    }
}