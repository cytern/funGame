package com.dam.gaming.pojo;

import lombok.Data;

/**
 * @author : dam
 * @description :窗口
 * @create :2021-06-16 14:58:00
 */
@Data
public class WindowPojo {
    private Integer height;
    private Integer width;
    private String name;

    public static WindowPojo getDemoPojo() {
        WindowPojo windowPojo = new WindowPojo();
        windowPojo.setHeight(720);
        windowPojo.setWidth(1280);
        windowPojo.setName("铸剑为犁");
        return  windowPojo;
    }
}
