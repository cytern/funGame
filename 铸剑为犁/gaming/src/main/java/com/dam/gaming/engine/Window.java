package com.dam.gaming.engine;

import com.dam.gaming.pojo.WindowPojo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.springframework.stereotype.Service;

import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * @author : dam
 * @description :
 * @create :2021-06-16 14:57:00
 */
@Service
@Slf4j
public class Window {
    private WindowPojo windowPojo;
    private long window;
     public void setWindowPojo(WindowPojo windowPojo) {
         this.windowPojo = windowPojo;
     }
    public void create() {
        GLFWErrorCallback.createPrint(System.err).set();

       if (!GLFW.glfwInit()) {
           log.error("glfw 未进行初始化");
           return;
       }
       GLFW.glfwDefaultWindowHints();
       GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE,GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
       window = GLFW.glfwCreateWindow(windowPojo.getWidth(),windowPojo.getHeight(),windowPojo.getName(),NULL,NULL);
       if (window == 0) {
           log.error("window 未进行初始化");
           return;
       }
        GLFWVidMode glfwVidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
       GLFW.glfwSetWindowPos(window,(glfwVidMode.width()*windowPojo.getWidth())/2,(glfwVidMode.height()*windowPojo.getHeight())/2);
       GLFW.glfwMakeContextCurrent(window);
       GLFW.glfwShowWindow(window);
       GLFW.glfwSwapInterval(1);
    }


    public void update() {
         GLFW.glfwPollEvents();
    }

    public void swapBuffers() {
         GLFW.glfwSwapBuffers(window);
    }

    public boolean shouldClose() {
         return GLFW.glfwWindowShouldClose(window);
    }
}
