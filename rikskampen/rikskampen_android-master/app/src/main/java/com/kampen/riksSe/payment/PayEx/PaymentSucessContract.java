package com.kampen.riksSe.payment.PayEx;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;

public class PaymentSucessContract {

    interface View extends BaseView<Presenter> {

        void setPaymentSucess(String message);
        void setPaymentFailed(String message);

    }

    interface Presenter extends BasePresenter {

        void performPaymentSign_up(String f_name, String l_name, String user_pass, String email, String gender, String dob, String hight, String weight,String fnameDuo,String lnameDuo,String product_id,String address,String tax,String total,String payment_status,String payment_methods,String reference_no,String order_status,String emailDuo,String userID);


    }

}
