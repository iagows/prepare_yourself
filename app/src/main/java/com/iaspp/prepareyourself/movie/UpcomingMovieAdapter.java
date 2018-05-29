package com.iaspp.prepareyourself.movie;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iaspp.prepareyourself.R;
import com.iaspp.prepareyourself.genre.GenreController;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UpcomingMovieAdapter extends RecyclerView.Adapter<UpcomingMovieAdapter.UpcomingHolder> {
    private List<MovieDTO> list;
    private MovieController movieController;
    private GenreController genreController;

    public UpcomingMovieAdapter(List<MovieDTO> list, MovieController movieController, GenreController genreController) {
        this.list = list;
        this.movieController = movieController;
        this.genreController = genreController;
    }

    public void addList(List<MovieDTO> list) {
        this.list.addAll(list);
        this.notifyItemInserted(list.size() - 1);
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
        StringBuilder genres = new StringBuilder();
        for (Integer id : dto.getGenreList()) {
            String name = genreController.getGenre(id);
            if (!name.equals("")) {
                genres.append(genreController.getGenre(id) + ", ");
            }
        }
        genres.setLength(genres.length() - 2);

        holder.genre.setText(genres.toString());

        String url = movieController.getImageUrl(dto);
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
