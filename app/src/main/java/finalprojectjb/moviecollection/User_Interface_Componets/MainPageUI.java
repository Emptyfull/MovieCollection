package finalprojectjb.moviecollection.User_Interface_Componets;

import android.app.AlertDialog;
import android.content.Context;


import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import finalprojectjb.moviecollection.Movie;
import finalprojectjb.moviecollection.MovieInfo;
import finalprojectjb.moviecollection.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by mor on 21/03/2018.
 */

public class MainPageUI extends LinearLayout {



    private GridView MovieGrid;
    private ICallback Ic;

    private final static String EditButtonText="Edit";
    private final static String DeleteButtonText="Delete";





    public MainPageUI(Context context) {

        super(context);
        if(context instanceof ICallback)
        this.Ic=(ICallback) context;
        else this.Ic=null;
        this.addView(MovieGrid=movieGrid(context));


    }

    private GridView movieGrid(final Context context){

        GridView movieGrid=new GridView(context);
        LinearLayout.LayoutParams movieGridParam=new LinearLayout.LayoutParams(MATCH_PARENT,MATCH_PARENT);
        movieGrid.setNumColumns(4);
        movieGrid.setLayoutParams(movieGridParam);


        movieGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(context, "hiiii", Toast.LENGTH_SHORT).show();
            }
        });

       movieGrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

               Object item = adapterView.getAdapter().getItem(i);

                if(item instanceof MovieInfo)
                { AlertDialog alert= alert(context,(MovieInfo)item);
                    alert.show();
                }

               return false;
           }
       });



        return movieGrid;

    }

    public void UpdateMovieGrid(ArrayList<MovieInfo> movieList){


       MovieGridAdapter adapter=new MovieGridAdapter(getContext(),movieList);

       MovieGrid.setAdapter(adapter);



    }

    private AlertDialog alert(Context context, final MovieInfo movieInfo){

        AlertDialog alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, EditButtonText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Ic.onEdit(movieInfo);

                    }
                });

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, DeleteButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Ic.onDelete(movieInfo);
            }
        });


    return alertDialog;
    }




    public interface ICallback{

        public void onEdit(MovieInfo movieInfo);
        public void onDelete(MovieInfo movieInfo);

    }





    private class MovieGridAdapter extends ArrayAdapter<MovieInfo> {

        private  GridView.LayoutParams movieParam=new GridView.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);

        public MovieGridAdapter(Context context, List<MovieInfo> objects) {
            super(context, 0, objects);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            MovieInfo current = getItem(position);

            MovieView movieView = new MovieView(getContext(), current);

            movieView.setLayoutParams(movieParam);
            return movieView;
        }


    }







}