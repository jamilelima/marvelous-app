package dev.jamile.marvelous.utils

import java.security.MessageDigest
import java.util.*

object Utils {
    fun getTimeStamp(): Long{
        return (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis/1000)
    }

    fun md5(toEncrypt: String): String {
        return try {
            val digest = MessageDigest.getInstance("md5")
            digest.update(toEncrypt.toByteArray())
            val bytes = digest.digest()
            val sb = StringBuilder()
            for (i in bytes.indices) {
                sb.append(String.format("%02X", bytes[i]))
            }
            sb.toString().toLowerCase(Locale.getDefault())
        } catch (exc: Exception) {
            "Error"
        }
    }
}