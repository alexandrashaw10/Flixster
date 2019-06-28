package com.codepath.flixster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.flixster.models.Movie;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieDetailsActivity extends AppCompatActivity {

    // the movie to display
    Movie movie;

    // the view objects
    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbVoteAverage;
    ImageView imageView;
    String imageUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        // resolve the view objects
        tvTitle = (TextView) findViewById(R.id.movieTitle);
        tvOverview = (TextView) findViewById(R.id.movieOverview);
        rbVoteAverage = (RatingBar) findViewById(R.id.rbVoteAverage);
        imageView = (ImageView) findViewById(R.id.movieBackdropImage);

        // unwrap the movie via intent, using its simple name as a key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        imageUrl = getIntent().getStringExtra("imageUrl");
        Log.d("MovieDetailsActivity", String.format("showing details for %s", movie.getTitle()));

        // set the title and overview and the image
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        int radius = 30;
        int margin = 10;

        // load image using Glide
        Glide.with(this)
                .load(imageUrl)
                .apply(new RequestOptions()
                        .transform(new RoundedCornersTransformation(radius, margin)))
                .into(imageView);

        // vote average is 0..10, convert to 0..5 by dividing by 2
        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage = voteAverage > 0 ? voteAverage / 2.0f : voteAverage);
        Log.i(this.getClass().getName(), movie.toString());
    }
}
