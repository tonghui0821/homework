package com.gupaoedu.patternSinglton.lazy;

public class LazySimpleSingleton {
    private static  LazySimpleSingleton lazy;

    private LazySimpleSingleton(){}

    public synchronized static LazySimpleSingleton getInstance(){
        if(null==lazy){
            lazy = new LazySimpleSingleton();
        }
        return lazy;
    }
}
