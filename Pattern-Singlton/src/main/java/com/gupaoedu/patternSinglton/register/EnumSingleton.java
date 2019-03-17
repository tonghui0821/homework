package com.gupaoedu.patternSinglton.register;

public enum EnumSingleton {
    INSTANCE;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private Object data;


    public static EnumSingleton getInstance(){
        return  INSTANCE;
    }
}
