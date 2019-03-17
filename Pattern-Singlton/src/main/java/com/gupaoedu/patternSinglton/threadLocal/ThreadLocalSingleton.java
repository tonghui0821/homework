package com.gupaoedu.patternSinglton.threadLocal;

public class ThreadLocalSingleton  {

    private ThreadLocalSingleton(){}
    private static final ThreadLocal<ThreadLocalSingleton> threadLoaclInstance = new ThreadLocal<ThreadLocalSingleton>(){

        @Override
        protected ThreadLocalSingleton initialValue(){
            return new ThreadLocalSingleton();
        }

    };

    public static ThreadLocalSingleton getInstance(){
        return threadLoaclInstance.get();
    }
}
