package dk.au.mad21fall.assignment1.au536878;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class DetailedActivity extends AppCompatActivity {

    Button bttnBack, bttnRate;
    TextView movieTitle, movieYear, movieRating,
            movieGenre, moviePlot, userRating, userNotes;
    ImageView movieIcon;

    Bundle mainActivityExtra;
    Bundle ratingActivityExtra;
    ActivityResultLauncher<Intent> resultFromRatingActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        bttnBack = findViewById(R.id.bttnBack);
        bttnBack.setOnClickListener((view -> finish()));

        bttnRate = findViewById(R.id.bttnRate);
        bttnRate.setOnClickListener((view -> onRateClick()));


        resultFromRatingActivity = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),new ActivityResultRatingHandler());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainActivityExtra = getIntent().getExtras();

        movieTitle = findViewById(R.id.textMovieTitleDetailedView);
        movieYear = findViewById(R.id.textMovieYearDetailedView);
        movieGenre = findViewById(R.id.textMovieGenreDetailedView);
        movieRating = findViewById(R.id.textMovieRatingDetailedView );
        moviePlot = findViewById(R.id.textMoviePlotDetailedView);
        movieIcon = findViewById(R.id.MovieIconDetailedView);
        userRating = findViewById(R.id.details_activity_userRating);
        userNotes = findViewById(R.id.txtUsernotes);

        movieTitle.setText(mainActivityExtra.getString("movieName"));
        movieYear.setText(mainActivityExtra.getString("movieYear"));
        movieGenre.setText(mainActivityExtra.getString("movieGenre"));
        movieRating.setText(mainActivityExtra.getString("movieRating"));
        moviePlot.setText(mainActivityExtra.getString("moviePlot"));

        movieIcon.setImageResource(Integer.parseInt(mainActivityExtra.getString("movieIcon")));
    }

    protected void onRateClick(){
       Intent i =  new Intent(this, RatingActivity.class);

        i.putExtra("movieName", mainActivityExtra.getString("movieName"));
        i.putExtra("movieIcon", mainActivityExtra.getString("movieIcon"));
        if(ratingActivityExtra != null){
            String tis = ratingActivityExtra.getString("userRating");

            i.putExtra("userRating", ratingActivityExtra.getString("userRating"));
            i.putExtra("userNotes", ratingActivityExtra.getString("userNotes"));
        }else{
            i.putExtra("userRating", "0");
            i.putExtra("userNotes", "");
        }
        Log.d("DETAILED", mainActivityExtra.getString("movieIcon"));
        resultFromRatingActivity.launch(i);
    }

    private class ActivityResultRatingHandler implements ActivityResultCallback<ActivityResult>{

        @Override
        public void onActivityResult(ActivityResult result) {
            Intent data = result.getData();
            if(result.getResultCode() == RESULT_OK) {
                userRating.setText(data.getStringExtra("userRating"));
                userNotes.setText(data.getStringExtra("userNotes"));

                ratingActivityExtra = data.getExtras();
            }
        }
    }
}
