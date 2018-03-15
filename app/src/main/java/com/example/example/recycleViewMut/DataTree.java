package com.example.example.recycleViewMut;

import java.util.List;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/28$ 10:02$
 * <p/>
 */
public class DataTree<K,V> {
    private K groupItem;
    private List<V> subItems;
    public DataTree(K groupItem,List<V> subItems){
        this.groupItem = groupItem;
        this.subItems = subItems;
    }

    public K getGroupItem() {
        return groupItem;
    }

    public java.util.List<V> getSubItems() {
        return subItems;
    }
}
