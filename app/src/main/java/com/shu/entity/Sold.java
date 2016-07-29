package com.shu.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/7/13.
 */
public class Sold implements Serializable{

    private static final long serialVersionUID = 1L;

    private int s_num;
    private String rfid;
    private Date s_date;
    private String shapcode;
    private double o_price;
    private double s_price;

    public Sold() {
    }

    public Sold(int s_num, String rfid, Date s_date, String shapcode, double o_price, double s_price) {
        this.s_num = s_num;
        this.rfid = rfid;
        this.s_date = s_date;
        this.shapcode = shapcode;
        this.o_price = o_price;
        this.s_price = s_price;
    }

    public int getS_num() {
        return s_num;
    }

    public void setS_num(int s_num) {
        this.s_num = s_num;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public Date getS_date() {
        return s_date;
    }

    public void setS_date(Date s_date) {
        this.s_date = s_date;
    }

    public String getShapcode() {
        return shapcode;
    }

    public void setShapcode(String shapcode) {
        this.shapcode = shapcode;
    }

    public double getO_price() {
        return o_price;
    }

    public void setO_price(double o_price) {
        this.o_price = o_price;
    }

    public double getS_price() {
        return s_price;
    }

    public void setS_price(double s_price) {
        this.s_price = s_price;
    }
}
