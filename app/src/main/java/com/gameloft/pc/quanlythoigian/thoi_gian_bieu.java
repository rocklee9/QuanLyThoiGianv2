package com.gameloft.pc.quanlythoigian;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class thoi_gian_bieu extends AppCompatActivity {

    private TextView tv_day;
    private TextView tv_note_0;
    private TextView tv_note_1;
    private TextView tv_note_2;
    private TextView tv_note_3;
    private TextView tv_note_4;
    private TextView tv_note_5;
    private TextView tv_note_6;
    private TextView tv_note_7;
    private TextView tv_note_8;
    private TextView tv_note_9;
    private TextView tv_note_10;
    private TextView tv_note_11;
    private TextView tv_note_12;
    private TextView tv_note_13;
    private TextView tv_note_14;
    private TextView tv_note_15;
    private TextView tv_note_16;
    private TextView tv_note_17;
    private TextView tv_note_18;
    private TextView tv_note_19;
    private TextView tv_note_20;
    private TextView tv_note_21;
    private TextView tv_note_22;
    private TextView tv_note_23;

    private Button btn_week;

    private  String SHARED_PREFERENCES_NAME;
    String mytime;
    private final String NOTE="note";
    private final String DATE="date";
    private final String DAY="day";
    private final String MONTH="month";
    private final String TIME_START="time_start";
    private final String TIME_END="time_end";
    private final String COlOR="color";
    private final String COlOR_START="#ffffff";

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

        btn_week= (Button)findViewById(R.id.btn_week);
        set_day();
        mytime= tv_day.getText().toString();
        SHARED_PREFERENCES_NAME=mytime;
        SHARED_PREFERENCES_NAME_KT=mytime;
        read_kt();
        read_data();
    }

    private void setWidgets() {


    }

    private void addWidgetsListener() {



        tv_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chon_ngay();
                mytime= tv_day.getText().toString();
                SHARED_PREFERENCES_NAME=mytime;
                read_data();
                SHARED_PREFERENCES_NAME_KT=mytime;
                read_kt();

            }

        });




        btn_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(thoi_gian_bieu.this,thoi_gian_bieu_tuan.class);
                startActivity(intent);
            }
        });

    }

