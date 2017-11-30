package com.gameloft.pc.quanlythoigian.models;

import java.io.Serializable;

/**
 * Created by HOAN on 9/20/2017.
 */

public class MonHoc extends GiangVien implements Serializable {
    private int id;
    private String tenMonHoc;
    private String thoiGian1;
    private String thoiGian2;
    private String phong;
    private String note;
    private boolean warning = false;
    private String hinh;

    public MonHoc(){

    }

    public MonHoc(String tenGV, String email, String sdt, String tenMonHoc, String thoiGian1, String thoiGian2, String phong, String note) {
        super(tenGV, email, sdt);
        this.tenMonHoc = tenMonHoc;
        this.thoiGian1 = thoiGian1;
        this.thoiGian2 = thoiGian2;
        this.phong = phong;
        this.note = note;
    }

    public MonHoc(String tenMonHoc, String thoiGian1, String thoiGian2, String phong) {
        this.tenMonHoc = tenMonHoc;
        this.thoiGian1 = thoiGian1;
        this.thoiGian2 = thoiGian2;
        this.phong = phong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public String getThoiGian1() {
        return thoiGian1;
    }

    public void setThoiGian1(String thoiGian) {
        this.thoiGian1 = thoiGian;
    }

    public String getThoiGian2() {
        //thoiGian2 = dft.format(date);
        return thoiGian2;
    }

    public void setThoiGian2(String thoiGian) {
        this.thoiGian2 = thoiGian;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isWarning() {
        return warning;
    }

    public void setWarning(boolean warning) {
        this.warning = warning;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
}
