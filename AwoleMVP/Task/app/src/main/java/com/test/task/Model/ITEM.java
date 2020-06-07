package com.test.task.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ITEM {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("LINKED_PROD_ID")
    @Expose
    private String lINKEDPRODID;
    @SerializedName("LINKED_SECTION_CODE")
    @Expose
    private String lINKEDSECTIONCODE;
    @SerializedName("NAME")
    @Expose
    private String nAME;
    @SerializedName("SORT")
    @Expose
    private String sORT;
    @SerializedName("IMAGE_FILE")
    @Expose
    private String iMAGEFILE;
    @SerializedName("ACTIVE_FROM")
    @Expose
    private String aCTIVEFROM;
    @SerializedName("ACTIVE_TO")
    @Expose
    private String aCTIVETO;
    @SerializedName("PRE_ORDER")
    @Expose
    private String pREORDER;
    @SerializedName("HIGHLIGHT")
    @Expose
    private String hIGHLIGHT;
    @SerializedName("IMAGE")
    @Expose
    private IMAGE iMAGE;
    @SerializedName("PRICES")
    @Expose
    private PRICES pRICES;
    @SerializedName("CART_DATA")
    @Expose
    private CARTDATA cARTDATA;
    @SerializedName("PRODUCT_LINK")
    @Expose
    private String pRODUCTLINK;
    @SerializedName("STATE")
    @Expose
    private String sTATE;
    @SerializedName("TIMER")
    @Expose
    private TIMER tIMER;
    @SerializedName("WEB_SALE")
    @Expose
    private String wEBSALE;
    @SerializedName("CART")
    @Expose
    private CART cART;
    @SerializedName("VIDEO_ID")
    @Expose
    private String vIDEOID;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getLINKEDPRODID() {
        return lINKEDPRODID;
    }

    public void setLINKEDPRODID(String lINKEDPRODID) {
        this.lINKEDPRODID = lINKEDPRODID;
    }

    public String getLINKEDSECTIONCODE() {
        return lINKEDSECTIONCODE;
    }

    public void setLINKEDSECTIONCODE(String lINKEDSECTIONCODE) {
        this.lINKEDSECTIONCODE = lINKEDSECTIONCODE;
    }

    public String getNAME() {
        return nAME;
    }

    public void setNAME(String nAME) {
        this.nAME = nAME;
    }

    public String getSORT() {
        return sORT;
    }

    public void setSORT(String sORT) {
        this.sORT = sORT;
    }

    public String getIMAGEFILE() {
        return iMAGEFILE;
    }

    public void setIMAGEFILE(String iMAGEFILE) {
        this.iMAGEFILE = iMAGEFILE;
    }

    public String getACTIVEFROM() {
        return aCTIVEFROM;
    }

    public void setACTIVEFROM(String aCTIVEFROM) {
        this.aCTIVEFROM = aCTIVEFROM;
    }

    public String getACTIVETO() {
        return aCTIVETO;
    }

    public void setACTIVETO(String aCTIVETO) {
        this.aCTIVETO = aCTIVETO;
    }

    public String getPREORDER() {
        return pREORDER;
    }

    public void setPREORDER(String pREORDER) {
        this.pREORDER = pREORDER;
    }

    public String getHIGHLIGHT() {
        return hIGHLIGHT;
    }

    public void setHIGHLIGHT(String hIGHLIGHT) {
        this.hIGHLIGHT = hIGHLIGHT;
    }

    public IMAGE getIMAGE() {
        return iMAGE;
    }

    public void setIMAGE(IMAGE iMAGE) {
        this.iMAGE = iMAGE;
    }

    public PRICES getPRICES() {
        return pRICES;
    }

    public void setPRICES(PRICES pRICES) {
        this.pRICES = pRICES;
    }

    public CARTDATA getCARTDATA() {
        return cARTDATA;
    }

    public void setCARTDATA(CARTDATA cARTDATA) {
        this.cARTDATA = cARTDATA;
    }

    public String getPRODUCTLINK() {
        return pRODUCTLINK;
    }

    public void setPRODUCTLINK(String pRODUCTLINK) {
        this.pRODUCTLINK = pRODUCTLINK;
    }

    public String getSTATE() {
        return sTATE;
    }

    public void setSTATE(String sTATE) {
        this.sTATE = sTATE;
    }

    public TIMER getTIMER() {
        return tIMER;
    }

    public void setTIMER(TIMER tIMER) {
        this.tIMER = tIMER;
    }

    public String getWEBSALE() {
        return wEBSALE;
    }

    public void setWEBSALE(String wEBSALE) {
        this.wEBSALE = wEBSALE;
    }

    public CART getCART() {
        return cART;
    }

    public void setCART(CART cART) {
        this.cART = cART;
    }

    public String getVIDEOID() {
        return vIDEOID;
    }

    public void setVIDEOID(String vIDEOID) {
        this.vIDEOID = vIDEOID;
    }

}
