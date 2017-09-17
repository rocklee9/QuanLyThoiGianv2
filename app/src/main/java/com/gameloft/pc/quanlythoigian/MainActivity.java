package com.gameloft.pc.quanlythoigian;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btntkb,btntgb;
    ConstraintLayout manhinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btntkb =(Button)findViewById(R.id.btntkb);
        btntgb=(Button)findViewById(R.id.btntgb);
        manhinh=(ConstraintLayout)findViewById(R.id.manHinh);
        manhinh.setBackgroundResource(R.drawable.bia1);

        btntkb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent tkb=new Intent(MainActivity.this,thoi_khoa_bieu.class);
                startActivity(tkb);
            }
        });

        btntgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tgb=new Intent(MainActivity.this,thoi_gian_bieu.class);
                startActivity(tgb);
            }
        });
    }
}
