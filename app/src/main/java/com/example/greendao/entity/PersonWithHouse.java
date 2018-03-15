package com.example.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2018/1/18$ 10:20$
 * <p/>
 */
@Entity
public class PersonWithHouse {
    @Id(autoincrement = true)
    private Long id;
    private Long personId;
    private Long houseId;
    public Long getHouseId() {
        return this.houseId;
    }
    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }
    public Long getPersonId() {
        return this.personId;
    }
    public void setPersonId(Long personId) {
        this.personId = personId;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1924045446)
    public PersonWithHouse(Long id, Long personId, Long houseId) {
        this.id = id;
        this.personId = personId;
        this.houseId = houseId;
    }
    @Generated(hash = 1242205380)
    public PersonWithHouse() {
    }

}
