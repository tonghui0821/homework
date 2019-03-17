package com.gupaoedu.patternSinglton.seriable;

public class SeriableSingleton {

    private SeriableSingleton(){}

    public static final SeriableSingleton INSTANCE = new SeriableSingleton();

    public static SeriableSingleton getInstance(){
        return INSTANCE;
    }
    private Object readResolve(){
        return INSTANCE;
    }
}
