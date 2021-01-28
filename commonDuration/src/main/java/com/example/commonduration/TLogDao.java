package com.example.commonduration;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TLogDao {

    @Insert
    void insertAll(com.example.commonduration.TLog... tLogs);

    @Delete
    void delete(com.example.commonduration.TLog tLog);

    @Update
    void updateUsers(com.example.commonduration.TLog... tLogs);

    @Query("SELECT * FROM time_logs")
    List<com.example.commonduration.TLog> getAll();

    @Query("SELECT * FROM time_logs where tag LIKE :tag")
    List<com.example.commonduration.TLog> getAllByTag(String tag);

}