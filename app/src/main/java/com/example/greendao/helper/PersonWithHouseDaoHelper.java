package com.example.greendao.helper;

import com.example.greendao.MyDatabaseLoader;
import com.example.greendao.dao.PersonWithHouseDao;
import com.example.greendao.entity.PersonWithHouse;

import java.util.List;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2018/1/17$ 17:43$
 * <p/>
 */
public class PersonWithHouseDaoHelper implements helperDao{
    private        PersonWithHouseDao       personWithHouseDao;
    private static PersonWithHouseDaoHelper personWithHouseDaoHelper;
    public PersonWithHouseDaoHelper(){
        personWithHouseDao = MyDatabaseLoader.getDaoSession().getPersonWithHouseDao();
    }
    public static PersonWithHouseDaoHelper getInstance(){
        if(personWithHouseDaoHelper == null ){
            personWithHouseDaoHelper = new PersonWithHouseDaoHelper();
        }
        return personWithHouseDaoHelper;
    }

    @Override
    public <T> void add(T bean) {
        if(personWithHouseDao != null  && bean != null){
            personWithHouseDao.insert((PersonWithHouse) bean);
        }
    }

    @Override
    public <T> void addAll(List<T> beanList) {
        if(personWithHouseDao != null  && beanList != null){
            personWithHouseDao.insertInTx((List<PersonWithHouse>) beanList);
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
