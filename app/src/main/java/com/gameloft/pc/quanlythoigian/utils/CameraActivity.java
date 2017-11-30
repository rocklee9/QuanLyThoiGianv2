package com.gameloft.pc.quanlythoigian.utils;

import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.gameloft.pc.quanlythoigian.R;
import com.gameloft.pc.quanlythoigian.TabFragment.TabFragment_monday;
import com.gameloft.pc.quanlythoigian.models.MonHoc;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by HOAN on 11/25/2017.
 */

public class CameraActivity extends AppCompatActivity {
    Button btnCam, btnSave, btnCancel;
    ImageView imgHinh;
    MonHoc monHoc;

    private String mCurrentPhotoPath;

    public static String strSeparator = "__,__";

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
                ActivityCompat.requestPermissions(CameraActivity.this, new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_CAMERA);

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
                if(imgHinh.getDrawable() != null){
                    if (monHoc.getHinh() != null){
                        monHoc.setHinh(monHoc.getHinh() + strSeparator + mCurrentPhotoPath);
                    }else {
                        monHoc.setHinh(mCurrentPhotoPath);
                    }
                    Intent data = new Intent();
                    data.putExtra("monHocCamed", monHoc);
                    setResult(TabFragment_monday.RESULT_CODE_CAM, data);
                    finish();
                }else{
                    Toast.makeText(CameraActivity.this,R.string.not_take,Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void  onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_CAMERA && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go

                File photoFile;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                    // Log.i(TAG, "IOException");
                    Toast.makeText(this,"Lỗi tạo file", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    if(Build.VERSION.SDK_INT >M){
                        Uri photoURI = FileProvider.getUriForFile(this,this.getApplicationContext().getPackageName() + ".my.package.name.provider",photoFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    }else{
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                    }
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivityForResult(intent, REQUEST_CODE_CAMERA2);
                }
            } else {
                Toast.makeText(this, R.string.canot_open_camera, Toast.LENGTH_LONG).show();
            }
        }

    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",         // suffix
                storageDir      // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
//        if(Build.VERSION.SDK_INT >= 24){
//            mCurrentPhotoPath = "file:" + image.getAbsolutePath();
//        }else{
//            mCurrentPhotoPath = image.getAbsolutePath();
//        }

        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_CAMERA2 && resultCode == RESULT_OK){
            btnCam.setVisibility(View.INVISIBLE);
            try{
                Log.i("mylog: ", mCurrentPhotoPath);
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(mCurrentPhotoPath));
                imgHinh.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
