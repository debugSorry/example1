package com.example.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2018/1/18$ 10:29$
 * <p/>
 */
@Entity
public class Body {
    @Id(autoincrement = true)
    private Long id;
    private int height;
    private int weight;
    public int getWeight() {
        return this.weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getHeight() {
        return this.height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }



    @Generated(hash = 139974340)
    public Body() {
    }
    @Generated(hash = 1826341444)
    public Body(Long id, int height, int weight) {
        this.id = id;
        this.height = height;
        this.weight = weight;
    }

}
