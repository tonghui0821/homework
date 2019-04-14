package com.gupao.pattern.homework;

public class StrategyTest {
    public static void main(String[] args) {
        PayStrategy.getPay(PayType.ALIPAY).pay();
    }
}
