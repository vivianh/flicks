package com.codepath.flicks;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MoviesAdapter extends ArrayAdapter<Movie> {

    public static class ViewHolder {
        @BindView(R.id.tvTitle) TextView originalTitle;
        @BindView(R.id.tvOverview) TextView overview;
        @BindView(R.id.ivPoster) ImageView poster;

        public ViewHolder (View view) {
            ButterKnife.bind(this, view);
        }
    }

    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        super(context, R.layout.item_movie, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.originalTitle.setText(movie.getOriginalTitle());
        viewHolder.overview.setText(movie.getOverview());
        viewHolder.poster.setImageResource(0);

        Picasso.with(getContext())
                .load(getImageURI(movie))
                .placeholder(R.drawable.placeholder)
                .transform(new RoundedCornersTransformation(10, 10))
                .into(viewHolder.poster);

        return convertView;
    }

    private String getImageURI(Movie movie) {
        if (getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return String.format("https://image.tmdb.org/t/p/w342/%s", movie.getBackdropPath());
        } else {
            return String.format("https://image.tmdb.org/t/p/w342/%s", movie.getPosterPath());
        }
    }
}
