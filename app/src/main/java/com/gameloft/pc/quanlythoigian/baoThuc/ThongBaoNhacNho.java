package com.gameloft.pc.quanlythoigian.baoThuc;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.gameloft.pc.quanlythoigian.R;
import com.gameloft.pc.quanlythoigian.models.MonHoc;

/**
 * Created by PC on 11/28/2017.
 */

public class ThongBaoNhacNho extends Service  {

    MonHoc monHoc;
    int day;

    MediaPlayer mediaPlayer;
    int id ;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Toi o ThongBaoNhacNho", "hi");

        //id = (int) intent.getSerializableExtra("id");
        String trangThai = intent.getExtras().getString("trangThaiCheck");
        if (trangThai.equals("on")){
            id = 1;
        }
        else if (trangThai.equals("off")){
            id = 0;
        }

        if (id == 1){
            /*//NhacChuong
            mediaPlayer = MediaPlayer.create(this,R.raw.nhac_chuong_tu_hom_nay);
            mediaPlayer.start();*/



            //thông báo nhắc nhở ra thanh thông báo của máy

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


            day = (int) intent.getSerializableExtra("ngay");

           /* //trả về TabFragement tuong ung khi click vào thông báo
            Intent mIntent = new Intent(ThongBaoNhacNho.this, ThoiKhoaBieuActivity.class);
            mIntent.putExtra("chitietmonhoc",monHoc);
            mIntent.putExtra("day", day);

            //Intent mIntent = new Intent(ThongBaoNhacNho.this, ThoiKhoaBieu.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, mIntent, 0);*/

            Intent mIntent = new Intent(this, ThongBaoNhacNho.class);
            mIntent.putExtra("id", 0);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, mIntent, 0);

            monHoc = (MonHoc) intent.getSerializableExtra("Thongtin");
            Notification.Builder notifyBuilder = new Notification.Builder(this);
            notifyBuilder.setContentTitle("Xách mông lên và đi học thôi!");
            notifyBuilder.setContentText("Môn học: "+ monHoc.getTenMonHoc() + "\n"
                    + "Thời gian: "+ monHoc.getThoiGian1() + " - " + monHoc.getThoiGian2() + "    Phòng: " + monHoc.getPhong());
            notifyBuilder.setSmallIcon(R.drawable.logo_app);
            notifyBuilder.setAutoCancel(true); //tự động tắt thông báo khi click vô nó
            notifyBuilder.setStyle(new Notification.BigTextStyle().bigText("Môn học: "+ monHoc.getTenMonHoc() + "\n" + "Thời gian: "+ monHoc.getThoiGian1() + " - " + monHoc.getThoiGian2() + "    Phòng: " + monHoc.getPhong()));
            //notifyBuilder.setActions();

            notifyBuilder.setContentIntent(pendingIntent);

            notificationManager.notify(1, notifyBuilder.build());

            mediaPlayer.stop();

            id = 0;
        }
        /*else if (id == 0){
            mediaPlayer.stop();
            mediaPlayer.reset();
        }*/
        return START_NOT_STICKY;
    }
}
