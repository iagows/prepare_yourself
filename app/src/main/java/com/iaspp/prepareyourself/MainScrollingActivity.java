package com.iaspp.prepareyourself;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.RequestQueue;
import com.iaspp.prepareyourself.config.TMDbConfig;
import com.iaspp.prepareyourself.interfaces.ICallback;
import com.iaspp.prepareyourself.interfaces.IDTO;
import com.iaspp.prepareyourself.movie.AbstractResponseDTO;
import com.iaspp.prepareyourself.movie.MovieController;
import com.iaspp.prepareyourself.utils.RequestType;

public class MainScrollingActivity extends AppCompatActivity implements ICallback.OnConfigLoaded {

    private MovieController movieController = null;
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
        this.movieController = new MovieController(getApplicationContext());
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

    @Override
    public void onConfig(TMDbConfig t) {
        movieController.setConfig(t);
        Log.i("CONFIG:", t.toString());
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
