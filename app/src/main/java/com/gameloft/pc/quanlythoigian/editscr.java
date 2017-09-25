package com.gameloft.pc.quanlythoigian;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class editscr extends AppCompatActivity {
    private final String SHARED_PREFERENCES_NAME="data_detailscr";
    private final String MON="mon";
    private final String PHONG="phong";
    private final String THOIGIAN="thoigian";
    private final String GIANGVIEN="giangvien";
    private final String EMAIL="email";
    private final String SDT="sdt";
    EditText edtmon,edtphong,edttg,edtgv,edtemail,edtsdt;
    Button btnluu,btnxoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editscr);

        anhxa();
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bd=new Bundle();
                add_data();
                Intent ht=new Intent(editscr.this,thoi_khoa_bieu.class);
                startActivity(ht);
            }


        });
        btnxoa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Bundle bd=new Bundle();
                remove_data();
                Intent ht1=new Intent(editscr.this,thoi_khoa_bieu.class);
                startActivity(ht1);
            }
        });
        inraeditscr();
    }
    private void anhxa(){
        edtmon=(EditText)findViewById(R.id.môn);
        edtphong=(EditText)findViewById(R.id.phong);
        edttg=(EditText)findViewById(R.id.thoigian);
        edtgv=(EditText)findViewById(R.id.giangvien);
        edtemail=(EditText)findViewById(R.id.email);
        edtsdt=(EditText)findViewById(R.id.sdt);
        btnluu=(Button)findViewById(R.id.btnhienthi);
        btnxoa=(Button)findViewById(R.id.btnxoadata);
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
    public void inraeditscr(){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFERENCES_NAME,MODE_PRIVATE);
        edtmon.setText(sharedPreferences.getString(MON,""));
        edtphong.setText(sharedPreferences.getString(PHONG,""));
        edttg.setText(sharedPreferences.getString(THOIGIAN,""));
        edtgv.setText(sharedPreferences.getString(GIANGVIEN,""));
        edtemail.setText(sharedPreferences.getString(EMAIL,""));
        edtsdt.setText(sharedPreferences.getString(SDT,""));
    }
    public void remove_data(){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFERENCES_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
