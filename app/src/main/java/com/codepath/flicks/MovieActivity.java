package com.codepath.flicks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MovieActivity extends AppCompatActivity {

    @BindView(R.id.lvMovies) ListView lvItems;
    @BindView(R.id.swipeContainer) SwipeRefreshLayout swipeContainer;

    private String MOVIES_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    private ArrayList<Movie> moviesList;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        setSwipeRefreshLayout();

        moviesList = new ArrayList<>();
        moviesAdapter = new MoviesAdapter(this, moviesList);
        lvItems.setAdapter(moviesAdapter);
        lvItems.setOnItemClickListener((adapterView, view, pos, l) -> launchMovieDetailView(moviesList.get(pos)));

        fetchMovies();
    }

    private void setSwipeRefreshLayout() {
        swipeContainer.setOnRefreshListener(() -> fetchMovies());

        swipeContainer.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
    }

    public void fetchMovies() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(MOVIES_URL, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    JSONArray results = response.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        moviesList.add(new Movie((JSONObject) results.get(i)));
                    }
                    moviesAdapter.notifyDataSetChanged();
                    swipeContainer.setRefreshing(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    private void launchMovieDetailView(Movie movie) {
        Intent i = new Intent(MovieActivity.this, MovieDetailsActivity.class);

        i.putExtra("original_title", movie.getOriginalTitle());
        i.putExtra("overview", movie.getOverview());
        i.putExtra("release_date", movie.getReleaseDate());
        i.putExtra("popularity", movie.getPopularity());
        i.putExtra("vote_count", movie.getVoteCount());
        i.putExtra("vote_average", movie.getVoteAverage());

        startActivity(i);
    }
}
