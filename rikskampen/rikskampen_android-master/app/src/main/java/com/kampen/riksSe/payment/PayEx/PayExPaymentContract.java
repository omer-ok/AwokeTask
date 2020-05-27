package com.kampen.riksSe.payment.PayEx;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;
import com.kampen.riksSe.payment.PayEx.PayExInvoiceSendDataModel.PayExInvoiceSendData;
import com.kampen.riksSe.payment.PayEx.PayExSendDataModel.PayExSendData;

public class PayExPaymentContract {

    interface View extends BaseView<Presenter> {

        void setPayExPaymentSucess(String message,double orderNumber);
        void setPayExPaymentFailed(String message);

        void setPayExInvoicePaymentSucess(String message,double orderNumber);
        void setPayExInvoicePaymentFailed(String message);

        void setPromoCodeSucess(String message,String discountedPackagePrice,String discountedActualPrice);
        void setPromoCodeFailed(String message);

    }

    interface Presenter extends BasePresenter {


         void SetPayExPayments(PayExSendData payExSendData);
         void SetPAyExInvoicePayment(PayExInvoiceSendData payExInvoiceSendData);
         void SetPromoCode(String PackageId,String PackagePrice,String DiscountCode);
    }

}
