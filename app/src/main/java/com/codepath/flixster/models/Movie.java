package com.codepath.flixster.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Movie {
    // values from API
    public String title;
    public String overview;
    public String posterPath; // only the path, not full url
    public String backdropPath;
    public Double voteAverage;

    // no-arg, empty constructor required for Parceler
    public Movie() {}

    // initialize JSON data
    public Movie(JSONObject object) throws JSONException {
        title = object.getString("title");
        overview = object.getString("overview");
        posterPath = object.getString("poster_path");
        backdropPath = object.getString("backdrop_path");
        voteAverage = object.getDouble("vote_average");
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    @Override
    public String toString() {
        return getTitle()+getPosterPath()+getVoteAverage();
    }
}
