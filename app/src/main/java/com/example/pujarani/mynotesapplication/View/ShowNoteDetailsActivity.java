package com.example.pujarani.mynotesapplication.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pujarani.mynotesapplication.Model.Notes;
import com.example.pujarani.mynotesapplication.R;

/**
 * Created by Puja.Rani on 28-01-2020.
 */

public class ShowNoteDetailsActivity extends AppCompatActivity{

    Context context;
    Notes note;
    Button back;
    TextView title, content, timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_details);
        initUI();
        loadData();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initUI() {
        context = this;
        note = getIntent().getParcelableExtra("Note");
        title = (TextView) findViewById(R.id.showtitle);
        content = (TextView) findViewById(R.id.showcontent);
        timestamp = (TextView) findViewById(R.id.showtimestamp);
        back = (Button) findViewById(R.id.detail_to_list);
    }

    private void loadData() {
        title.setText(note.getTitle());
        content.setText(note.getContent());
        CharSequence s = DateFormat.format("d MMMM yyyy, hh:mm a", note.getTimestamp());
        timestamp.setText("Created on: " + s.toString());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
