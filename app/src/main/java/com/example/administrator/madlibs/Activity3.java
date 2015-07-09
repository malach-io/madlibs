package com.example.administrator.madlibs;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Activity3 extends ActionBarActivity {
    public final static String STORY_NUM = "int";
    int storyNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_story);

        Intent intent = getIntent();
        String message = intent.getStringExtra("edit_message");

        TextView textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setText(message);
    }

    public void makeNewStory(View view) {
        final Context context = this;
        Intent intent = new Intent(context, Activity2.class);
        Activity2.STORY_NUMBER = Activity2.STORY_NUMBER + 1;
        System.out.println("testInt: " + Activity2.STORY_NUMBER);
        startActivity(intent);
    }
}
