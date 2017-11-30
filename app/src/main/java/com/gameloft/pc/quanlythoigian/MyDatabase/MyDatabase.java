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
    public static final String PIC = "PICTURE";

    public static final String TABLE_MONDAY = "MONDAY";

    public static final String TABLE_TUESDAY = "TUESDAY";

    public static final String TABLE_WEDNESDAY = "WEDNESDAY";

    public static final String TABLE_THURSDAY = "THURSDAY";

    public static final String TABLE_FRIDAY = "FRIDAY";

    public static final String TABLE_SATURDAY = "SATURDAY";

    public static final String TABLE_SUNDAY = "SUNDAY";
    

    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query2 = "CREATE TABLE "+ TABLE_MONDAY +" " +
                "("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MONHOC+" TEXT,"
                + TIME1 + " TEXT,"
                + TIME2 + " TEXT,"
                + PHONG + " TEXT,"
                + TENGV + " TEXT,"
                + EMAIL + " TEXT,"
                + SDT + " TEXT,"
                + NOTE + " TEXT,"
                + PIC + " TEXT);";
        //db.execSQL(query2);


        String query3 = "CREATE TABLE "+ TABLE_TUESDAY +" " +
                "("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MONHOC+" TEXT,"
                + TIME1 + " TEXT,"
                + TIME2 + " TEXT,"
                + PHONG + " TEXT,"
                + TENGV + " TEXT,"
                + EMAIL + " TEXT,"
                + SDT + " TEXT,"
                + NOTE + " TEXT,"
                + PIC + " TEXT);";
        //db.execSQL(query3);

        String query4 = "CREATE TABLE "+ TABLE_WEDNESDAY +" " +
                "("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MONHOC+" TEXT,"
                + TIME1 + " TEXT,"
                + TIME2 + " TEXT,"
                + PHONG + " TEXT,"
                + TENGV + " TEXT,"
                + EMAIL + " TEXT,"
                + SDT + " TEXT,"
                + NOTE + " TEXT,"
                + PIC + " TEXT);";
        //db.execSQL(query4);

        String query5 = "CREATE TABLE "+ TABLE_THURSDAY +" " +
                "("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MONHOC+" TEXT,"
                + TIME1 + " TEXT,"
                + TIME2 + " TEXT,"
                + PHONG + " TEXT,"
                + TENGV + " TEXT,"
                + EMAIL + " TEXT,"
                + SDT + " TEXT,"
                + NOTE + " TEXT,"
                + PIC + " TEXT);";
        //db.execSQL(query5);

        String query6 = "CREATE TABLE "+ TABLE_FRIDAY +" " +
                "("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MONHOC+" TEXT,"
                + TIME1 + " TEXT,"
                + TIME2 + " TEXT,"
                + PHONG + " TEXT,"
                + TENGV + " TEXT,"
                + EMAIL + " TEXT,"
                + SDT + " TEXT,"
                + NOTE + " TEXT,"
                + PIC + " TEXT);";
       // db.execSQL(query6);

        String query7 = "CREATE TABLE "+ TABLE_SATURDAY +" " +
                "("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MONHOC+" TEXT,"
                + TIME1 + " TEXT,"
                + TIME2 + " TEXT,"
                + PHONG + " TEXT,"
                + TENGV + " TEXT,"
                + EMAIL + " TEXT,"
                + SDT + " TEXT,"
                + NOTE + " TEXT,"
                + PIC + " TEXT);";
       // db.execSQL(query7);

        String query8 = "CREATE TABLE "+ TABLE_SUNDAY +" " +
                "("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MONHOC+" TEXT,"
                + TIME1 + " TEXT,"
                + TIME2 + " TEXT,"
                + PHONG + " TEXT,"
                + TENGV + " TEXT,"
                + EMAIL + " TEXT,"
                + SDT + " TEXT,"
                + NOTE + " TEXT,"
                + PIC + " TEXT);";
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
