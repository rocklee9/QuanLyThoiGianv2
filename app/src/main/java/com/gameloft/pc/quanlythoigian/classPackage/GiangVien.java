package com.gameloft.pc.quanlythoigian.classPackage;

import java.io.Serializable;

/**
 * Created by HOAN on 10/26/2017.
 */

public class GiangVien implements Serializable {
    protected String tenGV;
    protected String email;
    protected String sdt;

    public GiangVien(){

    }

    public GiangVien(String tenGV, String email, String sdt) {
        this.tenGV = tenGV;
        this.email = email;
        this.sdt = sdt;
    }

    public String getTenGV() {
        return tenGV;
    }

    public void setTenGV(String tenGV) {
        this.tenGV = tenGV;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
