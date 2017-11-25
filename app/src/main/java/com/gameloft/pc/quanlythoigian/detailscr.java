package com.gameloft.pc.quanlythoigian;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.gameloft.pc.quanlythoigian.MyDatabase.DatabaseAdapter;
import com.gameloft.pc.quanlythoigian.TabFragment.TabFragment_monday;
import com.gameloft.pc.quanlythoigian.classPackage.CustomAdapter;
import com.gameloft.pc.quanlythoigian.classPackage.MonHoc;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//import static android.icu.util.MeasureUnit.BYTE;

public class detailscr extends AppCompatActivity {

    public static final int REQUEST_CODE_CALL = 123;
    public static final int REQUEST_CODE_SMS = 456;


    EditText edtTenMon, edtPhong, edtGV, edtEmail, edtSdt, edtNote;
    TextView tvTime1, tvTime2;
    ImageButton btnBack, btnSendEmail, btnSendSMS, btnMakeCall, btnEdit;
    Button btnSave, btnCancel;
    ImageView imgHinh;
    MonHoc monHoc;
    DatabaseAdapter database;
    int day;

//    KeyListener keyListener1, keyListener2;

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
        database = new DatabaseAdapter(detailscr.this);
        monHoc = new MonHoc();
//        keyListener1 = tvTime1.getKeyListener();
//        keyListener2 = tvTime2.getKeyListener();
    }

    private void getWidgets() {
        edtTenMon = (EditText) findViewById(R.id.tvTenMon);
        edtPhong = (EditText) findViewById(R.id.tvPhong);
        tvTime1 = (TextView) findViewById(R.id.tvTime1);
        tvTime2 = (TextView) findViewById(R.id.tvTime2);
        edtGV = (EditText) findViewById(R.id.tvGV);
        edtEmail = (EditText) findViewById(R.id.tvEmail);
        edtSdt = (EditText) findViewById(R.id.tvSdt);
        edtNote = (EditText) findViewById(R.id.tvNote);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnSendEmail = (ImageButton) findViewById(R.id.btnSendEmail);
        btnSendSMS = (ImageButton) findViewById(R.id.btnSendSMS);
        btnMakeCall = (ImageButton) findViewById(R.id.btnMakeCall);
        btnEdit = (ImageButton) findViewById(R.id.btnEdit);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        imgHinh = (ImageView) findViewById(R.id.imgHinh);
    }

    private void setWidgets() {
        database.open();
        monHoc = (MonHoc) getIntent().getSerializableExtra("chitietmonhoc");
        day = getIntent().getIntExtra("day",2);
        showDetail();
    }

    private void addWidgetsListener() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent data = new Intent();
