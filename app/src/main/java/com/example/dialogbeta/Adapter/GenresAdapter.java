package com.example.dialogbeta.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dialogbeta.R;
import com.example.dialogbeta.model.Genres;

import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.GenresViewHolder> {

    Context context;
    List<Genres> genres;

    public GenresAdapter(Context context, List<Genres> genres) {
        this.context = context;
        this.genres = genres;
    }

    @NonNull
    @Override
    public GenresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View genreItems = LayoutInflater.from(context).inflate(R.layout.genres_item, parent,false);
        return new GenresViewHolder(genreItems);
    }

    @Override
    public void onBindViewHolder(@NonNull GenresViewHolder holder, int position) {
        holder.genreTitle.setText(genres.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public static final class GenresViewHolder extends RecyclerView.ViewHolder {

        TextView genreTitle;
        public GenresViewHolder(@NonNull View itemView) {
            super(itemView);

            genreTitle = itemView.findViewById(R.id.genreTitle);
        }
    }
}
