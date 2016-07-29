package com.shu.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/13.
 */
public class OnSale implements Serializable{

    private static final long serialVersionUID = 1L;

    private String rfid;
    private String shapcode;
    private int a_num;

    public OnSale() {
    }

    public OnSale(String rfid, String shapcode, int a_num) {
        this.rfid = rfid;
        this.shapcode = shapcode;
        this.a_num = a_num;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getShapcode() {
        return shapcode;
    }

    public void setShapcode(String shapcode) {
        this.shapcode = shapcode;
    }

    public int getA_num() {
        return a_num;
    }

    public void setA_num(int a_num) {
        this.a_num = a_num;
    }
}
