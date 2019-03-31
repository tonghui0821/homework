package com.gupao.pattern.homework;

import com.google.common.eventbus.EventBus;

/**
 *
 */
public class GuavaEventTest {

    public static void main(String[] args) {
        //消息总线
        EventBus eventBus = new EventBus();
        GuavaEvent guavaEvent = new GuavaEvent();
        eventBus.register(guavaEvent);
        Question question = new Question();
        question.setUserName("小明");
        question.setContent("观察者设计模式适用哪些场景？");
        eventBus.post(question);

    }
}
