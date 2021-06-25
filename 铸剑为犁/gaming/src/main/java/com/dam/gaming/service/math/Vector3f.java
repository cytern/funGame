package com.dam.gaming.service.math;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : dam
 * @description :
 * @create :2021-06-23 13:42:00
 */
@Data
@AllArgsConstructor
public class Vector3f {
    private float x,y,z;


    public void init (float x,float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
