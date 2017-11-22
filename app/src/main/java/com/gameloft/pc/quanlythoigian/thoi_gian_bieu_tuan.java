package com.gameloft.pc.quanlythoigian;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class thoi_gian_bieu_tuan extends AppCompatActivity {

    private Button btn_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thoi_gian_bieu_tuan);

        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();
    }

    private void init() {

    }

    private void getWidgets() {

        btn_date= (Button)findViewById(R.id.btn_date);
    }


    private void setWidgets() {

    }

    private void addWidgetsListener() {

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(thoi_gian_bieu_tuan.this, thoi_gian_bieu.class);
                startActivity(intent);
            }
        });
    }
}
