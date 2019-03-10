package main.java.com.gupao.edu.pattern.factory.factoryMethod;

import main.java.com.gupao.edu.pattern.factory.ICourse;
import main.java.com.gupao.edu.pattern.factory.JavaCourse;
import main.java.com.gupao.edu.pattern.factory.PythonCourse;

public class PythonCourseFactory implements ICourseFactory {
    public ICourse create() {
        return new PythonCourse();
    }
}
