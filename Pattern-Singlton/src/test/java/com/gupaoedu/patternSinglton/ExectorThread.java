package com.gupaoedu.patternSinglton;

import com.gupaoedu.patternSinglton.lazy.LazyDoubleCheckSingleton;
import com.gupaoedu.patternSinglton.lazy.LazySimpleSingleton;

import java.util.concurrent.ThreadLocalRandom;

public class ExectorThread implements Runnable{
    @Override
    public void run() {
//        LazySimpleSingleton singleton = LazySimpleSingleton.getInstance();
        LazyDoubleCheckSingleton singleton = LazyDoubleCheckSingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + ":" +  singleton);
    }
}
