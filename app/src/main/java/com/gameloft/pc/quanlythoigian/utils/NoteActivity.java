package com.gameloft.pc.quanlythoigian.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gameloft.pc.quanlythoigian.R;
import com.gameloft.pc.quanlythoigian.TabFragment.TabFragment_monday;
import com.gameloft.pc.quanlythoigian.models.MonHoc;


public class NoteActivity extends Activity {
    Button btnCam1, btnSave, btnCancel;
    EditText edtNote;
    MonHoc monHoc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();

    }

    private void init() {
        monHoc = new MonHoc();
    }

    private void getWidgets() {
//        btnCam1 = (Button) findViewById(R.id.btnCamera);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        edtNote = (EditText) findViewById(R.id.edtNote);
    }


    private void setWidgets() {
        monHoc = (MonHoc) getIntent().getSerializableExtra("monHocNote");
        edtNote.setText(monHoc.getNote());
    }

    private void addWidgetsListener() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monHoc.setNote(edtNote.getText().toString());
                Intent data = new Intent();
                data.putExtra("monHocNoted", monHoc);
                setResult(TabFragment_monday.RESULT_CODE_NOTE, data);
                finish();
            }
        });

//        btnCam1.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View view) {
//                Intent camscr = new Intent(NoteActivity.this, CameraActivity.class);
//                startActivity(camscr);
//            }
//        });

//        btnCam.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ActivityCompat.requestPermissions(NoteActivity.this,
//                        new String[]{Manifest.permission.CAMERA}, 100);
//            }
//        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            startActivityForResult(intent, 100);
//        } else {
//            Toast.makeText(this, R.string.ban_khong_duoc_mo_camera, Toast.LENGTH_LONG).show();
//        }
//
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
}