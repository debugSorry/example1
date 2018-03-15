package com.example.greendao.helper;

import com.example.greendao.MyDatabaseLoader;
import com.example.greendao.dao.BodyDao;
import com.example.greendao.entity.Body;

import java.util.List;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2018/1/18$ 10:41$
 * <p/>
 */
public class BodyDaoHelper implements helperDao{
    private  BodyDao       bodyDao;
    private static BodyDaoHelper bodyDaoHelper;
    public BodyDaoHelper(){
        bodyDao = MyDatabaseLoader.getDaoSession().getBodyDao();
    }
    public static BodyDaoHelper getinstance(){
        if(bodyDaoHelper == null){
            bodyDaoHelper = new BodyDaoHelper();
        }
        return bodyDaoHelper;
    }
    @Override
    public <T> void add(T bean) {
        if(bodyDao != null && bean != null){
            bodyDao.insert((Body)bean);
        }
    }

    @Override
    public <T> void addAll(List<T> beanList) {
        if(bodyDao != null && beanList != null){
            bodyDao.insertInTx((List<Body>)beanList);
        }
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <T> T query(String id) {
        return null;
    }

    @Override
    public <T> void updata(T bean) {

    }
}
