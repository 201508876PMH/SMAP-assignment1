package dk.au.mad21fall.assignment1.au536878;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdaptor extends RecyclerView.Adapter<MovieAdaptor.MovieViewHolder> {

    //interface for handling when a Movie item is clicked in various ways
    public interface IMovieItemClickedListener{
        void onMovieClicked(int index);
        void sendInvite(Movie movie);
    }

    //callback interface for movie actions on each item
    private IMovieItemClickedListener listener;
    
    //data in the adaptor
    private ArrayList<Movie> movieList;


    //constructor
    public MovieAdaptor(IMovieItemClickedListener listener){
        this.listener = listener;
    }

    //a method for updating the list - causes the adaptor/recycleview to update
    public void updateMovieList(ArrayList<Movie> lists){
        movieList = lists;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        return movieList.size();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        MovieViewHolder vh = new MovieViewHolder(v, listener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.txtName.setText(movieList.get(position).name);
        holder.txtYear.setText(movieList.get(position).year);
        holder.imgIcon.setImageResource(movieList.get(position).getResourceIdFromGenre());
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //viewholder ui widgets references
        ImageView imgIcon;
        TextView txtName;
        TextView txtGenre;
        TextView txtYear;
        TextView txtIMBD;
        TextView txtMovieResourceId;
        TextView txtPlot;

        //custom callback interface for user actions done to the view holder item
        IMovieItemClickedListener listener;

        //contructor
        public MovieViewHolder(@NonNull View itemView,
                               IMovieItemClickedListener movieItemClickedListener) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.imageMovieIcon);
            txtName = itemView.findViewById(R.id.textMovieTitle);
            //this.txtGenre = txtGenre;
            txtYear = itemView.findViewById(R.id.textMovieYear);
            //this.txtIMBD = txtIMBD;
            //this.txtMovieResourceId = txtMovieResourceId;
            //this.txtPlot = txtPlot;
            this.listener = movieItemClickedListener;

            //set click listener for whole list item
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            listener.onMovieClicked(getAdapterPosition());
        }
    }


}
