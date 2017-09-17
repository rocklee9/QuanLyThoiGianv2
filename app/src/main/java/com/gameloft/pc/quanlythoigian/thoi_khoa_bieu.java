package com.gameloft.pc.quanlythoigian;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class thoi_khoa_bieu extends AppCompatActivity {

    private Button btnapp,btn1,btn2,btn3;

    //khai báo 3 cái này để làm spinner daylist
    private Spinner spinnerDayList;
    private ArrayAdapter<String> spinnerDayListAdapter;
    private String []dayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thoi_khoa_bieu);
        //btndaylist=(Button)findViewById(R.id.daylist);
        btnapp=(Button)findViewById(R.id.btnapp);

        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);

        /*btndaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent daylist=new Intent(thoi_khoa_bieu.this,daylist.class);
                startActivity(daylist);
            }
        });*/

        //spinner daylist
        dayList=getResources().getStringArray(R.array.daylist);
        spinnerDayList=(Spinner)findViewById(R.id.spinnerDayList);
        spinnerDayListAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dayList);
        spinnerDayList.setAdapter(spinnerDayListAdapter);
        spinnerDayList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("selected: "+dayList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editscr=new Intent(thoi_khoa_bieu.this,editscr.class);
                startActivity(editscr);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailscr=new Intent(thoi_khoa_bieu.this,detailscr.class);
                startActivity(detailscr);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailscr=new Intent(thoi_khoa_bieu.this,detailscr.class);
                startActivity(detailscr);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailscr=new Intent(thoi_khoa_bieu.this,detailscr.class);
                startActivity(detailscr);
            }
        });
    }
}
