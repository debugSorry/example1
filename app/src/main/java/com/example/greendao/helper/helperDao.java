package com.example.greendao.helper;

import java.util.List;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2018/1/17$ 17:08$
 * <p/>
 */
public interface helperDao {
    //增加单条数据
    <T> void add(T bean);
    //增加list数据
    <T> void addAll(List<T> beanList);
    //通过id删除
    void delete(String id);
    //删除所有数据
    void deleteAll();
    //通过id查找
    <T> T  query(String id);
    //通过id修改
    <T> void updata(T bean);
}
