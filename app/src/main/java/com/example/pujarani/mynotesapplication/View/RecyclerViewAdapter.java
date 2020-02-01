package com.example.pujarani.mynotesapplication.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pujarani.mynotesapplication.Model.Notes;
import com.example.pujarani.mynotesapplication.R;

import java.util.List;

/**
 * Created by Puja.Rani on 28-01-2020.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    private final LayoutInflater mInflater;
    private List<Notes> notesList;
    itemClickListener listener;

    RecyclerViewAdapter(Context context, itemClickListener clickListener) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.listener = clickListener;
    }

    void setWords(List<Notes> notes) {
//        if(notesList != null && notesList.size() > 0) {
//            notesList.clear();
//        }
        notesList = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.notes_list_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (notesList != null) {
            Notes notes = notesList.get(i);
            viewHolder.title.setText((i + 1) + ".  " + notes.getTitle());
            viewHolder.bind(notes, listener);
        }
    }

    @Override
    public int getItemCount() {
        if (notesList != null)
            return notesList.size();
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            title = (TextView) itemView.findViewById(R.id.list_title);
        }

        public void bind(Notes note, itemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(note);
                }
            });
        }
    }

    public interface itemClickListener {
        void onItemClick(Notes notes);
    }
}
