package finalprojectjb.moviecollection.Http_Request_Classes;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import finalprojectjb.moviecollection.Movie;

import static finalprojectjb.moviecollection.Http_Request_Classes.TMDBCommands.*;

/**
 * Created by mor on 05/04/2018.
 */

public class MovieSearchRequest extends MovieRequest{

    private ArrayList<Movie> Movies=new ArrayList<Movie>();
    private ICallback Ic;



    public MovieSearchRequest(Activity activity, ICallback Ic){

        super(activity);
        this.Ic=Ic;

    }

    public void send(String subject){

        HttpRequest request=new HttpRequest(this);
        request.execute(Search(subject));

    }

    @Override
    public void onSuccess(String downloadedText) {


            try {

                JSONObject jsonObject = new JSONObject(downloadedText);
                JSONArray jsonArray = jsonObject.getJSONArray("results");


                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject subJSONObject = jsonArray.getJSONObject(i);
                    String movieName = subJSONObject.getString("title");
                    int movieID = subJSONObject.getInt("id");
                    Movie movie = new Movie(movieID, movieName);

                    Movies.add(movie);

                }

                Ic.onSearchRequestResult(Movies);

            } catch (JSONException ex) {
                Toast.makeText(activity, "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
            }



        progressDialog.dismiss();

    }


    public interface ICallback{

        public void onSearchRequestResult( ArrayList<Movie> Movies) ;

    }



}
