package com.gameloft.pc.quanlythoigian;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class detailscr extends AppCompatActivity {

    TextView txtten,txtphong,txttg,txtgv,txtemail,txtsdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailscr);
        anhxa();
        Intent lay=getIntent();
        Bundle bd=lay.getBundleExtra("hienthi");


        txtten.setText(bd.getString("mon"));
        txtphong.setText(bd.getString("phong"));
        txttg.setText(bd.getString("thoigian"));
        txtgv.setText(bd.getString("giangvien"));
        txtemail.setText(bd.getString("email"));
        txtsdt.setText(bd.getString("sdt"));

    }
    private void anhxa(){
        txtten=(TextView)findViewById(R.id.txttenm);
        txtphong=(TextView)findViewById(R.id.txtphong);
        txttg=(TextView)findViewById(R.id.txttg);
        txtgv=(TextView)findViewById(R.id.txtgv);
        txtemail=(TextView)findViewById(R.id.txtemail);
        txtsdt=(TextView)findViewById(R.id.txtsdt);

    }

}

