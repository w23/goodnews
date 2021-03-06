package fixes.android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;

import java.util.Collection;

class NotificationCompatJellyBean {
    static Notification add(Context context, Notification n,
            CharSequence contentTitle, CharSequence contentText, CharSequence contentInfo,
            RemoteViews tickerView, int number,
            PendingIntent contentIntent, PendingIntent fullScreenIntent, Bitmap largeIcon,
            Collection<NotificationActionInfo> actionInfos) {
        Notification.Builder b = new Notification.Builder(context)
                .setWhen(n.when)
                .setSmallIcon(n.icon, n.iconLevel)
                .setContent(n.contentView)
                .setTicker(n.tickerText, tickerView)
                .setSound(n.sound, n.audioStreamType)
                .setVibrate(n.vibrate)
                .setLights(n.ledARGB, n.ledOnMS, n.ledOffMS)
                .setOngoing((n.flags & Notification.FLAG_ONGOING_EVENT) != 0)
                .setOnlyAlertOnce((n.flags & Notification.FLAG_ONLY_ALERT_ONCE) != 0)
                .setAutoCancel((n.flags & Notification.FLAG_AUTO_CANCEL) != 0)
                .setDefaults(n.defaults)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setContentInfo(contentInfo)
                .setContentIntent(contentIntent)
                .setDeleteIntent(n.deleteIntent)
                .setFullScreenIntent(fullScreenIntent,
                        (n.flags & Notification.FLAG_HIGH_PRIORITY) != 0)
                .setLargeIcon(largeIcon)
                .setNumber(number);
        for (NotificationActionInfo actionInfo : actionInfos) {
            b.addAction(actionInfo.icon, actionInfo.title, actionInfo.intent);
        }
        return b.build();
    }

}
