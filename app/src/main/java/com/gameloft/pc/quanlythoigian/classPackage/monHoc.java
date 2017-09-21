package com.gameloft.pc.quanlythoigian.classPackage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Date;
import java.util.Locale;

/**
 * Created by HOAN on 9/20/2017.
 */

public class monHoc {
    private String tenMonHoc;
    private String thoiGian;
    private String phong;
    Date date = new Date();
    SimpleDateFormat dft = new SimpleDateFormat("HH:mm", Locale.getDefault());

    public monHoc(String tenMonHoc, String thoiGian, String phong) {
        this.tenMonHoc = tenMonHoc;
        this.thoiGian = thoiGian;
        this.phong = phong;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public String getThoiGian() {
        thoiGian = dft.format(date);
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }
}
