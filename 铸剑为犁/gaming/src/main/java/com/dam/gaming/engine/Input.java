package com.dam.gaming.engine;

import lombok.Data;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.springframework.stereotype.Service;

/**
 * @author : dam
 * @description :输入
 * @create :2021-06-17 09:43:00
 */
@Service
@Data
public class Input {
    private boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private double mouseX,mouseY;
    private GLFWKeyCallback keyCallback;
    private GLFWCursorPosCallback posCallback;
    private GLFWMouseButtonCallback mouseButtonCallback;

    public void initData() {
        keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keys[key] = (action != GLFW.GLFW_RELEASE);
            }
        };
        posCallback = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xPos, double yPos) {
                mouseX = xPos;
                mouseY = yPos;
            }
        };


        mouseButtonCallback = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                buttons[button] = (action != GLFW.GLFW_RELEASE);

            }
        };
    }
    public  boolean isKeyDown(int key) {
        return keys[key];
    }
    public boolean isButtonDown(int key) {
        return buttons[key];
    }

    public void destroy(){
        mouseButtonCallback.free();
        keyCallback.free();
        posCallback.free();
    }

}
