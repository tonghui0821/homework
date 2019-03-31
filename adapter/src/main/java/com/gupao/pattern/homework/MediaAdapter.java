package com.gupao.pattern.homework;

public class MediaAdapter implements MediaPlayer {


    @Override
    public void play(Class<? extends GpPlaybackPlayer> clazz) {
        try {
            GpPlaybackPlayer adapter = clazz.newInstance();
            if (adapter.supportPlayer(adapter)){
                adapter.player(adapter);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}