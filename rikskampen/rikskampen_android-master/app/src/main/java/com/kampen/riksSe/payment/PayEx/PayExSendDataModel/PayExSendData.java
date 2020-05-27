package com.kampen.riksSe.payment.PayEx.PayExSendDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayExSendData {

    @SerializedName("payment")
    @Expose
    private PaymentSend payment;

    public PaymentSend getPayment() {
        return payment;
    }

    public void setPayment(PaymentSend payment) {
        this.payment = payment;
    }


}
