package com.gameloft.pc.quanlythoigian;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.gameloft.pc.quanlythoigian.TabFragment.TabFragment_monday;
import com.gameloft.pc.quanlythoigian.classPackage.MonHoc;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity{

    Button btnCancel, btnSave;
    EditText edtTenMon, edtTime1, edtTime2, edtPhong, edtGV, edtEmail, edtSDT;
    MonHoc monHoc = new MonHoc();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getWidgets();
        addWidgetsListener();
    }


    private void getWidgets() {
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnSave = (Button) findViewById(R.id.btnSave);
        edtTenMon = (EditText) findViewById(R.id.edtTenMon);
        edtTime1 = (EditText) findViewById(R.id.edtTime1);
        edtTime2 = (EditText) findViewById(R.id.edtTime2);
        edtPhong = (EditText) findViewById(R.id.edtPhong);
        edtGV = (EditText) findViewById(R.id.edtGV);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSDT = (EditText) findViewById(R.id.edtSDT);
    }

    private void addWidgetsListener() {
        edtTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialogBatDau();
            }
        });
        edtTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialogKetThuc();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monHoc.setTenMonHoc(edtTenMon.getText().toString());
                monHoc.setThoiGian1(edtTime1.getText().toString());
                monHoc.setThoiGian2(edtTime2.getText().toString());
                monHoc.setPhong(edtPhong.getText().toString());
                monHoc.setTenGV(edtGV.getText().toString());
                monHoc.setEmail(edtEmail.getText().toString());
                monHoc.setSdt(edtSDT.getText().toString());
                Intent data = new Intent();
                data.putExtra("monhoc",monHoc);
                setResult(TabFragment_monday.RESULT_CODE_ADD,data);
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

    private void showTimePickerDialogBatDau() {
        final Calendar calendar= Calendar.getInstance();
        int hour= calendar.get(Calendar.HOUR);
        int min= calendar.get(Calendar.MINUTE);

        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfday, int minute) {
                SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
                calendar.set(Calendar.HOUR_OF_DAY,hourOfday);
                calendar.set(Calendar.MINUTE,minute);
                String s = dft.format(calendar.getTime());
                edtTime1.setText(s);
            }
        };

        TimePickerDialog pic=new TimePickerDialog(this, onTimeSetListener, hour, min, true);
        pic.show();

    }
    private void showTimePickerDialogKetThuc() {
        final Calendar calendar= Calendar.getInstance();
        int hour= calendar.get(Calendar.HOUR);
        int min= calendar.get(Calendar.MINUTE);

        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfday, int minute) {
                SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
                calendar.set(Calendar.HOUR_OF_DAY,hourOfday);
                calendar.set(Calendar.MINUTE,minute);
                String s = dft.format(calendar.getTime());
                edtTime2.setText(s);
            }
        };

        TimePickerDialog pic=new TimePickerDialog(this, onTimeSetListener, hour, min, true);
        pic.show();
    }

}
