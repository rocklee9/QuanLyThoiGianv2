package com.gameloft.pc.quanlythoigian;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Detail_tgb extends AppCompatActivity {

    private Button btn_chinh_sua;
    private Button btn_xoa;
    private Button btn_tro_lai;
    private TextView tv_cv;
    private TextView tv_time_end;
    private TextView tv_thoigian;

    private String mytime;
    private String ID;
    private int time_start;
    private int time_start_id;
    private int time_end;

    private  String SHARED_PREFERENCES_NAME;
    private final String NOTE="note";
    private final String DATE="date";
    private final String DAY="day";
    private final String MONTH="month";
    private final String TIME_START="time_start";
    private final String TIME_END="time_end";
    private final String MINUTE="minute";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tgb);

        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();
    }

    private void init() {

    }

    private void getWidgets() {
        btn_chinh_sua = (Button)findViewById(R.id.btn_sua_them);
        btn_xoa = (Button)findViewById(R.id.btn_xoa);
        btn_tro_lai = (Button)findViewById(R.id.btn_huy);
        tv_cv = (TextView)findViewById(R.id.tv_cv);
        tv_time_end = (TextView)findViewById(R.id.tv_time_end);
        tv_thoigian = (TextView)findViewById(R.id.tv_time);

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
        String s= tv_time_end.getText().toString();
        String [] s1= s.split(":");
        time_end= Integer.parseInt(s1[0].trim());
    }

    private void addWidgetsListener() {

        btn_chinh_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detail_tgb.this,tgb_editscr.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",mytime);
                bd.putString("id",ID);
                bd.putInt("time_start",time_start);
                intent.putExtra("key",bd);
                startActivity(intent);
            }
        });

        btn_tro_lai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences1 = getSharedPreferences(SHARED_PREFERENCES_NAME+String.valueOf(time_start),MODE_PRIVATE);
                time_start_id = Integer.parseInt(sharedPreferences1.getString(TIME_START,"24"));
                if(time_start_id<24) {
                    if (time_end > time_start) {


                        for (int i = time_start_id; i < time_end + 1; i++) {
                            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME + String.valueOf(i), MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.clear();
                            editor.apply();
                        }
                    } else {
                        for (int i = 0; i <= time_end; i++) {
                            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME + String.valueOf(i), MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.clear();
                            editor.apply();
                        }
                        for (int i = time_start_id; i < 24; i++) {
                            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME + String.valueOf(i), MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.clear();
                            editor.apply();
                        }
                    }
                    remove_kt();
                    Intent intent = new Intent(Detail_tgb.this,thoi_gian_bieu.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(Detail_tgb.this,R.string.khong_co_du_lieu_de_xoa,Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
    public void read_data(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME+ID,MODE_PRIVATE);
        tv_cv.setText(sharedPreferences.getString(NOTE,""));
        tv_time_end.setText(sharedPreferences.getString(TIME_END,"0"));
        tv_thoigian.setText("th " + sharedPreferences.getString(DAY,"--") + "," + sharedPreferences.getString(DATE,"--") +"thang " + sharedPreferences.getString(MONTH,"--")+ ",    "+ sharedPreferences.getString(TIME_START," ") + " to " + sharedPreferences.getString(TIME_END,"--") );
    }
    public void remove_kt(){
        SharedPreferences sharedPreferences2 = getSharedPreferences(SHARED_PREFERENCES_NAME_KT,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences2.edit();

        switch (time_start_id){
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
