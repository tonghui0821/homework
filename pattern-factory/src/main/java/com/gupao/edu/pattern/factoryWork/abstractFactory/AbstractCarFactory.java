package main.java.com.gupao.edu.pattern.factoryWork.abstractFactory;

public class AbstractCarFactory implements IAbstratorFactory {
    public ICarColour colour() {
        return new CarColour();
    }
}
