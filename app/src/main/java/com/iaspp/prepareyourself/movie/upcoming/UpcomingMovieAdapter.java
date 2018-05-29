package com.iaspp.prepareyourself.movie.upcoming;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.iaspp.prepareyourself.R;

import java.util.List;

public class UpcomingMovieAdapter extends RecyclerView.Adapter<UpcomingMovieAdapter.UpcomingHolder> {
    private List<ResultMovieUpcomingDTO> list;

    public UpcomingMovieAdapter(List<ResultMovieUpcomingDTO> list) {
        this.list = list;
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

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UpcomingHolder extends RecyclerView.ViewHolder {
        ViewStub image;
        TextView title;
        TextView genre;
        TextView release;

        public UpcomingHolder(View view) {
            super(view);
            image = view.findViewById(R.id.stub_img);
            title = view.findViewById(R.id.title_upcoming);
            genre = view.findViewById(R.id.genres_upcoming);
            release = view.findViewById(R.id.release_upcoming);
        }
    }
}
