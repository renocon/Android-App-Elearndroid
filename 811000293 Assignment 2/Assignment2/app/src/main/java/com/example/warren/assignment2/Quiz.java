package com.example.warren.assignment2;
/*
    This Activity serves the questions to the user and allows them to answer the questions while
    maintaining their score.
 */
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;


public class Quiz extends Activity {

    private ArrayList<Question> qs;
    private int cq,score;
    private RadioButton a,b,c,d;
    private ArrayList<RadioButton> check;
    private TextView tv;
    private Button btn;
    private Question q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getActionBar().setTitle("Quiz");
        qs = AppResources.getInstance(getApplicationContext()).questions;
        cq = 0;
        score = 0;
        a=(RadioButton) findViewById(R.id.a);
        b=(RadioButton) findViewById(R.id.b);
        c=(RadioButton) findViewById(R.id.c);
        d=(RadioButton) findViewById(R.id.d);
        tv = (TextView) findViewById(R.id.questionText);
        btn = (Button) findViewById(R.id.quizNextBtn);
        //LinearLayout ll = (LinearLayout) findViewById(R.id.mainLin);
        //ll.removeAllViews();

        check = new ArrayList<RadioButton>();
        check.add(a);
        check.add(b);
        check.add(c);
        check.add(d);
        paintQuestion();
    }

    //Puts the question on screen and determines the option to be presented to the user
    public void paintQuestion(){
        q = qs.get(cq);
        int len = q.a.size();
        a.setChecked(true);
        if(len<2 || len>4){
            a.setVisibility(View.INVISIBLE);
            b.setVisibility(View.INVISIBLE);
            c.setVisibility(View.INVISIBLE);
            d.setVisibility(View.INVISIBLE);
            tv.setText("Invalid Question Format. Please Restart App with correct format");
            return;
        }else if(len==2){
            a.setVisibility(View.VISIBLE);
            a.setText(q.a.get(0));
            b.setVisibility(View.VISIBLE);
            b.setText(q.a.get(1));
            c.setVisibility(View.INVISIBLE);
            d.setVisibility(View.INVISIBLE);
        }else if(len==3){
            a.setVisibility(View.VISIBLE);
            a.setText(q.a.get(0));
            b.setVisibility(View.VISIBLE);
            b.setText(q.a.get(1));
            c.setVisibility(View.VISIBLE);
            c.setText(q.a.get(2));
            d.setVisibility(View.INVISIBLE);
        }else if(len==4){
            a.setVisibility(View.VISIBLE);
            a.setText(q.a.get(0));
            b.setVisibility(View.VISIBLE);
            b.setText(q.a.get(1));
            c.setVisibility(View.VISIBLE);
            c.setText(q.a.get(2));
            d.setVisibility(View.VISIBLE);
            d.setText(q.a.get(3));
        }

        len = qs.size();

        if(cq== len-1){
            btn.setText("Finish!");
        }else{
            btn.setText("Next ->");
        }

        tv.setText(q.q);

        getActionBar().setTitle("Q: "+(cq+1)+" Score: "+ score);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
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

    public void submit(View v){
        if(check.get(q.correct).isChecked()) score++;
        if(cq==qs.size()-1){
            AppResources.getInstance(getApplicationContext()).updateScore(getSharedPreferences("current_user",MODE_PRIVATE).getString("user","logged_out3825"),score);
            //finish();
            startActivity(new Intent(getApplicationContext(),QuizChooser.class));
            return;
        }
        cq++;
        paintQuestion();
    }
}
