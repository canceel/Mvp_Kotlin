package com.shenme.mvp_kotlin.app.utils

import android.text.TextUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by CANC on 2017/8/30.
 */
class DateUtils {
    val TYPE_SERVICE = 0
    val TYPE_NORMAL = 1
    val HOUR = (1000 * 60 * 60).toLong()
    val SERVICE_FORMATER = "yyyy-MM-dd'T'HH:mm:ss"
    val NORMAL_FORMATER = "yyyy-MM-dd HH:mm:ss"
    val DELIVERY_DATE_FORMATER = "yyyy-MM-dd"
    val DELIVERY_DATE_SERVER_FORMATER = "yyyyMMdd"
    val DELIVERY_TIME_FORMATER = "HH:mm:ss"
    val sdf: SimpleDateFormat? = null

    /**
     * 将服务器的时间转换为Date

     * @param dateString 服务器日期
     * *
     * @return 日期类
     */
    private fun dateFormatService(dateString: String): Date? {
        if (TextUtils.isEmpty(dateString)) {
            return null
        } else {
            //去掉时区
            val stringNoTimeZone = dateString.substring(0, 19)
            val formatter: SimpleDateFormat
            if (dateString.contains("T")) {
                formatter = SimpleDateFormat(SERVICE_FORMATER)
            } else {
                formatter = SimpleDateFormat(NORMAL_FORMATER)
            }

            try {
                return formatter.parse(stringNoTimeZone)
            } catch (e: ParseException) {
                e.printStackTrace()
                return null
            }

        }
    }

    /**
     * 通过不同的format获取相应的日期String格式

     * @param serviceDate 服务器日期
     * *
     * @param format      时间格式
     * *
     * @return 相应格式的日期String
     */
    private fun getDate(serviceDate: String, format: String): String {
        if (TextUtils.isEmpty(serviceDate)) {
            return ""
        }
        val formatter = SimpleDateFormat(format)
        val date = dateFormatService(serviceDate) ?: return ""
        return formatter.format(date)
    }

    /**
     * 获取应用通用时间

     * @param serviceDate 服务器时间
     * *
     * @return
     */
    fun getNormalDate(serviceDate: String): String {
        return getDate(serviceDate, NORMAL_FORMATER)
    }

    fun getDateStr(calendar: Calendar?): String {
        if (calendar == null) {
            return ""
        }
        return calendar.get(Calendar.YEAR).toString() + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " 00:00:00"
    }
}