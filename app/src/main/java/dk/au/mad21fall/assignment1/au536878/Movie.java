package dk.au.mad21fall.assignment1.au536878;

import java.util.Locale;

//simple data class (DTO)
public class Movie {
    public String name;
    public String genre;
    public String year;
    public String rating;
    public String plot;

    public Movie(String name, String genre, String year, String rating, String plot) {
        this.name = name;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.plot = plot;
    }

    public int getResourceIdFromGenre(){
        switch(genre.toLowerCase(Locale.ROOT)) {
            case "action":
                return R.drawable.action;
            case "comedy":
                return R.drawable.comedy;
            case "drama":
                return R.drawable.drama;
            case "horror":
                return R.drawable.horror;
            case "romance":
                return R.drawable.romance;
            case "western":
                return R.drawable.western;
            default:
                return R.drawable.ic_launcher_background;
        }
    }
}
