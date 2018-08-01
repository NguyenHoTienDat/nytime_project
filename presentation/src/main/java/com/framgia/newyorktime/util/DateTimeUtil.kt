package com.framgia.newyorktime.util

import java.text.SimpleDateFormat
import java.util.*

private const val TIME_FORMAT = "yyyy-MM-dd"
private const val NOTICE_HOUR = "h ago"
private const val NOTICE_MINUTE = "m ago"
private const val NOTICE_NOW = "just now"

fun String.convertNewsPublishTime(): String {
    return if (SimpleDateFormat(TIME_FORMAT).format(Calendar.getInstance().time)
            == this.substring(0, 10))
        calculateDiffTime(this@convertNewsPublishTime)
    else this.substring(0, 10)
}

/**
 * Calculate diff time between publish time and current time
 * We temporarily ignore time zone, it will be handled later
 */
private fun calculateDiffTime(publishTime: String): String {
    val hourPublish = publishTime.substring(11,13)
    val minPublish = publishTime.substring(14,16)
    val curHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val curMin = Calendar.getInstance().get(Calendar.MINUTE)
    if (curHour.toString() != hourPublish) {
        return Math.abs(curHour - hourPublish.toInt()).toString() + NOTICE_HOUR
    } else if (curMin.toString() != minPublish) {
        return Math.abs(curMin - minPublish.toInt()).toString() + NOTICE_MINUTE
    }

    return NOTICE_NOW
}
