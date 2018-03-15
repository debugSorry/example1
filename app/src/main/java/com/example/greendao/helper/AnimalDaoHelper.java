package com.example.greendao.helper;

import com.example.greendao.MyDatabaseLoader;
import com.example.greendao.dao.AnimalDao;
import com.example.greendao.entity.Animal;

import java.util.List;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2018/1/17$ 17:09$
 * <p/>
 */
public class AnimalDaoHelper implements helperDao{
    private AnimalDao animalDao;
    private static  AnimalDaoHelper animalDaoHelper ;
    public AnimalDaoHelper(){
        animalDao = MyDatabaseLoader.getDaoSession().getAnimalDao();
    }
    public static AnimalDaoHelper getInsteance(){
        if(animalDaoHelper == null){
            animalDaoHelper = new AnimalDaoHelper();
        }
        return animalDaoHelper;
    }

    @Override
    public <T> void add(T bean) {
        if(animalDao != null && bean != null){
            animalDao.insert((Animal) bean);
        }
    }

    @Override
    public <T> void addAll(List<T> beanList) {
        if(animalDao != null && beanList !=null){
            animalDao.insertInTx((List<Animal>)beanList);
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
