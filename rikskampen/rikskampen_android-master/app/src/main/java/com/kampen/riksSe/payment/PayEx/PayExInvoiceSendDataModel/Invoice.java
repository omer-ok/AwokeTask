package com.kampen.riksSe.payment.PayEx.PayExInvoiceSendDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Invoice {

    @SerializedName("invoiceType")
    @Expose
    private String invoiceType;

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

}
