package com.example.pujarani.mynotesapplication.View;


import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.PathInterpolator;
import android.widget.TextView;

import com.example.pujarani.mynotesapplication.Model.Notes;
import com.example.pujarani.mynotesapplication.R;
import com.example.pujarani.mynotesapplication.ViewModel.NotesViewModelClass;

import java.io.File;
import java.util.Date;
import java.util.List;


public class NotesListActivity extends AppCompatActivity implements LifecycleOwner {

    RecyclerView list;
    TextView no_notes;
    NotesViewModelClass viewModelClass;
    ProgressDialog dialog;
    Context context;
    RecyclerViewAdapter adapter;
    Bundle bundle;
    boolean datafetched = false;
    private LifecycleRegistry lifecycleRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_list);

        initUI();
        loadData();
    }

    private void initUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        no_notes = (TextView) findViewById(R.id.no_notes);
        lifecycleRegistry = new LifecycleRegistry(this);
        lifecycleRegistry.markState(Lifecycle.State.CREATED);
        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading....");
        adapter = new RecyclerViewAdapter(this, new RecyclerViewAdapter.itemClickListener() {
            @Override
            public void onItemClick(Notes notes) {
                Intent noteDetails = new Intent(NotesListActivity.this, ShowNoteDetailsActivity.class);
                noteDetails.putExtra("Note", (Parcelable) notes);
                startActivity(noteDetails);
            }
        });
        viewModelClass = ViewModelProviders.of(this).get(NotesViewModelClass.class);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotesListActivity.this, CreateNoteActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        showDialog();
        viewModelClass.getAllNotes().observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(@Nullable List<Notes> notes) {
                if (notes.size() == 0) {
                    no_notes.setVisibility(View.VISIBLE);
                } else {
                    no_notes.setVisibility(View.GONE);
                }
                adapter.setWords(notes);
            }
        });
        hideDialog();

        list = (RecyclerView) findViewById(R.id.list_notes);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    }


    public void showDialog() {
        if (dialog != null) dialog.show();
    }

    public void hideDialog() {
        if (dialog.isShowing()) dialog.dismiss();
    }

  /*  public static void deleteDatabaseFile(Context context, String databaseName) {
        File databases = new File(context.getApplicationInfo().dataDir + "/databases");
        File db = new File(databases, databaseName);
        if (db.delete())
            System.out.println("Database deleted");
        else
            System.out.println("Failed to delete database");

        File journal = new File(databases, databaseName + "-journal");
        if (journal.exists()) {
            if (journal.delete())
                System.out.println("Database journal deleted");
            else
                System.out.println("Failed to delete database journal");
        }
    }*/

    @Override
    protected void onStart() {
        super.onStart();
        lifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }
}
