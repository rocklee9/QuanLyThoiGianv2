package com.gameloft.pc.quanlythoigian;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class editscr extends AppCompatActivity {
    private final String SHARED_PREFERENCES_NAME="data_detailscr";
    private final String MON="mon";
    private final String PHONG="phong";
    private final String THOIGIAN="thoigian";
    private final String GIANGVIEN="giangvien";
    private final String EMAIL="email";
    private final String SDT="sdt";
    private EditText edtmon,edtphong,edttg,edtgv,edtemail,edtsdt;
    private Button btnluu,btnxoa;

    private EditText edtThoiGianBatDau, edtThoiGianKetThuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editscr);

        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();

        //inraeditscr() hàm này được thay bởi hàm setWidgets()
    }
    private void init() {
        edtmon=(EditText)findViewById(R.id.môn);
        edtphong=(EditText)findViewById(R.id.phong);
        edttg=(EditText) findViewById(R.id.thoigianbatdau);
        edtgv=(EditText)findViewById(R.id.giangvien);
        edtemail=(EditText)findViewById(R.id.email);
        edtsdt=(EditText)findViewById(R.id.sdt);
        btnluu=(Button)findViewById(R.id.btnhienthi);
        //btnxoa=(Button)findViewById(R.id.btnxoadata);
        edtThoiGianBatDau=(EditText) findViewById(R.id.thoigianbatdau);
        edtThoiGianKetThuc=(EditText) findViewById(R.id.thoigianketthuc);
    }

    private void getWidgets() {

    }

    private void setWidgets() {
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFERENCES_NAME,MODE_PRIVATE);
        edtmon.setText(sharedPreferences.getString(MON,""));
        edtphong.setText(sharedPreferences.getString(PHONG,""));
        edttg.setText(sharedPreferences.getString(THOIGIAN,""));
        edtgv.setText(sharedPreferences.getString(GIANGVIEN,""));
        edtemail.setText(sharedPreferences.getString(EMAIL,""));
        edtsdt.setText(sharedPreferences.getString(SDT,""));

    }

    private void addWidgetsListener() {
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bd=new Bundle();
                add_data();
                Intent ht=new Intent(editscr.this,thoi_khoa_bieu1.class);
                startActivity(ht);
            }


        });
        btnxoa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Bundle bd=new Bundle();
                remove_data();
                Intent ht1=new Intent(editscr.this,thoi_khoa_bieu1.class);
                startActivity(ht1);
            }
        });
        edtThoiGianBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialogBatDau();
            }
        });
        edtThoiGianKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialogKetThuc();
            }
        });
    }



    public void add_data(){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFERENCES_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(MON,edtmon.getText().toString());
        editor.putString(PHONG,edtphong.getText().toString());
        editor.putString(THOIGIAN,edttg.getText().toString());
        editor.putString(GIANGVIEN,edtgv.getText().toString());
        editor.putString(EMAIL,edtemail.getText().toString());
        editor.putString(SDT,edtsdt.getText().toString());
        editor.apply();
    }

    public void remove_data(){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFERENCES_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

/*
    public void inraeditscr(){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFERENCES_NAME,MODE_PRIVATE);
        edtmon.setText(sharedPreferences.getString(MON,""));
        edtphong.setText(sharedPreferences.getString(PHONG,""));
        edttg.setText(sharedPreferences.getString(THOIGIAN,""));
        edtgv.setText(sharedPreferences.getString(GIANGVIEN,""));
        edtemail.setText(sharedPreferences.getString(EMAIL,""));
        edtsdt.setText(sharedPreferences.getString(SDT,""));
    }
*/

    private void showTimePickerDialogBatDau() {
        Calendar calendar= Calendar.getInstance();
        int hour= calendar.get(Calendar.HOUR);
        int min= calendar.get(Calendar.MINUTE);

        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfday, int minute) {
                edtThoiGianBatDau.setText(hourOfday+" : "+minute);
            }
        };

        TimePickerDialog pic=new TimePickerDialog(this, onTimeSetListener, hour, min, true);
        pic.show();

    }
    private void showTimePickerDialogKetThuc() {
        Calendar calendar= Calendar.getInstance();
        int hour= calendar.get(Calendar.HOUR);
        int min= calendar.get(Calendar.MINUTE);

        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfday, int minute) {
                edtThoiGianKetThuc.setText(hourOfday+" : "+minute);
            }
        };

        TimePickerDialog pic=new TimePickerDialog(this, onTimeSetListener, hour, min, true);
        pic.show();

    }
}
