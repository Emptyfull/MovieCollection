package finalprojectjb.moviecollection;

import java.io.Serializable;



public class MovieInfo extends Movie {




    private String imagePath=null;
    private String overView=null;
    private String releaseDate=null;

    private int rating=0;
    private String genres=null;
    private int runTime=0;



    private int movieID;

    public MovieInfo(){}
    public MovieInfo(int movieRequestID, String movieName, String imagePath ,
                     String overView,String releaseDate, String genres, int rating ,int runTime)
    {
        super(movieRequestID,movieName);

        this.imagePath=imagePath;
        this.overView=overView;
        this.releaseDate=releaseDate;
        this.genres=genres;
        this.rating=rating;
        this.runTime=runTime;

    }


    public void updateMovieInfo(MovieInfo movieInfo){

        this.setMovieName(movieInfo.getMovieName());
        this.imagePath=movieInfo.getImagePath();
        this.overView=movieInfo.getOverView();
        this.releaseDate=movieInfo.getReleaseDate();
        this.rating=movieInfo.getRating();
        this.genres=movieInfo.getGenres();
        this.runTime=movieInfo.getRunTime();
    } //update  the MovieInfo parameters except movieRequestID and movieID;


    public void setMovieID(int movieID){

        this.movieID=movieID;


    }

    public int getMovieID() {
        return movieID;
    }


    public String getImagePath() {
        return imagePath;
    }

    public String getOverView() {
        return overView;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getRating() {
        return rating;
    }

    public String getGenres() {
        return genres;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }


}

