package com.gameloft.pc.quanlythoigian;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gameloft.pc.quanlythoigian.TabFragment.TabFragment_monday;
import com.gameloft.pc.quanlythoigian.classPackage.MonHoc;

import java.io.ByteArrayOutputStream;

/**
 * Created by Administrator on 11/22/2017.
 */

public class camActiv extends AppCompatActivity {
    Button btnCam, btnSave, btnCancel;
    ImageView imgHinh;
    MonHoc monHoc;

    public static final int REQUEST_CODE_CAMERA = 100;
    public static final int REQUEST_CODE_CAMERA2 = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam);

        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();
    }

    private void init() {
        monHoc = new MonHoc();
    }

    private void setWidgets() {
        monHoc = (MonHoc) getIntent().getSerializableExtra("monHocCam");
    }


    private void getWidgets() {
        btnCam = (Button) findViewById(R.id.btnCapture);
        btnSave = (Button) findViewById(R.id.btnSavPic);
        btnCancel = (Button) findViewById(R.id.btnCancelPic);
        imgHinh = (ImageView) findViewById(R.id.imgBitmap);
    }

    private void addWidgetsListener()
    {
        btnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(camActiv.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monHoc.setHinh(ImageView_To_Byte(imgHinh));
                Intent data = new Intent();
                data.putExtra("monHocCamed", monHoc);
                setResult(TabFragment_monday.RESULT_CODE_CAM, data);
                finish();
            }
        });
    }

    @Override
    public void  onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if (requestCode == REQUEST_CODE_CAMERA && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CODE_CAMERA2);
        }
        else
        {
            Toast.makeText(this, "Không mở được Camera!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_CAMERA2 && resultCode == RESULT_OK && data != null ){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgHinh.setImageBitmap(bitmap);
        }
    }

    public byte[] ImageView_To_Byte(ImageView imgv){

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

}
