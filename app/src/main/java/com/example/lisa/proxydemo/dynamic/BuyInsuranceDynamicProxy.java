package com.example.lisa.proxydemo.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import com.example.lisa.proxydemo.dynamic.IBuyInsurance.BuyInsuranceCallback;


/**
 * TODO 动态代理类
 */
public class BuyInsuranceDynamicProxy implements InvocationHandler {

    IBuyInsurance buyInsurance;

    public BuyInsuranceDynamicProxy(IBuyInsurance buyInsurance){
        this.buyInsurance = buyInsurance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        int money = (int)args[0];
        BuyInsuranceCallback callback = (BuyInsuranceCallback) args[1];

        int changeMoney = money;

        if(money>=3000){

            // 销售建议可以买两个险种
            callback.feedback("推荐您可以购买这三个险种的保险，买这些保险都有哪些益处balaba");
            buyInsurance.buyInsurance(1500,callback);
            changeMoney -=1500;
            buyInsurance.buyInsurance(1000,callback);
            changeMoney -= 1000;
            buyInsurance.buyInsurance(500,callback);
            changeMoney -= 500;

            callback.change(changeMoney);

        }else if(money>=2500){

            // 销售建议可以买两个险种
            callback.feedback("推荐您购买这两个险种的保险，买这些保险都有哪些益处balabala~~~");
            buyInsurance.buyInsurance(1500,callback);
            changeMoney -=1500;
            buyInsurance.buyInsurance(1000,callback);// 其实就是买1000的
            changeMoney -=1000;

            callback.change(changeMoney);
        }else if(money >= 1000){
            // 销售建议可以买价位为1000的这个险种
            callback.feedback("推荐您购买这个险种的保险，买这份保险都有哪些益处balabala~~~");
            buyInsurance.buyInsurance(1000,callback);
            changeMoney -= 1000;
            callback.change(changeMoney);
        }else{
            // 销售建议只买得起最低标准的险种
            callback.feedback("推荐您购买这个险种的保险，买这份保险都有哪些益处balabala~~~");
            buyInsurance.buyInsurance(money,callback);
        }
        return null;
    }
}
