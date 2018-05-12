package com.example.lisa.proxydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.lisa.proxydemo.another.BuyInsuranceProxy;
import com.example.lisa.proxydemo.dynamic.BuyInsuranceDynamicProxy;
import com.example.lisa.proxydemo.dynamic.BuyInsuranceIml;
import com.example.lisa.proxydemo.dynamic.IBuyInsurance;

import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity implements IBuyInsurance.BuyInsuranceCallback {

    public static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"~~~~~");

        BuyInsuranceIml buyInsuranceIml1 = new BuyInsuranceIml();
        Log.d(TAG,"小谢最近赚了点钱，准备自己买保险咯~~~~~");
        buyInsuranceIml1.buyInsurance(3200,this);

        Log.d(TAG,"小谢最近赚了点钱，准备找静态销售买保险咯~~~~~");
        BuyInsuranceProxy buyInsuranceProxy = new BuyInsuranceProxy(new com.example.lisa.proxydemo.another.BuyInsuranceIml());
        buyInsuranceProxy.buyInsurance(3200, new com.example.lisa.proxydemo.another.IBuyInsurance.BuyInsuranceCallback() {
            @Override
            public void change(int changeMoney) {
                Log.d(TAG,"找零："+changeMoney);
            }

            @Override
            public void feedback(String feedbackInfo) {
                Log.d(TAG,"反馈信息是："+feedbackInfo);
            }
        });

        Log.d(TAG,"小谢最近赚了点钱，准备找动态销售买保险咯~~~~~");
        IBuyInsurance buyInsuranceIml = new BuyInsuranceIml();
        dynamicProxyBuyCar(buyInsuranceIml,3200);



    }



    public void dynamicProxyBuyCar(IBuyInsurance buyInsuranceIml,int money){
        BuyInsuranceDynamicProxy dynamicProxy = new BuyInsuranceDynamicProxy(buyInsuranceIml);
        ClassLoader classLoader = buyInsuranceIml.getClass().getClassLoader();
        IBuyInsurance buyInsurance= (IBuyInsurance) Proxy.newProxyInstance(classLoader,new Class[]{IBuyInsurance.class},dynamicProxy);

        Log.d(TAG,"花"+money+"人民币买车");
        buyInsurance.buyInsurance(money, this);
    }

    /**
     * TODO 找零
     * @param changeMoney
     */
    @Override
    public void change(int changeMoney) {
        Log.d(TAG,"找零："+changeMoney);
    }

    /**
     * TODO 回馈信息
     * @param feedbackInfo
     */
    @Override
    public void feedback(String feedbackInfo) {
        Log.d(TAG,"反馈信息是："+feedbackInfo);
    }
}
