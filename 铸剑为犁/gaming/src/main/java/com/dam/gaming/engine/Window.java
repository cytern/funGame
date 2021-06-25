package com.dam.gaming.engine;

import com.dam.gaming.pojo.WindowPojo;
import com.dam.gaming.service.math.Vector3f;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
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

    private final Input input;

    private WindowPojo windowPojo;
    private long windowId;
    private long time;
    private Integer frames = 0;
    private GLFWWindowSizeCallback windowSizeCallback;
    private boolean isResize;
    private boolean isFullScreen = false;
    private int windowPosX;
    private int windowPosY;

    private Vector3f vector3f =  new Vector3f(0,0,0);

    public Window(Input input) {
        this.input = input;
    }

    public void setWindowPojo(WindowPojo windowPojo) {
        this.windowPojo = windowPojo;
    }

    public void create() {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!GLFW.glfwInit()) {
            log.error("glfw 未进行初始化");
            return;
        }
        input.initData();
        GLFW.glfwDefaultWindowHints();
        windowId = GLFW.glfwCreateWindow(windowPojo.getWidth(), windowPojo.getHeight(), windowPojo.getName(), isFullScreen? GLFW.glfwGetPrimaryMonitor() : 0, NULL);
        if (windowId == 0) {
            log.error("window 未进行初始化");
            return;
        }

        GLFWVidMode glfwVidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        windowPosX = (glfwVidMode.width() - windowPojo.getWidth()) / 2;
        windowPosY = (glfwVidMode.height() - windowPojo.getHeight()) / 2;
        GLFW.glfwSetWindowPos(windowId, windowPosX, windowPosY);
        GLFW.glfwMakeContextCurrent(windowId);
        GL.createCapabilities();
        GLFW.glfwShowWindow(windowId);
        GLFW.glfwSwapInterval(1);
        createCallbacks();
        time = System.currentTimeMillis();
        Vector3f vector3f = new Vector3f(0,0,0);
        this.vector3f = vector3f;
    }


    public void update() {
        if (isResize) {
            GL11.glViewport(0,0,windowPojo.getWidth(),windowPojo.getHeight());
            isResize = false;
            if (isFullScreen) {

                GLFW.glfwSetWindowMonitor(windowId,GLFW.glfwGetPrimaryMonitor(),0,0,windowPojo.getWidth(),windowPojo.getHeight(), 0);
            }else {
                GLFW.glfwSetWindowMonitor(windowId,0,0,0,windowPojo.getWidth(),windowPojo.getHeight(),0);
            }
        }
        GL11.glClearColor(vector3f.getX(),vector3f.getY(),vector3f.getZ(),1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GLFW.glfwPollEvents();
        frames++;
        if (System.currentTimeMillis() > time + 1000) {
            GLFW.glfwSetWindowTitle(windowId, windowPojo.getName() + "   |FPS: " + frames);
            frames = 0;
            time = System.currentTimeMillis();
        }

    }

    public void swapBuffers() {
        GLFW.glfwSwapBuffers(windowId);
    }

    public void destroy() {
        input.destroy();
        GLFW.glfwWindowShouldClose(windowId);
        GLFW.glfwDestroyWindow(windowId);
        GLFW.glfwTerminate();
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(windowId);
    }

    public void setBackgroundColor( float r,float g,float b) {
       vector3f.setY(r);
       vector3f.setY(g);
       vector3f.setZ(b);

    }

    public void setFullScreen() {
        this.isFullScreen = !this.isFullScreen;
        isResize = true;
    }

    private void createCallbacks() {
        windowSizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                windowPojo.setWidth(width);
                windowPojo.setHeight(height);
                isResize = true;
            }
        };
        GLFW.glfwSetKeyCallback(windowId, input.getKeyCallback());
        GLFW.glfwSetCursorPosCallback(windowId, input.getPosCallback());
        GLFW.glfwSetMouseButtonCallback(windowId, input.getMouseButtonCallback());
        GLFW.glfwSetWindowSizeCallback(windowId, windowSizeCallback);
    }

}
