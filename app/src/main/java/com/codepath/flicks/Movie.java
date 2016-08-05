package com.codepath.flicks;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie {
    private String originalTitle;
    private String overview;
    private String posterPath;
    private String backdropPath;
    private String releaseDate;
    private int voteCount;
    private float voteAverage;
    private float popularity;

    public Movie(JSONObject jsonObject) {
        try {
            this.originalTitle = jsonObject.getString("original_title");
            this.overview = jsonObject.getString("overview");
            this.posterPath = jsonObject.getString("poster_path");
            this.backdropPath = jsonObject.getString("backdrop_path");
            this.releaseDate = jsonObject.getString("release_date");
            this.voteCount = Integer.parseInt(jsonObject.getString("vote_count"));
            this.voteAverage = Float.parseFloat(jsonObject.getString("vote_average"));
            this.popularity = Float.parseFloat(jsonObject.getString("popularity"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public float getPopularity() {
        return popularity;
    }
}
