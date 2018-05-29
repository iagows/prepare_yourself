package com.iaspp.prepareyourself.movie;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iaspp.prepareyourself.R;
import com.iaspp.prepareyourself.utils.AbstractController;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UpcomingMovieAdapter extends RecyclerView.Adapter<UpcomingMovieAdapter.UpcomingHolder> {
    private List<MovieDTO> list;
    private MovieController abstractController;

    public UpcomingMovieAdapter(List<MovieDTO> list, MovieController abstractController) {
        this.list = list;
        this.abstractController = abstractController;
    }

    public void addList(List<MovieDTO> list){
        this.list.addAll(list);
        this.notifyItemInserted(list.size()-1);
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
        MovieDTO dto = list.get(position);

        holder.title.setText(dto.getOriginalTitle());
        holder.release.setText(dto.getReleaseDate());
        holder.genre.setText(dto.getGenreList().toString());

        String url = abstractController.getImageUrl(dto);
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
