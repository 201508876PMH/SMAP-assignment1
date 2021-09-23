package dk.au.mad21fall.assignment1.au536878;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RatingActivity extends AppCompatActivity {

    TextView movieTitle, userRating, multilineText;
    ImageView movieIcon;
    Button cancelbttn, bttnOK;

    SeekBar seekbar;
    int progress = 0;

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
    }

    @Override
    protected void onStart() {
        super.onStart();
        extras = getIntent().getExtras();

        movieTitle = findViewById(R.id.activity_rating_title);
        userRating = findViewById(R.id.activity_rating_userRating);
        cancelbttn = findViewById(R.id.cancelBttn);
        bttnOK = findViewById(R.id.bttnOK);
        movieIcon = findViewById(R.id.activity_rating_icon);
        multilineText = findViewById(R.id.editTextTextMultiLine);
        seekbar = findViewById(R.id.activity_rating_seekbar);

        cancelbttn.setOnClickListener((view -> cancelbttn()));
        bttnOK.setOnClickListener((view -> bttnOKClick()));

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
                updateUI();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        movieTitle.setText(extras.getString("movieName"));
        movieIcon.setImageResource(Integer.parseInt(extras.getString("movieIcon")));
        progress = Integer.parseInt(extras.getString("userRating"));
        multilineText.setText(extras.getString("userNotes"));
        updateUI();
    }

    private void updateUI() {
        userRating.setText(String.valueOf(progress));
    }

    protected void bttnOKClick(){
        Intent i =  new Intent();
        i.putExtra("userRating", String.valueOf(progress));
        i.putExtra("userNotes", multilineText.getText().toString());
        setResult(RESULT_OK, i);
        finish();
    }
    protected void cancelbttn(){
        Intent i = new Intent();
        setResult(RESULT_CANCELED,i);
        finish();
    }
}
