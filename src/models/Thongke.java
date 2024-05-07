/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author ADMIN
 */
public class Thongke {
    private double doanhthu,von,loinhuan;
    private Users users;
    private int soSP;
    private ChiTietSP chiTietSP;
    public Thongke() {
    }

    public Thongke(double doanhthu) {
        this.doanhthu = doanhthu;
    }

    public Thongke(int soSP) {
        this.soSP = soSP;
    }

    public Thongke(Users users, int soSP) {
        this.users = users;
        this.soSP = soSP;
    }

    public Thongke(int soSP, ChiTietSP chiTietSP) {
        this.soSP = soSP;
        this.chiTietSP = chiTietSP;
    }

    public double getDoanhthu() {
        return doanhthu;
    }

    public void setDoanhthu(double doanhthu) {
        this.doanhthu = doanhthu;
    }

    public double getVon() {
        return von;
    }

    public void setVon(double von) {
        this.von = von;
    }

    public double getLoinhuan() {
        return loinhuan;
    }

    public void setLoinhuan(double loinhuan) {
        this.loinhuan = loinhuan;
    }

    @Override
    public String toString() {
        return "Thongke{" + "doanhthu=" + doanhthu + ", von=" + von + ", loinhuan=" + loinhuan + '}';
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public int getSoSP() {
        return soSP;
    }

    public void setSoSP(int soSP) {
        this.soSP = soSP;
    }

    public ChiTietSP getChiTietSP() {
        return chiTietSP;
    }

    public void setChiTietSP(ChiTietSP chiTietSP) {
        this.chiTietSP = chiTietSP;
    }


    
    
}
