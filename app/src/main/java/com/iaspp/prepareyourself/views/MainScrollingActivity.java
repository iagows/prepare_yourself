package com.iaspp.prepareyourself.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.iaspp.prepareyourself.R;
import com.iaspp.prepareyourself.config.TMDbConfig;
import com.iaspp.prepareyourself.genre.GenreController;
import com.iaspp.prepareyourself.interfaces.ICallback;
import com.iaspp.prepareyourself.interfaces.IDTO;
import com.iaspp.prepareyourself.movie.MovieAdapter;
import com.iaspp.prepareyourself.movie.MovieController;
import com.iaspp.prepareyourself.movie.MovieDTO;
import com.iaspp.prepareyourself.movie.MovieResponseDTO;
import com.iaspp.prepareyourself.utils.EndlessRecyclerViewScrollListener;
import com.iaspp.prepareyourself.utils.RecyclerItemListener;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class MainScrollingActivity extends AppCompatActivity implements ICallback.OnConfigLoaded {

    public static final String CHANGE_TO_MOVIE_INFO = "com.iaspp.prepareyourself.show_movie_info";
    private MovieController movieController;
    private GenreController genreController;
    private RecyclerView rv;
    private EndlessRecyclerViewScrollListener endless;
    private SearchView et_find;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv = findViewById(R.id.main_recycler_view);

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

        et_find = findViewById(R.id.et_find);

        et_find.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                resetCounter();
                if (StringUtils.isNotBlank(query)) {
                    doSearch(query, 0);
                } else {
                    getUpcoming(0);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        ImageView searchCleanBt = et_find.findViewById(R.id.search_close_btn);
        searchCleanBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetCounter();
                et_find.setQuery("",true);
                getUpcoming(0);
            }
        });
    }

    private void startRecyclerView() {
        MovieAdapter adapter = new MovieAdapter(new ArrayList<MovieDTO>(), this.movieController, this.genreController);
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

        rv.addOnItemTouchListener(new RecyclerItemListener(getApplicationContext(), rv, new RecyclerItemListener.RecyclerTouchListener() {
            @Override
            public void onClickItem(View v, int position) {
                gotoMovie(v, position);
            }

            @Override
            public void onLongClickItem(View v, int position) {
                Log.i("clique", "longo");
            }
        }));

        endless = new EndlessRecyclerViewScrollListener(llm) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (totalItemsCount < movieController.getTotalMovies()) {
                    String query = et_find.getQuery().toString();
                    if (StringUtils.isNotBlank(query)) {
                        doSearch(query, page);
                    } else {
                        getUpcoming(page);
                    }
                }
            }
        };
        rv.addOnScrollListener(endless);
    }

    private void gotoMovie(View v, int position) {
        Intent intent = new Intent(this, ShowMovieInfo.class);
        MovieDTO dto = ((MovieAdapter) rv.getAdapter()).get(position);
        intent.putExtra(CHANGE_TO_MOVIE_INFO, dto);
        startActivity(intent);
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
                onMovieDataReceived(dto);
            }

            @Override
            public void onFail(String msg) {
                Log.e("Show error", "error" + msg);
            }
        }, page + 1);
    }

    private void doSearch(String query, int page) {
        movieController.getByName(getApplicationContext(), new ICallback.OnRequest() {
            @Override
            public void onSucess(IDTO dto) {
                onMovieDataReceived(dto);
            }

            @Override
            public void onFail(String msg) {
                Log.e("Show error", "error" + msg);
            }
        }, page + 1, query);
    }

    private void onMovieDataReceived(IDTO dto) {
        MovieResponseDTO response = (MovieResponseDTO) dto;

        movieController.setTotalMovies(response.getTotalResults());
        MovieAdapter adapter = (MovieAdapter) rv.getAdapter();
        adapter.addList(response.getResultList());

        rv.getAdapter().notifyDataSetChanged();
    }

    private void resetCounter() {
        ((MovieAdapter) rv.getAdapter()).clearList();
        endless.resetState();
        movieController.setTotalMovies(0);
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
