package com.kampen.riksSe.payment.PayEx.PayExInvoiceResponceModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PayExInvoiceResponceData {


    @SerializedName("payment")
    @Expose
    private PaymentInvoiceResponce payment;
    @SerializedName("operations")
    @Expose
    private List<OperationInvoice> operations = null;

    public PaymentInvoiceResponce getPayment() {
        return payment;
    }

    public void setPayment(PaymentInvoiceResponce payment) {
        this.payment = payment;
    }

    public List<OperationInvoice> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationInvoice> operations) {
        this.operations = operations;
    }

}
