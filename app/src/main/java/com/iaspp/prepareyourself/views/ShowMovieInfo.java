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

public class ShowMovieInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movie_info);

        Intent intent = getIntent();
        final MovieDTO dto = intent.getParcelableExtra(MainScrollingActivity.CHANGE_TO_MOVIE_INFO);

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

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBt(dto);
            }
        });
    }

    private void onClickBt(MovieDTO dto) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, dto.getOriginalTitle());

        String sharedVia = getString(R.string.shared_via);
        String appName = getString(R.string.app_name);

        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, dto.getFullUrl() + "\n" + sharedVia + " \"" + appName + "\"\n" + dto.getOverview());
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_via)));
    }
}
