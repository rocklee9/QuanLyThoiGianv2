package com.gameloft.pc.quanlythoigian.utils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gameloft.pc.quanlythoigian.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String language = sp.getString("language", "");
        if(language.equals("")){
            language = "vi";
        }
        setLanguage(language);

        setContentView(R.layout.activity_main);

    }

    public void gotoThoiKhoaBieu(View view){
        startActivity(new Intent(MainActivity.this, ThoiKhoaBieuActivity.class));
    }
    public void gotoThoiGianBieu(View view){
        startActivity(new Intent(MainActivity.this, ThoiGianBieuActivity.class));
    }

    //dialog chọn ngôn ngữ
    public void showSelectLanguage(View view){
        String []languages = getResources().getStringArray(R.array.languages);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.ngon_ngu);
        builder.setItems(languages, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String language = "";
                switch (i){
                    case 0:
                        language = "vi";
                        break;
                    case 1:
                        language = "en";
                        break;
                    case 2:
                        language = "ja";
                        break;
                }
                setLanguage(language);
                setContentView(R.layout.activity_main);
            }
        });
        builder.create();
        builder.show();
    }

    private void setLanguage(String language){
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = getResources().getConfiguration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("language", language);
        editor.commit();
    }

    //dialog đánh giá app
    public void showRating(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.danh_gia_app);
        builder.setMessage(R.string.ban_co_thich_ung_dung_khong);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.co, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("https://play.google.com/store");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(R.string.khong, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNeutralButton(R.string.de_sau, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
