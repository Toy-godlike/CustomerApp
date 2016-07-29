package com.shu.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/7/13.
 */
public class Discount implements Serializable{

    private static final long serialVersionUID = 1L;

    private int d_num;
    private String shapcode;
    private float disc;
    private Date beginDate;
    private Date endDate;

    public Discount() {
    }

    public Discount(int d_num, String shapcode, float disc, Date beginDate, Date endDate) {
        this.d_num = d_num;
        this.shapcode = shapcode;
        this.disc = disc;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public int getD_num() {
        return d_num;
    }

    public void setD_num(int d_num) {
        this.d_num = d_num;
    }

    public String getShapcode() {
        return shapcode;
    }

    public void setShapcode(String shapcode) {
        this.shapcode = shapcode;
    }

    public float getDisc() {
        return disc;
    }

    public void setDisc(float disc) {
        this.disc = disc;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
