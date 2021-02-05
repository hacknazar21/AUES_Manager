package com.example.auesmanager;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.auesmanager.pojo.Rasp;
import com.example.auesmanager.pojo.RaspDAO;
import com.example.auesmanager.pojo.TODO;
import com.example.auesmanager.pojo.TODOdao;

@Database(entities = {TODO.class, Rasp.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TODOdao getTODODao();
    public abstract RaspDAO getRaspDao();
}
