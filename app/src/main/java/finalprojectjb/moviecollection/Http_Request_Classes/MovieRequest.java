package finalprojectjb.moviecollection.Http_Request_Classes;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by mor on 01/04/2018.
 */

 abstract class MovieRequest implements HttpRequest.Callbacks {

    protected Activity activity;
    protected ProgressDialog progressDialog;


    public MovieRequest(Activity activity){

        this.activity = activity;
        progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("Downloading...");
        progressDialog.setMessage("Please Wait...");

    }

    public void onAboutToStart() {
        progressDialog.show();
    }

    public void onError(String errorMessage) {
        Toast.makeText(activity, "Error: " + errorMessage, Toast.LENGTH_LONG).show();
        progressDialog.dismiss();
    }





}

