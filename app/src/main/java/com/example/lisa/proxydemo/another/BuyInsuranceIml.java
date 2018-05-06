package com.example.lisa.proxydemo.another;

/**
 * TODO 购买保险实现类
 */
public class BuyInsuranceIml implements IBuyInsurance {


    @Override
    public void buyInsurance(int money,BuyInsuranceCallback callback) {
        int changeMoney =0;
        if(money>=1500){
            String buyInsuranceInfo = "spend ¥"+money+" for insurance type three！";
            callback.feedback(buyInsuranceInfo);
            changeMoney = money - 1500;
            if(changeMoney!=0){
                callback.change(changeMoney);
            }
        }else if(money>=1000){
            String buyInsuranceInfo = "spend ¥"+money+" for insurance type two！";
            callback.feedback(buyInsuranceInfo);
            changeMoney = money - 1000;
            if(changeMoney!=0){
                callback.change(changeMoney);
            }
        }else if(money>=500){
            String buyInsuranceInfo = "spend ¥"+money+" for insurance type one！";
            callback.feedback(buyInsuranceInfo);
            changeMoney = money - 500;
            if(changeMoney!=0){
                callback.change(changeMoney);
            }
        }else {
            callback.feedback("钱太少不购买！");
            changeMoney = money;
            if(changeMoney!=0){
                callback.change(changeMoney);
            }
        }

    }
}
