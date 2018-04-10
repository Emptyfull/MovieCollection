package finalprojectjb.moviecollection;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import finalprojectjb.moviecollection.User_Interface_Componets.EditPageUI;
import finalprojectjb.moviecollection.User_Interface_Componets.MainPageUI;
import finalprojectjb.moviecollection.User_Interface_Componets.SearchPageUI;

public class MainPageActivity extends AppCompatActivity implements MainPageUI.ICallback{


    private final int REQUEST_CODE_ADD_MOVIE = 1;
    private final int REQUEST_CODE_EDIT_MOVIE = 2;
    private ArrayList<MovieInfo> movies;
    private Database database;


    private MainPageUI mainPageUI;


    private MenuItem menuItemDeleteAll;
    private MenuItem menuItemExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = new Database();
        mainPageUI=new MainPageUI(this);
        movies=database.getAllMovies();


        setContentView(mainPageUI);

        if(movies.size()!=0) mainPageUI.UpdateMovieGrid(movies);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        menuItemDeleteAll=menu.findItem(R.id.menuItemDeleteAll);
        menuItemExit=menu.findItem(R.id.menuItemDeleteAll);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId=item.getItemId();


        switch (itemId) {

            case R.id.menuItemExit:
                finish();
                return true;
            case R.id.menuItemDeleteAll:
                movies.clear();
                database.removeAll();
                mainPageUI.UpdateMovieGrid(movies);
                return true;
            case R.id.menuItemadd:
                android.app.AlertDialog addDialoge=AddDialouge();
                addDialoge.show();

                return true;


        }
        
        return false;



    }



    private android.app.AlertDialog AddDialouge(){

        android.app.AlertDialog alertDialog=new android.app.AlertDialog.Builder(this).create();
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "interent ADD",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        onInternetAdd();


                    }
                });

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "MANUAL ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                onManualAdd();
            }
        });

        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {



            }
        });






        return alertDialog;

    }





    public void onManualAdd(){

        MovieInfo movie=new MovieInfo();
        Intent intent=new Intent(this, EditPageActivity.class);
        intent.putExtra("movie",movie);
        startActivityForResult(intent,REQUEST_CODE_ADD_MOVIE);


    }

    public void onInternetAdd(){

        Intent intent=new Intent(this,SearchPageActivity.class);
        startActivityForResult(intent,REQUEST_CODE_ADD_MOVIE);

    }



    @Override
    public void onEdit(MovieInfo movieInfo) {

        Intent intent=new Intent(this,EditPageActivity.class);
        intent.putExtra("movie",movieInfo);
        startActivityForResult(intent,REQUEST_CODE_EDIT_MOVIE);




    }

    @Override
    public void onDelete(MovieInfo movieInfo) {

        database.deleteMovie(movieInfo);
        movies.remove(movieInfo);
        mainPageUI.UpdateMovieGrid(movies);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {



            if(requestCode==REQUEST_CODE_EDIT_MOVIE && resultCode==RESULT_OK)
            {
                MovieInfo movie=(MovieInfo)data.getSerializableExtra("movie");
                database.updateMovie(movie);
                for(MovieInfo m: movies){

                    if(m.getMovieID()==movie.getMovieID()){

                        m.updateMovieInfo(movie);
                        break;

                    }

                }

                mainPageUI.UpdateMovieGrid(movies);

            }

        if(requestCode==REQUEST_CODE_ADD_MOVIE && resultCode==RESULT_OK)
        {
            MovieInfo movie=(MovieInfo)data.getSerializableExtra("movie");

            database.addMovie(movie);
            movies.add(movie);

            mainPageUI.UpdateMovieGrid(movies);

        }



    }
}

