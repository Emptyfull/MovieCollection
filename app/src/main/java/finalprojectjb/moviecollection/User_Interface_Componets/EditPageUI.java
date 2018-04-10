package finalprojectjb.moviecollection.User_Interface_Componets;

import android.content.Context;


import android.text.InputFilter;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import finalprojectjb.moviecollection.MovieInfo;
import finalprojectjb.moviecollection.MyApp;
import finalprojectjb.moviecollection.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;



public class EditPageUI extends LinearLayout {

    private TagLine title;
    private WebView movieImage;
    private TagLine[] categoryLines;
    private UrlImageLine urlImageLine;
    private EditText Body;
    private ICallback Ic;

    private static final String[] categoryNames={"Release Date","Genres","Rating", "RunTime"};
    private static final String titleTag="subject";
    private static final String okButtonText="OK";
    private static final String cancelButtonText="Cancel";
    private static final String showButtonText="Show";
    private static final String tagUrlText="URL";

    private static final String ImageBaseUrl="https://image.tmdb.org/t/p/w92";

    private static  int TEXTSIZEURL=dipSizeConvert( MyApp.getContext().getResources().getDimension(R.dimen.urlTextSize));
    private static  int TEXTSIZECATEGORY=dipSizeConvert(MyApp.getContext().getResources().getDimension(R.dimen.categoryTextSize));
    private static  int TEXTSIZEOVERVIEW=dipSizeConvert(MyApp.getContext().getResources().getDimension(R.dimen.overViewTextSize));





    public EditPageUI(Context context,ICallback Ic) {
        super(context);
        setInterface(Ic);
        this.setOrientation(VERTICAL);

        title = new TagLine(context);
        title.setTagText(titleTag);
        LinearLayout.LayoutParams titleParam=new LayoutParams(MATCH_PARENT,0);
        titleParam.weight=1;
        title.setLayoutParams(titleParam);
        this.addView(title);

        LinearLayout sectionContainer=sectionContainer(context);
        LinearLayout.LayoutParams sectionContainerParam= new LinearLayout.LayoutParams(MATCH_PARENT,0);
        sectionContainerParam.weight=5;
        sectionContainer.setLayoutParams(sectionContainerParam);
        this.addView(sectionContainer);


        Body=new EditText(context);
        LinearLayout.LayoutParams BodyParam=new LayoutParams(MATCH_PARENT,0);
        BodyParam.weight=3;
        Body.setLayoutParams(BodyParam);
        Body.setTextSize(TEXTSIZEOVERVIEW);
        this.addView(Body);

        LinearLayout activityButtons=activityButtons(context);
        LinearLayout.LayoutParams activityButtonsParam=new LayoutParams(MATCH_PARENT,0);
        activityButtonsParam.weight=1;
        activityButtons.setLayoutParams(activityButtonsParam);
        this.addView(activityButtons);



    }

    private void setInterface(ICallback Ic){

        this.Ic=Ic;


    }

    private LinearLayout sectionContainer(Context context){

        LinearLayout container =new LinearLayout(context);
        container.setOrientation(HORIZONTAL);
        LinearLayout categorySection=categorySection(context);
        LinearLayout.LayoutParams categorySectionParam=new LayoutParams(0,MATCH_PARENT);
        categorySectionParam.weight=1;
        categorySection.setLayoutParams(categorySectionParam);
        container.addView(categorySection);


        LinearLayout imageSection=imageSection(context);
        LinearLayout.LayoutParams imageSectionParam=new LayoutParams(0,MATCH_PARENT);
        imageSectionParam.weight=1;
        imageSection.setLayoutParams(imageSectionParam);
        container.addView(imageSection);

        return container;



    }

    private LinearLayout categorySection(Context context) {

        LinearLayout categorySection = new LinearLayout(context);


        categorySection.setOrientation(VERTICAL);


        LinearLayout.LayoutParams categoryLineParam = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);


        categoryLines=new TagLine[categoryNames.length];

        for (int i = 0; i < categoryNames.length; i++) {

            categoryLines[i]=new TagLine(context);
            categoryLines[i].setTagText(categoryNames[i]);
            categoryLines[i].setTextSize(TEXTSIZECATEGORY);
            categoryLines[i].setLayoutParams(categoryLineParam);
            categorySection.addView(categoryLines[i]);

        }

