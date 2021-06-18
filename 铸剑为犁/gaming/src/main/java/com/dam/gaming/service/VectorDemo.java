package com.dam.gaming.service;

import org.springframework.stereotype.Service;

/**
 * @author : dam
 * @description :
 * @create :2021-06-17 17:40:00
 */
@Service
public class VectorDemo {
    private float x,y,z;
    public void init(float x,float y,float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
