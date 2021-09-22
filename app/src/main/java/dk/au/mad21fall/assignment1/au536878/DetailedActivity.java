package dk.au.mad21fall.assignment1.au536878;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailedActivity extends AppCompatActivity {

    Button bttnBack;
    TextView movieTitle, movieYear, movieRating, movieGenre, moviePlot;




    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        bttnBack = findViewById(R.id.bttnBack);
        bttnBack.setOnClickListener((view -> finish()));
    }

    @Override
    protected void onStart() {
        super.onStart();

        Bundle extras = getIntent().getExtras();
        movieTitle = findViewById(R.id.textMovieTitleDetailedView);
        movieYear = findViewById(R.id.textMovieYearDetailedView);
        movieGenre = findViewById(R.id.textMovieGenreDetailedView);
        moviePlot = findViewById(R.id.textMoviePlotDetailedView);
        movieRating = findViewById(R.id.textMovieRatingDetailedView );

        movieTitle.setText(extras.getString("movieName"));
        movieYear.setText(extras.getString("movieYear"));
        movieGenre.setText(extras.getString("movieGenre"));
        movieRating.setText(extras.getString("movieRating"));
        moviePlot.setText(extras.getString("moviePlot"));
        Log.d("DETAILED_AC", "lort:" + extras.getString("movieName"));
    }
}
