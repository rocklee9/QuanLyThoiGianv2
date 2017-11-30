package com.gameloft.pc.quanlythoigian.MyDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gameloft.pc.quanlythoigian.models.MonHoc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HOAN on 10/26/2017.
 */

public class DatabaseAdapter {
    MyDatabase db;
    SQLiteDatabase database;
    String[] columns = {MyDatabase.ID,MyDatabase.MONHOC, MyDatabase.TIME1, MyDatabase.TIME2, MyDatabase.PHONG,
                        MyDatabase.TENGV, MyDatabase.EMAIL, MyDatabase.SDT, MyDatabase.NOTE, MyDatabase.PIC};

    public DatabaseAdapter(Context context){
        db = new MyDatabase(context);
    }

    public void open(){
        database = db.getWritableDatabase();
    }

    public void close(){
        db.close();
    }

    public boolean AddMonHoc(MonHoc monHoc, int day){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDatabase.MONHOC,monHoc.getTenMonHoc());
        contentValues.put(MyDatabase.TIME1,monHoc.getThoiGian1());
        contentValues.put(MyDatabase.TIME2,monHoc.getThoiGian2());
        contentValues.put(MyDatabase.PHONG,monHoc.getPhong());
        contentValues.put(MyDatabase.TENGV,monHoc.getTenGV());
        contentValues.put(MyDatabase.EMAIL,monHoc.getEmail());
        contentValues.put(MyDatabase.SDT,monHoc.getSdt());
        contentValues.put(MyDatabase.NOTE,monHoc.getNote());
        contentValues.put(MyDatabase.PIC,monHoc.getHinh());

        long check = 0;

