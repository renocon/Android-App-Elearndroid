package com.example.warren.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getActionBar().setTitle("Choose User");

        ArrayAdapter ad = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,AppResources.getInstance(getApplicationContext()).getUsers());

        ListView lv = (ListView) findViewById(R.id.userList);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(new LoginListener());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public class LoginListener implements AdapterView.OnItemClickListener{


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_LONG);
            SharedPreferences sp = getSharedPreferences("current_user",MODE_PRIVATE);
            SharedPreferences.Editor ed = sp.edit();
            ed.putString("user",AppResources.getInstance(getApplicationContext()).getUsernameFromPosition(position));
            ed.commit();
            //finish();
            startActivity(new Intent(getApplicationContext(),QuizChooser.class));
        }
    }
}
