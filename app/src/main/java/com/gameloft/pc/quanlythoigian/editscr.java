package com.gameloft.pc.quanlythoigian;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.gameloft.pc.quanlythoigian.TabFragment.TabFragment_monday;
import com.gameloft.pc.quanlythoigian.classPackage.MonHoc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.gameloft.pc.quanlythoigian.R.id.edtNote;

public class editscr extends AppCompatActivity {

    Button btnCancel, btnSave;
    EditText edtTenMon, edtPhong, edtGV, edtEmail, edtSDT;
    MonHoc monHoc;
    TextView tvTime1, tvTime2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editscr);

        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();

    }
    private void init() {
        monHoc = new MonHoc();
    }

    private void getWidgets() {
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnSave = (Button) findViewById(R.id.btnSave);
        edtTenMon = (EditText) findViewById(R.id.edtTenMon);
        tvTime1 = (TextView) findViewById(R.id.tvTime1);
        tvTime2 = (TextView) findViewById(R.id.tvTime2);
        edtPhong = (EditText) findViewById(R.id.edtPhong);
        edtGV = (EditText) findViewById(R.id.edtGV);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSDT = (EditText) findViewById(R.id.edtSDT);
    }

    private void setWidgets() {
        monHoc = (MonHoc) getIntent().getSerializableExtra("monhocEdit");
        edtTenMon.setText(monHoc.getTenMonHoc());
        edtPhong.setText(monHoc.getPhong());
        tvTime1.setText(monHoc.getThoiGian1());
        tvTime2.setText(monHoc.getThoiGian2());
        edtGV.setText(monHoc.getTenGV());
        edtEmail.setText(monHoc.getEmail());
        edtSDT.setText(monHoc.getSdt());

    }

     private void addWidgetsListener() {
         tvTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialogBatDau();
            }
        });
         tvTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialogKetThuc();
            }
        });
         btnSave.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(edtTenMon.getText().toString().isEmpty()){
                     AlertDialog.Builder rm = new AlertDialog.Builder(editscr.this);
                     rm.setMessage("Lưu mà không có tên môn học ?");
                     rm.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             dialog.cancel();
                         }
                     });
                     rm.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             setMonHoc();
                             Intent data = new Intent();
                             data.putExtra("monhocEdited",monHoc);
                             setResult(TabFragment_monday.RESULT_CODE_EDIT,data);
                             finish();
                         }
                     });
                     rm.create().show();
                 }else{
                     setMonHoc();
                     Intent data = new Intent();
                     data.putExtra("monhocEdited",monHoc);
                     setResult(TabFragment_monday.RESULT_CODE_EDIT,data);
                     finish();
                 }
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
                tvTime1.setText(s);
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
                tvTime2.setText(s);
            }
        };

        TimePickerDialog pic=new TimePickerDialog(this, onTimeSetListener, hour, min, true);
        pic.show();
    }

    public void setMonHoc(){
        monHoc.setTenMonHoc(edtTenMon.getText().toString());
        monHoc.setThoiGian1(tvTime1.getText().toString());
        monHoc.setThoiGian2(tvTime2.getText().toString());
        monHoc.setPhong(edtPhong.getText().toString());
        monHoc.setTenGV(edtGV.getText().toString());
        monHoc.setEmail(edtEmail.getText().toString());
        monHoc.setSdt(edtSDT.getText().toString());
    }
}
