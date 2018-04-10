package finalprojectjb.moviecollection.User_Interface_Componets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.Gravity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import finalprojectjb.moviecollection.Http_Request_Classes.MovieSearchRequest;
import finalprojectjb.moviecollection.Movie;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;



public class SearchPageUI extends LinearLayout {


    private EditText subject;
    private ListView MovieList;
    private ICallback iSearch;




    public SearchPageUI(Context context, ICallback iSearch) {
        super(context);

        this.iSearch=iSearch;
        this.setOrientation(VERTICAL);




        this.addView(searchEngine(context));
        this.addView( movieList(context));
        this.addView(cancelButton(context));





    }

    private LinearLayout searchEngine(Context context){

        LinearLayout searchEngine = new LinearLayout(context);
        LayoutParams searchEngineParams=new LayoutParams(MATCH_PARENT,0);
        searchEngineParams.weight=1;
        searchEngine.setOrientation(HORIZONTAL);
        searchEngine.setLayoutParams(searchEngineParams);

        TextView title=new TextView(context);
        LayoutParams titleparam=new LayoutParams(0,WRAP_CONTENT);
        titleparam.weight=1;
        title.setLayoutParams(titleparam);
        title.setText("Search");

        searchEngine.addView(title);

        subject=new EditText(context);
        LayoutParams subjectParam=new LayoutParams(0,WRAP_CONTENT);
        subjectParam.weight=5;
        subject.setLayoutParams(subjectParam);

        subject.setBackground(null);
        searchEngine.addView(subject);

        Button Go=new Button(context);
        LayoutParams GoParam=new LayoutParams(0,WRAP_CONTENT);
        GoParam.weight=1;

        Go.setText("GO");
        Go.setLayoutParams(GoParam);
        searchEngine.addView(Go);
        Go.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                iSearch.onSearch(subject.getText().toString());
            }
        });
        return searchEngine;


    }

    private ListView movieList (Context context){

        MovieList=new ListView(context);
        LinearLayout.LayoutParams  MovieListParam=new LinearLayout.LayoutParams(MATCH_PARENT,0);
        MovieListParam.weight=5;
        MovieList.setLayoutParams( MovieListParam);


        MovieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Object item = adapterView.getAdapter().getItem(i);

                if(item instanceof  Movie) {
                    iSearch.onListClick(((Movie) item).getMovieRequestID(),((Movie) item).getMovieName());


                }

            }
        });



        return  MovieList;

    }

    private LinearLayout cancelButton(Context context){

        LinearLayout tempLayout=new LinearLayout(context);
        LinearLayout.LayoutParams tempLayoutParam=new LinearLayout.LayoutParams(MATCH_PARENT,0);
        tempLayoutParam.weight=1;
        tempLayout.setLayoutParams(tempLayoutParam);
        tempLayout.setOrientation(VERTICAL);

        Button cancel=new Button(context);
        LinearLayout.LayoutParams cancelParam=new LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
        cancelParam.gravity= Gravity.CENTER_HORIZONTAL ;
        cancel.setLayoutParams(cancelParam);
        cancel.setText("Cancel");
        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                iSearch.onCancel();



            }
        });

        tempLayout.addView(cancel);
        return tempLayout;

    }







    public void updateMovieList(ArrayList<Movie> Movies) {

        MovieListAdapter adapter=new MovieListAdapter(getContext(),Movies);
        MovieList.setAdapter(adapter);

    }





    public interface ICallback{

        public void onCancel();
        public void onSearch(String Text);
        public void onListClick(int MovieID, String MovieName);
    }


    private class MovieListAdapter extends ArrayAdapter<Movie> {



        public MovieListAdapter(Context context, List<Movie> objects) {
            super(context, 0, objects);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            Movie current = getItem(position);
           TextView MovieName=new TextView(getContext());
           MovieName.setText(current.getMovieName());
            return MovieName;
        }


    }



}
