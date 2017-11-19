package com.gameloft.pc.quanlythoigian.Dialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.gameloft.pc.quanlythoigian.R;
import com.gameloft.pc.quanlythoigian.thoi_khoa_bieu;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by minhhoang on 11/10/2017.
 */

public class CustomDialog extends Dialog {

    private TextView tv_time_end;
    private Button btn_huy;
    private Button btn_luu;
    private EditText edt_cv;



    public CustomDialog(@NonNull Context context) {
        super(context);

        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();
    }

    private void init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_tgb);

    }



    private void getWidgets() {

        btn_huy= (Button)findViewById(R.id.btn_huy);
        btn_luu= (Button)findViewById(R.id.btn_luu);
        tv_time_end= (TextView)findViewById(R.id.tv_time_end);
        edt_cv= (EditText)findViewById(R.id.edt_cv);

    }

    private void setWidgets() {

        setCanceledOnTouchOutside(false);

    }

    private void addWidgetsListener() {


        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(CustomDialog.super.getContext(),"da luu",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CustomDialog.super.getContext(), thoi_khoa_bieu.class);
                dismiss();

            }
        });
        tv_time_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nhap_time();


            }
        });
    }

    private void nhap_time() {

        final Calendar calendar= Calendar.getInstance();
        int gio= calendar.get(Calendar.HOUR);
        int phut= calendar.get(Calendar.MINUTE);

        TimePickerDialog timepickerdialog= new TimePickerDialog(CustomDialog.super.getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(0,0,0,hourOfDay,minute);
                SimpleDateFormat simpledate= new SimpleDateFormat("hh:mm a");
                tv_time_end.setText(simpledate.format(calendar.getTime()));

            }
        },gio,phut,true);
        timepickerdialog.show();
    }

}
