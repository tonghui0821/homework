package main.java.com.gupao.edu.pattern.factoryWork.methodFactory;

public class MethodFactoryTest {
    public static void main(String[] args) {
        ICarFactory carFactory = new BenzCarFactory();
        carFactory.car().name();
    }
}
