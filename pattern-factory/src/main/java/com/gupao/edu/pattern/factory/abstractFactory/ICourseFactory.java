package main.java.com.gupao.edu.pattern.factory.abstractFactory;

import main.java.com.gupao.edu.pattern.factory.ICourse;

public interface ICourseFactory {

    ICourse createCourse();

    INote createNote();

    IVideo createVideo();

    ISource createSource();
}
