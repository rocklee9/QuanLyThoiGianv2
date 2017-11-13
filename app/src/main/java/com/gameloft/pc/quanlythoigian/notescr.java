package com.gameloft.pc.quanlythoigian;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gameloft.pc.quanlythoigian.TabFragment.TabFragment_monday;
import com.gameloft.pc.quanlythoigian.classPackage.MonHoc;


public class notescr extends Activity {
    Button btnCam, btnSave, btnCancel;
    EditText edtNote;
    MonHoc monHoc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notescr);

        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();

    }

    private void init() {
        monHoc = new MonHoc();
    }

    private void getWidgets() {
        btnCam =(Button)findViewById(R.id.btnCamera);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        edtNote = (EditText) findViewById(R.id.edtNote);
    }


    private void setWidgets(){
        monHoc = (MonHoc) getIntent().getSerializableExtra("monHocNote");
        edtNote.setText(monHoc.getNote());
    }

    private void addWidgetsListener() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monHoc.setNote(edtNote.getText().toString());
                Intent data = new Intent();
                data.putExtra("monHocNoted",monHoc);
                setResult(TabFragment_monday.RESULT_CODE_NOTE,data);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}
