package com.gameloft.pc.quanlythoigian.utils;

import android.Manifest;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.gameloft.pc.quanlythoigian.MyDatabase.DatabaseAdapter;
import com.gameloft.pc.quanlythoigian.R;
import com.gameloft.pc.quanlythoigian.TabFragment.TabFragment_monday;
import com.gameloft.pc.quanlythoigian.adapters.GridViewAdapter;
import com.gameloft.pc.quanlythoigian.models.MonHoc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//import static android.icu.util.MeasureUnit.BYTE;

public class DetailActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_CALL = 123;
    public static final int REQUEST_CODE_SMS = 456;
    public static final int REQUEST_CODE_CAMERA_FROM_DETAIL = 1511;
    public static final int REQUEST_CODE_LOAD_IMAGE = 309;

    public static String strSeparator = "__,__";


    EditText edtTenMon, edtPhong, edtGV, edtEmail, edtSdt, edtNote;
    TextView tvTime1, tvTime2;
    ImageButton btnBack, btnSendEmail, btnSendSMS, btnMakeCall, btnEdit, btnTime1, btnTime2, btnAddImage;
    Button btnSave, btnCancel;
    ImageView imvSelected;
    ImageButton btnBackToDetail;
    Button btnDelete;
    ImageButton btnShare;
    MonHoc monHoc;
    DatabaseAdapter database;
    int day;
    GridViewAdapter adapter;
    GridView gridView;

    List<String> strImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();

    }

    private void init() {
        database = new DatabaseAdapter(DetailActivity.this);
        monHoc = new MonHoc();
        adapter = new GridViewAdapter(getApplicationContext());
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
        btnTime1 = (ImageButton) findViewById(R.id.btnEditTime1);
        btnTime2 = (ImageButton) findViewById(R.id.btnEditTime2);
        btnAddImage = (ImageButton) findViewById(R.id.btnAddImage);
        gridView = (GridView) findViewById(R.id.grvImage);
    }

    private void setWidgets() {
        database.open();
        monHoc = (MonHoc) getIntent().getSerializableExtra("chitietmonhoc");
        day = getIntent().getIntExtra("day",2);
        showDetail();
        gridView.setFocusable(false);
    }

    private void addWidgetsListener() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                if(edtSdt.getText().toString().trim().isEmpty()){
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
                    if (!hasPermissions(DetailActivity.this, PERMISSIONS)) {
                        ActivityCompat.requestPermissions(DetailActivity.this, PERMISSIONS, REQUEST_CODE_CALL);
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
                    if (!hasPermissions(DetailActivity.this, PERMISSIONS)) {
                        ActivityCompat.requestPermissions(DetailActivity.this, PERMISSIONS, REQUEST_CODE_SMS);
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
                final Dialog dialog = new Dialog(DetailActivity.this);
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
                btnTime1.setVisibility(View.VISIBLE);
                btnTime2.setVisibility(View.VISIBLE);

                btnMakeCall.setVisibility(View.INVISIBLE);
                btnSendEmail.setVisibility(View.INVISIBLE);
                btnSendSMS.setVisibility(View.INVISIBLE);
            }
        });

        btnTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialogBatDau();
            }
        });
        btnTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialogKetThuc();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtTenMon.getText().toString().isEmpty()){
                    AlertDialog.Builder rm = new AlertDialog.Builder(DetailActivity.this);
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
                                Toast.makeText(DetailActivity.this,R.string.cap_nhat_thanh_cong,Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(DetailActivity.this,R.string.loi_cap_nhat_du_lieu,Toast.LENGTH_SHORT).show();
                            }
                            showDetail();
                        }
                    });
                    rm.create().show();
                }else{
                    setMonHoc();
                    boolean check = database.update(monHoc,day);
                    if(check){
                        Toast.makeText(DetailActivity.this,R.string.cap_nhat_thanh_cong,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(DetailActivity.this,R.string.loi_cap_nhat_du_lieu,Toast.LENGTH_SHORT).show();
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

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showImage(position);
            }
        });

        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(btnAddImage);
                openContextMenu(btnAddImage);
            }
        });
    }

    private void showImage(final int position) {
        setContentView(R.layout.image_selected);
        imvSelected = (ImageView) findViewById(R.id.imvSelected);
        btnBackToDetail = (ImageButton) findViewById(R.id.btnBackToDetail);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnShare = (ImageButton) findViewById(R.id.btnShare);

     //   decodeBase64AndSetImage(strImage[position],imvSelected);

        try{
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(strImage.get(position)));
            imvSelected.setImageBitmap(bitmap);
        }catch (IOException e){
            e.printStackTrace();
        }
        btnBackToDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //   Context context = getApplicationContext();
                File file = new File(Uri.parse(strImage.get(position)).getPath());
                file.delete();
             //   context.getContentResolver().delete(Uri.parse(strImage.get(position)),null,null);
                strImage.remove(position);
                monHoc.setHinh(convertArrayToString(strImage));
                boolean check = database.update(monHoc,day);
                if(check){
                    Toast.makeText(DetailActivity.this,R.string.deleted,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DetailActivity.this,R.string.loi_cap_nhat_du_lieu,Toast.LENGTH_SHORT).show();
                }
                recreate();
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/*");
                share.putExtra(Intent.EXTRA_STREAM, Uri.parse(strImage.get(position)));
                startActivity(Intent.createChooser(share, getResources().getString(R.string.share_using)));
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
                    Toast.makeText(DetailActivity.this,R.string.ung_dung_chua_duoc_cap_quyen_de_goi_dien_thoai,Toast.LENGTH_SHORT).show();
                }
            case REQUEST_CODE_SMS:
                if((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    makeSMS();
                }else{
                    Toast.makeText(DetailActivity.this,R.string.ung_dung_chua_duoc_cap_quyen_de_gui_tin_nhan,Toast.LENGTH_SHORT).show();
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
            Toast.makeText(DetailActivity.this, R.string.khong_ho_tro_email, Toast.LENGTH_SHORT).show();
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
        edtGV.setText(monHoc.getTenGV());
        edtEmail.setText(monHoc.getEmail());
        edtSdt.setText(monHoc.getSdt());
        edtNote.setText(monHoc.getNote());
        if(monHoc.getHinh() != null){
            strImage = convertStringToArray(monHoc.getHinh());
            adapter = new GridViewAdapter(this,strImage,R.layout.dong_gridview);
            gridView.setAdapter(adapter);
        }

        makeEditTextLikeTextView(edtTenMon);
        makeEditTextLikeTextView(edtPhong);
        makeEditTextLikeTextView(edtGV);
        makeEditTextLikeTextView(edtEmail);
        makeEditTextLikeTextView(edtNote);
        makeEditTextLikeTextView(edtSdt);

        btnSave.setVisibility(View.INVISIBLE);
        btnCancel.setVisibility(View.INVISIBLE);

        btnTime1.setVisibility(View.INVISIBLE);
        btnTime2.setVisibility(View.INVISIBLE);

        btnMakeCall.setVisibility(View.INVISIBLE);
        btnSendEmail.setVisibility(View.INVISIBLE);
        btnSendSMS.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = this.getMenuInflater();
        menuInflater.inflate(R.menu.context_menu_image,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itAddFromGallery:
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQUEST_CODE_LOAD_IMAGE);
                return true;

            case R.id.itCamera:
                Intent iCam = new Intent(DetailActivity.this,CameraActivity.class);
                iCam.putExtra("monHocCam",monHoc);
                startActivityForResult(iCam,REQUEST_CODE_CAMERA_FROM_DETAIL);
                return true;

        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_CAMERA_FROM_DETAIL ){
            if(resultCode == TabFragment_monday.RESULT_CODE_CAM){
                monHoc = (MonHoc) data.getSerializableExtra("monHocCamed");
                database.update(monHoc,day);
                showDetail();
            }
        }

        if(requestCode == REQUEST_CODE_LOAD_IMAGE ){
            if(resultCode == RESULT_OK && data != null){
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();


                if (monHoc.getHinh() != null){
                    monHoc.setHinh(monHoc.getHinh() + strSeparator + "file:" + picturePath);
                }else {
                    monHoc.setHinh("file:" + picturePath);
                }
                database.update(monHoc,day);
                showDetail();
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",         // suffix
                storageDir      // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
    //    mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    public static List<String> convertStringToArray(String str){
        String[] arr = str.split(strSeparator);
        List<String> list = new ArrayList<String>(Arrays.asList(arr));
        return list;
    }

    public static String convertArrayToString(List<String> array){
        String str = "";
        for (int i = 0;i<array.size(); i++) {
            str = str+array.get(i);
            // Do not append comma at the end of last element
            if(i<array.size()-1){
                str = str+strSeparator;
            }
        }
        return str;
    }
}

