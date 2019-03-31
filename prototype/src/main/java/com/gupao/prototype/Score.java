package com.gupao.prototype;

import java.io.Serializable;

public class Score implements Serializable {

    private String name;
    private int socre;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSocre() {
        return socre;
    }

    public void setSocre(int socre) {
        this.socre = socre;
    }
}
