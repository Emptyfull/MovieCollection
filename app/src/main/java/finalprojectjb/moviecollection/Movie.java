package finalprojectjb.moviecollection;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;



public class Movie implements Serializable {

    private int movieRequestID=0;
    private String movieName;




        public Movie(){}
       public Movie (int movieRequestID, String movieName ){

        this.movieRequestID=movieRequestID;
        this.movieName=movieName;

        }



    public int getMovieRequestID() {
        return movieRequestID;
    }

    public String getMovieName() {
        return movieName;
    }



    public void setMovieRequestID(int requestID) {
        this.movieRequestID = requestID;
    }

    public void setMovieName(String name) {
        this.movieName = movieName;
    }
}
