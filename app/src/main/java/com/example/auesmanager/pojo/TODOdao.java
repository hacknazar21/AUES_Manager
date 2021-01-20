package com.example.auesmanager.pojo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TODOdao{

    @Insert
    void insertAll(TODO... todo);

    @Delete
    void delete(TODO... todo);

    @Query("SELECT * FROM todos")
    List<TODO> getAllTodos();

}
