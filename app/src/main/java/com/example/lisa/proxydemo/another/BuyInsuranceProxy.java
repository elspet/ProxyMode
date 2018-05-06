package com.example.lisa.proxydemo.another;

/**
 * TODO 保险销售→购买保险的代理
 */
public class BuyInsuranceProxy implements IBuyInsurance{

    public BuyInsuranceIml buyInsuranceIml;
    public BuyInsuranceCallback callback;

    public BuyInsuranceProxy(BuyInsuranceIml buyInsurance){
        this.buyInsuranceIml = buyInsurance;
    }

    @Override
    public void buyInsurance(int money,BuyInsuranceCallback callback) {
        this.callback = callback;
        int changeMoney = money;

        if(money>=3000){

            // 销售建议可以买两个险种
            callback.feedback("推荐您可以购买这三个险种的保险，买这些保险都有哪些益处balaba");
            buyInsuranceIml.buyInsurance(1500,callback);
            changeMoney -=1500;
            buyInsuranceIml.buyInsurance(1000,callback);
            changeMoney -= 1000;
            buyInsuranceIml.buyInsurance(500,callback);
            changeMoney -= 500;

            callback.change(changeMoney);

        }else if(money>=2500){

            // 销售建议可以买两个险种
            callback.feedback("推荐您购买这两个险种的保险，买这些保险都有哪些益处balabala~~~");
            buyInsuranceIml.buyInsurance(1500,callback);
            changeMoney -=1500;
            buyInsuranceIml.buyInsurance(1000,callback);// 其实就是买1000的
            changeMoney -=1000;

            callback.change(changeMoney);
        }else if(money >= 1000){
            // 销售建议可以买价位为1000的这个险种
            callback.feedback("推荐您购买这个险种的保险，买这份保险都有哪些益处balabala~~~");
            buyInsuranceIml.buyInsurance(1000,callback);
            changeMoney -= 1000;
            callback.change(changeMoney);
        }else{
            // 销售建议只买得起最低标准的险种
            callback.feedback("推荐您购买这个险种的保险，买这份保险都有哪些益处balabala~~~");
            buyInsuranceIml.buyInsurance(money,callback);
        }
    }
}
