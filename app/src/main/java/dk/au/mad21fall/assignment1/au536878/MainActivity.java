package dk.au.mad21fall.assignment1.au536878;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements MovieAdaptor.IMovieItemClickedListener {


    //widgets
    private RecyclerView rcvList;
    private MovieAdaptor adaptor;
    private Button bttnExit;

    //data
    private ArrayList<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up recyclerview with adaptor and layout manager
        adaptor = new MovieAdaptor(this);
        rcvList = findViewById(R.id.rcvMovies);
        rcvList.setLayoutManager(new LinearLayoutManager(this));
        rcvList.setAdapter(adaptor);

        loadCSV();
        adaptor.updateMovieList(movies);

        bttnExit = findViewById(R.id.bttnExit);
        bttnExit.setOnClickListener((view -> finish()));
    }

    public void loadCSV(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("movie_data.csv")));
            // do reading, usually loop until end of file reading

            String mLine;

            //skips first line in *.csv file
            mLine = reader.readLine();

            while ((mLine = reader.readLine()) != null) {
                String[] input = mLine.split((","));
                String plot;

                //fetch plot
                if(input[4].contains("\"")){
                    plot = mLine.split("\"")[1];
                }else{
                    plot = input[4];
                }

                Movie movie = new Movie(
                        input[0],
                        input[1],
                        input[2],
                        input[3],
                        plot
                );
                movies.add(movie);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //callback when a person item is clicked in the list
    @Override
    public void onMovieClicked(int index) {
        Intent i =  new Intent(this, DetailedActivity.class);
        Movie movieObject = movies.get(index);
        i.putExtra("movieName", movieObject.name);
        i.putExtra("movieYear", movieObject.year);
        i.putExtra("movieRating", movieObject.rating);
        i.putExtra("moviePlot", movieObject.plot);
        i.putExtra("movieGenre", movieObject.genre);
        i.putExtra("movieIcon", String.valueOf(movieObject.getResourceIdFromGenre()));
        startActivity(i);
    }
}