//    private void dialog_note() {
//
//
//        CustomDialog dialog= new CustomDialog(this);
//
//
//        dialog.show();
//
//    }

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
            }
        },nam,thang,ngay);

        datepickerdialog.show();
    }

    private String set_day(){
        Calendar calendar= Calendar.getInstance();
        int nam= calendar.get(calendar.YEAR);
        int thang= calendar.get(calendar.MONTH);
        int ngay= calendar.get(calendar.DATE);

        calendar.set(nam, thang, ngay);
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
        tv_day.setText(simpleDateFormat.format(calendar.getTime()));
        return simpleDateFormat.format(calendar.getTime());
    }
    public void read_kt(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME_KT,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
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
        editor.apply();
    }

    public void read_data(){

        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFERENCES_NAME+"0",MODE_PRIVATE);
        if(KT0==1) {
            tv_note_0.setText(sharedPreferences.getString(NOTE, ""));
            tv_note_0.setBackgroundResource(R.drawable.custom5);
        }else {
            String s = sharedPreferences.getString(COlOR, "null");
            if (s != "null")
                tv_note_0.setBackgroundColor(Color.parseColor(s));
        }

        SharedPreferences sharedPreferences1=getSharedPreferences(SHARED_PREFERENCES_NAME+"1",MODE_PRIVATE);
        if(KT1==1) {
            tv_note_1.setText(sharedPreferences1.getString(NOTE, ""));
            tv_note_1.setBackgroundResource(R.drawable.custom5);
        }else {
            String s1 = sharedPreferences1.getString(COlOR, "null");
            if (s1 != "null")
                tv_note_1.setBackgroundColor(Color.parseColor(s1));
        }

        SharedPreferences sharedPreferences2=getSharedPreferences(SHARED_PREFERENCES_NAME+"2",MODE_PRIVATE);
        if(KT2==1) {
            tv_note_2.setText(sharedPreferences2.getString(NOTE, ""));
            tv_note_2.setBackgroundResource(R.drawable.custom5);
        }else {
            String s2 = sharedPreferences2.getString(COlOR, "null");
            if (s2 != "null")
                tv_note_2.setBackgroundColor(Color.parseColor(s2));
        }

        SharedPreferences sharedPreferences3=getSharedPreferences(SHARED_PREFERENCES_NAME+"3",MODE_PRIVATE);
        if(KT3==1) {
            tv_note_3.setText(sharedPreferences3.getString(NOTE, ""));
            tv_note_3.setBackgroundResource(R.drawable.custom5);
        }else {
            String s3 = sharedPreferences3.getString(COlOR, "null");
            if (s3 != "null")
                tv_note_3.setBackgroundColor(Color.parseColor(s3));
        }

        SharedPreferences sharedPreferences4=getSharedPreferences(SHARED_PREFERENCES_NAME+"4",MODE_PRIVATE);
        if(KT4==1) {
            tv_note_4.setText(sharedPreferences4.getString(NOTE, ""));
            tv_note_4.setBackgroundResource(R.drawable.custom5);
        }else {
            String s4 = sharedPreferences4.getString(COlOR, "null");
            if (s4 != "null")
                tv_note_4.setBackgroundColor(Color.parseColor(s4));
        }

        SharedPreferences sharedPreferences5=getSharedPreferences(SHARED_PREFERENCES_NAME+"5",MODE_PRIVATE);
        if(KT5==1) {
            tv_note_5.setText(sharedPreferences5.getString(NOTE, ""));
            tv_note_5.setBackgroundResource(R.drawable.custom5);
        }else {
            String s5 = sharedPreferences5.getString(COlOR, "null");
            if (s5 != "null")
                tv_note_5.setBackgroundColor(Color.parseColor(s5));
        }

        SharedPreferences sharedPreferences6=getSharedPreferences(SHARED_PREFERENCES_NAME+"6",MODE_PRIVATE);
        if(KT6==1) {
            tv_note_6.setText(sharedPreferences6.getString(NOTE, ""));
            tv_note_6.setBackgroundResource(R.drawable.custom5);
        }else {
            String s6 = sharedPreferences6.getString(COlOR, "null");
            if (s6 != "null")
                tv_note_6.setBackgroundColor(Color.parseColor(s6));
        }

        SharedPreferences sharedPreferences7=getSharedPreferences(SHARED_PREFERENCES_NAME+"7",MODE_PRIVATE);
        if(KT7==1) {
            tv_note_7.setText(sharedPreferences7.getString(NOTE, ""));
            tv_note_7.setBackgroundResource(R.drawable.custom5);
        }else {
            String s7 = sharedPreferences7.getString(COlOR, "null");
            if (s7 != "null")
                tv_note_7.setBackgroundColor(Color.parseColor(s7));
        }

        SharedPreferences sharedPreferences8=getSharedPreferences(SHARED_PREFERENCES_NAME+"8",MODE_PRIVATE);
        if(KT8==1) {
            tv_note_8.setText(sharedPreferences8.getString(NOTE, ""));
            tv_note_8.setBackgroundResource(R.drawable.custom5);
        }else {
            String s8 = sharedPreferences8.getString(COlOR, "null");
            if (s8 != "null")
                tv_note_8.setBackgroundColor(Color.parseColor(s8));
        }

        SharedPreferences sharedPreferences9=getSharedPreferences(SHARED_PREFERENCES_NAME+"9",MODE_PRIVATE);
        if(KT9==1) {
            tv_note_9.setText(sharedPreferences9.getString(NOTE, ""));
            tv_note_9.setBackgroundResource(R.drawable.custom5);
        }else {
            String s9 = sharedPreferences9.getString(COlOR, "null");
            if (s9 != "null")
                tv_note_9.setBackgroundColor(Color.parseColor(s9));
        }

        SharedPreferences sharedPreferences10=getSharedPreferences(SHARED_PREFERENCES_NAME+"10",MODE_PRIVATE);
        if(KT10==1) {
            tv_note_10.setText(sharedPreferences10.getString(NOTE, ""));
            tv_note_10.setBackgroundResource(R.drawable.custom5);
        }else {
            String s10 = sharedPreferences10.getString(COlOR, "null");
            if (s10 != "null")
                tv_note_10.setBackgroundColor(Color.parseColor(s10));
        }

        SharedPreferences sharedPreferences11=getSharedPreferences(SHARED_PREFERENCES_NAME+"11",MODE_PRIVATE);
        if(KT11==1) {
            tv_note_11.setText(sharedPreferences11.getString(NOTE, ""));
            tv_note_11.setBackgroundResource(R.drawable.custom5);
        }else {
            String s11 = sharedPreferences11.getString(COlOR, "null");
            if (s11 != "null")
                tv_note_11.setBackgroundColor(Color.parseColor(s11));
        }

        SharedPreferences sharedPreferences12=getSharedPreferences(SHARED_PREFERENCES_NAME+"12",MODE_PRIVATE);
        if(KT12==1) {
            tv_note_12.setText(sharedPreferences12.getString(NOTE, ""));
            tv_note_12.setBackgroundResource(R.drawable.custom5);
        }else {
            String s12 = sharedPreferences12.getString(COlOR, "null");
            if (s12 != "null")
                tv_note_12.setBackgroundColor(Color.parseColor(s12));
        }

        SharedPreferences sharedPreferences13=getSharedPreferences(SHARED_PREFERENCES_NAME+"13",MODE_PRIVATE);
        if(KT13==1) {
            tv_note_13.setText(sharedPreferences13.getString(NOTE, ""));
            tv_note_13.setBackgroundResource(R.drawable.custom5);
        }else {
            String s13 = sharedPreferences13.getString(COlOR, "null");
            if (s13 != "null")
                tv_note_13.setBackgroundColor(Color.parseColor(s13));
        }

        SharedPreferences sharedPreferences14=getSharedPreferences(SHARED_PREFERENCES_NAME+"14",MODE_PRIVATE);
        if(KT14==1) {
            tv_note_14.setText(sharedPreferences14.getString(NOTE, ""));
            tv_note_14.setBackgroundResource(R.drawable.custom5);
        }else {
            String s14 = sharedPreferences14.getString(COlOR, "null");
            if (s14 != "null")
                tv_note_14.setBackgroundColor(Color.parseColor(s14));
        }

        SharedPreferences sharedPreferences15=getSharedPreferences(SHARED_PREFERENCES_NAME+"15",MODE_PRIVATE);
        if(KT15==1) {
            tv_note_15.setText(sharedPreferences15.getString(NOTE, ""));
            tv_note_15.setBackgroundResource(R.drawable.custom5);
        }else {
            String s15 = sharedPreferences15.getString(COlOR, "null");
            if (s15 != "null")
                tv_note_15.setBackgroundColor(Color.parseColor(s15));
        }

        SharedPreferences sharedPreferences16=getSharedPreferences(SHARED_PREFERENCES_NAME+"16",MODE_PRIVATE);
        if(KT16==1) {
            tv_note_16.setText(sharedPreferences16.getString(NOTE, ""));
            tv_note_16.setBackgroundResource(R.drawable.custom5);
        }else {
            String s16 = sharedPreferences16.getString(COlOR, "null");
            if (s16 != "null")
                tv_note_16.setBackgroundColor(Color.parseColor(s16));
        }

        SharedPreferences sharedPreferences17=getSharedPreferences(SHARED_PREFERENCES_NAME+"17",MODE_PRIVATE);
        if(KT17==1) {
            tv_note_17.setText(sharedPreferences17.getString(NOTE, ""));
            tv_note_17.setBackgroundResource(R.drawable.custom5);
        }else {
            String s17 = sharedPreferences17.getString(COlOR, "null");
            if (s17 != "null")
                tv_note_17.setBackgroundColor(Color.parseColor(s17));
        }

        SharedPreferences sharedPreferences18=getSharedPreferences(SHARED_PREFERENCES_NAME+"18",MODE_PRIVATE);
        if(KT18==1) {
            tv_note_18.setText(sharedPreferences18.getString(NOTE, ""));
            tv_note_18.setBackgroundResource(R.drawable.custom5);
        }else {
            String s18 = sharedPreferences18.getString(COlOR, "null");
            if (s18 != "null")
                tv_note_18.setBackgroundColor(Color.parseColor(s18));
        }

        SharedPreferences sharedPreferences19=getSharedPreferences(SHARED_PREFERENCES_NAME+"19",MODE_PRIVATE);
        if(KT19==1) {
            tv_note_19.setText(sharedPreferences19.getString(NOTE, ""));
            tv_note_19.setBackgroundResource(R.drawable.custom5);
        }else {
            String s19 = sharedPreferences19.getString(COlOR, "null");
            if (s19 != "null")
                tv_note_19.setBackgroundColor(Color.parseColor(s19));
        }

        SharedPreferences sharedPreferences20=getSharedPreferences(SHARED_PREFERENCES_NAME+"20",MODE_PRIVATE);
        if(KT20==1) {
            tv_note_20.setText(sharedPreferences20.getString(NOTE, ""));
            tv_note_20.setBackgroundResource(R.drawable.custom5);
        }else {
            String s20 = sharedPreferences20.getString(COlOR, "null");
            if (s20 != "null")
                tv_note_20.setBackgroundColor(Color.parseColor(s20));
        }

        SharedPreferences sharedPreferences21=getSharedPreferences(SHARED_PREFERENCES_NAME+"21",MODE_PRIVATE);
        if(KT21==1) {
            tv_note_21.setText(sharedPreferences21.getString(NOTE, ""));
            tv_note_21.setBackgroundResource(R.drawable.custom5);
        }else {
            String s21 = sharedPreferences21.getString(COlOR, "null");
            if (s21 != "null")
                tv_note_21.setBackgroundColor(Color.parseColor(s21));
        }

        SharedPreferences sharedPreferences22=getSharedPreferences(SHARED_PREFERENCES_NAME+"22",MODE_PRIVATE);
        if(KT22==1) {
            tv_note_22.setText(sharedPreferences22.getString(NOTE, ""));
            tv_note_22.setBackgroundResource(R.drawable.custom5);
        }else {
            String s22 = sharedPreferences22.getString(COlOR, "null");
            if (s22 != "null")
                tv_note_22.setBackgroundColor(Color.parseColor(s22));
        }

        SharedPreferences sharedPreferences23=getSharedPreferences(SHARED_PREFERENCES_NAME+"23",MODE_PRIVATE);
        if(KT23==1) {
            tv_note_23.setText(sharedPreferences23.getString(NOTE, ""));
            tv_note_23.setBackgroundResource(R.drawable.custom5);
        }else {
            String s23 = sharedPreferences23.getString(COlOR, "null");
            if (s23 != "null")
                tv_note_23.setBackgroundColor(Color.parseColor(s23));
        }
    }
    public void onClick(View view){

        switch (view.getId()){
            case R.id.tv_0 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","0");
                bd.putInt("time_start",0);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }
            case R.id.tv_1 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","1");
                bd.putInt("time_start",1);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }
            case R.id.tv_2 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","2");
                bd.putInt("time_start",2);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }
            case R.id.tv_3 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","3");
                bd.putInt("time_start",3);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }case R.id.tv_4 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","4");
                bd.putInt("time_start",4);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }case R.id.tv_5 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","5");
                bd.putInt("time_start",5);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }case R.id.tv_6 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","6");
                bd.putInt("time_start",6);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }case R.id.tv_7 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","7");
                bd.putInt("time_start",7);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }case R.id.tv_8 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","8");
                bd.putInt("time_start",8);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }case R.id.tv_9 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","9");
                bd.putInt("time_start",9);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }case R.id.tv_10 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","10");
                bd.putInt("time_start",10);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }case R.id.tv_11 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","11");
                bd.putInt("time_start",11);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }case R.id.tv_12 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","12");
                bd.putInt("time_start",12);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }case R.id.tv_13 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","13");
                bd.putInt("time_start",13);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }case R.id.tv_14 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","14");
                bd.putInt("time_start",14);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }case R.id.tv_15 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","15");
                bd.putInt("time_start",15);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }case R.id.tv_16 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","16");
                bd.putInt("time_start",16);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }case R.id.tv_17 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","17");
                bd.putInt("time_start",17);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }case R.id.tv_18 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","18");
                bd.putInt("time_start",18);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }case R.id.tv_19 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","19");
                bd.putInt("time_start",19);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }case R.id.tv_20 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","20");
                bd.putInt("time_start",20);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }case R.id.tv_21 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","21");
                bd.putInt("time_start",21);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }case R.id.tv_22 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","22");
                bd.putInt("time_start",22);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }case R.id.tv_23 :{
                Intent intent = new Intent(thoi_gian_bieu.this,Detail_tgb.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",tv_day.getText().toString());
                bd.putString("id","23");
                bd.putInt("time_start",23);
                intent.putExtra("key",bd);
                startActivity(intent);
                break;
            }
        }
    }
}
