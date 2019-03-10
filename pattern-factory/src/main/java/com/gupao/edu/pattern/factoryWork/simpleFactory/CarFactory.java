package main.java.com.gupao.edu.pattern.factoryWork.simpleFactory;

import main.java.com.gupao.edu.pattern.factoryWork.ICar;

public class CarFactory {
    public ICar creatCar(Class clazz){
        if(null!=clazz){
            try {
                return (ICar) clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
