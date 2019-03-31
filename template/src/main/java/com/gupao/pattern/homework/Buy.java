package com.gupao.pattern.homework;

public class Buy implements TemplateInterface {
    @Override
    public void choice() {
        System.out.println("选择东西");
    }

    @Override
    public void pay() {
        System.out.println("付款");
    }
}
