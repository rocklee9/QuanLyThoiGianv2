package com.gameloft.pc.quanlythoigian;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class tgb_editscr extends AppCompatActivity {

    private  String SHARED_PREFERENCES_NAME_KT;
    private String KT_0="0";
    private String KT_1="1";
    private String KT_2="2";
    private String KT_3="3";
    private String KT_4="4";
    private String KT_5="5";
    private String KT_6="6";
    private String KT_7="7";
    private String KT_8="8";
    private String KT_9="9";
    private String KT_10="10";
    private String KT_11="11";
    private String KT_12="12";
    private String KT_13="13";
    private String KT_14="14";
    private String KT_15="15";
    private String KT_16="16";
    private String KT_17="17";
    private String KT_18="18";
    private String KT_19="19";
    private String KT_20="20";
    private String KT_21="21";
    private String KT_22="22";
    private String KT_23="23";



    private  String SHARED_PREFERENCES_NAME;
    private final String NOTE="note";
    private final String DATE="date";
    private final String DAY="day";
    private final String MONTH="month";
    private final String TIME_START="time_start";
    private final String TIME_END="time_end";
    private final String HOUR="hour";
    private final String MINUTE="minute";
    private final String COlOR="color";
    private String date;
    private String month;
    private int time_start_start;


    private int KT1;
    private int KT2;
    private int KT3;
    private int KT4;
    private int KT5;
    private int KT6;
    private int KT7;
    private int KT8;
    private int KT9;
    private int KT10;
    private int KT11;
    private int KT12;
    private int KT13;
    private int KT14;
    private int KT15;
    private int KT16;
    private int KT17;
    private int KT18;
    private int KT19;
    private int KT20;
    private int KT21;
    private int KT22;
    private int KT23;
    private int KT0;
    private int time_end;
    private int time_start;
    private int time_end_start;
    private int dem;
    private TextView tv_time_end;
    private Button btn_huy;
    private Button btn_luu;
    private EditText edt_cv;
    private String mytime;
    private String ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tgb_editsr);

        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();
    }

    private void init() {



    }

    private void getWidgets() {
        btn_huy= (Button)findViewById(R.id.btn_huy);
        btn_luu= (Button)findViewById(R.id.btn_luu);
        tv_time_end= (TextView)findViewById(R.id.tv_time_end);
        edt_cv= (EditText)findViewById(R.id.edt_cv);

        Intent intent = getIntent();
        Bundle bd = intent.getBundleExtra("key");
        mytime= bd.get("thoi_gian").toString();
        ID=bd.get("id").toString();
        time_start=bd.getInt("time_start");
        SHARED_PREFERENCES_NAME=mytime;
        SHARED_PREFERENCES_NAME_KT=mytime;


    }

    private void setWidgets() {

        read_data();
        String s = tv_time_end.getText().toString();
        String [] s1= s.split(":");
        time_end_start = Integer.valueOf(s1[0].trim());
        s= mytime;
         s1= s.split("-");
        date = s1[0].trim();
        month = s1[1].trim();


    }

    private void addWidgetsListener() {

        tv_time_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nhap_time();


            }
        });

        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = tv_time_end.getText().toString();
                if(s=="0"){
                    Toast.makeText(tgb_editscr.this,"ban vui long chon thoi gian ket thuc", Toast.LENGTH_SHORT).show();
                }else {
                    String[] s1 = s.split(":");
                    time_end = Integer.parseInt(s1[0].trim());
                    if(time_end> time_end_start){
                        if(time_end_start >= time_start) {
                                add_data_thuan(time_start, time_end);
                        }else {
                            if(time_end > time_start){
                                remove_data_nghich();
                                add_data_thuan(time_start,time_end);
                            }else if(time_end == time_start){
                                remove_data_nghich();
                                add_data();
                            }else {
                                add_data_nghich(time_end,time_start);
                            }
                        }
                    }else if(time_end ==time_end_start){
                        if(time_end > time_start){
                            add_data_thuan(time_start,time_end);
                        }else if(time_end == time_start){
                            add_data();
                            }else {
                            add_data_nghich(time_end,time_start);
                             }
                    }else {
                        if(time_end > time_start){
                            if(time_end_start > time_start){
                                remove_data();
                                add_data_thuan(time_start,time_end);
                            }
                        }else if(time_end == time_start){
                            remove_data();
                            add_data();
                        }else {
                            if (time_start >= time_end_start) {
                                add_data_nghich(time_end, time_start);
                            } else {
                                remove_data();
                                add_data_nghich(time_end, time_start);
                            }
                        }

                    }
                    if((time_start_start <24)&&(time_start> time_start_start)){
                        add_data_thuan1(time_start_start, time_start-1);
                    }
                    kt_id();
                    Toast.makeText(tgb_editscr.this, " da luu", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
    private void nhap_time() {

        final Calendar calendar= Calendar.getInstance();
        int gio= calendar.get(Calendar.HOUR);
        int phut= calendar.get(Calendar.MINUTE);

        TimePickerDialog timepickerdialog= new TimePickerDialog(tgb_editscr.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(0,0,0,hourOfDay,minute);

                SimpleDateFormat simpledate= new SimpleDateFormat("HH:mm");
                String s= simpledate.format(calendar.getTime());
                tv_time_end.setText(s);

            }
        },gio,phut,true);
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFERENCES_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(TIME_END,String.valueOf(gio));
        editor.apply();
        timepickerdialog.show();
    }
    public void add_data_thuan(int a,int b) {
        for (int i = a; i < b+1; i++) {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME + String.valueOf(i), MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(NOTE, edt_cv.getText().toString());
            editor.putString(TIME_END, tv_time_end.getText().toString());
            editor.putString(TIME_START,String.valueOf(a));
            editor.putInt(HOUR,a);
            editor.putString(COlOR,"#1e9bef");
            editor.putString(DATE,date);
            editor.putString(MONTH,month);

            editor.apply();
        }
    }

    public void add_data_thuan1(int a,int b) {
        for (int i = a; i < b+1; i++) {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME + String.valueOf(i), MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(TIME_END, String.valueOf(b)+":00");
            editor.putString(TIME_START,String.valueOf(a));
            editor.putInt(HOUR,a);

            editor.apply();
        }
    }

    public void add_data_nghich(int a,int b){
        for(int i=0; i< a +1 ; i++){
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME + String.valueOf(i), MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(NOTE, edt_cv.getText().toString());
            editor.putString(TIME_END, tv_time_end.getText().toString());
            editor.putString(TIME_START,String.valueOf(time_start));
            editor.putInt(HOUR,time_start);
            editor.putString(COlOR,"#1e9bef");
            editor.putString(DATE,date);
            editor.putString(MONTH,month);
            editor.apply();
        }
        for(int i= b; i<24; i++){
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME + String.valueOf(i), MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(NOTE, edt_cv.getText().toString());
            editor.putString(TIME_END, tv_time_end.getText().toString());
            editor.putString(TIME_START,String.valueOf(time_start));
            editor.putInt(HOUR,time_start);
            editor.putString(COlOR,"#1e9bef");
            editor.putString(DATE,date);
            editor.putString(MONTH,month);
            editor.apply();
        }
    }
    public void add_data(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME + String.valueOf(time_start), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NOTE, edt_cv.getText().toString());
        editor.putString(TIME_END, tv_time_end.getText().toString());
        editor.putString(TIME_START,String.valueOf(time_start));
        editor.putInt(HOUR,time_start);
        editor.putString(COlOR,"#1e9bef");
        editor.putString(DATE,date);
        editor.putString(MONTH,month);
        editor.apply();
    }

    public void read_data(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME+ID,MODE_PRIVATE);
        edt_cv.setText(sharedPreferences.getString(NOTE,""));
        tv_time_end.setText(sharedPreferences.getString(TIME_END,"0"));
        time_start_start = sharedPreferences.getInt(HOUR,24);

    }
    public void remove_data(){
        for (int i= time_end+1; i<time_end_start +1 ;i++) {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME + String.valueOf(i), MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
        }
    }
    public void remove_data_nghich(){
        for(int i = time_end; i< 24 ; i++){
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME + String.valueOf(i), MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
        }
        for (int i= 0; i<time_end_start +1 ;i++) {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME + String.valueOf(i), MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
        }
    }
    public void kt_id(){
        switch (ID){
            case "0":{
                KT0=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_0, KT0);
                editor.apply();
                break;
            }
            case "1":{
                KT1=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_1, KT1);
                editor.apply();

                break;
            }
            case "2":{
                KT2=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_2, KT2);
                editor.apply();
                break;
            }
            case "3":{
                KT3=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_3, KT3);
                editor.apply();
                break;
            }
            case "4":{
                KT4=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_4, KT4);
                editor.apply();
                break;
            }
            case "5":{
                KT5=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_5, KT5);
                editor.apply();
                break;
            }
            case "6":{
                KT6=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_6, KT6);
                editor.apply();
                break;
            }
            case "7":{
                KT7=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_7, KT7);
                editor.apply();
                break;
            }
            case "8":{
                KT8=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_8, KT8);
                editor.apply();
                break;
            }
            case "9":{
                KT9=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_9, KT9);
                editor.apply();
                break;
            }
            case "10":{
                KT10=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_10, KT10);
                editor.apply();
                break;
            }
            case "11":{
                KT11=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_11, KT11);
                editor.apply();
                break;
            }
            case "12":{
                KT12=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_12, KT12);
                editor.apply();
                break;
            }
            case "13":{
                KT13=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_13, KT13);
                editor.apply();
                break;
            }
            case "14":{
                KT14=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_14, KT14);
                editor.apply();
                break;
            }
            case "15":{
                KT15=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_15, KT15);
                editor.apply();
                break;
            }
            case "16":{
                KT16=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_16, KT16);
                editor.apply();
                break;
            }
            case "17":{
                KT17=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_17, KT17);
                editor.apply();
                break;
            }
            case "18":{
                KT18=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_18, KT18);
                editor.apply();
                break;
            }
            case "19":{
                KT19=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_19, KT19);
                editor.apply();
                break;
            }
            case "20":{
                KT20=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_20, KT20);
                editor.apply();
                break;
            }
            case "21":{
                KT21=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_21, KT21);
                editor.apply();
                break;
            }
            case "22":{
                KT22=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_22, KT22);
                editor.apply();
                break;
            }
            case "23":{
                KT23=1;
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT, MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putInt(KT_23, KT23);
                editor.apply();
                break;
            }
        }
    }
    }
