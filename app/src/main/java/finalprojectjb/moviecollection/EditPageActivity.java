package finalprojectjb.moviecollection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.Serializable;

import finalprojectjb.moviecollection.User_Interface_Componets.EditPageUI;

public class EditPageActivity extends AppCompatActivity implements EditPageUI.ICallback{


    EditPageUI editPageUI;
    MovieInfo movieInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent=getIntent();
        movieInfo=(MovieInfo)intent.getSerializableExtra("movie");

        editPageUI=new EditPageUI(this, this);
        setContentView(editPageUI);
        editPageUI.UpdateEditPageUI(movieInfo);


    }

    @Override
    public void onPressOk() {

    if(!editPageUI.getTitleText().equals(null)) {
        editPageUI.MovieInfoAccordingToEditPageUI(movieInfo);
        Intent intent = new Intent();
        intent.putExtra("movie", movieInfo);
        setResult(RESULT_OK, intent);
        finish();
    }
    else{
        Toast.makeText(this, "cannot leave Empty Title", Toast.LENGTH_SHORT).show();
    }


    }

    @Override
    public void onPressCancel() {

        finish();
    }
}
