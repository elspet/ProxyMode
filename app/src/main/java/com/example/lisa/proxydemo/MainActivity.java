package com.example.lisa.proxydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lisa.proxydemo.dynamic.BuyInsuranceIml;
import com.example.lisa.proxydemo.dynamic.IBuyInsurance;

public class MainActivity extends AppCompatActivity implements IBuyInsurance.BuyInsuranceCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int money = 3500;
//        IBuyInsurance buyInsurance = new BuyInsuranceIml();
        BuyInsuranceIml buyInsuranceIml = new BuyInsuranceIml();

    }

    /**
     * TODO 找零
     * @param changeMoney
     */
    @Override
    public void change(int changeMoney) {

    }

    /**
     * TODO 回馈信息
     * @param feedbackInfo
     */
    @Override
    public void feedback(String feedbackInfo) {

    }
}
