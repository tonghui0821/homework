package main.java.com.gupao.edu.pattern.factoryWork.methodFactory;

import main.java.com.gupao.edu.pattern.factoryWork.BenzCar;
import main.java.com.gupao.edu.pattern.factoryWork.BmwCar;
import main.java.com.gupao.edu.pattern.factoryWork.ICar;

public class BmwCarFactory implements ICarFactory {
    public ICar car() {
        return new BmwCar();
    }
}
