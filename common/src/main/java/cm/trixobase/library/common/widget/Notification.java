package cm.trixobase.library.common.widget;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;

import cm.trixobase.library.common.R;

/*
 * Powered by Trixobase Enterprise on 26/12/2020.
 */

public class Notification {

    private NotificationManager manager;
    private NotificationCompat.Builder builder;

    private Notification() {}

    public static class Builder {

        private Notification instance;
        private Context context;

        private Builder(Context context) {
            this.context = context;
            instance = new Notification();
            instance.manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            instance.builder = new NotificationCompat.Builder(context);
            instance.builder.setAutoCancel(true);
            setTicker(context.getString(R.string.app_name))
            .setTitle(context.getString(R.string.app_name))
            .setIcon(R.drawable.iv_app)
            .setLogo(BitmapFactory.decodeResource(context.getResources(), R.drawable.iv_app))
            .setContent("")
            .setChannel();
        }

        public Builder setTicker(String announce) {
            instance.builder.setTicker(announce);
            return this;
        }

        public Builder setTitle(String title) {
            instance.builder.setContentTitle(title);
            return this;
        }

        public Builder setContent(String content) {
            instance.builder.setContentText(content);
            return this;
        }

        public Builder setIntent(Intent intent) {
            instance.builder.setContentIntent(PendingIntent.getActivity(context,0, intent,0));
            return this;
        }

        public Builder setIcon(int drawable) {
            instance.builder.setSmallIcon(drawable);
            return this;
        }

        public Builder setLogo(Bitmap logo) {
            instance.builder.setLargeIcon(logo);
            return this;
        }

        private void setChannel() {
            instance.builder.setChannelId("Notification");
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("Notification","This is 2", NotificationManager.IMPORTANCE_HIGH);
                Uri uri= Settings.System.DEFAULT_NOTIFICATION_URI;

                channel.setDescription("This is 1");
                channel.setSound(uri, android.app.Notification.AUDIO_ATTRIBUTES_DEFAULT);
                instance.manager.createNotificationChannel(channel);
            }
        }

        public void show() {
            instance.builder.setWhen(System.currentTimeMillis());
            instance.show();
        }

        public void showAfter(long millis) {
            instance.builder.setWhen(millis);
            instance.show();
        }

    }

    public static Builder builder(Context context) {
        return new Builder(context);
    }

    private void show() {
        manager.notify(1, builder.build());
    }

}
