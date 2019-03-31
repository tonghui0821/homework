package com.gupao.pattern.homework;

public class Gplyer implements GpPlaybackPlayer {
    @Override
    public boolean supportPlayer(Object player) {
        return player instanceof Gplyer;
    }

    @Override
    public void player( Object player) {
        System.out.println("通过小泡播放器播放回放");
    }
}
