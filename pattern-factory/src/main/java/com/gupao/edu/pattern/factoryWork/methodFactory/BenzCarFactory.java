package main.java.com.gupao.edu.pattern.factoryWork.methodFactory;

import main.java.com.gupao.edu.pattern.factoryWork.BenzCar;
import main.java.com.gupao.edu.pattern.factoryWork.ICar;

public class BenzCarFactory implements ICarFactory {
    public ICar car() {
        return new BenzCar();
    }
}
