package com.example.administrator.madlibs;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Activity2 extends ActionBarActivity {

    public static int STORY_NUMBER = 0;

    private Story story = new Story();
    private int storyCounter = 0, wordsRemaining;
    private EditText editText;
    private TextView textView3;
    private TextView textView4;
    private static List<String> wordList = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        story.clear();
        wordList.clear();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_form);

        editText = (EditText) findViewById(R.id.edit_message);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);

        TypedArray raw_array = getResources().obtainTypedArray(R.array.raw_array);
        System.out.println("raw_array.length = " + raw_array.length());
        if (STORY_NUMBER <= (raw_array.length() - 1)){
            System.out.println("condition 1: started");
            story.read(getResources().openRawResource(raw_array.getResourceId(STORY_NUMBER, -1)));
            formLay();
        }
        else {
            System.out.println("condition 2: started");
            Context context = this;
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        }
    }

    public void sendMessage(View view) {
        if(wordsRemaining >= 2) {
//            if(story.wordLibs.get(storyCounter).isEmpty()) Toast.makeText(Activity2.this, "empty : ", Toast.LENGTH_SHORT).show();
            storyCounter++;
            wordList.add(editText.getText().toString());
            formLay();
        }
        else {
            wordList.add(editText.getText().toString());
            System.out.println("wordList length:" + wordList.size());
            Context context = this;
            Intent intent = new Intent(context, Activity3.class);
            story.storyMixer(wordList, 0, 0);
            intent.putExtra("edit_message", (story.listToString(story.wordList)));
            startActivity(intent);
        }
    }

    public void formLay() {
        wordsRemaining = story.placeholders.size() - storyCounter;
        editText.setHint(story.placeholders.get(storyCounter));
        textView3.setText("story number: " + (STORY_NUMBER + 1) + "  " + wordsRemaining + " word(s) left");
        textView4.setText("please type a/an " + story.placeholders.get(storyCounter));
        editText.getText().clear();
    }
}

