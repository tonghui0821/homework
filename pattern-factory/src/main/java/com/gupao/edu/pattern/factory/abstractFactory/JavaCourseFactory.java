package main.java.com.gupao.edu.pattern.factory.abstractFactory;

import main.java.com.gupao.edu.pattern.factory.ICourse;
import main.java.com.gupao.edu.pattern.factory.JavaCourse;

public class JavaCourseFactory implements ICourseFactory {
    public ICourse createCourse() {
        return new JavaCourse();
    }

    public INote createNote() {
        return new JaveNote();
    }

    public IVideo createVideo() {
        return new JavaVideo();
    }

    public ISource createSource() {
        return new JavaSource();
    }
}
