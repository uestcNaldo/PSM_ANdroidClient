package com.uestc.naldo.psm.dao;

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

import com.uestc.naldo.psm.model.Notification;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NOTIFICATION".
*/
public class NotificationDao extends AbstractDao<Notification, Long> {

    public static final String TABLENAME = "NOTIFICATION";

    /**
     * Properties of entity Notification.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property Content = new Property(2, String.class, "content", false, "CONTENT");
        public final static Property Datetime = new Property(3, String.class, "datetime", false, "DATETIME");
        public final static Property AdminId = new Property(4, long.class, "adminId", false, "ADMIN_ID");
    }

    private Query<Notification> admin_NotificationsQuery;

    public NotificationDao(DaoConfig config) {
        super(config);
    }
    
    public NotificationDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NOTIFICATION\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "\"TITLE\" TEXT," + // 1: title
                "\"CONTENT\" TEXT," + // 2: content
                "\"DATETIME\" TEXT," + // 3: datetime
                "\"ADMIN_ID\" INTEGER NOT NULL );"); // 4: adminId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NOTIFICATION\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Notification entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(3, content);
        }
 
        String datetime = entity.getDatetime();
        if (datetime != null) {
            stmt.bindString(4, datetime);
        }
        stmt.bindLong(5, entity.getAdminId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Notification entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(3, content);
        }
 
        String datetime = entity.getDatetime();
        if (datetime != null) {
            stmt.bindString(4, datetime);
        }
        stmt.bindLong(5, entity.getAdminId());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public Notification readEntity(Cursor cursor, int offset) {
        Notification entity = new Notification( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // content
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // datetime
            cursor.getLong(offset + 4) // adminId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Notification entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setContent(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDatetime(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAdminId(cursor.getLong(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Notification entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Notification entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Notification entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "notifications" to-many relationship of Admin. */
    public List<Notification> _queryAdmin_Notifications(long adminId) {
        synchronized (this) {
            if (admin_NotificationsQuery == null) {
                QueryBuilder<Notification> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.AdminId.eq(null));
                admin_NotificationsQuery = queryBuilder.build();
            }
        }
        Query<Notification> query = admin_NotificationsQuery.forCurrentThread();
        query.setParameter(0, adminId);
        return query.list();
    }

}