        switch (day){
            case 2:
                check = database.insert(MyDatabase.TABLE_MONDAY,null,contentValues);
                break;

            case 3:
                check = database.insert(MyDatabase.TABLE_TUESDAY,null,contentValues);
                break;

            case 4:
                check = database.insert(MyDatabase.TABLE_WEDNESDAY,null,contentValues);
                break;

            case 5:
                check = database.insert(MyDatabase.TABLE_THURSDAY,null,contentValues);
                break;

            case 6:
                check = database.insert(MyDatabase.TABLE_FRIDAY,null,contentValues);
                break;

            case 7:
                check = database.insert(MyDatabase.TABLE_SATURDAY,null,contentValues);
                break;

            case 8:
                check = database.insert(MyDatabase.TABLE_SUNDAY,null,contentValues);
                break;
        }
        if(check != 0){
            return true;
        }else return false;
    }

    public List<MonHoc> getData(int day){
        List<MonHoc> listMonHoc = new ArrayList<MonHoc>();
        Cursor cursor;
        switch (day){
            case 2:
                cursor = database.query(MyDatabase.TABLE_MONDAY,columns,null,null,null,null,MyDatabase.TIME1+" ASC");
                break;

            case 3:
                cursor = database.query(MyDatabase.TABLE_TUESDAY,columns,null,null,null,null,MyDatabase.TIME1+" ASC");
                break;

            case 4:
                cursor = database.query(MyDatabase.TABLE_WEDNESDAY,columns,null,null,null,null,MyDatabase.TIME1+" ASC");
                break;

            case 5:
                cursor = database.query(MyDatabase.TABLE_THURSDAY,columns,null,null,null,null,MyDatabase.TIME1+" ASC");
                break;

            case 6:
                cursor = database.query(MyDatabase.TABLE_FRIDAY,columns,null,null,null,null,MyDatabase.TIME1+" ASC");
                break;

            case 7:
                cursor = database.query(MyDatabase.TABLE_SATURDAY,columns,null,null,null,null,MyDatabase.TIME1+" ASC");
                break;

            default:
                cursor = database.query(MyDatabase.TABLE_SUNDAY,columns,null,null,null,null,MyDatabase.TIME1+" ASC");
                break;
        }
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            int id = cursor.getInt(cursor.getColumnIndex(MyDatabase.ID));
            String tenMon = cursor.getString(cursor.getColumnIndex(MyDatabase.MONHOC));
            String time1 = cursor.getString(cursor.getColumnIndex(MyDatabase.TIME1));
            String time2 = cursor.getString(cursor.getColumnIndex(MyDatabase.TIME2));
            String phong = cursor.getString(cursor.getColumnIndex(MyDatabase.PHONG));
            String giangvien = cursor.getString(cursor.getColumnIndex(MyDatabase.TENGV));
            String email = cursor.getString(cursor.getColumnIndex(MyDatabase.EMAIL));
            String sdt = cursor.getString(cursor.getColumnIndex(MyDatabase.SDT));
            String note = cursor.getString(cursor.getColumnIndex(MyDatabase.NOTE));
            byte[] hinh = cursor.getBlob(cursor.getColumnIndex(MyDatabase.PIC));
            boolean warning = false;

            MonHoc monHoc = new MonHoc();
            monHoc.setId(id);
            monHoc.setTenMonHoc(tenMon);
            monHoc.setThoiGian1(time1);
            monHoc.setThoiGian2(time2);
            monHoc.setPhong(phong);
            monHoc.setTenGV(giangvien);
            monHoc.setEmail(email);
            monHoc.setSdt(sdt);
            monHoc.setNote(note);
            monHoc.setHinh(hinh);
            monHoc.setWarning(warning);

            listMonHoc.add(monHoc);
            cursor.moveToNext();
        }

        for(int i=0;i<listMonHoc.size()-1;i++){
            if(timeConvert(listMonHoc.get(i).getThoiGian2()) > timeConvert(listMonHoc.get(i+1).getThoiGian1())){
                listMonHoc.get(i).setWarning(true);
                listMonHoc.get(i+1).setWarning(true);
            }
        }

        return listMonHoc;
    }

    public boolean delete(MonHoc monHoc, int day){
        long check=0;
        switch (day){
            case 2:
                check = database.delete(MyDatabase.TABLE_MONDAY,MyDatabase.ID + "=" +monHoc.getId(),null);
                break;
            case 3:
                check = database.delete(MyDatabase.TABLE_TUESDAY,MyDatabase.ID + "=" +monHoc.getId(),null);
                break;
            case 4:
                check = database.delete(MyDatabase.TABLE_WEDNESDAY,MyDatabase.ID + "=" +monHoc.getId(),null);
                break;
            case 5:
                check = database.delete(MyDatabase.TABLE_THURSDAY,MyDatabase.ID + "=" +monHoc.getId(),null);
                break;
            case 6:
                check = database.delete(MyDatabase.TABLE_FRIDAY,MyDatabase.ID + "=" +monHoc.getId(),null);
                break;
            case 7:
                check = database.delete(MyDatabase.TABLE_SATURDAY,MyDatabase.ID + "=" +monHoc.getId(),null);
                break;
            case 8:
                check = database.delete(MyDatabase.TABLE_SUNDAY,MyDatabase.ID + "=" +monHoc.getId(),null);
                break;
        }
        if(check !=0){
            return true;
        }else {
            return false;
        }
    }

    public boolean update(MonHoc monHoc, int day){
        long check = 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDatabase.ID,monHoc.getId());
        contentValues.put(MyDatabase.MONHOC,monHoc.getTenMonHoc());
        contentValues.put(MyDatabase.TIME1,monHoc.getThoiGian1());
        contentValues.put(MyDatabase.TIME2,monHoc.getThoiGian2());
        contentValues.put(MyDatabase.PHONG,monHoc.getPhong());
        contentValues.put(MyDatabase.TENGV,monHoc.getTenGV());
        contentValues.put(MyDatabase.EMAIL,monHoc.getEmail());
        contentValues.put(MyDatabase.SDT,monHoc.getSdt());
        contentValues.put(MyDatabase.NOTE,monHoc.getNote());
        contentValues.put(MyDatabase.PIC,monHoc.getHinh());

        switch (day){
            case 2:
                check = database.update(MyDatabase.TABLE_MONDAY,contentValues,MyDatabase.ID + "=" + monHoc.getId(),null);
                break;
            case 3:
                check = database.update(MyDatabase.TABLE_TUESDAY,contentValues,MyDatabase.ID + "=" + monHoc.getId(),null);
                break;
            case 4:
                check = database.update(MyDatabase.TABLE_WEDNESDAY,contentValues,MyDatabase.ID + "=" + monHoc.getId(),null);
                break;
            case 5:
                check = database.update(MyDatabase.TABLE_THURSDAY,contentValues,MyDatabase.ID + "=" + monHoc.getId(),null);
                break;
            case 6:
                check = database.update(MyDatabase.TABLE_FRIDAY,contentValues,MyDatabase.ID + "=" + monHoc.getId(),null);
                break;
            case 7:
                check = database.update(MyDatabase.TABLE_SATURDAY,contentValues,MyDatabase.ID + "=" + monHoc.getId(),null);
                break;
            case 8:
                check = database.update(MyDatabase.TABLE_SUNDAY,contentValues,MyDatabase.ID + "=" + monHoc.getId(),null);
                break;
        }
        if(check != 0 ){
            return true;
        }else{
            return false;
        }
    }

    public int timeConvert(String time){
        if(time.trim().isEmpty()) return 0;
        String[] strings = time.split(":");
        return (Integer.valueOf(strings[0].trim())*60 + Integer.valueOf(strings[1].trim()));
    }
}
