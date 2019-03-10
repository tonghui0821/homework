package main.java.com.gupao.edu.pattern.factory.simpleFactory;

import main.java.com.gupao.edu.pattern.factory.ICourse;

public class CourseFactory {

//    public ICourse create(String name){
//        if("java".equals(name)){
//            return new JavaCourse();
//        }
//        return null;
//    }

//    public ICourse create(String className){
//        try{
//            if(!(null==className || "".equals(className))){
//                return (ICourse)Class.forName(className).newInstance();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }

    public ICourse create(Class clazz){
        try{
            if(null!=clazz){
                return (ICourse)clazz.newInstance();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
