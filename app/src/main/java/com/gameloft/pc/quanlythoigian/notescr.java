package com.gameloft.pc.quanlythoigian;

import android.app.Activity;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.content.Intent;
import android.graphics.Bitmap;
import android.content.pm.PackageManager;
import android.widget.Toast;

public class notescr extends Activity {
    Button btncam;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notescr);

        btncam=(Button)findViewById(R.id.btnCamera);
        iv=(ImageView)findViewById(R.id.imgView);

        btncam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturePicture();
            }
        });
    }
    private void capturePicture(){
        if(getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 100);
        }else {
            Toast.makeText(getApplication(),"Cam không được hỗ trợ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Lấy ảnh từ intent Camera của mình về dưới dạng bitmap và hiển thị lên imageview của mình
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        iv.setImageBitmap(bitmap);
    }
}
