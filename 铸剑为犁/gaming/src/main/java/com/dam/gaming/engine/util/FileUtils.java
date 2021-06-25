package com.dam.gaming.engine.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author : dam
 * @description :
 * @create :2021-06-18 15:11:00
 */
public class FileUtils {

    public static String loadString(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Class.class.getResourceAsStream(path)));
            String line = "";
             while ((line = reader.readLine()) != null) {
                 stringBuilder.append(line).append("\n");
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  stringBuilder.toString();
    }
}
