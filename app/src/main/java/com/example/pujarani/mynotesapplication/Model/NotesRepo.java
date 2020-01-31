package com.example.pujarani.mynotesapplication.Model;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Puja.Rani on 29-01-2020.
 */

public class NotesRepo {

    private NotesDAO notesDAO;
    private LiveData<List<Notes>> allNotes;
    private NotesDatabase database;


    public NotesRepo(Application application) {
        database = NotesDatabase.getNotesDatabase_instance(application);
        notesDAO = database.notesDAO();
        allNotes = notesDAO.getAllNotesSaved();
    }

    public LiveData<List<Notes>> getAllNotes() {
        return allNotes;
    }

    public void insertNote(Notes notes) {
        NotesDatabase.databasewriteexecutor.execute(new Runnable() {
            @Override
            public void run() {
                notesDAO.insert(notes);
            }
        });
        NotesDatabase.deleteInstance();
    }

    public void delete() {
        notesDAO.deleteAll();
        NotesDatabase.deleteInstance();
    }

}
