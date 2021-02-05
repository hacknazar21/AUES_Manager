package com.example.auesmanager.pojo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.Collection;
import java.util.List;

@Dao
public interface RaspDAO{

    @Insert
    void insertAll(Rasp rasp);

    @Delete
    void delete(Collection<Rasp> rasp);

    @Query("SELECT * FROM rasps")
    List<Rasp> getAllRasps();

}
