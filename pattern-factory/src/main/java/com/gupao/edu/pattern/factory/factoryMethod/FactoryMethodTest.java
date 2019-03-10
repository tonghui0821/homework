package main.java.com.gupao.edu.pattern.factory.factoryMethod;

import main.java.com.gupao.edu.pattern.factory.ICourse;

public class FactoryMethodTest {
    public static void main(String[] args) {
        ICourseFactory courseFactory = new PythonCourseFactory();
        ICourse course = courseFactory.create();
        course.record();
    }
}
