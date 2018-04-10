package finalprojectjb.moviecollection.Http_Request_Classes;

import android.app.Activity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import finalprojectjb.moviecollection.MovieInfo;

import static finalprojectjb.moviecollection.Http_Request_Classes.TMDBCommands.FindDetails;


/**
 * Created by mor on 06/04/2018.
 */

public class MovieInfoRequest extends MovieRequest implements Serializable{


    private ICallback Ic;

    public MovieInfoRequest(Activity activity, ICallback Ic) {

        super(activity);
        this.Ic=Ic;
    }


    public void send(int MovieID){

        HttpRequest request=new HttpRequest(this);
        request.execute(FindDetails(MovieID));

    }





    @Override
    public void onSuccess(String downloadedText) {



        try {

            JSONObject jsonObject = new JSONObject(downloadedText);

            int requestID= jsonObject.getInt("id");
            String name=jsonObject.getString("title");
            String imagePath=jsonObject.getString("poster_path");
            String overview=jsonObject.getString("overview");
            String releaseDate=jsonObject.getString("release_date");
            String genres=JsonArrayStringConverter(jsonObject.getJSONArray("genres"),"name");
            int rating=(int)(jsonObject.optDouble("vote_average",0)+0.5);
            int runTime=jsonObject.optInt("runtime",0);

            MovieInfo Edit=new MovieInfo(requestID,name,imagePath,overview,releaseDate,genres,rating,runTime);
            Ic.OnInfoRequestResult(Edit);

        }
           catch (JSONException ex) {
            Toast.makeText(activity, "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }


        progressDialog.dismiss();
    }

    private String JsonArrayStringConverter(JSONArray jsonArray,String name) throws JSONException{

       String text="";

        for (int i = 0; i <jsonArray.length() ; i++) {

            JSONObject jsonObject=jsonArray.getJSONObject(i);
            text+=jsonObject.getString(name);
            if(i==(jsonArray.length()-1))break;
            text+=", ";
        }

        return text;

    }




    public interface ICallback{

        public void OnInfoRequestResult(MovieInfo Result);



    }



}
