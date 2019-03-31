package com.gupao.prototype;

import java.util.ArrayList;
import java.util.List;

public class PersonTest {
    public static void main(String[] args) {
        Person personA  = new Person();
        personA.setAge(18);
        personA.setName("Seven");
        List<Score> scoreList = new ArrayList<>();
        Score score = new Score();
        score.setName("英语");
        score.setSocre(100);
        scoreList.add(score);
        score = new Score();
        score.setName("数学");
        score.setSocre(99);
        scoreList.add(score);
        personA.setScoreList(scoreList);
        try {
            Person personB = (Person)personA.clone();
            Person personC = personA.shallowClone(personA);
            System.out.println(personA.getScoreList()==personB.getScoreList());
            System.out.println(personA.getScoreList()==personC.getScoreList());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
