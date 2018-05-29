package com.iaspp.prepareyourself;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.iaspp.prepareyourself.config.TMDbConfig;
import com.iaspp.prepareyourself.genre.GenreController;
import com.iaspp.prepareyourself.interfaces.ICallback;
import com.iaspp.prepareyourself.interfaces.IDTO;
import com.iaspp.prepareyourself.movie.MovieController;
import com.iaspp.prepareyourself.movie.MovieDTO;
import com.iaspp.prepareyourself.movie.MovieResponseDTO;
import com.iaspp.prepareyourself.movie.UpcomingMovieAdapter;
import com.iaspp.prepareyourself.utils.RecyclerItemListener;

import java.util.ArrayList;
import java.util.List;

public class MainScrollingActivity extends AppCompatActivity implements ICallback.OnConfigLoaded {

    private MovieController movieController = null;
    private GenreController genreController;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        rv = findViewById(R.id.main_recycler_view);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        init();
    }

    private void init() {
        this.movieController = new MovieController(getApplicationContext(), getWindowManager());
        this.genreController = new GenreController(getApplicationContext(), getWindowManager());
        this.startRecyclerView();
        this.movieController.initConfiguration(getApplicationContext(), new ICallback.OnRequest() {
            @Override
            public void onSucess(IDTO dto) {
                onConfig((TMDbConfig) dto);
            }

            @Override
            public void onFail(String msg) {
                Log.e("Show error", "error" + msg);
            }
        });
    }

    private void startRecyclerView() {
        UpcomingMovieAdapter adapter = new UpcomingMovieAdapter(new ArrayList<MovieDTO>(), this.movieController, this.genreController);
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

        rv.addOnItemTouchListener(new RecyclerItemListener(getApplicationContext(), rv, new RecyclerItemListener.RecyclerTouchListener() {

            @Override
            public void onClickItem(View v, int position) {
                Log.i("clique", "curto");
            }

            @Override
            public void onLongClickItem(View v, int position) {
                Log.i("clique", "longo");
            }
        }));
    }

    @Override
    public void onConfig(TMDbConfig t) {
        movieController.setConfig(t);
        Log.i("CONFIG:", t.toString());
        getGenres();
    }

    private void getGenres() {
        genreController.getGenres(getApplicationContext(), new ICallback.OnRequest() {
            @Override
            public void onSucess(IDTO dto) {
                onGetGenres(dto);
            }

            @Override
            public void onFail(String msg) {
                Log.e("Show error", "error" + msg);
            }
        });
    }


    private void onGetGenres(IDTO dto) {
        genreController.saveGenres(dto);
        getUpcoming();
    }

    private void getUpcoming() {
        movieController.getUpcoming(getApplicationContext(), new ICallback.OnRequest() {
            @Override
            public void onSucess(IDTO dto) {
                onUpcoming(dto);
            }

            @Override
            public void onFail(String msg) {
                Log.e("Show error", "error" + msg);
            }
        });
    }

    private void onUpcoming(IDTO dto) {
        Log.i("UPCOMING", dto.toString());
        ((UpcomingMovieAdapter) rv.getAdapter()).addList(((MovieResponseDTO) dto).getResultList());
        rv.getAdapter().notifyDataSetChanged();

        /*UpcomingMovieAdapter adapter = new UpcomingMovieAdapter(((MovieResponseDTO) dto).getResultList(), this.movieController);
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
