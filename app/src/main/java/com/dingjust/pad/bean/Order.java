package com.dingjust.pad.bean;

/**
 * @author: haungsonglin
 * @version: 1.0
 */
public class Order {
    private String padid;
    private String jobid;
    private String date;
    private String ordid;
    private String ortyp;
    private String matnr;
    private String lfdat;
    private String gcdm;
    private String gxmc;
    private int smv;
    private String jqdm;
    private String jqdmtyp;
    private int diflv;
    private String qmreq;
    private String vduri;
    private String time;

    public Order(String matnr, String ortyp, String lfdat, int smv, String time, String gxmc, String jqdm, int diflv, String qmreq) {
        this.padid = padid;
        this.date = date;
        this.ordid = ordid;
        this.ortyp = ortyp;
        this.matnr = matnr;
        this.lfdat = lfdat;
        this.gxmc = gxmc;
        this.smv = smv;
        this.time = time;
        this.jqdm = jqdm;
        this.diflv = diflv;
        this.qmreq = qmreq;
    }

    public String getPadid() {
        return padid;
    }

    public void setPadid(String padid) {
        this.padid = padid;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrdid() {
        return ordid;
    }

    public void setOrdid(String ordid) {
        this.ordid = ordid;
    }

    public String getOrtyp() {
        return ortyp;
    }

    public void setOrtyp(String ortyp) {
        this.ortyp = ortyp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getLfdat() {
        return lfdat;
    }

    public void setLfdat(String lfdat) {
        this.lfdat = lfdat;
    }

    public String getGcdm() {
        return gcdm;
    }

    public void setGcdm(String gcdm) {
        this.gcdm = gcdm;
    }

    public String getGxmc() {
        return gxmc;
    }

    public void setGxmc(String gxmc) {
        this.gxmc = gxmc;
    }

    public int getSmv() {
        return smv;
    }

    public void setSmv(int smv) {
        this.smv = smv;
    }

    public String getJqdm() {
        return jqdm;
    }

    public void setJqdm(String jqdm) {
        this.jqdm = jqdm;
    }

    public String getJqdmtyp() {
        return jqdmtyp;
    }

    public void setJqdmtyp(String jqdmtyp) {
        this.jqdmtyp = jqdmtyp;
    }

    public int getDiflv() {
        return diflv;
    }

    public void setDiflv(int diflv) {
        this.diflv = diflv;
    }

    public String getQmreq() {
        return qmreq;
    }

    public void setQmreq(String qmreq) {
        this.qmreq = qmreq;
    }

    public String getVduri() {
        return vduri;
    }

    public void setVduri(String vduri) {
        this.vduri = vduri;
    }
}
