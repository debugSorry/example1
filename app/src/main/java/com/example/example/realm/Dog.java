package com.example.example.realm;

import io.realm.RealmObject;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2018/1/15$ 13:45$
 * <p/>
 */
public class Dog extends RealmObject{
    private String name;
    private int age;
    private boolean isFale;

    public boolean isFale() {
        return isFale;
    }

    public void setFale(boolean fale) {
        isFale = fale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
