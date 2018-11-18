package top.iofox.lib.otool.ui.view;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import androidx.core.app.NotificationCompat;

/**
 * Created by [Oliver Chu] on 2018/11/19 1:25
 */
public class ONotification {
    private static final String TAG = "ONotification";
    public Context context;
    private NotificationManager manager;

    public ONotification(Context context) {
        this.context = context;
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // 通知渠道的id

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String id = "my_channel_01";
            if (manager.getNotificationChannel(id) == null) {
                // 用户可以看到的通知渠道的名字.
                NotificationChannel mChannel = null;
                mChannel = new NotificationChannel(id, "测试渠道标题", NotificationManager.IMPORTANCE_HIGH);
                mChannel.setDescription("测试描述");
                mChannel.enableLights(true);
                mChannel.setLightColor(Color.RED);
                mChannel.enableVibration(true);
                mChannel.shouldShowLights();
                manager.createNotificationChannel(mChannel);
            } else {

            }
        }
    }

    public void notice(String title, String message, Class clz) {
        Intent intent1 = new Intent(context, clz);
        intent1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent intent = PendingIntent.getActivity(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification compat = new NotificationCompat.Builder(context, "my_channel_01")
                .setShowWhen(true)
                .setSmallIcon(android.R.drawable.ic_menu_close_clear_cancel)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(intent)
                .setAutoCancel(true)
                .setLights(Color.BLUE, 1000, 100)
                .build();
        manager.notify(0, compat);
    }


}
