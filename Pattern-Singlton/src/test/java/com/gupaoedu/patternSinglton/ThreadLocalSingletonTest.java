package com.gupaoedu.patternSinglton;

import com.gupaoedu.patternSinglton.threadLocal.ThreadLocalSingleton;

public class ThreadLocalSingletonTest {
    public static void main(String[] args) {
        System.out.println(ThreadLocalSingleton.getInstance());
    }
}
