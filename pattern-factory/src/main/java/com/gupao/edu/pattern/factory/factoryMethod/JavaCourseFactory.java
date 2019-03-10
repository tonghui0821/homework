package main.java.com.gupao.edu.pattern.factory.factoryMethod;

import main.java.com.gupao.edu.pattern.factory.ICourse;
import main.java.com.gupao.edu.pattern.factory.JavaCourse;

public class JavaCourseFactory implements ICourseFactory {
    public ICourse create() {
        return new JavaCourse();
    }
}
