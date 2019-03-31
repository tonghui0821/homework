package com.gupao.pattern.homework;

public interface TemplateInterface {

    public void choice();

    public void pay();

    default void course() {
        choice();

        pay();
    }
}
