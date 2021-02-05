package com.example.auesmanager.DB;

import android.content.Context;

import androidx.room.Room;

import com.example.auesmanager.AppDatabase;
import com.example.auesmanager.R;
import com.example.auesmanager.pojo.TODO;

import java.util.List;
import java.util.concurrent.Callable;

public class TodoDB {
    private static AppDatabase db;

    public static void TodoDBInit(Context context){
        db = Room.databaseBuilder(context, AppDatabase.class, "popus-database").build();
    }
    public static class GetDataFromDB implements Callable<List> {
        @Override
        public List<TODO> call() throws Exception {
            return db.getTODODao().getAllTodos();
        }
    }
    public static class DelDataFromDB implements Callable<Void> {
        private final TODO todo;

        public DelDataFromDB(TODO todo) {
            this.todo = todo;
        }
        @Override
        public Void call() throws Exception {
            DelData(todo);
            return null;
        }
    }
    public static class SetDataToDB implements Callable<Void> {
        private final String textDO;
        private final String textTime;

        public SetDataToDB(String textDO, String textTime) {
            this.textDO = textDO;
            this.textTime = textTime;
        }
        @Override
        public Void call() throws Exception {
            NewData(textDO, textTime);
            return null;
        }
    }
    private static void NewData(String textDO, String textTime) {
        db.getTODODao().insertAll(new TODO(textDO, textTime, R.drawable.ic_baseline_assignment, "None"));

    }
    private static void DelData(TODO todo) {
        db.getTODODao().delete(todo);

    }
}


