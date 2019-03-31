package com.gupao.prototype;

import java.io.*;
import java.util.List;

public class Person implements Cloneable,Serializable {

    private int age;
    private String name;

    public List<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
    }

    private List<Score> scoreList;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return deepClone();
    }

    private Object deepClone(){
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);

            Person person = (Person)ois.readObject();

            return person;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Person shallowClone(Person target){
        Person person = new Person();
        person.setAge(target.age);
        person.setName(target.name);
        person.setScoreList(target.getScoreList());
        return person;
    }
}
