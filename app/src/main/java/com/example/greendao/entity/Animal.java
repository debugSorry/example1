package com.example.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2018/1/16$ 16:45$
 * <p/>
 */
@Entity
public class Animal {
    @Id(autoincrement = true)
    private Long id;
    private Long ownerId;
    private String name;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getOwnerId() {
        return this.ownerId;
    }
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
    @Generated(hash = 1577152568)
    public Animal(Long id, Long ownerId, String name) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
    }
    @Generated(hash = 308569294)
    public Animal() {
    }

}
