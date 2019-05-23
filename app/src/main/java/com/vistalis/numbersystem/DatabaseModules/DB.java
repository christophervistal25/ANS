package com.vistalis.numbersystem.DatabaseModules;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.vistalis.numbersystem.DatabaseModules.Daos.ArticlesDao;
import com.vistalis.numbersystem.DatabaseModules.Models.Articles;

@Database(entities = {Articles.class},version = 1)
public abstract class DB extends RoomDatabase {

    private static DB appDatabase;
    private Context context;
    public abstract ArticlesDao categoryDao();


    public synchronized  static DB getInstance(Context context){
        if(appDatabase == null){
            appDatabase = Room.databaseBuilder(context.getApplicationContext(), DB.class, "number_system")
                    .allowMainThreadQueries()
                    .build();
        }
        return appDatabase;
    }

    public void destroyInstance() {
        appDatabase = null;
    }
}
