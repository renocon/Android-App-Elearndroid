package com.example.warren.assignment2;
/*
    This activity lets the user choose a tutorial or logout or the quiz.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class QuizChooser extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_chooser);
        SharedPreferences sp = getSharedPreferences("current_user",MODE_PRIVATE);
        getActionBar().setTitle(sp.getString("user","logged_out3825")+" Score: "+AppResources.getInstance(getApplicationContext()).getScore(sp.getString("user","logged_out3825")));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz_chooser, menu);
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

    public void logout(View v){

        //Toast.makeText(getApplicationContext(),"button: "+s,Toast.LENGTH_LONG).show();
        SharedPreferences sp = getSharedPreferences("current_user",MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("user","logged_out3825");
        ed.commit();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    public void lessons(View v){
        Button b = (Button) v;
        String s = b.getText().toString();
        Intent i = new Intent(getApplicationContext(),LessonViewer.class);
        i.putExtra("lesson",s.toLowerCase());
        startActivity(i);
    }

    public void quiz(View v){
        startActivity(new Intent(getApplicationContext(),Quiz.class));
    }

}
