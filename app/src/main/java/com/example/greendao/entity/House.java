package com.example.greendao.entity;

import com.example.greendao.dao.DaoSession;
import com.example.greendao.dao.HouseDao;
import com.example.greendao.dao.PersonDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2018/1/18$ 10:18$
 * <p/>
 */
@Entity
public class House {
    @Id(autoincrement = true)
    private Long id;
    private String location;
    private String price;
    @ToMany
    @JoinEntity(
            entity = PersonWithHouse.class,
            sourceProperty = "houseId",
            targetProperty = "personId"
    )
    private List<Person> personList;
    /** Used for active entity operations. */
    @Generated(hash = 1167916919)
    private transient HouseDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
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
    @Generated(hash = 245188521)
    public synchronized void resetPersonList() {
        personList = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1924813050)
    public List<Person> getPersonList() {
        if (personList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PersonDao targetDao = daoSession.getPersonDao();
            List<Person> personListNew = targetDao._queryHouse_PersonList(id);
            synchronized (this) {
                if(personList == null) {
                    personList = personListNew;
                }
            }
        }
        return personList;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 451323429)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHouseDao() : null;
    }
    @Generated(hash = 1015322267)
    public House(Long id, String location, String price) {
        this.id = id;
        this.location = location;
        this.price = price;
    }
    @Generated(hash = 389023854)
    public House() {
    }
}
