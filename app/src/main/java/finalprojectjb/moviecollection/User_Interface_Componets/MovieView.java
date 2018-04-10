package finalprojectjb.moviecollection.User_Interface_Componets;

import android.content.Context;

import android.graphics.Bitmap;
import android.media.Image;
import android.support.v4.widget.ImageViewCompat;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.AppCompatTextView;

import finalprojectjb.moviecollection.Movie;
import finalprojectjb.moviecollection.MovieInfo;
import finalprojectjb.moviecollection.R;

import static android.view.Gravity.CENTER_HORIZONTAL;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by mor on 21/03/2018.
 */


//Private Package Class;

public class MovieView extends LinearLayout{

   private TextView movieName;
   private WebView movieImage;


   private final static String BaseURl="https://image.tmdb.org/t/p/w92";




   private MovieView(Context context) {
        super(context);


       this.setOrientation(VERTICAL);
        movieImage=new WebView(context);
        LayoutParams movieImageParam=new LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
        movieImage.setLayoutParams(movieImageParam);

        addView(movieImage);


        movieName=new TextView(context);
        LayoutParams movieNameParam=new LayoutParams(MATCH_PARENT,MATCH_PARENT);

       int maxLengthofEditText = 13;
        movieName.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLengthofEditText)});
        movieName.setLayoutParams(movieNameParam);




        addView(movieName);

    }

    public MovieView(Context context, MovieInfo movie ){

        this(context);
        this.setName(movie.getMovieName());
        this.setImage(movie.getImagePath());


    }



    public void setName(String name){

        movieName.setText(name);

    }

    public void setImage(String url){

        movieImage.loadUrl(BaseURl+url);


    }



}
