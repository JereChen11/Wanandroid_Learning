package com.jere.test.article.modle.roomdatabase;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
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
