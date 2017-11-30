package com.gameloft.pc.quanlythoigian.utils;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.gameloft.pc.quanlythoigian.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditTgbActivity extends AppCompatActivity {


    private  String SHARED_PREFERENCES_NAME_KT;
    private String KT_0="0", KT_1="1", KT_2="2", KT_3="3", KT_4="4", KT_5="5", KT_6="6", KT_7="7", KT_8="8", KT_9="9", KT_10="10",
            KT_11="11", KT_12="12", KT_13="13", KT_14="14", KT_15="15", KT_16="16", KT_17="17", KT_18="18", KT_19="19", KT_20="20",
            KT_21="21", KT_22="22", KT_23="23";

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
    private String day;

    private int KT1, KT2, KT3, KT4, KT5, KT6, KT7, KT8, KT9, KT10, KT11, KT12, KT13, KT14, KT15,
            KT16, KT17, KT18, KT19, KT20, KT21, KT22, KT23, KT0;

    private int KTmau;
    private int time_end;
    private int time_start;
    private int time_end_start;
    private int time_start_start;
    private String mytime;
    private String ID;

    private TextView tv_time_end;
    private Button btn_huy;
    private Button btn_luu;
    private EditText edt_cv;
    private RadioGroup group;
    private RadioButton raidio_xanh, raidio_do, raidio_xanh_la, raidio_tim, raidio_vang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tgb_edit);

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
        group= (RadioGroup)findViewById(R.id.radiogroup_mau);
        raidio_xanh = (RadioButton)findViewById(R.id.mau_xanh);
        raidio_do = (RadioButton)findViewById(R.id.mau_do);
        raidio_xanh_la = (RadioButton)findViewById(R.id.mau_xanh_la_cay);
        raidio_tim = (RadioButton)findViewById(R.id.mau_tim);
        raidio_vang = (RadioButton)findViewById(R.id.mau_vang);

        Intent intent = getIntent();
        Bundle bd = intent.getBundleExtra("key");
        mytime= bd.get("thoi_gian").toString();
        ID=bd.get("id").toString();
        time_start=bd.getInt("time_start");
        date=bd.getString("date");
        SHARED_PREFERENCES_NAME=mytime;
        SHARED_PREFERENCES_NAME_KT=mytime;

        SharedPreferences sharedPreferences = getSharedPreferences(mytime + String.valueOf(time_start) , MODE_PRIVATE);
        KTmau= sharedPreferences.getInt(COlOR , 1);
        get_radiobuttom(KTmau);

    }

    private void setWidgets() {

        read_data();
        String s = tv_time_end.getText().toString();
        String [] s1= s.split(":");
        time_end_start = Integer.valueOf(s1[0].trim());
        s= mytime;
         s1= s.split("-");
        day = s1[0].trim();
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

                chon_mau();
                String s = tv_time_end.getText().toString();
                String[] s1 = s.split(":");
                time_end = Integer.parseInt(s1[0].trim());
                if(time_end == 0) time_end = 24;

                if(s=="0:00"){
                    Toast.makeText(EditTgbActivity.this,"ban vui long chon thoi gian ket thuc", Toast.LENGTH_SHORT).show();
                }else if(time_end <= time_start) {
                    Toast.makeText(EditTgbActivity.this,"thoi gian ket thuc phai lon hon thoi gian bat dau,vui long chon lai", Toast.LENGTH_SHORT).show();
                }else {

                    if(time_end_start <= time_start) {

                        SharedPreferences sharedPreferences2 = getSharedPreferences(SHARED_PREFERENCES_NAME + String.valueOf(time_end),MODE_PRIVATE);
                        String s_tam = sharedPreferences2.getString(TIME_END,"24");
                        String [] s1_tam = s_tam.split(":");
                        int tam = Integer.parseInt(s1_tam[0].trim());
                        if(tam < 24) {
                            add_data_thuan1(time_end, tam, Lay_mau());
                        }
                        add_data_thuan(time_start,time_end);


                    }else {
                        if(time_end > time_end_start) {

                            if (time_start == time_start_start) {
                               remove_data();
                                add_data_thuan(time_start, time_end);
                            } else {
                                add_data_thuan1(time_start_start, time_start,Lay_mau());
                                add_data_thuan(time_start, time_end);
                            }

                        }else {
                                add_data_thuan1(time_start_start, time_start, Lay_mau());
                                add_data_thuan1(time_end, time_end_start, Lay_mau());
                                add_data_thuan(time_start, time_end);
                        }

                    }
                    for(int i=0 ; i<24; i++){
                        SharedPreferences sharedPreferences1= getSharedPreferences(SHARED_PREFERENCES_NAME + String.valueOf(i),MODE_PRIVATE);
                        String h = sharedPreferences1.getString(TIME_START,"24");
                        kt_id(h);
                    }
                    Toast.makeText(EditTgbActivity.this, " da luu", Toast.LENGTH_SHORT).show();
                    setResult(3);
                    finish();
                }
            }
        });
    }
    private void get_radiobuttom(int i){
        switch (i){
            case 1 : {
                raidio_xanh.setChecked(true);
                break;
            }
            case 2 : {
                raidio_do.setChecked(true);
                break;
            }
            case 3 : {
                raidio_xanh_la.setChecked(true);
                break;
            }
            case 4 : {
                raidio_tim.setChecked(true);
                break;
            }
            case 5 : {
                raidio_vang.setChecked(true);
                break;
            }
        }
    }
    private void chon_mau(){
        int idChecker = group.getCheckedRadioButtonId();
        switch (idChecker){
            case R.id.mau_xanh : {
                KTmau= 1;
                break;
            }
            case R.id.mau_do : {
                KTmau= 2;
                break;
            }
            case R.id.mau_xanh_la_cay : {
                KTmau= 3;
                break;
            }
            case R.id.mau_tim : {
                KTmau= 4;
                break;
            }
            case R.id.mau_vang : {
                KTmau= 5;
                break;
            }

        }

    }
    private void nhap_time() {

        final Calendar calendar= Calendar.getInstance();
        int gio= calendar.get(Calendar.HOUR);
        int phut= calendar.get(Calendar.MINUTE);

        TimePickerDialog timepickerdialog= new TimePickerDialog(EditTgbActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(0,0,0,hourOfDay,minute);

                SimpleDateFormat simpledate= new SimpleDateFormat("HH");
                String s= simpledate.format(calendar.getTime());
                tv_time_end.setText(s+":00");

            }
        },gio,phut,true);
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFERENCES_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(TIME_END,String.valueOf(gio));
        editor.apply();
        timepickerdialog.show();
    }
    public void add_data_thuan(int a,int b) {
        for (int i = a; i < b; i++) {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME + String.valueOf(i), MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String s =sharedPreferences.getString(TIME_START,"24");
            remove_kt(Integer.parseInt(s));
            editor.putString(NOTE, edt_cv.getText().toString());
            editor.putString(TIME_END, String.valueOf(b)+":00");
            editor.putString(TIME_START,String.valueOf(a));
            editor.putInt(HOUR,a);
            editor.putInt(COlOR,KTmau);
            editor.putString(DATE,date);
            editor.putString(DAY,day);
            editor.putString(MONTH,month);
            editor.apply();
        }
    }

    public void add_data_thuan1(int a,int b,int c) {
        for (int i = a; i < b; i++) {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME + String.valueOf(i), MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String s =sharedPreferences.getString(TIME_START,"24");
            remove_kt(Integer.parseInt(s));
            editor.putString(TIME_END, String.valueOf(b)+":00");
            editor.putString(TIME_START,String.valueOf(a));
            editor.putInt(HOUR,a);
            editor.putInt(COlOR,c);
            editor.putString(DATE,date);
            editor.putString(DAY,day);
            editor.putString(MONTH,month);
            editor.apply();
        }
    }
    public void read_data(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME+ID,MODE_PRIVATE);
        edt_cv.setText(sharedPreferences.getString(NOTE,""));
        tv_time_end.setText(sharedPreferences.getString(TIME_END,"0:00"));
        time_start_start = sharedPreferences.getInt(HOUR,24);
    }
    public void remove_data(){
        for (int i= time_end; i<time_end_start; i++) {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME + String.valueOf(i), MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String s =sharedPreferences.getString(TIME_START,"24");
            remove_kt(Integer.parseInt(s));
            editor.clear();
            editor.apply();
        }
    }
    public void remove_all(int a, int b){
        for (int i= a; i< b; i++) {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME + String.valueOf(i), MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String s =sharedPreferences.getString(TIME_START,"24");
            remove_kt(Integer.parseInt(s));
            editor.clear();
            editor.apply();
        }
    }
    public void kt_id(String a){
        switch (a){
            case "0":{
                kt(KT_0 , KT0);
                break;
            }
            case "1":{
                kt(KT_1 , KT1);

                break;
            }
            case "2":{
                kt(KT_2 , KT2);
                break;
            }
            case "3":{
                kt(KT_3 , KT3);
                break;
            }
            case "4":{
                kt(KT_4 , KT4);
                break;
            }
            case "5":{
                kt(KT_5 , KT5);
                break;
            }
            case "6":{
                kt(KT_6 , KT6);
                break;
            }
            case "7":{
                kt(KT_7 , KT7);
                break;
            }
            case "8":{
                kt(KT_8 , KT8);
                break;
            }
            case "9":{
                kt(KT_9 , KT9);
                break;
            }
            case "10":{
                kt(KT_10 , KT10);
                break;
            }
            case "11":{
                kt(KT_11 , KT11);
                break;
            }
            case "12":{
                kt(KT_12 , KT12);
                break;
            }
            case "13":{
                kt(KT_13 , KT13);
                break;
            }
            case "14":{
                kt(KT_14 , KT14);
                break;
            }
            case "15":{
                kt(KT_15 , KT15);
                break;
            }
            case "16":{
                kt(KT_16 , KT16);
                break;
            }
            case "17":{
                kt(KT_17 , KT17);
                break;
            }
            case "18":{
                kt(KT_18 , KT18);
                break;
            }
            case "19":{
                kt(KT_19 , KT19);
                break;
            }
            case "20":{
                kt(KT_20 , KT20);
                break;
            }
            case "21":{
                kt(KT_21 , KT21);
                break;
            }
            case "22":{
                kt(KT_22 , KT22);
                break;
            }
            case "23":{
                kt(KT_23 , KT23);
                break;
            }
        }
    }

    public void kt(String KT_0 , int KT0){
        KT0=1;
        SharedPreferences sharedPreferences = getSharedPreferences(mytime, MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putInt(KT_0, KT0);
        editor.apply();
    }
    public int Lay_mau(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME+ID,MODE_PRIVATE);
        int k=sharedPreferences.getInt(COlOR,1);
        return k;
    }
    public void remove_kt(int a){
        SharedPreferences sharedPreferences2 = getSharedPreferences(SHARED_PREFERENCES_NAME_KT,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences2.edit();

        switch (a){
            case 0:{
                editor.remove(KT_0);
                editor.apply();
                break;
            }
            case 1:{
                editor.remove(KT_1);
                editor.apply();
                break;
            }
            case 2:{
                editor.remove(KT_2);
                editor.apply();
                break;
            }
            case 3:{
                editor.remove(KT_3);
                editor.apply();
                break;
            }
            case 4:{
                editor.remove(KT_4);
                editor.apply();
                break;
            }
            case 5:{
                editor.remove(KT_5);
                editor.apply();
                break;
            }
            case 6:{
                editor.remove(KT_6);
                editor.apply();
                break;
            }
            case 7:{
                editor.remove(KT_7);
                editor.apply();
                break;
            }
            case 8:{
                editor.remove(KT_8);
                editor.apply();
                break;
            }
            case 9:{
                editor.remove(KT_9);
                editor.apply();
                break;
            }
            case 10:{
                editor.remove(KT_10);
                editor.apply();
                break;
            }
            case 11:{
                editor.remove(KT_11);
                editor.apply();
                break;
            }
            case 12:{
                editor.remove(KT_12);
                editor.apply();
                break;
            }
            case 13:{
                editor.remove(KT_13);
                editor.apply();
                break;
            }
            case 14:{
                editor.remove(KT_14);
                editor.apply();
                break;
            }
            case 15:{
                editor.remove(KT_15);
                editor.apply();
                break;
            }
            case 16:{
                editor.remove(KT_16);
                editor.apply();
                break;
            }
            case 17:{
                editor.remove(KT_17);
                editor.apply();
                break;
            }
            case 18:{
                editor.remove(KT_18);
                editor.apply();
                break;
            }
            case 19:{
                editor.remove(KT_19);
                editor.apply();
                break;
            }
            case 20:{
                editor.remove(KT_20);
                editor.apply();
                break;
            }
            case 21:{
                editor.remove(KT_21);
                editor.apply();
                break;
            }
            case 22:{
                editor.remove(KT_22);
                editor.apply();
                break;
            }
            case 23:{
                editor.remove(KT_23);
                editor.apply();
                break;
            }
        }

    }
    }
