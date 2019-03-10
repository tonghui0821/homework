package main.java.com.gupao.edu.pattern.factory.simpleFactory;

import main.java.com.gupao.edu.pattern.factory.ICourse;
import main.java.com.gupao.edu.pattern.factory.JavaCourse;

public class SimpleFactoryTest {
    public static void main(String[] args) {
        CourseFactory factory = new CourseFactory();
        //        ICourse course = factory.create("java");
//        ICourse course = factory.create("main.java.com.gupao.edu.pattern.factory.simpleFactory.JavaCourse");
        ICourse course = factory.create(JavaCourse.class);
        course.record();
    }
}
