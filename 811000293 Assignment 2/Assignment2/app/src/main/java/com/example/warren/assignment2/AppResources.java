package com.example.warren.assignment2;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Warren on 3/21/2015.
 * This class handles all Java related resources much like the res folder of the android project.
 * All resources are managed here to keep data away from logic. The file is written and reaed from here.
 * It is a singleton class to ensure consistency over various threads. All lesson, quiz and user data
 * is managed from here.
 */
public class AppResources {
    private static AppResources ar;
    public ArrayList<User> users;
    public ArrayList<Lesson> lessons;
    public ArrayList<Question> questions;
    private Context context;

    private AppResources(Context app){
        context = app;
        lessons = new ArrayList<Lesson>();
        questions = new ArrayList<Question>();
        popLessons();
        popQuestions();
        load();
    }

    public void updateScore(String user, int score){
        for(int x = 0;x < users.size();x++){
            if(users.get(x).name.equals(user)){
                users.get(x).score = score;
            }
        }
        save();
    }

    public static synchronized AppResources getInstance(Context app){
        if(ar==null){

            ar = new AppResources(app);
        }

        return ar;
    }

//    public static synchronized AppResources getInstance(){
//        if(ar==null){
//            ar = new AppResources(null);
//            //return null;
//        }
//
//        return ar;
//    }


