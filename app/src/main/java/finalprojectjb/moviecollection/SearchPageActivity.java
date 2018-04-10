package finalprojectjb.moviecollection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import finalprojectjb.moviecollection.Http_Request_Classes.MovieInfoRequest;
import finalprojectjb.moviecollection.Http_Request_Classes.MovieSearchRequest;
import finalprojectjb.moviecollection.User_Interface_Componets.EditPageUI;
import finalprojectjb.moviecollection.User_Interface_Componets.SearchPageUI;

public class SearchPageActivity extends AppCompatActivity implements SearchPageUI.ICallback,MovieSearchRequest.ICallback,MovieInfoRequest.ICallback {

    private SearchPageUI searchPageUI;
    private final int REQUEST_CODE_ADD_MOVIE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchPageUI=new SearchPageUI(this,this);
        setContentView(searchPageUI);
    }


    @Override
    public void onCancel() {

        finish();

    }

    @Override
    public void onSearch(String name) {


        MovieSearchRequest request=new MovieSearchRequest(this, this);
        request.send(name);

    }

    @Override
    public void onListClick(int movieID, String movieName) {

        MovieInfoRequest request=new MovieInfoRequest(this,this);
        request.send(movieID);

    }


    @Override
    public void onSearchRequestResult(ArrayList<Movie> Movies) {

        searchPageUI.updateMovieList(Movies);



    }

    @Override
    public void OnInfoRequestResult(MovieInfo Result) {

        Intent intent=new Intent(this, EditPageActivity.class);

        intent.putExtra("movie",Result);
        startActivityForResult(intent, REQUEST_CODE_ADD_MOVIE );

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==REQUEST_CODE_ADD_MOVIE && resultCode==RESULT_OK)
        setResult(RESULT_OK,data);

    }
}
