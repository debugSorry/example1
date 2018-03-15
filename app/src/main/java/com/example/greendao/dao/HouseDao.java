package com.example.greendao.dao;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.example.greendao.entity.PersonWithHouse;

import com.example.greendao.entity.House;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "HOUSE".
*/
public class HouseDao extends AbstractDao<House, Long> {

    public static final String TABLENAME = "HOUSE";

    /**
     * Properties of entity House.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Location = new Property(1, String.class, "location", false, "LOCATION");
        public final static Property Price = new Property(2, String.class, "price", false, "PRICE");
    }

    private DaoSession daoSession;

    private Query<House> person_PersonWithHouseQuery;

    public HouseDao(DaoConfig config) {
        super(config);
    }
    
    public HouseDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HOUSE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"LOCATION\" TEXT," + // 1: location
                "\"PRICE\" TEXT);"); // 2: price
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HOUSE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, House entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String location = entity.getLocation();
        if (location != null) {
            stmt.bindString(2, location);
        }
 
        String price = entity.getPrice();
        if (price != null) {
            stmt.bindString(3, price);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, House entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String location = entity.getLocation();
        if (location != null) {
            stmt.bindString(2, location);
        }
 
        String price = entity.getPrice();
        if (price != null) {
            stmt.bindString(3, price);
        }
    }

    @Override
    protected final void attachEntity(House entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public House readEntity(Cursor cursor, int offset) {
        House entity = new House( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // location
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // price
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, House entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setLocation(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPrice(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(House entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(House entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(House entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "personWithHouse" to-many relationship of Person. */
    public List<House> _queryPerson_PersonWithHouse(Long personId) {
        synchronized (this) {
            if (person_PersonWithHouseQuery == null) {
                QueryBuilder<House> queryBuilder = queryBuilder();
                queryBuilder.join(PersonWithHouse.class, PersonWithHouseDao.Properties.HouseId)
                    .where(PersonWithHouseDao.Properties.PersonId.eq(personId));
                person_PersonWithHouseQuery = queryBuilder.build();
            }
        }
        Query<House> query = person_PersonWithHouseQuery.forCurrentThread();
        query.setParameter(0, personId);
        return query.list();
    }

}
