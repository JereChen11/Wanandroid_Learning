package com.jere.test.article.modle.roomdatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * @author jere
 */
@Database(entities = {Article.class}, version = 1)
public abstract class AppRoomDatabase extends RoomDatabase {
    public IArticleDao articleDao;
    public static AppRoomDatabase instance;


    public static AppRoomDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, AppRoomDatabase.class, "article.db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return instance;
    }
}
