package com.gupao.pattern.homework;

import com.google.common.eventbus.Subscribe;

/**
 *
 */
public class GuavaEvent {

    @Subscribe
    public void subscribe(Question question){

        System.out.println("收到：" + question.getUserName() + "同学问题");
        System.out.println("问题内容为：" + question.getContent());
    }

}
