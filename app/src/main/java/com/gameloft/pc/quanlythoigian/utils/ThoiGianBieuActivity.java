package com.gameloft.pc.quanlythoigian.utils;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gameloft.pc.quanlythoigian.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThoiGianBieuActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView tv_day, tv_note_0, tv_note_1, tv_note_2, tv_note_3, tv_note_4, tv_note_5, tv_note_6, tv_note_7, tv_note_8, 
            tv_note_9, tv_note_10, tv_note_11, tv_note_12, tv_note_13, tv_note_14, tv_note_15, tv_note_16, tv_note_17, tv_note_18, tv_note_19, 
            tv_note_20, tv_note_21, tv_note_22, tv_note_23;

    private final String NOTE="note";
    private final String DATE="date";
    private final String DAY="day";
    private final String MONTH="month";
    private final String TIME_START="time_start";
    private final String TIME_END="time_end";
    private final String COlOR="color";

    private String KT_0="0", KT_1="1", KT_2="2", KT_3="3", KT_4="4", KT_5="5", KT_6="6", KT_7="7", KT_8="8", KT_9="9", KT_10="10", 
             KT_11="11", KT_12="12", KT_13="13", KT_14="14", KT_15="15", KT_16="16", KT_17="17", KT_18="18", KT_19="19", KT_20="20",
             KT_21="21", KT_22="22", KT_23="23";

    private int KT1, KT2, KT3, KT4, KT5, KT6, KT7, KT8, KT9, KT10, KT11, KT12, KT13, KT14, KT15,
            KT16, KT17, KT18, KT19, KT20, KT21, KT22, KT23, KT0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thoi_gian_bieu);

        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();

    }
    private void init() {

    }

    public void getWidgets() {
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        tv_day= (TextView)findViewById(R.id.tv_day);
        tv_note_0 = (TextView)findViewById(R.id.tv_0);
        tv_note_1 = (TextView)findViewById(R.id.tv_1);
        tv_note_2 = (TextView)findViewById(R.id.tv_2);
        tv_note_3 = (TextView)findViewById(R.id.tv_3);
        tv_note_4 = (TextView)findViewById(R.id.tv_4);
        tv_note_5 = (TextView)findViewById(R.id.tv_5);
        tv_note_6 = (TextView)findViewById(R.id.tv_6);
        tv_note_7 = (TextView)findViewById(R.id.tv_7);
        tv_note_8 = (TextView)findViewById(R.id.tv_8);
        tv_note_9 = (TextView)findViewById(R.id.tv_9);
        tv_note_10= (TextView)findViewById(R.id.tv_10);
        tv_note_11= (TextView)findViewById(R.id.tv_11);
        tv_note_12= (TextView)findViewById(R.id.tv_12);
        tv_note_13= (TextView)findViewById(R.id.tv_13);
        tv_note_14= (TextView)findViewById(R.id.tv_14);
        tv_note_15= (TextView)findViewById(R.id.tv_15);
        tv_note_16= (TextView)findViewById(R.id.tv_16);
        tv_note_17= (TextView)findViewById(R.id.tv_17);
        tv_note_18= (TextView)findViewById(R.id.tv_18);
        tv_note_19= (TextView)findViewById(R.id.tv_19);
        tv_note_20= (TextView)findViewById(R.id.tv_20);
        tv_note_21= (TextView)findViewById(R.id.tv_21);
        tv_note_22= (TextView)findViewById(R.id.tv_22);
        tv_note_23= (TextView)findViewById(R.id.tv_23);

        set_day();
        read_data();
    }

    private void setWidgets() {

    }

    private void addWidgetsListener() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThoiGianBieuActivity.this, MainActivity.class));
            }
        });

        tv_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chon_ngay();
                read_data();
            }

        });

    }

    private void chon_ngay(){

        final Calendar calendar= Calendar.getInstance();
        int nam= calendar.get(calendar.YEAR);
        int thang= calendar.get(calendar.MONTH);
        int ngay= calendar.get(calendar.DATE);
        DatePickerDialog datepickerdialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpledateformat= new SimpleDateFormat("dd-MM-yyyy");
                tv_day.setText(simpledateformat.format(calendar.getTime()));
                clear_data();
                read_data();
            }
        },nam,thang,ngay);

        datepickerdialog.show();
    }

    private void clear_data() {
        tv_note_0.setBackgroundResource(R.drawable.custom4);
        tv_note_1.setBackgroundResource(R.drawable.custom4);
        tv_note_2.setBackgroundResource(R.drawable.custom4);
        tv_note_3.setBackgroundResource(R.drawable.custom4);
        tv_note_4.setBackgroundResource(R.drawable.custom4);
        tv_note_5.setBackgroundResource(R.drawable.custom4);
        tv_note_6.setBackgroundResource(R.drawable.custom4);
        tv_note_7.setBackgroundResource(R.drawable.custom4);
        tv_note_8.setBackgroundResource(R.drawable.custom4);
        tv_note_9.setBackgroundResource(R.drawable.custom4);
        tv_note_10.setBackgroundResource(R.drawable.custom4);
        tv_note_11.setBackgroundResource(R.drawable.custom4);
        tv_note_12.setBackgroundResource(R.drawable.custom4);
        tv_note_13.setBackgroundResource(R.drawable.custom4);
        tv_note_14.setBackgroundResource(R.drawable.custom4);
        tv_note_15.setBackgroundResource(R.drawable.custom4);
        tv_note_16.setBackgroundResource(R.drawable.custom4);
        tv_note_17.setBackgroundResource(R.drawable.custom4);
        tv_note_18.setBackgroundResource(R.drawable.custom4);
        tv_note_19.setBackgroundResource(R.drawable.custom4);
        tv_note_20.setBackgroundResource(R.drawable.custom4);
        tv_note_21.setBackgroundResource(R.drawable.custom4);
        tv_note_22.setBackgroundResource(R.drawable.custom4);
        tv_note_23.setBackgroundResource(R.drawable.custom4);

        tv_note_0.setText("");
        tv_note_1.setText("");
        tv_note_2.setText("");
        tv_note_3.setText("");
        tv_note_4.setText("");
        tv_note_5.setText("");
        tv_note_6.setText("");
        tv_note_7.setText("");
        tv_note_8.setText("");
        tv_note_9.setText("");
        tv_note_10.setText("");
        tv_note_11.setText("");
        tv_note_12.setText("");
        tv_note_13.setText("");
        tv_note_14.setText("");
        tv_note_15.setText("");
        tv_note_16.setText("");
        tv_note_17.setText("");
        tv_note_18.setText("");
        tv_note_19.setText("");
        tv_note_20.setText("");
        tv_note_21.setText("");
        tv_note_22.setText("");
        tv_note_23.setText("");
    }

    private void set_day(){
        Calendar calendar= Calendar.getInstance();
        int nam= calendar.get(calendar.YEAR);
        int thang= calendar.get(calendar.MONTH);
        int ngay= calendar.get(calendar.DATE);

        calendar.set(nam, thang, ngay);
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
        tv_day.setText(simpleDateFormat.format(calendar.getTime()));
    }
    public void read_kt(){
        SharedPreferences sharedPreferences = getSharedPreferences(tv_day.getText().toString(),MODE_PRIVATE);
        KT0=sharedPreferences.getInt(KT_0,0);
        KT1=sharedPreferences.getInt(KT_1,0);
        KT2=sharedPreferences.getInt(KT_2,0);
        KT3=sharedPreferences.getInt(KT_3,0);
        KT4=sharedPreferences.getInt(KT_4,0);
        KT5=sharedPreferences.getInt(KT_5,0);
        KT6=sharedPreferences.getInt(KT_6,0);
        KT7=sharedPreferences.getInt(KT_7,0);
        KT8=sharedPreferences.getInt(KT_8,0);
        KT9=sharedPreferences.getInt(KT_9,0);
        KT10=sharedPreferences.getInt(KT_10,0);
        KT11=sharedPreferences.getInt(KT_11,0);
        KT12=sharedPreferences.getInt(KT_12,0);
        KT13=sharedPreferences.getInt(KT_13,0);
        KT14=sharedPreferences.getInt(KT_14,0);
        KT15=sharedPreferences.getInt(KT_15,0);
        KT16=sharedPreferences.getInt(KT_16,0);
        KT17=sharedPreferences.getInt(KT_17,0);
        KT18=sharedPreferences.getInt(KT_18,0);
        KT19=sharedPreferences.getInt(KT_19,0);
        KT20=sharedPreferences.getInt(KT_20,0);
        KT21=sharedPreferences.getInt(KT_21,0);
        KT22=sharedPreferences.getInt(KT_22,0);
        KT23=sharedPreferences.getInt(KT_23,0);
    }

    public void read_data(){
        read_kt();
        read_date(tv_note_0,"0",KT0);
        read_date(tv_note_1,"1",KT1);
        read_date(tv_note_2,"2",KT2);
        read_date(tv_note_3,"3",KT3);
        read_date(tv_note_4,"4",KT4);
        read_date(tv_note_5,"5",KT5);
        read_date(tv_note_6,"6",KT6);
        read_date(tv_note_7,"7",KT7);
        read_date(tv_note_8,"8",KT8);
        read_date(tv_note_9,"9",KT9);
        read_date(tv_note_10,"10",KT10);
        read_date(tv_note_11,"11",KT11);
        read_date(tv_note_12,"12",KT12);
        read_date(tv_note_13,"13",KT13);
        read_date(tv_note_14,"14",KT14);
        read_date(tv_note_15,"15",KT15);
        read_date(tv_note_16,"16",KT16);
        read_date(tv_note_17,"17",KT17);
        read_date(tv_note_18,"18",KT18);
        read_date(tv_note_19,"19",KT19);
        read_date(tv_note_20,"20",KT20);
        read_date(tv_note_21,"21",KT21);
        read_date(tv_note_22,"22",KT22);
        read_date(tv_note_23,"23",KT23);

    }
    public void read_date(TextView x,String i, int KT){
        SharedPreferences sharedPreferences=getSharedPreferences(tv_day.getText().toString()+i,MODE_PRIVATE);
        int color = sharedPreferences.getInt(COlOR,0);
        if(KT == 1) {
            x.setText("( "+ sharedPreferences.getString(TIME_START,"")+":00 - "+sharedPreferences.getString(TIME_END,"")+" )"+ sharedPreferences.getString(NOTE, ""));
            switch (color){
                case 1:{
                    x.setBackgroundResource(R.drawable.nen_xanh);
                    break;
                }
                case 2:{
                    x.setBackgroundResource(R.drawable.nen_do);
                    break;
                }
                case 3:{
                    x.setBackgroundResource(R.drawable.nen_xanh_la);
                    break;
                }
                case 4:{
                    x.setBackgroundResource(R.drawable.nen_tim);
                    break;
                }
                case 5:{
                    x.setBackgroundResource(R.drawable.nen_vang);
                    break;
                }
            }

        }else {

            switch (color) {
                case 1: {
                    x.setBackgroundResource(R.drawable.custom_ridio_xanh);
                    break;
                }
                case 2: {
                    x.setBackgroundResource(R.drawable.custom_ridio_do);
                    break;
                }
                case 3: {
                    x.setBackgroundResource(R.drawable.custom_ridio_xanh_la);
                    break;
                }
                case 4: {
                    x.setBackgroundResource(R.drawable.custom_ridio_tim);
                    break;
                }
                case 5: {
                    x.setBackgroundResource(R.drawable.custom_ridio_vang);
                    break;
                }
            }
        }
    }
    public void onClick(View view){

        switch (view.getId()){
            case R.id.tv_0 :{
                Onclick(0);
                break;
            }
            case R.id.tv_1 :{
                Onclick(1);
                break;
            }
            case R.id.tv_2 :{
                Onclick(2);
                break;
            }
            case R.id.tv_3 :{
                Onclick(3);
                break;
            }case R.id.tv_4 :{
                Onclick(4);
                break;
            }case R.id.tv_5 :{
                Onclick(5);
                break;
            }case R.id.tv_6 :{
                Onclick(6);
                break;
            }case R.id.tv_7 :{
                Onclick(7);
                break;
            }case R.id.tv_8 :{
                Onclick(8);
                break;
            }case R.id.tv_9 :{
                Onclick(9);
                break;
            }case R.id.tv_10 :{
                Onclick(10);
                break;
            }case R.id.tv_11 :{
                Onclick(11);
                break;
            }case R.id.tv_12 :{
                Onclick(12);
                break;
            }case R.id.tv_13 :{
                Onclick(13);
                break;
            }case R.id.tv_14 :{
                Onclick(14);
                break;
            }case R.id.tv_15 :{
                Onclick(15);
                break;
            }case R.id.tv_16 :{
                Onclick(16);
                break;
            }case R.id.tv_17 :{
                Onclick(17);
                break;
            }case R.id.tv_18 :{
                Onclick(18);
                break;
            }case R.id.tv_19 :{
                Onclick(19);
                break;
            }case R.id.tv_20 :{
                Onclick(20);
                break;
            }case R.id.tv_21 :{
                Onclick(21);
                break;
            }case R.id.tv_22 :{
                Onclick(22);
                break;
            }case R.id.tv_23 :{
                Onclick(23);
                break;
            }
        }
    }
    public void Onclick(int i){
        Intent intent = new Intent(ThoiGianBieuActivity.this, DetailTgbActivity.class);
        Bundle bd = new Bundle();
        bd.putString("thoi_gian",tv_day.getText().toString());
        bd.putString("id",String.valueOf(i));
        bd.putInt("time_start",i);
        intent.putExtra("key",bd);
        startActivity(intent);
    }
}
