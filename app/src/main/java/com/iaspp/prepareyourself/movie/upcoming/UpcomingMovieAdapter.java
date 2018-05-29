package com.iaspp.prepareyourself.movie.upcoming;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iaspp.prepareyourself.R;
import com.iaspp.prepareyourself.movie.MovieController;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UpcomingMovieAdapter extends RecyclerView.Adapter<UpcomingMovieAdapter.UpcomingHolder> {
    private List<ResultMovieUpcomingDTO> list;
    private MovieController controller;

    public UpcomingMovieAdapter(List<ResultMovieUpcomingDTO> list, MovieController controller) {
        this.list = list;
        this.controller = controller;
    }

    @NonNull
    @Override
    public UpcomingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_upcoming, parent, false);
        return new UpcomingHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingHolder holder, int position) {
        ResultMovieUpcomingDTO dto = list.get(position);

        holder.title.setText(dto.getOriginalTitle());
        holder.release.setText(dto.getReleaseDate());
        holder.release.setText(dto.getGenreList().toString());

        String url = controller.getImageUrl(dto);
        Picasso.get().load(url).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UpcomingHolder extends RecyclerView.ViewHolder {
        final ImageView image;
        final TextView title;
        final TextView genre;
        final TextView release;

        public UpcomingHolder(View view) {
            super(view);
            image = view.findViewById(R.id.image_stub);
            title = view.findViewById(R.id.title_upcoming);
            genre = view.findViewById(R.id.genres_upcoming);
            release = view.findViewById(R.id.release_upcoming);
        }
    }
}
