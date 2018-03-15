package com.example.greendao.entity;

import com.example.greendao.dao.AnimalDao;
import com.example.greendao.dao.BodyDao;
import com.example.greendao.dao.DaoSession;
import com.example.greendao.dao.HouseDao;
import com.example.greendao.dao.PersonDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2018/1/16$ 16:38$
 * <p/>
 */
@Entity
public class Person {
    //自增id类型只能是Long
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "user")
    private String username;
    private int age;
    private String addParam;
    private Long bodyId;
    //一对一
    @ToOne(joinProperty = "bodyId")
    private Body body;
    //一对多
    @ToMany(referencedJoinProperty = "ownerId")
    private List<Animal> animals;
    //多对多
    @ToMany
    @JoinEntity(
            entity = PersonWithHouse.class,
            sourceProperty = "personId",
            targetProperty = "houseId"

    )
    private List<House> personWithHouse;

    /** Used for active entity operations. */
    @Generated(hash = 778611619)
    private transient PersonDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    @Generated(hash = 1363066672)
    private transient Long body__resolvedKey;

    @Generated(hash = 563997172)
    public Person(Long id, String username, int age, String addParam, Long bodyId) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.addParam = addParam;
        this.bodyId = bodyId;
    }

    @Generated(hash = 1024547259)
    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 404113861)
    public synchronized void resetAnimals() {
        animals = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 491040605)
    public List<Animal> getAnimals() {
        if (animals == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AnimalDao targetDao = daoSession.getAnimalDao();
            List<Animal> animalsNew = targetDao._queryPerson_Animals(id);
            synchronized (this) {
                if(animals == null) {
                    animals = animalsNew;
                }
            }
        }
        return animals;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2056799268)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPersonDao() : null;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 394950633)
    public synchronized void resetPersonWithHouse() {
        personWithHouse = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1062711132)
    public List<House> getPersonWithHouse() {
        if (personWithHouse == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HouseDao targetDao = daoSession.getHouseDao();
            List<House> personWithHouseNew = targetDao._queryPerson_PersonWithHouse(id);
            synchronized (this) {
                if(personWithHouse == null) {
                    personWithHouse = personWithHouseNew;
                }
            }
        }
        return personWithHouse;
    }



    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 769936085)
    public void setBody(Body body) {
        synchronized (this) {
            this.body = body;
            bodyId = body == null ? null : body.getId();
            body__resolvedKey = bodyId;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 805985644)
    public Body getBody() {
        Long __key = this.bodyId;
        if (body__resolvedKey == null || !body__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BodyDao targetDao = daoSession.getBodyDao();
            Body bodyNew = targetDao.load(__key);
            synchronized (this) {
                body = bodyNew;
                body__resolvedKey = __key;
            }
        }
        return body;
    }

    public Long getBodyId() {
        return this.bodyId;
    }

    public void setBodyId(Long bodyId) {
        this.bodyId = bodyId;
    }

    public String getAddParam() {
        return this.addParam;
    }

    public void setAddParam(String addParam) {
        this.addParam = addParam;
    }

   



}
