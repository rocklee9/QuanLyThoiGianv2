package com.gameloft.pc.quanlythoigian.MyDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HOAN on 10/26/2017.
 */

public class MyDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "THOIKHOABIEU";
    public static final int DB_VERSION = 1;

    public static final String ID = "_id";
    public static final String MONHOC = "MONHOC";
    public static final String TIME1 = "THOIGIANBATDAU";
    public static final String TIME2 = "THOIGIANKETTHUC";
    public static final String PHONG = "PHONG";
    public static final String TENGV = "GIANGVIEN";
    public static final String EMAIL = "EMAILGIANGVIEN";
    public static final String SDT = "SDTGIANGVIEN";
    public static final String NOTE = "NOTE";

    public static final String TABLE_MONDAY = "MONDAY";
    public static final String ID_TBMONDAY = "_id";
    public static final String MONHOC_TBMONDAY = "MONHOC";
    public static final String TIME1_TBMONDAY = "THOIGIANBATDAU";
    public static final String TIME2_TBMONDAY = "THOIGIANKETTHUC";
    public static final String PHONG_TBMONDAY = "PHONG";
    public static final String TENGV_TBMONDAY = "GIANGVIEN";
    public static final String EMAIL_TBMONDAY = "EMAILGIANGVIEN";
    public static final String SDT_TBMONDAY = "SDTGIANGVIEN";
    public static final String NOTE_TBMONDAY = "NOTE";

    public static final String TABLE_TUESDAY = "TUESDAY";
    public static final String ID_TBTUESDAY = "_id";
    public static final String MONHOC_TBTUESDAY = "MONHOC";
    public static final String TIME1_TBTUESDAY = "THOIGIANBATDAU";
    public static final String TIME2_TBTUESDAY = "THOIGIANKETTHUC";
    public static final String PHONG_TBTUESDAY = "PHONG";
    public static final String TENGV_TBTUESDAY = "GIANGVIEN";
    public static final String EMAIL_TBTUESDAY = "EMAILGIANGVIEN";
    public static final String SDT_TBTUESDAY = "SDTGIANGVIEN";
    public static final String NOTE_TBTUESDAY = "NOTE";

    public static final String TABLE_WEDNESDAY = "WEDNESDAY";
    public static final String ID_TBWEDNESDAY = "_id";
    public static final String MONHOC_TBWEDNESDAY = "MONHOC";
    public static final String TIME1_TBWEDNESDAY = "THOIGIANBATDAU";
    public static final String TIME2_TBWEDNESDAY = "THOIGIANKETTHUC";
    public static final String PHONG_TBWEDNESDAY = "PHONG";
    public static final String TENGV_TBWEDNESDAY = "GIANGVIEN";
    public static final String EMAIL_TBWEDNESDAY = "EMAILGIANGVIEN";
    public static final String SDT_TBWEDNESDAY = "SDTGIANGVIEN";
    public static final String NOTE_TBWEDNESDAY = "NOTE";

    public static final String TABLE_THURSDAY = "THURSDAY";
    public static final String ID_TBTHURSDAY = "_id";
    public static final String MONHOC_TBTHURSDAY = "MONHOC";
    public static final String TIME1_TBTHURSDAY = "THOIGIANBATDAU";
    public static final String TIME2_TBTHURSDAY = "THOIGIANKETTHUC";
    public static final String PHONG_TBTHURSDAY = "PHONG";
    public static final String TENGV_TBTHURSDAY = "GIANGVIEN";
    public static final String EMAIL_TBTHURSDAY = "EMAILGIANGVIEN";
    public static final String SDT_TBTHURSDAY = "SDTGIANGVIEN";
    public static final String NOTE_TBTHURSDAY = "NOTE";

    public static final String TABLE_FRIDAY = "FRIDAY";
    public static final String ID_TBFRIDAY = "_id";
    public static final String MONHOC_TBFRIDAY = "MONHOC";
    public static final String TIME1_TBFRIDAY = "THOIGIANBATDAU";
    public static final String TIME2_TBFRIDAY = "THOIGIANKETTHUC";
    public static final String PHONG_TBFRIDAY = "PHONG";
    public static final String TENGV_TBFRIDAY = "GIANGVIEN";
    public static final String EMAIL_TBFRIDAY = "EMAILGIANGVIEN";
    public static final String SDT_TBFRIDAY = "SDTGIANGVIEN";
    public static final String NOTE_TBFRIDAY = "NOTE";

    public static final String TABLE_SATURDAY = "SATURDAY";
    public static final String ID_TBSATURDAY = "_id";
    public static final String MONHOC_TBSATURDAY = "MONHOC";
    public static final String TIME1_TBSATURDAY = "THOIGIANBATDAU";
    public static final String TIME2_TBSATURDAY = "THOIGIANKETTHUC";
    public static final String PHONG_TBSATURDAY = "PHONG";
    public static final String TENGV_TBSATURDAY = "GIANGVIEN";
    public static final String EMAIL_TBSATURDAY = "EMAILGIANGVIEN";
    public static final String SDT_TBSATURDAY = "SDTGIANGVIEN";
    public static final String NOTE_TBSATURDAY = "NOTE";

    public static final String TABLE_SUNDAY = "SUNDAY";
    public static final String ID_TBSUNDAY = "_id";
    public static final String MONHOC_TBSUNDAY = "MONHOC";
    public static final String TIME1_TBSUNDAY = "THOIGIANBATDAU";
    public static final String TIME2_TBSUNDAY = "THOIGIANKETTHUC";
    public static final String PHONG_TBSUNDAY = "PHONG";
    public static final String TENGV_TBSUNDAY = "GIANGVIEN";
    public static final String EMAIL_TBSUNDAY = "EMAILGIANGVIEN";
    public static final String SDT_TBSUNDAY = "SDTGIANGVIEN";
    public static final String NOTE_TBSUNDAY = "NOTE";




    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query2 = "CREATE TABLE "+ TABLE_MONDAY +" " +
                "("+ID_TBMONDAY+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MONHOC_TBMONDAY+" TEXT,"
                + TIME1_TBMONDAY + " TEXT,"
                + TIME2_TBMONDAY + " TEXT,"
                + PHONG_TBMONDAY + " TEXT,"
                + TENGV_TBMONDAY + " TEXT,"
                + EMAIL_TBMONDAY + " TEXT,"
                + SDT_TBMONDAY + " TEXT,"
                + NOTE_TBMONDAY + " TEXT);";
        //db.execSQL(query2);


        String query3 = "CREATE TABLE "+ TABLE_TUESDAY +" " +
                "("+ID_TBTUESDAY+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MONHOC_TBTUESDAY+" TEXT,"
                + TIME1_TBTUESDAY + " TEXT,"
                + TIME2_TBTUESDAY + " TEXT,"
                + PHONG_TBTUESDAY + " TEXT,"
                + TENGV_TBTUESDAY + " TEXT,"
                + EMAIL_TBTUESDAY + " TEXT,"
                + SDT_TBTUESDAY + " TEXT,"
                + NOTE_TBTUESDAY + " TEXT);";
        //db.execSQL(query3);

        String query4 = "CREATE TABLE "+ TABLE_WEDNESDAY +" " +
                "("+ID_TBWEDNESDAY+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MONHOC_TBWEDNESDAY+" TEXT,"
                + TIME1_TBWEDNESDAY + " TEXT,"
                + TIME2_TBWEDNESDAY + " TEXT,"
                + PHONG_TBWEDNESDAY + " TEXT,"
                + TENGV_TBWEDNESDAY + " TEXT,"
                + EMAIL_TBWEDNESDAY + " TEXT,"
                + SDT_TBWEDNESDAY + " TEXT,"
                + NOTE_TBWEDNESDAY + " TEXT);";
        //db.execSQL(query4);

        String query5 = "CREATE TABLE "+ TABLE_THURSDAY +" " +
                "("+ID_TBTHURSDAY+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MONHOC_TBTHURSDAY+" TEXT,"
                + TIME1_TBTHURSDAY + " TEXT,"
                + TIME2_TBTHURSDAY + " TEXT,"
                + PHONG_TBTHURSDAY + " TEXT,"
                + TENGV_TBTHURSDAY + " TEXT,"
                + EMAIL_TBTHURSDAY + " TEXT,"
                + SDT_TBTHURSDAY + " TEXT,"
                + NOTE_TBTHURSDAY + " TEXT);";
        //db.execSQL(query5);

        String query6 = "CREATE TABLE "+ TABLE_FRIDAY +" " +
                "("+ID_TBFRIDAY+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MONHOC_TBFRIDAY+" TEXT,"
                + TIME1_TBFRIDAY + " TEXT,"
                + TIME2_TBFRIDAY + " TEXT,"
                + PHONG_TBFRIDAY + " TEXT,"
                + TENGV_TBFRIDAY + " TEXT,"
                + EMAIL_TBFRIDAY + " TEXT,"
                + SDT_TBFRIDAY + " TEXT,"
                + NOTE_TBFRIDAY + " TEXT);";
       // db.execSQL(query6);

        String query7 = "CREATE TABLE "+ TABLE_SATURDAY +" " +
                "("+ID_TBSATURDAY+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MONHOC_TBSATURDAY+" TEXT,"
                + TIME1_TBSATURDAY + " TEXT,"
                + TIME2_TBSATURDAY + " TEXT,"
                + PHONG_TBSATURDAY + " TEXT,"
                + TENGV_TBSATURDAY + " TEXT,"
                + EMAIL_TBSATURDAY + " TEXT,"
                + SDT_TBSATURDAY + " TEXT,"
                + NOTE_TBSATURDAY + " TEXT);";
       // db.execSQL(query7);

        String query8 = "CREATE TABLE "+ TABLE_SUNDAY +" " +
                "("+ID_TBSUNDAY+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MONHOC_TBSUNDAY+" TEXT,"
                + TIME1_TBSUNDAY + " TEXT,"
                + TIME2_TBSUNDAY + " TEXT,"
                + PHONG_TBSUNDAY + " TEXT,"
                + TENGV_TBSUNDAY + " TEXT,"
                + EMAIL_TBSUNDAY + " TEXT,"
                + SDT_TBSUNDAY + " TEXT,"
                + NOTE_TBSUNDAY + " TEXT);";
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);
        db.execSQL(query6);
        db.execSQL(query7);
        db.execSQL(query8);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST" + TABLE_MONDAY);

        db.execSQL("DROP TABLE IF EXIST" + TABLE_TUESDAY);

        db.execSQL("DROP TABLE IF EXIST" + TABLE_WEDNESDAY);

        db.execSQL("DROP TABLE IF EXIST" + TABLE_THURSDAY);

        db.execSQL("DROP TABLE IF EXIST" + TABLE_FRIDAY);

        db.execSQL("DROP TABLE IF EXIST" + TABLE_SATURDAY);

        db.execSQL("DROP TABLE IF EXIST" + TABLE_SUNDAY);
        onCreate(db);
    }
}
