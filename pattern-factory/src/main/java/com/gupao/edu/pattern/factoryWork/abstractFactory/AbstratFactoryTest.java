package main.java.com.gupao.edu.pattern.factoryWork.abstractFactory;

public class AbstratFactoryTest {
    public static void main(String[] args) {
        IAbstratorFactory factory = new AbstractCarFactory();
        factory.colour().getColour();
    }
}