//                data.putExtra("monhoc-maybeEdited",monHoc);
//                setResult(TabFragment_monday.RESULT_CODE_DETAIL,data);
                finish();
            }
        });

        edtEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtEmail.getText().toString().trim().isEmpty()){
                    return;
                }else{
                    btnSendEmail.setVisibility(View.VISIBLE);
                }
                btnSendSMS.setVisibility(View.INVISIBLE);
                btnMakeCall.setVisibility(View.INVISIBLE);
            }
        });

        edtSdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtEmail.getText().toString().trim().isEmpty()){
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
                tvMail.setText("To: " + edtEmail.getText().toString());
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

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeEditTextEditable(edtEmail);
                makeEditTextEditable(edtGV);
                makeEditTextEditable(edtNote);
                makeEditTextEditable(edtSdt);
                makeEditTextEditable(edtPhong);
                makeEditTextEditable(edtTenMon);
//                tvTime1.setKeyListener(keyListener1);
//                tvTime2.setKeyListener(keyListener2);
                btnCancel.setVisibility(View.VISIBLE);
                btnSave.setVisibility(View.VISIBLE);
            }
        });

        tvTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialogBatDau();
            }
        });
        tvTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialogKetThuc();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtTenMon.getText().toString().isEmpty()){
                    AlertDialog.Builder rm = new AlertDialog.Builder(detailscr.this);
                    rm.setMessage(R.string.luu_ma_khong_co_ten_mon_hoc);
                    rm.setNegativeButton(R.string.huy, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    rm.setPositiveButton(R.string.luu, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setMonHoc();
                            boolean check = database.update(monHoc,day);
                            if(check){
                                Toast.makeText(detailscr.this,R.string.cap_nhat_thanh_cong,Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(detailscr.this,R.string.loi_cap_nhat_du_lieu,Toast.LENGTH_SHORT).show();
                            }
                            makeEditTextLikeTextView(edtTenMon);
                            makeEditTextLikeTextView(edtPhong);
                            makeEditTextLikeTextView(edtGV);
                            makeEditTextLikeTextView(edtEmail);
                            makeEditTextLikeTextView(edtNote);
                            makeEditTextLikeTextView(edtSdt);
                            showDetail();
                        }
                    });
                    rm.create().show();
                }else{
                    setMonHoc();
                    boolean check = database.update(monHoc,day);
                    if(check){
                        Toast.makeText(detailscr.this,R.string.cap_nhat_thanh_cong,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(detailscr.this,R.string.loi_cap_nhat_du_lieu,Toast.LENGTH_SHORT).show();
                    }
                    showDetail();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetail();
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
                    Toast.makeText(detailscr.this,R.string.ung_dung_chua_duoc_cap_quyen_de_goi_dien_thoai,Toast.LENGTH_SHORT).show();
                }
            case REQUEST_CODE_SMS:
                if((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    makeSMS();
                }else{
                    Toast.makeText(detailscr.this,R.string.ung_dung_chua_duoc_cap_quyen_de_gui_tin_nhan,Toast.LENGTH_SHORT).show();
                }
        }
    }

    public void makeCall() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + edtSdt.getText().toString().trim()));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }

    public void makeSMS(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:" + edtSdt.getText().toString().trim()));
        startActivity(intent);
    }

    public void makeEmail(String sub, String con){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL,edtEmail.getText().toString().trim());
        intent.putExtra(Intent.EXTRA_SUBJECT, sub);
        intent.putExtra(Intent.EXTRA_TEXT, con);
        intent.setType("message/rfc822");
        try{
            startActivity(intent.createChooser(intent,"Email"));
        }catch(android.content.ActivityNotFoundException ex){
            Toast.makeText(detailscr.this, R.string.khong_ho_tro_email, Toast.LENGTH_SHORT).show();
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

    private void makeEditTextEditable(EditText editText){
        if(editText != edtNote) {
            editText.setBackgroundResource(android.R.drawable.edit_text);
        }
        editText.setClickable(true);
        editText.setCursorVisible(true);
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
    }


    private void makeEditTextLikeTextView(EditText editText){
        if(editText != edtNote) {
            editText.setBackgroundResource(android.R.drawable.screen_background_light_transparent);
        }
        editText.setClickable(false);
        editText.setCursorVisible(false);
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
//        tvTime1.setKeyListener(null);
//        tvTime2.setKeyListener(null);
    }

    public void showTimePickerDialogBatDau() {
        final Calendar calendar= Calendar.getInstance();
        int hour= calendar.get(Calendar.HOUR);
        int min= calendar.get(Calendar.MINUTE);

        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfday, int minute) {
                SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
                calendar.set(Calendar.HOUR_OF_DAY,hourOfday);
                calendar.set(Calendar.MINUTE,minute);
                String s = dft.format(calendar.getTime());
                tvTime1.setText(s);
            }
        };

        TimePickerDialog pic=new TimePickerDialog(this, onTimeSetListener, hour, min, true);
        pic.show();

    }
    public void showTimePickerDialogKetThuc() {
        final Calendar calendar= Calendar.getInstance();
        int hour= calendar.get(Calendar.HOUR);
        int min= calendar.get(Calendar.MINUTE);

        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfday, int minute) {
                SimpleDateFormat dft = new SimpleDateFormat("HH:mm");
                calendar.set(Calendar.HOUR_OF_DAY,hourOfday);
                calendar.set(Calendar.MINUTE,minute);
                String s = dft.format(calendar.getTime());
                tvTime2.setText(s);
            }
        };

        TimePickerDialog pic=new TimePickerDialog(this, onTimeSetListener, hour, min, true);
        pic.show();
    }

    public void setMonHoc(){
        monHoc.setTenMonHoc(edtTenMon.getText().toString());
        monHoc.setThoiGian1(tvTime1.getText().toString());
        monHoc.setThoiGian2(tvTime2.getText().toString());
        monHoc.setPhong(edtPhong.getText().toString());
        monHoc.setTenGV(edtGV.getText().toString());
        monHoc.setEmail(edtEmail.getText().toString());
        monHoc.setSdt(edtSdt.getText().toString());
        monHoc.setNote(edtNote.getText().toString());
    }

    private void showDetail(){
        edtTenMon.setText(monHoc.getTenMonHoc());
        edtPhong.setText(monHoc.getPhong());
        tvTime1.setText(monHoc.getThoiGian1());
        tvTime2.setText(monHoc.getThoiGian2());
//        tvTime1.setKeyListener(null);
//        tvTime2.setKeyListener(null);
        edtGV.setText(monHoc.getTenGV());
        edtEmail.setText(monHoc.getEmail());
        edtSdt.setText(monHoc.getSdt());
        edtNote.setText(monHoc.getNote());
        if(monHoc.getHinh() != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(monHoc.getHinh(), 0, monHoc.getHinh().length);
            imgHinh.setImageBitmap(bitmap);
        }

        makeEditTextLikeTextView(edtTenMon);
        makeEditTextLikeTextView(edtPhong);
        makeEditTextLikeTextView(edtGV);
        makeEditTextLikeTextView(edtEmail);
        makeEditTextLikeTextView(edtNote);
        makeEditTextLikeTextView(edtSdt);

        btnSave.setVisibility(View.INVISIBLE);
        btnCancel.setVisibility(View.INVISIBLE);

        tvTime1.setClickable(false);
        tvTime1.setFocusable(false);
        tvTime1.setFocusableInTouchMode(false);

        tvTime2.setClickable(false);
        tvTime2.setFocusable(false);
        tvTime2.setFocusableInTouchMode(false);


    }
}

