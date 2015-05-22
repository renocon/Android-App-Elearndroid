package com.example.warren.assignment2;
/*
    This class, once a lesson is selected, allows the user to navigate through its pages.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;


public class LessonViewer extends Activity {

    private Lesson lesson;
    private ScrollView sv;
    private TextView textarea;
    private ImageView image;
    private Button back,next;
    private int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_viewer);

        lesson = AppResources.getInstance(getApplicationContext()).getLesson(getIntent().getStringExtra("lesson"));
        if(lesson.pages.size()<1){
            finish();
            return;
        }
        getActionBar().setTitle(lesson.title.toUpperCase());
        sv = (ScrollView) findViewById(R.id.scrollView);


        textarea = (TextView) findViewById(R.id.pageText);
        image = (ImageView) findViewById(R.id.pageImage);
        back = (Button) findViewById(R.id.backBtn);
        next = (Button) findViewById(R.id.nextBtn);
        page = 0;
        //image.setScaleX(1);
        //image.setScaleType(ImageView.ScaleType.FIT_START);
        //image.setMaxHeight(150);
        //image.setMaxHeight(150);
        //image.setScaleX(1);
        //image.getMaxWidth();
        //image.setS
        paintPage();
    }

    //puts appropriate page data on screen
    private void paintPage() {
        if(page == 0){
            back.setVisibility(View.INVISIBLE);
        }else{
            back.setVisibility(View.VISIBLE);
        }

        if(page == lesson.pages.size()-1){
            next.setText("Finish!");
        } else{
            next.setText("Next ->");
        }

        textarea.setText(lesson.pages.get(page).txt);
        if(lesson.pages.get(page).img!=-1){
            image.setVisibility(View.VISIBLE);
            image.setImageResource(lesson.pages.get(page).img);
        } else image.setVisibility(View.INVISIBLE);
        sv.scrollTo(0,0);
    }

    public void nav(View v){
        if(v==back){
            if(page>0) page--;
        }
        if(v==next){
            if(page<lesson.pages.size()-1) page++;
            else {
                finish();
                return;
            }
        }

        paintPage();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lesson_viewer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