        return categorySection;

    }

    private LinearLayout imageSection(Context context) {

        LinearLayout imageSection = new LinearLayout(context);
        imageSection.setOrientation(VERTICAL);


        movieImage = new WebView(context);

        LinearLayout.LayoutParams movieImageParam = new LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);

        movieImage.setLayoutParams(movieImageParam);
        imageSection.addView(movieImage);

        urlImageLine=new UrlImageLine(context);
        LinearLayout.LayoutParams urlImageLineParams = new LinearLayout.LayoutParams(WRAP_CONTENT ,WRAP_CONTENT);

        urlImageLine.setLayoutParams(urlImageLineParams);
        imageSection.addView(urlImageLine);

        return imageSection;

    }

    private LinearLayout activityButtons(Context context){

        LinearLayout holder=new LinearLayout(context);

        Button okButton=new Button(context);
        LinearLayout.LayoutParams okButtonParam=new LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
        okButton.setLayoutParams(okButtonParam);
        okButton.setText(okButtonText);
        okButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            Ic.onPressOk();

            }
        });
        holder.addView(okButton);




        Button cancelButton=new Button(context);
        LinearLayout.LayoutParams CancelButtonParam=new LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
        CancelButtonParam.gravity=Gravity.START;
        cancelButton.setLayoutParams(CancelButtonParam);
        cancelButton.setText(cancelButtonText);
        cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Ic.onPressCancel();


            }
        });
        holder.addView(cancelButton);

        return holder;


    }



    public void UpdateEditPageUI(MovieInfo movieinfo){

        if(movieinfo==null) {return;}

        title.setFillBoxText(movieinfo.getMovieName());
        categoryLines[0].setFillBoxText(movieinfo.getReleaseDate());
        categoryLines[1].setFillBoxText(movieinfo.getGenres());
        categoryLines[2].setFillBoxText(""+movieinfo.getRating());
        categoryLines[3].setFillBoxText(""+movieinfo.getRunTime());
        Body.setText(movieinfo.getOverView());
        urlImageLine.setUrlText(movieinfo.getImagePath());
        updateMovieWebView(movieinfo.getImagePath());


    }

    public void MovieInfoAccordingToEditPageUI(MovieInfo movieInfo){

        Toast.makeText(getContext(), title.getFillBoxText()+"FUCK YOU", Toast.LENGTH_SHORT).show();
        movieInfo.setMovieName(title.getFillBoxText());
        movieInfo.setImagePath(urlImageLine.getUrlText());
        movieInfo.setReleaseDate(categoryLines[0].getFillBoxText());
        movieInfo.setGenres(categoryLines[1].getFillBoxText());
        movieInfo.setRating(Integer.valueOf(categoryLines[2].getFillBoxText()));
        movieInfo.setRunTime(Integer.valueOf(categoryLines[3].getFillBoxText()));
        movieInfo.setOverView(Body.getText().toString());

    }

    public String getTitleText(){

        return title.getFillBoxText();



    }


    public void updateMovieWebView(String Movieurl){

        ;

                movieImage.loadUrl(ImageBaseUrl+Movieurl);


    }


    private static int dipSizeConvert(double Width){

        DisplayMetrics displayMetrics= MyApp.getContext().getResources().getDisplayMetrics();

        return (int)(Width/displayMetrics.density);

    }



    private class UrlImageLine extends LinearLayout {

       private TextView urlName;
       private EditText urlText;
       private  Button show;


        public UrlImageLine(Context context) {
            super(context);

            urlName = new TextView(context);
            LinearLayout.LayoutParams urlNameParam = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
            urlName.setLayoutParams(urlNameParam);
            urlName.setText(tagUrlText);
            urlName.setTextSize(TEXTSIZEURL);
            this.addView(urlName);
            urlText = new EditText(context);
            LinearLayout.LayoutParams urlTextParam = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
            urlText.setLayoutParams(urlTextParam);
            urlText.setTextSize(TEXTSIZEURL);
            urlText.setBackground(null);
            urlText.setVisibility(GONE);
            this.addView(urlText);
            show = new Button(context);
            LinearLayout.LayoutParams showParam = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
            show.setText(showButtonText);
            show.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                    String name=urlText.getText().toString();
                    updateMovieWebView(name);
                }
            });

            this.addView(show);
        }

        public void setUrlText(String text){

            urlText.setText(text);

        }

        public String getUrlText(){

            return urlText.getText().toString();


        }
    }

    private class TagLine extends LinearLayout {

        private TextView tag;
        private EditText fillBox;


        public TagLine(Context context) {
            super(context);
            this.setOrientation(HORIZONTAL);

            tag = new TextView(context);
            LinearLayout.LayoutParams CategoryParam = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
            tag.setLayoutParams(CategoryParam);
            this.addView(tag);


            fillBox = new EditText(context);
            LinearLayout.LayoutParams FillBoxParam = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
            fillBox.setLayoutParams(FillBoxParam);
            fillBox.setBackground(null);
            this.addView(fillBox);


        }

        public void setTagText(String Text) {

            tag.setText(Text);

        }

        public void setTextSize(int size){

            tag.setTextSize(size);
            fillBox.setTextSize(size);

        }
        public TextView getTag() {
            return tag;
        }

        public void setFillBoxText(String Text){

            fillBox.setText(Text);


        }

        public String getFillBoxText(){

            return fillBox.getText().toString();
        }


    }

    public interface  ICallback{

        void onPressOk();
        void onPressCancel();


    }

}
