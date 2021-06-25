package com.dam.gaming.graphics;

import com.dam.gaming.service.math.Vector3f;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : dam
 * @description :
 * @create :2021-06-23 13:51:00
 */
@Getter
@Setter
public class Vertex {
    private Vector3f position;

    public Vertex(Vector3f position) {
        this.position = position;
    }

}
