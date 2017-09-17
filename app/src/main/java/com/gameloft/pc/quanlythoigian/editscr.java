package com.gameloft.pc.quanlythoigian;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class editscr extends AppCompatActivity {

    EditText edtmon,edtphong,edttg,edtgv,edtemail,edtsdt;
    Button btnluu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editscr);
        anhxa();
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bd=new Bundle();
                bd.putString("mon",edtmon.getText().toString());
                bd.putString("phong",edtphong.getText().toString());
                bd.putString("thoigian",edttg.getText().toString());
                bd.putString("giangvien",edtgv.getText().toString());
                bd.putString("email",edtemail.getText().toString());
                bd.putString("sdt",edtsdt.getText().toString());
                Intent ht=new Intent(editscr.this,detailscr.class);
                ht.putExtra("hienthi",bd);
                startActivity(ht);

            }
        });
    }
    private void anhxa(){
        edtmon=(EditText)findViewById(R.id.môn);
        edtphong=(EditText)findViewById(R.id.phong);
        edttg=(EditText)findViewById(R.id.thoigian);
        edtgv=(EditText)findViewById(R.id.giangvien);
        edtemail=(EditText)findViewById(R.id.email);
        edtsdt=(EditText)findViewById(R.id.sdt);
        btnluu=(Button)findViewById(R.id.btnhienthi);
    }
}