    private void load(){
        File file = new File(context.getFilesDir(),"/quiz.dat");

        if(!file.exists()){
            users = new ArrayList<User>();
            save();
            Log.d("file","new file created!");
            //return;
        }

        FileInputStream fin = null;
        try {
            fin = new FileInputStream(context.getFilesDir()+"/quiz.dat");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectInputStream ois = null;
        try {

            ois = new ObjectInputStream(fin);
            users = (ArrayList<User>) ois.readObject();
            Log.d("load","success");
        } catch (Exception e) {
            e.printStackTrace();
            users = new ArrayList<User>();
            Log.d("load","fail");
        }


    }

    private void save(){
        try{
            Log.d("filepath",context.getFilesDir()+"/quiz.dat");
            FileOutputStream fout = new FileOutputStream(context.getFilesDir()+"/quiz.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(users);
            oos.close();
            System.out.println("Done");

        }catch(Exception ex){
            ex.printStackTrace();
            Log.d("save","fail");
        }

        Log.d("save","success");
    }

    public boolean canAddUser(){
        if(users.size()>=5)return false;
        return true;
    }

    public void addUser(User newUser){
        users.add(newUser);
        save();
        Log.d("user","added");
    }

    public boolean hasUsers() {
        if(users.size()>0)return true;

        return false;
    }

    public ArrayList<String> getUsers(){

        ArrayList<String> u = new ArrayList<String>();

        for(int x = 0;x < users.size();x++) u.add(users.get(x).name);

        return u;
    }

    public boolean userExists(String newUser){
        for(int x = 0;x < users.size();x++){
            if(users.get(x).name.equals(newUser))return true;
        }

        return false;
    }

    public String getUsernameFromPosition(int i){
        if(users.size()>0) return users.get(i).name;
        return null;
    }

    //this is where lessons are defined/hard coded.
    private void popLessons() {
        Lesson l;
        String s;

        //intro
        l = new Lesson("Intro");
        s = "\"Android is a mobile operating system (OS) based on the Linux kernel and currently developed by Google. With a user interface based on direct manipulation, Android is designed primarily for touchscreen mobile devices such as smartphones and tablet computers, with specialized user interfaces for televisions (Android TV), cars (Android Auto), and wrist watches (Android Wear).\" - Wikipedia";

        l.addPage(new Page(s,R.raw.android_logo));

        s = new String();
        s="The OS uses touch inputs that loosely correspond to real-world actions, like swiping, tapping, pinching, and reverse pinching to manipulate on-screen objects, and a virtual keyboard.";
        l.addPage(new Page(s,R.raw.android_devices));

        s = new String();
        s="To make apps, we make use of the Android Studio IDE which provides all the tools necessary for making high quality applications. The graphical widgets and layouts that will be explored in these tutorials are called views. These views allow developers to easily implement a wide range of functionality in applications.\nNB: Each application runs within an Application Context. This is the environment in which the application is given by the system for execution";
        l.addPage(new Page(s,R.raw.studio_logo));

        lessons.add(l);

        //Toast
        l = new Lesson("Toast");
        s = new String();
        s="A Toast is a messaging mechanism that overlays messages on the screen of the Android device for a period of time specified by the developer. It is usually triggered upon the occurrence of some event.";
        l.addPage(new Page(s,-1));

        s = new String();
        s="These messages can be used to give the user information or to display debugging information during testing.";
        l.addPage(new Page(s,R.raw.toast2));

        s = new String();
        s="To create and display a Toast that says 'This is a Toast message', the following Java code must be written and executed:\n\nToast.makeText(getApplicationContext(),\"This is a Toast message\",Toast.LENGTH_SHORT).show;";
        l.addPage(new Page(s,R.raw.toast1));

        lessons.add(l);

        //ImageView
        l = new Lesson("ImageView");
        s = new String();
        s="An ImageView widget is used to display an image on the screen of the android device.";
        l.addPage(new Page(s,R.raw.iv1));

        s = new String();
        s="It allows both local and external images to be drawn onto the screen.";
        l.addPage(new Page(s,R.raw.iv2));

        s = new String();
        s="A local image, once placed into the resources folder of the project, can be set by calling the method 'imageView.setImageResource(R.raw.some_image1);'.";
        l.addPage(new Page(s,-1));

        s = new String();
        s="An external image may be loaded from online by using imageView.setImageURI(\"www.example.com/some_image.png\");";
        l.addPage(new Page(s,-1));

        lessons.add(l);

        //ListView
        l = new Lesson("ListView");
        s = new String();
        s="ListViews are used to display lists of selectable items on the screen.";
        l.addPage(new Page(s,R.raw.lv1));

        s = new String();
        s="In order to populate this list, some array must be available. This can be either in the form of a dynamic array in java code or a static array in the strings.xml file in the resource folder of the project.";
        l.addPage(new Page(s,-1));

        s = new String();
        s="Once the arrays are made available, the must be bound to an ArrayAdapter. This ArrayAdapter is then used as an input to the list.";
        l.addPage(new Page(s,R.raw.lv2));

        s = new String();
        s="If the array is in the java code, the array adapter may be declared as:\n\n String [] array ={\"Item 1\",\"Item 2\"};\nArrayAdapter aa = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1, array);";

        l.addPage(new Page(s,R.raw.lv3));

        s = new String();
        s="Alternatively if it is declared in strings.xml as:\n\n <string-array name=\"items\">\n\t<item>item 1</item>\n\t<item> item 2</item>\n</string-array>\n\n Then the adapter may be declared as:\n\n ArrayAdapter aa = ArrayAdapter.createFromResource(getApplicationContext(),android.R.layout.simple_list_item_1,R.string.items);";
        l.addPage(new Page(s,-1));

        s = new String();
        s="The input 'android.R.layout.simple_list_item_1' is a predefined layout provided by the Android API to describe how a simple ListView should look.";
        l.addPage(new Page(s,-1));

        s = new String();
        s="Then, the ListView has to be pulled into the Java:\n\n ListView lv = (ListView) findViewById(R.id.listView1);\n\nThen the adapter must be bound to the view:\n\n lv.setAdapter(aa);";
        l.addPage(new Page(s,-1));

        s = new String();
        s="The listener must now be set if it is the intention that some code is run when an item is clicked. This is done by implementing the OnItemClickListener class and setting an instance of it as the listener:\n\nlv.setOnItemClickListener(new MyOnItemClickListener());\n\nThe ListView is now ready to be used.";
        l.addPage(new Page(s,R.raw.lv4));

        lessons.add(l);

        //EditText
        l = new Lesson("EditText");
        s = new String();
        s="The EditText widget allows the user to input information via the onscreen keyboard.";
        l.addPage(new Page(s,R.raw.et1));

        s = new String();
        s="The format of this input(eg. Name, numbers, email etc), can be set to easily have access to basic validation tools like ensuring that e-mail addresses are in the form 'me@mail.com' and that password fields are not visible as plain text.";
        l.addPage(new Page(s,-1));

        s = new String();
        s="Upon triggering some event, like a button click, the information can be read and processed by code written by the developer.";
        l.addPage(new Page(s,-1));

        lessons.add(l);

        //Button
        l = new Lesson("Button");
        s = new String();
        s="Button widgets give the user buttons that can be clicked.";
        l.addPage(new Page(s,R.raw.b1));


        s = new String();
        s="These buttons have to be linked to onClick Listeners if anything is to happen when they are clicked";
        l.addPage(new Page(s,-1));

        s = new String();
        s="There are 2 ways to set these onClick Listeners";
        l.addPage(new Page(s,-1));

        s = new String();
        s="One way is to implement the onClickListener class and bind an instance of the class to the button using:\n\n Button b = (Button) findViewById(R.id.button1);\n// get button from xml into java for code execution\nb.setOnClickListener(new MyOnClickListener());";
        l.addPage(new Page(s,R.raw.b3));

        s = new String();
        s="The alternative is to create a method with the signature:\n\npublic void onClick(View v)\n\nWhen the method is created like this, its name can be selected from a list in the graphic editor. ";
        l.addPage(new Page(s,R.raw.b2));

        s = new String();
        s="Once the listener is bound to the button, when the user clicks the button, the linked Java code will be executed.";
        l.addPage(new Page(s,-1));

        lessons.add(l);

        //Spinner
        l = new Lesson("Spinner");
        s = new String();
        s="The spinner widget gives the user a dropdown list of options to choose from.";
        l.addPage(new Page(s,R.raw.s1));

        s = new String();
        s="This widget utilizes the same ArrayAdapter as shown in the ListView Lesson. The Adapter is acquired and set in the same way as the ListView.";
        l.addPage(new Page(s,-1));

        s = new String();
        s="The difference this time is, instead of setting an OnItemClickListener, an OnItemSelectedListener used.";
        l.addPage(new Page(s,-1));

        s = new String();
        s="Also, a different layout is utilized:\n\nandroid.R.layout.simple_spinner_item";
        l.addPage(new Page(s,-1));

        lessons.add(l);
        //Chronometer
        l = new Lesson("Chronometer");
        s = new String();
        s="A chronometer view implements a simple onscreen timer. It extends the TextView Class. It updates its contents automatically.";
        l.addPage(new Page(s,R.raw.cm1));

        s= new String();
        s="To start the timer from zero, set the base using:\n\nchronometer.setBase(SystemClock.elapsedRealtime());\n\nand start it using:\n\nchronometer.start();";
        l.addPage(new Page(s,-1));


        s= new String();
        s="To stop it call:\n\nchronometer.stop();";
        l.addPage(new Page(s,-1));

        s= new String();
        s="In order to periodically run some code, it is necessary to implement an OnChronometerTickListener.";
        l.addPage(new Page(s,-1));

        lessons.add(l);
    }

    //this is where questions are defined/hard coded.
    private void popQuestions(){

        //Question 1
        String s = "What are the ways to link a GUI button to code that will be executed when the button is clicked?";
        ArrayList<String> ans = new ArrayList<String>();
        ans.add("Implement OnClickListener in java class and set an instance of it as the button's listener.\n");
        ans.add("Create a method with signature 'public void onClick(View v)' and set the listener in the graphical editor.\n");
        ans.add("Neither");
        ans.add("Both");

        questions.add(new Question(s,ans,3));


        //Question 2
        s = new String();
        s = "An EditText Widget is primarily used to";
        ans = new ArrayList<String>();
        ans.add("Display text");
        ans.add("Capture text input from user");
        ans.add("Generate random text");
        questions.add(new Question(s,ans,1));

        //Question 3
        s = new String();
        s = "A ListView is used to show a dropdown menu of options for the user to select from.";
        ans = new ArrayList<String>();
        ans.add("True");
        ans.add("False");
        questions.add(new Question(s,ans,1));

        //Question 4
        s = new String();
        s= "Which method in Java can be used to set an ImageView's contents";
        ans = new ArrayList<String>();
        ans.add("setImageURI");
        ans.add("setDisplayPic");
        ans.add("changeImage");
        ans.add("None");
        questions.add(new Question(s,ans,0));

        //Question 5
        s = new String();
        s = "Which of these classes can be used to implement a timer in Android?";
        ans = new ArrayList<String>();
        ans.add("Toast");
        ans.add("Timer");
        ans.add("TimeZone");
        ans.add("Chronometer");
        questions.add(new Question(s,ans,3));
    }


    public Lesson getLesson(String lesson) {

        for(int x = 0; x<lessons.size();x++){
            if(lessons.get(x).title.toLowerCase().equals(lesson)){
                return lessons.get(x);
            }
        }
        return null;
    }

    public int getScore(String string) {
        for(int x = 0;x < users.size();x++){
            if(users.get(x).name.toLowerCase().equals(string.toLowerCase())){
                return users.get(x).score;
            }
        }
        return 0;
    }
}
