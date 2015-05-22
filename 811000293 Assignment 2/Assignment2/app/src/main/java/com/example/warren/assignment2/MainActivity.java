package com.example.warren.assignment2;
/*
    Name: Warren O'Connell
    ID: 811000293
    COURSE: COMP3275
    Assignment 2: ElearnDroid

    This is the first activity that is launched and it gives the user options to create a profile
    or login to a previously created account.
 */
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
    private Button newUserBtn,loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppResources.getInstance(getApplicationContext());

        SharedPreferences sp = getSharedPreferences("current_user",MODE_PRIVATE);
        if(!sp.getString("user","logged_out3825").equals("logged_out3825")){
            Intent i = new Intent(getApplicationContext(),QuizChooser.class);
            startActivity(i);
            //finish();
        }

        ActionBar ab = getActionBar();
        ab.setTitle("Main Menu");

        newUserBtn = (Button) findViewById(R.id.newUserBtn);
        loginBtn = (Button) findViewById(R.id.loginBtn);

        if(!AppResources.getInstance(getApplicationContext()).canAddUser()){
            newUserBtn.setVisibility(View.INVISIBLE);
        }

        if(!AppResources.getInstance(getApplicationContext()).hasUsers()){
            loginBtn.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    public void onClickMainActivity(View v){
        if(v==newUserBtn){
            Intent i = new Intent(getApplicationContext(),MakeNewUser.class);
            startActivity(i);
        }
        if(v==loginBtn){
            Intent i = new Intent(getApplicationContext(),Login.class);
            startActivity(i);
        }
    }
}
