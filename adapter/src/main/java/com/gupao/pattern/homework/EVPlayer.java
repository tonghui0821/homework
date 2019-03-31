package com.gupao.pattern.homework;

public class EVPlayer implements GpPlaybackPlayer {
    @Override
    public boolean supportPlayer(Object player) {
        return player instanceof EVPlayer;
    }

    @Override
    public void player(Object player) {
        System.out.println("通过EVPlayer播放器播放回放");
    }
}
