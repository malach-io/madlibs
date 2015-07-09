// !YOU MAY WANT TO CHANGE THE PACKAGE BELOW SO THAT IT MATCHES YOUR PROJECT'S PACKAGE!
package com.example.administrator.madlibs;

import java.io.*;
import java.util.*;

public class Story implements Serializable {
    private String text;                 // text of the story
    public List<String> placeholders;   // list of placeholders to fill in
    public List<String> wordList;
    private int filledIn;                // number of placeholders that have been filled in
    {
        // instance initializer; runs before any constructor
        text = "";
        placeholders = new ArrayList<String>();
        wordList = new ArrayList<String>();
        filledIn = 0;
        clear();
    }

    /** constructs a new empty Story */
    public Story() { }

    /** constructs a new Story reading its text from the given Scanner */
//    public Story(Scanner input) { read(input);}

    /** resets the story back to an empty initial state */
    public void clear() {
        text = "";
        placeholders.clear();
        wordList.clear();
        filledIn = 0;
    }

    /** replaces the next unfilled placeholder with the given word */
    public void fillInPlaceholder(String word) {
        if (!isFilledIn()) {
            text = text.replace("<" + filledIn + ">", word);
            filledIn++;
        }
    }

    /** returns the next placeholder such as "adjective",
     *  or empty string if story is completely filled in already */
    public String getNextPlaceholder() {
        if (isFilledIn()) return "";
        else return placeholders.get(filledIn);
    }

    /** returns total number of placeholders in the story */
    public int getPlaceholderCount() { return placeholders.size(); }

    /** returns how many placeholders still need to be filled in */
    public int getPlaceholderRemainingCount() { return placeholders.size() - filledIn; }

    /** returns true if all placeholders have been filled in */
    public boolean isFilledIn() { return filledIn >= placeholders.size(); }


    /** reads initial story text from the given Scanner
//     * @param input*/
    public void read(InputStream raw) {
        Scanner input = new Scanner(raw);
        while (input.hasNext()) {
            String sentence = input.next();
            for (String word: sentence.split(" ")){
                wordList.add(word);
                if (word.startsWith("<")) {
                    // "<plural-noun>" becomes "plural noun"
                    String placeholder = word.substring(1, word.length() - 1).replace("-", " ");
                    placeholders.add(placeholder);
                }else {
                    // a regular word; just concatenate
                    if (!text.isEmpty()) {
                        text += " ";
                    }
                }
                text += word;
            }
        }
    }

    public String listToString(List<String> list) {
        String string = "";
        for (String word: list){
            string += (word + " ");
        }
        return string;
    }

    public void storyMixer(List<String> listV, int indexS, int indexV){
        if (indexS <= wordList.size() - 1){
            if (wordList.get(indexS).startsWith("<")){
                wordList.set(indexS, listV.get(indexV));
                indexS++;
                indexV++;
            }
            else indexS++;
            storyMixer(listV, indexS, indexV);
        }
    }

    /** returns story text */
    public String toString() { return text; }
}