# ProxyMode

代理模式可以分为动态代理和静态代理两种代理方式。
动态代理相对于静态代理更受推崇。

#### 一、静态代理
`IBuyInsurance` 一个抽象接口，里面有一个或多个抽象方法：
`BuyInsuranceIml`一个抽象接口的实现类，可以直接调用
`BuyInsuranceProxy`一个实现了抽象接口的代理类，实现了抽象接口的抽象方法，调用到实现类。


BuyInsuranceProxy 静态代理关键代码：
```java
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

```

---

#### 二、动态代理
`IBuyInsurance` 一个抽象接口，里面有一个或多个抽象方法：
`BuyInsuranceIml`一个抽象接口的实现类，可以直接调用
`BuyInsuranceDynamicProxy`一个实现了`InvocationHandler`接口的代理类，构造函数中使用到了IBuyInsurance接口作为参数。


BuyInsuranceDynamicProxy重写的invoke方法展示：
```java
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
```

动态代理的调用方法展示：

```java
    public void dynamicProxyBuyCar(IBuyInsurance buyInsuranceIml,int money){
        BuyInsuranceDynamicProxy dynamicProxy = new BuyInsuranceDynamicProxy(buyInsuranceIml);
        ClassLoader classLoader = buyInsuranceIml.getClass().getClassLoader();
        IBuyInsurance buyInsurance= (IBuyInsurance) Proxy.newProxyInstance(classLoader,new Class[]{IBuyInsurance.class},dynamicProxy);

        Log.d(TAG,"花"+money+"人民币买车");
        buyInsurance.buyInsurance(money, this);
    }
```
