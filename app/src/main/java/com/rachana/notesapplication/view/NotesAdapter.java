package com.rachana.notesapplication.view;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.rachana.notesapplication.R;
import com.rachana.notesapplication.database.model.Note;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private final List<Note> notesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        final TextView note;
        final TextView dot;
        final TextView timestamp;

        MyViewHolder(View view) {
            super(view);
            note = view.findViewById(R.id.note);
            dot = view.findViewById(R.id.dot);
            timestamp = view.findViewById(R.id.timestamp);
        }
    }


    public NotesAdapter(Context context, List<Note> notesList) {
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note note = notesList.get(position);

        holder.note.setText(note.getNote());

        // Displaying dot from HTML character code
        holder.dot.setText(Html.fromHtml("&#8226;"));

        // Formatting and displaying timestamp
        holder.timestamp.setText(formatDate(note.getTimestamp()));
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    /**
     * Formatting timestamp to `MMM d` format
     * Input: 2018-02-21 00:15:42
     * Output: Feb 21
     */
    private String formatDate(String dateStr) {
        try {

            Date mParsedDate;
            String mOutputDateString;
            SimpleDateFormat mInputDateFormat =
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault());
            SimpleDateFormat mOutputDateFormat =
                    new SimpleDateFormat("MMM d", java.util.Locale.getDefault());
            mParsedDate = mInputDateFormat.parse(dateStr);
            mOutputDateString = mOutputDateFormat.format(mParsedDate);
            return mOutputDateString;
        } catch (ParseException ignored) {

        }

        return "";
    }
}
