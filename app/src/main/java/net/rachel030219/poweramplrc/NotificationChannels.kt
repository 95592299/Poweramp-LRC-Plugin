package net.rachel030219.poweramplrc

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

/**
 * Creates every notification channel used by the app.
 *
 * This method is safe to call repeatedly. Existing channels are deliberately
 * left untouched so the user's channel settings are preserved.
 */
object NotificationChannels {
    const val ENTRANCE = "ENTRANCE"
    const val PLACEHOLDER = "PLACEHOLDER"
    const val LOCK = "LOCK"
    const val INDEPENDENCE = "INDEPENDENCE"

    fun ensureCreated(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channels = listOf(
            NotificationChannel(
                ENTRANCE,
                context.getString(R.string.notification_entrance_channel_name),
                NotificationManager.IMPORTANCE_HIGH
            ),
            NotificationChannel(
                PLACEHOLDER,
                context.getString(R.string.notification_placeholder_channel_name),
                NotificationManager.IMPORTANCE_LOW
            ),
            NotificationChannel(
                LOCK,
                context.getString(R.string.notification_lock_channel_name),
                NotificationManager.IMPORTANCE_LOW
            ),
            NotificationChannel(
                INDEPENDENCE,
                context.getString(R.string.preference_experimental_independence),
                NotificationManager.IMPORTANCE_DEFAULT
            )
        )

        channels.forEach { channel ->
            if (manager.getNotificationChannel(channel.id) == null) {
                manager.createNotificationChannel(channel)
            }
        }
    }
}
