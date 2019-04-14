package com.gupao.pattern.homework;

import java.util.HashMap;
import java.util.Map;

public class PayStrategy {

    private static final Map<String,Pay> payMap = new HashMap<>();
    static{
        payMap.put(PayType.ALIPAY,new AliPay());
        payMap.put(PayType.JDPAY,new JdPay());
    }
    public static Pay getPay(String payKey){
        return payMap.get(payKey);
    }
}
