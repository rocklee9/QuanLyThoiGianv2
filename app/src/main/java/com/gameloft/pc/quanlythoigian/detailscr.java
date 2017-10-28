package com.gameloft.pc.quanlythoigian;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.gameloft.pc.quanlythoigian.classPackage.MonHoc;

public class detailscr extends AppCompatActivity {


    TextView tvTenMon, tvPhong, tvTime, tvGV, tvEmail, tvSdt, tvNote;
    ImageButton btnBack;
    MonHoc monHoc;

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
        monHoc = new MonHoc();
    }

    private void getWidgets() {
        tvTenMon = (TextView) findViewById(R.id.tvTenMon);
        tvPhong = (TextView) findViewById(R.id.tvPhong);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvGV = (TextView) findViewById(R.id.tvGV);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvSdt = (TextView) findViewById(R.id.tvSdt);
        tvNote = (TextView) findViewById(R.id.tvNote);
        btnBack = (ImageButton) findViewById(R.id.btnBack);

    }

    private void setWidgets() {
        monHoc = (MonHoc) getIntent().getSerializableExtra("chitietmonhoc");
        tvTenMon.setText(monHoc.getTenMonHoc());
        tvPhong.setText(monHoc.getPhong());
        tvTime.setText(monHoc.getThoiGian1() + "-" + monHoc.getThoiGian2());
        tvGV.setText(monHoc.getTenGV());
        tvEmail.setText(monHoc.getEmail());
        tvSdt.setText(monHoc.getSdt());
        tvNote.setText(monHoc.getNote());
    }

    private void addWidgetsListener() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}

