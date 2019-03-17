package com.gupaoedu.patternSinglton;

import com.gupaoedu.patternSinglton.register.ContainerSingleton;

public class ContainerSingletonTest {
    public static void main(String[] args) {
        Object obj = ContainerSingleton.getBean("com.gupaoedu.patternSinglton.Pojo");

        System.out.println(obj);
    }
}
