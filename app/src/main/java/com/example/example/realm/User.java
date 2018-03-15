package com.example.example.realm;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2018/1/15$ 9:37$
 * <p/>
 */
public class User extends RealmObject{
    @PrimaryKey
    private String id;
//    private int age;
    @Required
    private String name;
    @Required
    private String money;
    private RealmList<Dog> dogRealmList;

    public RealmList<Dog> getDogRealmList() {
        return dogRealmList;
    }

    public void setDogRealmList(RealmList<Dog> dogRealmList) {
        this.dogRealmList = dogRealmList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
