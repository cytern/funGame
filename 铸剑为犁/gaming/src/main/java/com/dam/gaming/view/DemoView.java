package com.dam.gaming.view;

import com.dam.gaming.engine.Window;
import com.dam.gaming.pojo.WindowPojo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.glfw.GLFW;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author : dam
 * @description :测试线程
 * @create :2021-06-16 14:38:00
 */
@Service
@Slf4j
@AllArgsConstructor
public class DemoView {
    private final Window window;
    public static long time;
    private static int frames;

    public void render() {
        window.swapBuffers();
    }

    public void update() {
        window.update();
        frames ++ ;
        if (System.currentTimeMillis() > time + 1000) {
            log.info("打印的frame 为 =[{}]",frames);
            time = System.currentTimeMillis();
            frames = 0;
        }
    }
    public void run () {
        init();
        while (!window.shouldClose()){
            render();
            update();
        }
    }

    public void init() {
        log.info("初始化游戏");
        WindowPojo windowPojo = WindowPojo.getDemoPojo();
        window.setWindowPojo(windowPojo);
        window.create();
        time = System.currentTimeMillis();
    }
    @Async("taskPool")
    public void start(){
        run();
    }
}
