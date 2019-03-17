package com.gupaoedu.patternSinglton.lazy;

public class LazyDoubleCheckSingleton {
    private volatile static  LazyDoubleCheckSingleton lazy;

    private LazyDoubleCheckSingleton(){}

    public  static LazyDoubleCheckSingleton getInstance(){
        if(null==lazy){
            synchronized (LazyDoubleCheckSingleton.class){
                if(null==lazy){
                    lazy = new LazyDoubleCheckSingleton();
                }
            }
        }
        return lazy;
    }
}
