package com.example.greendao.helper;

import com.example.greendao.MyDatabaseLoader;
import com.example.greendao.dao.PersonDao;
import com.example.greendao.entity.Person;

import java.util.List;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2018/1/17$ 17:43$
 * <p/>
 */
public class PersonDaoHelper implements helperDao{
    private PersonDao personDao;
    private static PersonDaoHelper personDaoHelper;
    public PersonDaoHelper(){
        personDao = MyDatabaseLoader.getDaoSession().getPersonDao();
    }
    public static PersonDaoHelper getInstance(){
        if(personDaoHelper == null ){
            personDaoHelper = new PersonDaoHelper();
        }
        return personDaoHelper;
    }

    @Override
    public <T> void add(T bean) {
        if(personDao != null  && bean != null){
            personDao.insert((Person) bean);
        }
    }

    @Override
    public <T> void addAll(List<T> beanList) {
        if(personDao !=null &&beanList != null){
            personDao.insertInTx((List<Person>) beanList);
        }
    }

    @Override
    public void delete(String id) {
        if(personDao !=null && id  != null && !"".equals(id)){
            personDao.deleteByKey(Long.valueOf(id));
        }

    }

    @Override
    public void deleteAll() {
        if(personDao !=null){
            personDao.deleteAll();
        }
    }

    @Override
    public <T> void updata(T bean) {
        if(personDao !=null && bean != null){
            personDao.update((Person) bean);
        }
    }

    @Override
    public List<Person> query(String id) {
        if(personDao !=null && id  != null && !"".equals(id)){
          List<Person> list=personDao.queryBuilder().where(PersonDao.Properties.Id.eq(id)).list();
          return list;
        }
        return null;
    }

}
