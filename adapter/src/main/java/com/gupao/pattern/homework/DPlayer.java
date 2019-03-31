package com.gupao.pattern.homework;

public class DPlayer implements GpPlaybackPlayer {
    @Override
    public boolean supportPlayer(Object player) {
        return player instanceof DPlayer;
    }

    @Override
    public void player(Object player) {
        System.out.println("通过大黄蜂播放器播放回放");
    }
}
