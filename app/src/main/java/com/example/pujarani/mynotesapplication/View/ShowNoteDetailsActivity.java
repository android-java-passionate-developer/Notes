package com.example.pujarani.mynotesapplication.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pujarani.mynotesapplication.Model.Notes;
import com.example.pujarani.mynotesapplication.R;

/**
 * Created by Puja.Rani on 28-01-2020.
 */

public class ShowNoteDetailsActivity extends AppCompatActivity implements View.OnClickListener {

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
        timestamp.setText("Created on: " + note.getTimestamp());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.detail_to_list:
                onBackPressed();
                break;
        }
    }
}
