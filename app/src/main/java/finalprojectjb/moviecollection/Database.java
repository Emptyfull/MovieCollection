package finalprojectjb.moviecollection;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;


public class Database extends SQLiteOpenHelper{


    public Database() {
        super(MyApp.getContext(), "MoviesDataBase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE Movies(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " requestId INTEGER NOT NULL, name TEXT NOT NULL, imagePath TEXT, overView TEXT, " +
                "releaseDate TEXT, genres TEXT, rating INTEGER,runTime INTEGER)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE Movies");
        onCreate(db);

    }


    public void addMovie(MovieInfo movieInfo) {
        String sql = String.format("INSERT INTO Movies (requestId,name,imagePath,overView,releaseDate,genres,rating,runTime) VALUES(%d,'%s','%s','%s','%s','%s',%d,%d);",
                movieInfo.getMovieRequestID(), movieInfo.getMovieName().replace("'",""),movieInfo.getImagePath(),
                movieInfo.getOverView().replace("'",""),movieInfo.getReleaseDate(),
                movieInfo.getGenres(),movieInfo.getRating(),movieInfo.getRunTime());


        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        Cursor cursor = db.rawQuery("SELECT last_insert_rowid()", null);
        cursor.moveToNext();
        int id = cursor.getInt(0);
        movieInfo.setMovieID(id);
        cursor.close();
        db.close();
     }

    public void removeAll()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("Movies", null, null );

         }

    public void updateMovie(MovieInfo movieInfo) {
        String sql = String.format("UPDATE Movies SET name='%s',requestId=%d,imagePath='%s'" +
                        ",overView='%s',releaseDate='%s',genres='%s',rating=%d" +
                        ", runTime=%d WHERE _id=%d",
                movieInfo.getMovieName(), movieInfo.getMovieRequestID(),movieInfo.getImagePath(),
                movieInfo.getOverView(),movieInfo.getReleaseDate(),movieInfo.getGenres(),
                movieInfo.getRating(),movieInfo.getRunTime(),
                movieInfo.getMovieID());


        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }

    public void deleteMovie(MovieInfo movieInfo) {
        String sql = String.format("DELETE FROM Movies WHERE _id=%d", movieInfo.getMovieID());
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }


    public ArrayList<MovieInfo> getAllMovies() {

        ArrayList<MovieInfo> movies = new ArrayList<MovieInfo>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Movies", null);


        int idIndex = cursor.getColumnIndex("_id");
        int RequestIdIndex = cursor.getColumnIndex("requestId");
        int nameIndex = cursor.getColumnIndex("name");
        int imagePathIndex = cursor.getColumnIndex("imagePath");
        int overViewIndex = cursor.getColumnIndex("overView");
        int releaseDateIndex = cursor.getColumnIndex("releaseDate");
        int genresIndex = cursor.getColumnIndex("genres");
        int ratingIndex = cursor.getColumnIndex("rating");
        int runTimeIndex = cursor.getColumnIndex("runTime");


        while(cursor.moveToNext()) {
            int id = cursor.getInt(idIndex);
            int requestId= cursor.getInt(RequestIdIndex);
            String name = cursor.getString(nameIndex);
            String imagePath=cursor.getString(imagePathIndex);
            String overView=cursor.getString(overViewIndex);
            String releaseDate=cursor.getString(releaseDateIndex);
            String genres=cursor.getString(genresIndex);
            int rating=cursor.getInt(ratingIndex);
            int runTime=cursor.getInt(runTimeIndex);

            MovieInfo movie = new MovieInfo(requestId,name,imagePath,overView,releaseDate,genres,rating,runTime);
            movie.setMovieID(id);
            movies.add(movie);
        }

        cursor.close();
        db.close();

        return movies;
    }
}

