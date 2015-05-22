package com.example.warren.assignment2;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MakeNewUser extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_new_user);
        ActionBar ab = getActionBar();
        ab.setTitle("Add User");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_make_new_user, menu);
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

    public void begin(View v){
        EditText et = (EditText) findViewById(R.id.newUserName);
        String s = et.getText().toString();
        if(s.length()<1){
            Toast.makeText(getApplicationContext(),"Enter Username before beginning",Toast.LENGTH_LONG);
            return;
        }

        if(AppResources.getInstance(getApplicationContext()).userExists(s)){
            Toast.makeText(getApplicationContext(),"That User Name is already taken",Toast.LENGTH_LONG);
            return;
        }

        User newUser = new User(s);
        AppResources.getInstance(getApplicationContext()).addUser(newUser);

        SharedPreferences sp = getSharedPreferences("current_user",MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();

        ed.putString("user",s);
        ed.commit();

        startActivity(new Intent(getApplicationContext(),QuizChooser.class));
    }
}
