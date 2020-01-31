package com.example.pujarani.mynotesapplication.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.pujarani.mynotesapplication.Model.Notes;
import com.example.pujarani.mynotesapplication.Model.NotesRepo;

import java.util.List;


/**
 * Created by Puja.Rani on 29-01-2020.
 */

public class NotesViewModelClass extends AndroidViewModel {

    private NotesRepo noterepository;
    private LiveData<List<Notes>> allNotes;

    public NotesViewModelClass(@NonNull Application application) {
        super(application);
        noterepository = new NotesRepo(application);
        allNotes = noterepository.getAllNotes();
    }

    public LiveData<List<Notes>> getAllNotes() {
        return allNotes;
    }

    public void insert(Notes notes) {
        noterepository.insertNote(notes);
    }

    public void deleteAllNotes() {
        noterepository.delete();
    }
}
