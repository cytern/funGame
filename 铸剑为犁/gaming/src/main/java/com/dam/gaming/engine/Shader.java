package com.dam.gaming.engine;

import com.dam.gaming.engine.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

/**
 * @author : dam
 * @description :阴影
 * @create :2021-06-18 15:20:00
 */
@Slf4j
public class Shader {
    private String vertexFile,framentFile;
    private int vertexId,framentId,programId;

    public void init(String vertexFile,String framentFile) {
        vertexFile = FileUtils.loadString(vertexFile);
        framentFile = FileUtils.loadString(framentFile);
    }


    public void create() {
        programId = GL20.glCreateProgram();
        vertexId = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        GL20.glShaderSource(vertexId,vertexFile);
        GL20.glCompileShader(vertexId);

        if (GL20.glGetShaderi(vertexId,GL20.GL_COMPILE_STATUS )== GL11.GL_FALSE) {
            log.error("001渲染失败   " + GL20.glGetShaderInfoLog(vertexId));
        }

        framentId = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(framentId,framentFile);
        GL20.glCompileShader(framentId);

        if (GL20.glGetShaderi(framentId,GL20.GL_COMPILE_STATUS )== GL11.GL_FALSE) {
            log.error("002渲染失败   " + GL20.glGetShaderInfoLog(framentId));
        }


        GL20.glAttachShader(programId,vertexId);
        GL20.glAttachShader(programId,framentId);

        GL20.glLinkProgram(programId);
        if (GL20.glGetProgrami(programId,GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
            log.error("程序失败   [{}]" , GL20.glGetProgramInfoLog(programId));
        }
        GL20.glValidateProgram(programId);
    }
}
