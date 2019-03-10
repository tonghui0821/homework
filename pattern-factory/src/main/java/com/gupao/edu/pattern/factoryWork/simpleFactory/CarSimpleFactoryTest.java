package main.java.com.gupao.edu.pattern.factoryWork.simpleFactory;

import main.java.com.gupao.edu.pattern.factoryWork.BenzCar;
import main.java.com.gupao.edu.pattern.factoryWork.ICar;

public class CarSimpleFactoryTest {
    public static void main(String[] args) {
        ICar car = new CarFactory().creatCar(BenzCar.class);
        car.name();
    }
}
