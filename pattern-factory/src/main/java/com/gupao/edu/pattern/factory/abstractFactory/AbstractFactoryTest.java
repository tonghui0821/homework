package main.java.com.gupao.edu.pattern.factory.abstractFactory;

public class AbstractFactoryTest {
    public static void main(String[] args) {
        ICourseFactory factory = new JavaCourseFactory();
        factory.createCourse().record();
        factory.createNote();
        factory.createVideo();
    }
}
