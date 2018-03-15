package com.example.greendao.helper;

import com.example.greendao.MyDatabaseLoader;
import com.example.greendao.dao.HouseDao;
import com.example.greendao.entity.House;

import java.util.List;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2018/1/17$ 17:43$
 * <p/>
 */
public class HouseDaoHelper implements helperDao{
    private        HouseDao       houseDao;
    private static HouseDaoHelper houseDaoHelper;
    public HouseDaoHelper(){
        houseDao = MyDatabaseLoader.getDaoSession().getHouseDao();
    }
    public static HouseDaoHelper getInstance(){
        if(houseDaoHelper == null ){
            houseDaoHelper = new HouseDaoHelper();
        }
        return houseDaoHelper;
    }

    @Override
    public <T> void add(T bean) {
        if(houseDao != null  && bean != null){
            houseDao.insert((House) bean);
        }
    }

    @Override
    public <T> void addAll(List<T> beanList) {
        if(houseDao != null  && beanList != null){
            houseDao.insertInTx((List<House>) beanList);
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
