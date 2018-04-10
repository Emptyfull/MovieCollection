package finalprojectjb.moviecollection.Http_Request_Classes;

/**
 * Created by mor on 05/04/2018.
 */

 class TMDBCommands {



        private final static String key="682ad6d569c8a524ecc4dcdb3fe76bf3";

        private final static String website= "https://api.themoviedb.org";

        private final static String language="&language=en-US";


        static String Search(String query){

            String prefix="/3/search/movie?";

            String request=website +prefix+"api_key="+key+"&query="+query;

            return request;


        }


        static String FindDetails(int movieId){

            String prefix ="/3/movie/";

            String request=website+prefix+movieId+"?api_key="+key+language;

            return request;




        }

    //=========================IMAGES=======================================/
    private final static String base_url= "https://image.tmdb.org/t/p/";

    private final static String[] backdropSizes={"w300","w780","w1280","original"};
    private final static String[] logo_sizes={"w45", "w92", "w154", "w185", "w300", "w500", "original"};
    private final static String[] poster_sizes={ "w92", "w154", "w185", "w342", "w500", "w780", "original"};
    private final static String[] profile_sizes= {"w45","w185","h632","original"};
    private final static String[] still_sizes= {"w92","w185","w300","original"};

    private final static String MovieViewDisplaySize=logo_sizes[1];
    private final static String MoviePosterSize=poster_sizes[2];



 }
