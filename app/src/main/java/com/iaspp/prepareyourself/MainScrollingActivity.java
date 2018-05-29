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
import com.iaspp.prepareyourself.movie.MovieAdapter;
import com.iaspp.prepareyourself.utils.EndlessRecyclerViewScrollListener;
import com.iaspp.prepareyourself.utils.RecyclerItemListener;

import java.util.ArrayList;

public class MainScrollingActivity extends AppCompatActivity implements ICallback.OnConfigLoaded {

    private MovieController movieController = null;
    private GenreController genreController;
    private RecyclerView rv;
    private int totalMovies;

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

    private EndlessRecyclerViewScrollListener endless;

    private void startRecyclerView() {
        MovieAdapter adapter = new MovieAdapter(new ArrayList<MovieDTO>(), this.movieController, this.genreController);
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

        endless = new EndlessRecyclerViewScrollListener(llm) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (totalItemsCount < totalMovies) {
                    getUpcoming(page);
                }
            }
        };
        rv.addOnScrollListener(endless);
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
        getUpcoming(0);
    }

    private void getUpcoming(int page) {
        movieController.getUpcoming(getApplicationContext(), new ICallback.OnRequest() {
            @Override
            public void onSucess(IDTO dto) {
                onUpcoming(dto);
            }

            @Override
            public void onFail(String msg) {
                Log.e("Show error", "error" + msg);
            }
        }, page + 1);
    }

    private void onUpcoming(IDTO dto) {
        MovieResponseDTO response = (MovieResponseDTO) dto;
        totalMovies = ((MovieResponseDTO) dto).getTotalResults();
        Log.i("UPCOMING", response.toString());
        ((MovieAdapter) rv.getAdapter()).addList(response.getResultList());
        rv.getAdapter().notifyDataSetChanged();
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
