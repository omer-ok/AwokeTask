package com.kampen.riksSe.payment.PayEx.PayExResponceModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PayExAllResponceData {

    @SerializedName("payment")
    @Expose
    private PaymentResponce payment;
    @SerializedName("operations")
    @Expose
    private List<Operation> operations = null;

    public PaymentResponce getPayment() {
        return payment;
    }

    public void setPayment(PaymentResponce paymentResponce) {
        this.payment = paymentResponce;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

}
