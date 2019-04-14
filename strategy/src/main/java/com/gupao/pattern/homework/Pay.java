package com.gupao.pattern.homework;

public abstract class Pay {
    public abstract String getName();
    public void pay(){
        System.out.println(getName()+"支付");
    }
}
