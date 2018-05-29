package com.iaspp.prepareyourself.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.iaspp.prepareyourself.R;
import com.iaspp.prepareyourself.movie.MovieDTO;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Text;

public class ShowMovieInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movie_info);

        Intent intent = getIntent();
        MovieDTO dto = intent.getParcelableExtra(MainScrollingActivity.CHANGE_TO_MOVIE_INFO);

        final ImageView image;
        final TextView title;
        final TextView genre;
        final TextView release;
        final TextView overview;
        final Button share;

        image = findViewById(R.id.image_stub);
        title = findViewById(R.id.title_upcoming);
        genre = findViewById(R.id.genres_upcoming);
        release = findViewById(R.id.release_upcoming);
        overview = findViewById(R.id.tf_overview);
        share = findViewById(R.id.bt_share);

        title.setText(dto.getOriginalTitle());
        genre.setText(dto.getGenreWithComma());
        release.setText(dto.getReleaseDate());
        overview.setText(dto.getOverview());

        Picasso.get().load(dto.getFullUrl()).into(image);


    }

    private void onShare(){

    }
}
