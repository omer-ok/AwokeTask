package com.kampen.riksSe.payment.PayEx.PayExInvoiceSendDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayExInvoiceSendData {

    @SerializedName("payment")
    @Expose
    private PaymentInvoice payment;
    @SerializedName("invoice")
    @Expose
    private Invoice invoice;

    public PaymentInvoice getPayment() {
        return payment;
    }

    public void setPayment(PaymentInvoice payment) {
        this.payment = payment;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }


}
