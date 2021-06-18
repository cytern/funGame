package com.dam.gaming.view;

import com.dam.gaming.engine.Input;
import com.dam.gaming.engine.Window;
import com.dam.gaming.pojo.WindowPojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static org.lwjgl.glfw.GLFW.*;

/**
 * @author : dam
 * @description :测试线程
 * @create :2021-06-16 14:38:00
 */
@Service
@Slf4j

public class DemoView {
    private final Window window;
    private final Input input;

    private float r = 0;
    private float g = 0;
    private float b = 0;

    public DemoView(Window window, Input input) {
        this.window = window;
        this.input = input;
    }


    public void render() {
        window.swapBuffers();
    }

    public void update() {
        window.update();
        if (input.isButtonDown(GLFW_MOUSE_BUTTON_LEFT)){
            log.info("X:  [{}], Y: [{}]",input.getMouseX(),input.getMouseY() );
        }
    }
    public void run () {
        init();
        while (!window.shouldClose()){
            update();
            render();
            if (input.isKeyDown(GLFW_KEY_Q)) {
                log.info("输入了Q");
            }
            if (input.isKeyDown(GLFW_KEY_ESCAPE)) {
                break;
            }
        }
        window.destroy();
    }

    public void init() {
        log.info("初始化游戏");
        WindowPojo windowPojo = WindowPojo.getDemoPojo();
        window.setWindowPojo(windowPojo);
        window.setBackgroundColor(1,0,0);
        window.create();
    }
    @Async("taskPool")
    public void start(){
        run();
    }
}
