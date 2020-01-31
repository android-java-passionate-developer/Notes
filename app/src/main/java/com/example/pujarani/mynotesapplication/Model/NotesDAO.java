package com.example.pujarani.mynotesapplication.Model;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Puja.Rani on 29-01-2020.
 */

@Dao
public interface NotesDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Notes notes);

    @Query("SELECT * FROM Notes_Table")
    LiveData<List<Notes>> getAllNotesSaved();

    @Query("Delete from Notes_Table")
    void deleteAll();
}
