package com.gameloft.pc.quanlythoigian;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gameloft.pc.quanlythoigian.classPackage.MonHoc;

public class detailscr extends AppCompatActivity {

    public static final int REQUEST_CODE_CALL = 123;
    public static final int REQUEST_CODE_SMS = 456;


    TextView tvTenMon, tvPhong, tvTime, tvGV, tvEmail, tvSdt, tvNote;
    ImageButton btnBack, btnSendEmail, btnSendSMS, btnMakeCall;
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
        btnSendEmail = (ImageButton) findViewById(R.id.btnSendEmail);
        btnSendSMS = (ImageButton) findViewById(R.id.btnSendSMS);
        btnMakeCall = (ImageButton) findViewById(R.id.btnMakeCall);

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

        tvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvEmail.getText().toString().trim().isEmpty()){
                    return;
                }else{
                    btnSendEmail.setVisibility(View.VISIBLE);
                }
                btnSendSMS.setVisibility(View.INVISIBLE);
                btnMakeCall.setVisibility(View.INVISIBLE);
            }
        });

        tvSdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvEmail.getText().toString().trim().isEmpty()){
                    return;
                }else{
                    btnMakeCall.setVisibility(View.VISIBLE);
                    btnSendSMS.setVisibility(View.VISIBLE);
                }
                btnSendEmail.setVisibility(View.INVISIBLE);
            }
        });

        btnMakeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    String[] PERMISSIONS = {android.Manifest.permission.CALL_PHONE};
                    if (!hasPermissions(detailscr.this, PERMISSIONS)) {
                        ActivityCompat.requestPermissions(detailscr.this, PERMISSIONS, REQUEST_CODE_CALL);
                    } else {
                        makeCall();
                    }
                } else {
                    makeCall();
                }
            }
        });

        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    String[] PERMISSIONS = {android.Manifest.permission.SEND_SMS};
                    if (!hasPermissions(detailscr.this, PERMISSIONS)) {
                        ActivityCompat.requestPermissions(detailscr.this, PERMISSIONS, REQUEST_CODE_SMS);
                    } else {
                        makeSMS();
                    }
                } else {
                    makeSMS();
                }
            }
        });

        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(detailscr.this);
                dialog.setContentView(R.layout.dialog_send_email);
                Button dialogNegativeButton = (Button) dialog.findViewById(R.id.btnCancel);
                Button dialogPositiveButton = (Button) dialog.findViewById(R.id.btnSend);
                TextView tvMail = (TextView) dialog.findViewById(R.id.tvEmail) ;
                final EditText edtSubject = (EditText) dialog.findViewById(R.id.edtSubject);
                final EditText edtContent = (EditText) dialog.findViewById(R.id.edtContent);
                tvMail.setText("To: " + tvEmail.getText().toString());
                dialogNegativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialogPositiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        makeEmail(edtSubject.getText().toString(),edtContent.getText().toString());
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE_CALL:
                if((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    makeCall();
                }else{
                    Toast.makeText(detailscr.this,"App chưa đươc cấp quyền để gọi điện thoại !",Toast.LENGTH_SHORT).show();
                }
            case REQUEST_CODE_SMS:
                if((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    makeSMS();
                }else{
                    Toast.makeText(detailscr.this,"App chưa đươc cấp quyền gửi tin nhắn !",Toast.LENGTH_SHORT).show();
                }
        }
    }

    public void makeCall() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + tvSdt.getText().toString().trim()));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }

    public void makeSMS(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:" + tvSdt.getText().toString().trim()));
        startActivity(intent);
    }

    public void makeEmail(String sub, String con){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL,tvEmail.getText().toString().trim());
        intent.putExtra(Intent.EXTRA_SUBJECT, sub);
        intent.putExtra(Intent.EXTRA_TEXT, con);
        intent.setType("message/rfc822");
        try{
            startActivity(intent.createChooser(intent,"Email"));
        }catch(android.content.ActivityNotFoundException ex){
            Toast.makeText(detailscr.this, "Không có ứng dụng hỗ trợ email được cài đặt !", Toast.LENGTH_SHORT).show();
        }
    }

    private static boolean hasPermissions(Context context, String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}

