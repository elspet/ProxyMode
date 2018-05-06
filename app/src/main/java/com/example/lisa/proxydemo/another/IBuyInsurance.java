package com.example.lisa.proxydemo.another;


/**
 * TODO 对外开放的购买保险的接口
 */
public interface IBuyInsurance {

    void buyInsurance(int money,BuyInsuranceCallback callback);

    interface BuyInsuranceCallback{
        void change(int changeMoney);
        void feedback(String feedbackInfo);
    }
}
