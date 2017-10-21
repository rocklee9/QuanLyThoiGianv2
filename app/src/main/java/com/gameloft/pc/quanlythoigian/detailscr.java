package com.gameloft.pc.quanlythoigian;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class detailscr extends AppCompatActivity {

    private final String SHARED_PREFERENCES_NAME="data_detailscr";
    private final String MON="mon";
    private final String PHONG="phong";
    private final String THOIGIAN="thoigian";
    private final String GIANGVIEN="giangvien";
    private final String EMAIL="email";
    private final String SDT="sdt";
    TextView txtten,txtphong,txttg,txtgv,txtemail,txtsdt,monhoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailscr);

        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();

        //final String mon=null;
    }

    private void init() {
        final String mon=null;
    }

    private void getWidgets() {
        txtten=(TextView)findViewById(R.id.txttenm);
        txtphong=(TextView)findViewById(R.id.txtphong);
        txttg=(TextView)findViewById(R.id.txttg);
        txtgv=(TextView)findViewById(R.id.txtgv);
        txtemail=(TextView)findViewById(R.id.txtemail);
        txtsdt=(TextView)findViewById(R.id.txtsdt);
        monhoc=(TextView)findViewById(R.id.monhoc);
    }

    private void setWidgets() {
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFERENCES_NAME,MODE_PRIVATE);
        txtten.setText(sharedPreferences.getString(MON,""));
        monhoc.setText(sharedPreferences.getString(MON,"MON HOC"));
        txtphong.setText(sharedPreferences.getString(PHONG,""));
        txttg.setText(sharedPreferences.getString(THOIGIAN,""));
        txtgv.setText(sharedPreferences.getString(GIANGVIEN,""));
        txtemail.setText(sharedPreferences.getString(EMAIL,""));
        txtsdt.setText(sharedPreferences.getString(SDT,""));
    }

    private void addWidgetsListener() {
    }


}

