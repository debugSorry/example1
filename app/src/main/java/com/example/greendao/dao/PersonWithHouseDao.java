package com.example.greendao.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.greendao.entity.PersonWithHouse;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PERSON_WITH_HOUSE".
*/
public class PersonWithHouseDao extends AbstractDao<PersonWithHouse, Long> {

    public static final String TABLENAME = "PERSON_WITH_HOUSE";

    /**
     * Properties of entity PersonWithHouse.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PersonId = new Property(1, Long.class, "personId", false, "PERSON_ID");
        public final static Property HouseId = new Property(2, Long.class, "houseId", false, "HOUSE_ID");
    }


    public PersonWithHouseDao(DaoConfig config) {
        super(config);
    }
    
    public PersonWithHouseDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PERSON_WITH_HOUSE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"PERSON_ID\" INTEGER," + // 1: personId
                "\"HOUSE_ID\" INTEGER);"); // 2: houseId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PERSON_WITH_HOUSE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PersonWithHouse entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long personId = entity.getPersonId();
        if (personId != null) {
            stmt.bindLong(2, personId);
        }
 
        Long houseId = entity.getHouseId();
        if (houseId != null) {
            stmt.bindLong(3, houseId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PersonWithHouse entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long personId = entity.getPersonId();
        if (personId != null) {
            stmt.bindLong(2, personId);
        }
 
        Long houseId = entity.getHouseId();
        if (houseId != null) {
            stmt.bindLong(3, houseId);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PersonWithHouse readEntity(Cursor cursor, int offset) {
        PersonWithHouse entity = new PersonWithHouse( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // personId
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2) // houseId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PersonWithHouse entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPersonId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setHouseId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PersonWithHouse entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PersonWithHouse entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PersonWithHouse entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
