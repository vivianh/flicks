package com.codepath.flicks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity {

    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvOverview) TextView tvOverview;
    @BindView(R.id.tvReleaseDate) TextView tvReleaseDate;
    @BindView(R.id.tvPopularity) TextView tvPopularity;
    @BindView(R.id.tvVoteAverage) TextView tvVoteAverage;
    @BindView(R.id.rbVoteAverage) RatingBar rbVoteAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        tvTitle.setText(intent.getStringExtra("original_title"));
        tvOverview.setText(intent.getStringExtra("overview"));
        tvReleaseDate.setText(getResources().getString(R.string.release_date, intent.getStringExtra("release_date")));
        tvPopularity.setText(getResources().getString(R.string.popularity, intent.getFloatExtra("popularity", 0)));
        tvVoteAverage.setText(getResources().getString(R.string.vote_average));
        rbVoteAverage.setRating((intent.getFloatExtra("vote_average", 0) / 2));
    }
}
