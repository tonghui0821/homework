package com.gupaoedu.patternSinglton.lazy;

public class LazyInnerClassSingleton {

    private LazyInnerClassSingleton(){
        if(LazyHolder.lazy!=null){
            throw new RuntimeException("不允许创建多个实例");
        }
    }

    //懒汉式单例
    public static LazyInnerClassSingleton getInstance(){
        return LazyHolder.lazy;
    }
    private static class LazyHolder{
        private static final LazyInnerClassSingleton lazy = new LazyInnerClassSingleton();
    }
}
