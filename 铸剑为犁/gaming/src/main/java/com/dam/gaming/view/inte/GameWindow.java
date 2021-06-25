package com.dam.gaming.view.inte;

/**
 * @author : dam
 * @description :窗口接口
 * @create :2021-06-23 10:30:00
 */
public interface GameWindow {
    int width = 1080;
    int height = 760;
    void init(Integer width,Integer height);

    void render();
}
