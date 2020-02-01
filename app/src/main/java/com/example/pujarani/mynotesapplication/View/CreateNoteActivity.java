package com.example.pujarani.mynotesapplication.View;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pujarani.mynotesapplication.Model.Notes;
import com.example.pujarani.mynotesapplication.R;
import com.example.pujarani.mynotesapplication.ViewModel.NotesViewModelClass;

import java.util.Date;


/**
 * Created by Puja.Rani on 28-01-2020.
 */

public class CreateNoteActivity extends AppCompatActivity{

    EditText title, content;
    Button save, back;
    private NotesViewModelClass viewModelClass;
    ProgressDialog dialog;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note);

        initUI();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    saveNotes();
                } else {
                    Toast.makeText(context, "You forgot to add title or contents for your notes.", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    private void initUI() {
        context = this;
        title = (EditText) findViewById(R.id.add_title);
        content = (EditText) findViewById(R.id.add_content);
        content.setVerticalScrollBarEnabled(true);
        save = (Button) findViewById(R.id.save);
        back = (Button) findViewById(R.id.create_to_list);
        dialog = new ProgressDialog(context);
        dialog.setMessage("Saving...");
        viewModelClass = ViewModelProviders.of(this).get(NotesViewModelClass.class);
    }

    private boolean validate() {
        if (title.getText() != null && title.getText().toString().length() > 0
                && content.getText() != null && content.getText().toString().length() > 0) {
            return true;
        }
        return false;
    }

    private void saveNotes() {
        showDialog();
        Date d = new Date();
        Notes notes = new Notes(title.getText().toString(), content.getText().toString(), d.getTime());
        viewModelClass.insert(notes);
        hideDialog();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void showDialog() {
        if (dialog != null) dialog.show();
    }

    public void hideDialog() {
        if (dialog.isShowing()) dialog.dismiss();
    }

}
