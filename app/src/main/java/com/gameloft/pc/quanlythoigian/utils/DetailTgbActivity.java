package com.gameloft.pc.quanlythoigian.utils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gameloft.pc.quanlythoigian.MyDatabase.DatabaseAdapter;
import com.gameloft.pc.quanlythoigian.R;
import com.gameloft.pc.quanlythoigian.models.MonHoc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DetailTgbActivity extends AppCompatActivity {

    private Button btn_chinh_sua;
    private Button btn_xoa;
    private ImageButton btn_tro_lai;
    private TextView tv_cv;
    private TextView tv_time_end;
    private TextView tv_thoigian;

    private String mytime;
    private String ID;
    private int time_start;
    private int time_start_id;
    private int time_end;
    private  String date;

    List<MonHoc> listMonHoc;
    DatabaseAdapter database;

    private  String SHARED_PREFERENCES_NAME;
    private final String NOTE="note";
    private final String DATE="date";
    private final String DAY="day";
    private final String MONTH="month";
    private final String TIME_START="time_start";
    private final String TIME_END="time_end";
    private final String MINUTE="minute";


    private  String SHARED_PREFERENCES_NAME_KT;
    private String KT_0="0", KT_1="1", KT_2="2", KT_3="3", KT_4="4", KT_5="5", KT_6="6", KT_7="7", KT_8="8", KT_9="9", KT_10="10",
            KT_11="11", KT_12="12", KT_13="13", KT_14="14", KT_15="15", KT_16="16", KT_17="17", KT_18="18", KT_19="19", KT_20="20",
            KT_21="21", KT_22="22", KT_23="23";

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
        btn_tro_lai = (ImageButton)findViewById(R.id.btn_huy);
        tv_cv = (TextView)findViewById(R.id.tv_cv);
        tv_time_end = (TextView)findViewById(R.id.tv_time_end);
        tv_thoigian = (TextView)findViewById(R.id.tv_time);

        Intent intent = getIntent();
        Bundle bd = intent.getBundleExtra("key");
        mytime= bd.get("thoi_gian").toString();
        ID=bd.get("id").toString();
        time_start=bd.getInt("time_start");
        date=bd.getString("date");
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
                Intent intent = new Intent(DetailTgbActivity.this, EditTgbActivity.class);
                Bundle bd = new Bundle();
                bd.putString("thoi_gian",mytime);
                bd.putString("id",ID);
                bd.putInt("time_start",time_start);
                bd.putString("date",date);
                intent.putExtra("key",bd);
                startActivityForResult(intent,2);
            }
        });

        btn_tro_lai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(2);
               finish();
            }
        });

        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences1 = getSharedPreferences(SHARED_PREFERENCES_NAME+String.valueOf(time_start),MODE_PRIVATE);
                time_start_id = Integer.parseInt(sharedPreferences1.getString(TIME_START,"24"));
                if(time_start_id<24) {
                    for (int i = time_start_id; i < time_end ; i++) {
                        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME + String.valueOf(i), MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.apply();
                    }
                    remove_kt();
                    setResult(5);
                    finish();
                }else {
                    Toast.makeText(DetailTgbActivity.this,R.string.khong_co_du_lieu_de_xoa,Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
    public void read_data(){

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME+ID,MODE_PRIVATE);
        tv_cv.setText(sharedPreferences.getString(NOTE,""));
        tv_time_end.setText(sharedPreferences.getString(TIME_END,"0"));
        tv_thoigian.setText(sharedPreferences.getString(DATE,"--")
                + " , " + sharedPreferences.getString(DAY,"--")
                +" thang " + sharedPreferences.getString(MONTH,"--")+ ",  "
                + sharedPreferences.getString(TIME_START," ") + ":00 - "
                + sharedPreferences.getString(TIME_END,"--") );
        read_data_tkb();
    }
    public  String layngay(String dateOfYear){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        String dayOfWeek = "";
        try {
            calendar.setTime(sdf.parse(dateOfYear));
            switch (calendar.get(Calendar.DAY_OF_WEEK)){
                case 1:
                    dayOfWeek = "1";
                    break;
                case 2:
                    dayOfWeek = "2";
                    break;
                case 3:
                    dayOfWeek = "3";
                    break;
                case 4:
                    dayOfWeek = "4";
                    break;
                case 5:
                    dayOfWeek = "5";
                    break;
                case 6:
                    dayOfWeek = "6";
                    break;
                case 7:
                    dayOfWeek = "7";
                    break;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dayOfWeek;
    }
    public void read_data_tkb(){
        listMonHoc =  new ArrayList<>();
        database = new DatabaseAdapter(DetailTgbActivity.this);
        database.open();
        String n = layngay(mytime);
        switch (n){
            case "1":{
                listMonHoc = database.getData(8);
                break;
            }
            case "2":{
                listMonHoc = database.getData(2);
                break;
            }
            case "3":{
                listMonHoc = database.getData(3);
                break;
            }
            case "4":{
                listMonHoc = database.getData(4);
                break;
            }
            case "5":{
                listMonHoc = database.getData(5);
                break;
            }
            case "6":{
                listMonHoc = database.getData(6);
                break;
            }
            case "7":{
                listMonHoc = database.getData(7);
                break;
            }
        }
        MonHoc monHoc;
        for(int i=0; i<listMonHoc.size(); i++){
            monHoc=listMonHoc.get(i);
            String time_start = monHoc.getThoiGian1();
            String time_end = monHoc.getThoiGian2();
            String s = "hoc mon "+ monHoc.getTenMonHoc() + " tai phong "+ monHoc.getPhong();
            String [] tam = time_start.split(":");
            int time1 = Integer.parseInt(tam[0].trim());
            String [] tam1 = time_end.split(":");
            int time2;
            if(Integer.parseInt(tam1[1].trim()) < 30){
                time2 = Integer.parseInt(tam1[0].trim());
            }else {
                time2 = Integer.parseInt(tam1[0].trim())+1;
            }
            if(time1 <= Integer.parseInt(ID) && Integer.parseInt(ID) < time2){
                String [] tam2 = mytime.split("-");
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME+ID,MODE_PRIVATE);
                tv_cv.setText(sharedPreferences.getString(NOTE,s));
                tv_time_end.setText(sharedPreferences.getString(TIME_END,String.valueOf(time2)+":00"));
                tv_thoigian.setText(sharedPreferences.getString(DATE,date)
                        + " , " + sharedPreferences.getString(DAY,tam2[0])
                        +" thang " + sharedPreferences.getString(MONTH,tam2[1])+ ",  "
                        + sharedPreferences.getString(TIME_START,String.valueOf(time1)) + ":00 - "
                        + sharedPreferences.getString(TIME_END,String.valueOf(time2)) +":00");
            }
        }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2){
            if(resultCode==3)
                read_data();

        }
    }
}
