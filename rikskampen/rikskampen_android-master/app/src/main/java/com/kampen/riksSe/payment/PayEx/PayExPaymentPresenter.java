package com.kampen.riksSe.payment.PayEx;

import android.content.Context;
import androidx.annotation.NonNull;

import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.payment.PayEx.PayExInvoiceSendDataModel.PayExInvoiceSendData;
import com.kampen.riksSe.payment.PayEx.PayExSendDataModel.PayExSendData;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.SaveSharedPreference;

//import androidx.annotation.NonNull;
import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class PayExPaymentPresenter implements PayExPaymentContract.Presenter {


    private final PayExPaymentContract.View mPayExView;
    private Context mContext;

    public PayExPaymentPresenter(@NonNull PayExPaymentContract.View PayExView, Context context) {
        mPayExView = checkNotNull(PayExView);
        this.mContext = context;
    }

    public Realm_User provideUserLocal(Context context) {

        String[] params = SaveSharedPreference.getLoggedParams(context);

        Realm_User user = Repository.provideUserLocal(params[0], params[1]);

        return user;
    }

    @Override
    public void SetPayExPayments(PayExSendData payExSendData) {

        Repository.PayExPayment(payExSendData, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {

                if (mPayExView != null && status.getCode() == 201 && status.getStatus().equals("Created")) {

                    mPayExView.setPayExPaymentSucess(status.getMsg(), status.getOrdernumber());

                } else {

                    mPayExView.setPayExPaymentFailed("PayEx Server Error");

                }


            }
        });

    }

    @Override
    public void SetPAyExInvoicePayment(PayExInvoiceSendData payExInvoiceSendData) {

        Repository.PayExInvoicePayment(payExInvoiceSendData, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {

                if (mPayExView != null && status.getCode() == 201 && status.getStatus().equals("Created")) {

                    mPayExView.setPayExInvoicePaymentSucess(status.getMsg(), status.getOrdernumber());

                } else {

                    mPayExView.setPayExInvoicePaymentFailed("PayEx Server Error");

                }


            }
        });


    }

    @Override
    public void SetPromoCode( String PackageId, String PackagePrice, String DiscountCode) {
        Realm_User user = provideUserLocal(mContext);

        String  token="bearer "+user.getToken();

        Repository.getPromoCodeData(user.getId(),PackageId,PackagePrice,DiscountCode,token, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                //mPayExView.setPromoCodeSucess(status.getMsg(),status.getDiscountedPacagePrice(),status.getDiscountedActualPrice());

                if (status.getCode() == 200 && status.getStatus().equals("success")) {

                    mPayExView.setPromoCodeSucess(status.getMsg(),status.getDiscountedPacagePrice(),status.getDiscountedActualPrice());

                } else {

                    mPayExView.setPromoCodeFailed(status.getMsg());

                }

            }
        });



    }
}