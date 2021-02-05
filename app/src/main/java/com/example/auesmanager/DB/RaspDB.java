package com.example.auesmanager.DB;

import android.content.Context;

import androidx.room.Room;

import com.example.auesmanager.AppDatabase;
import com.example.auesmanager.pojo.Rasp;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

public class RaspDB {
    private static AppDatabase Rdb;

    public static void RaspDBInit(Context context){
        Rdb = Room.databaseBuilder(context, AppDatabase.class, "popus-database").build();
    }
    public static class GetDataRaspFromDB implements Callable<List> {
        @Override
        public List<Rasp> call() throws Exception {
            return Rdb.getRaspDao().getAllRasps();
        }
    }

    public static class DelDataRaspFromDB implements Callable<Void> {
        private final Collection<Rasp> rasp;

        public DelDataRaspFromDB(Collection<Rasp> rasp) {
            this.rasp = rasp;
        }
        @Override
        public Void call() throws Exception {
            DelDataRasp(rasp);
            return null;
        }
    }

    public static class SetDataRaspToDB implements Callable<Void> {
        private final String Dayl;
        private final String Timel;
        private final String SubGroup;
        private final  String Namel;
        private final  String Sensei;
        private final  String Rooml;

        public SetDataRaspToDB(String Dayl, String Timel, String SubGroup, String Namel, String Sensei, String Rooml) {
            this.Dayl = Dayl;
            this.Timel = Timel;
            this.SubGroup = SubGroup;
            this.Namel = Namel;
            this.Sensei = Sensei;
            this.Rooml = Rooml;
        }
        @Override
        public Void call() throws Exception {
            NewDataRasp(Dayl, Timel, SubGroup, Namel, Sensei, Rooml);
            return null;
        }
    }
    private static void NewDataRasp(String Dayl, String Timel, String SubGroup, String Namel, String Sensei, String Rooml) {
        Rdb.getRaspDao().insertAll(new Rasp(Dayl, Timel, SubGroup, Namel, Sensei, Rooml));

    }
    private static void DelDataRasp(Collection<Rasp> rasp){
        Rdb.getRaspDao().delete(rasp);
    }

}
