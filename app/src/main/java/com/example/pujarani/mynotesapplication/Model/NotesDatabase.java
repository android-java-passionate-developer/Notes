package com.example.pujarani.mynotesapplication.Model;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Puja.Rani on 29-01-2020.
 */

@Database(entities = {Notes.class}, version = 1,exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {

    private static volatile NotesDatabase notesDatabase_instance;

    static final ExecutorService databasewriteexecutor = Executors.newFixedThreadPool(4);

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Notes_Table "
                    +"ADD COLUMN timestamp TEXT");

        }
    };

    static NotesDatabase getNotesDatabase_instance(final Context context){
        if(notesDatabase_instance == null){
            synchronized (NotesDatabase.class){
                if(notesDatabase_instance == null){
                    notesDatabase_instance = Room.databaseBuilder(context.getApplicationContext(),
                            NotesDatabase.class,"Notes_Database" )
//                            .addMigrations(MIGRATION_1_2)
                            .build();
                }
            }
        }
        return notesDatabase_instance;
    }

    public abstract NotesDAO notesDAO();

    static void deleteInstance(){
        notesDatabase_instance = null;
    }


    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
