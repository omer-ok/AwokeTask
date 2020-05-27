package com.kampen.riksSe.payment.PayEx;

import androidx.annotation.NonNull;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.data_manager.Repository;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class PaymentSucessPresenter implements PaymentSucessContract.Presenter{



    @NonNull
    private final PaymentSucessContract.View  mSignupPaymentView;



    public PaymentSucessPresenter(@NonNull PaymentSucessContract.View paymentView)
    {
        mSignupPaymentView = checkNotNull(paymentView);

    }

    @Override
    public void performPaymentSign_up(String f_name, String l_name, String user_pass, String email, String gender, String dob, String hight, String weight, String fnameDuo, String lnameDuo, String product_id, String address, String tax, String total, String payment_status, String payment_methods, String reference_no, String order_status, String emailDuo,String userID) {


        Repository.PaymentsignUpUser(f_name, l_name,user_pass,email,gender,dob,hight,weight,fnameDuo,lnameDuo,product_id,address,tax,total,payment_status,payment_methods,reference_no,order_status,emailDuo,userID ,new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mSignupPaymentView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {

                    mSignupPaymentView.setPaymentSucess(status.getMsg());

                }
                else
                {

                    mSignupPaymentView.setPaymentFailed(status.getMsg());

                }

            }
        });

    }




   /* @Override
    public void performSign_up(String f_name, String l_name, String user_pass, String email, String gender, String dob, String hight, String weight) {

        Repository.signUpUser(f_name, l_name,user_pass,email,gender,dob,hight,weight, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mSignupView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {

                    mSignupView.setSignup(status.getMsg());

                }
                else
                {

                    mSignupView.setSignupFailed(status.getMsg());

                }



            }
        });
    }*/


}
