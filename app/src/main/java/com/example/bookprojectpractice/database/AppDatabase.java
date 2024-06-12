package com.example.bookprojectpractice.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.bookprojectpractice.model.Book;

@Database(entities = {Book.class}, version = 4)
@TypeConverters(ClassConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase instance;
    public abstract BookDao bookDao();

    //khởi tạo một database với đơn luồng
    //synchronized : chỉ một luồng được chạy AppDatabase trong 1 thời điểm
    public static synchronized AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "favoriteDB")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
