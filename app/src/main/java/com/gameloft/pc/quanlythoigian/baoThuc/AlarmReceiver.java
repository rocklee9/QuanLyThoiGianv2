package com.gameloft.pc.quanlythoigian.baoThuc;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.gameloft.pc.quanlythoigian.R;
import com.gameloft.pc.quanlythoigian.models.MonHoc;
import com.gameloft.pc.quanlythoigian.utils.ThoiKhoaBieuActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by PC on 11/28/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {

    MonHoc monHoc;
    int day;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Toi o Receiver", "hi");

        monHoc = (MonHoc) intent.getSerializableExtra("monHocBaoThuc");
        day = (int) intent.getSerializableExtra("day");
        String trangThai = intent.getExtras().getString("trangThaiCheck");

        Intent i = new Intent(context, RingtonePlayingService.class);
        context.startService(i);

        createNotification(context);

        /*Intent myIntent = new Intent(context, ThongBaoNhacNho.class);
        myIntent.putExtra("Thongtin",monHoc);
        myIntent.putExtra("ngay", day);
        myIntent.putExtra("trangThaiCheck", trangThai);

        context.startService(myIntent);*/
    }

    public void createNotification(Context context) {

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.logo_app)
                .setContentTitle("Xách mông lên và đi học thôi!")
                .setContentText("Môn học: " + monHoc.getTenMonHoc() + "\n"
                        + "Thời gian: " + monHoc.getThoiGian1() + " - " + monHoc.getThoiGian2() + "    Phòng: " + monHoc.getPhong())
                .setSmallIcon(R.mipmap.logo_app)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Môn học: " + monHoc.getTenMonHoc() + "\n" + "Thời gian: " + monHoc.getThoiGian1() + " - " + monHoc.getThoiGian2() + "    Phòng: " + monHoc.getPhong()))
                .setSound(soundUri)
                .setPriority(NotificationCompat.PRIORITY_HIGH);


        //To add a dismiss button
        Intent dismissIntent = new Intent(context, RingtonePlayingService.class);
        dismissIntent.setAction(RingtonePlayingService.ACTION_DISMISS);

        PendingIntent pendingIntent = PendingIntent.getService(context,
                123, dismissIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action action = new NotificationCompat.Action
                (android.R.drawable.ic_lock_idle_alarm, "Bỏ qua", pendingIntent);
        builder.addAction(action);
        // end of setting action button to notification


        Intent intent1 = new Intent(context, ThoiKhoaBieuActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 123, intent1,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pIntent);


        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(NOTIFICATION_SERVICE);
        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(123, notification);
    }
}
