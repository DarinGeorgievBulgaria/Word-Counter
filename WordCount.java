//package com.company;


import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * This class processes the text.
 * @author Darin Georgiev
 */
public class WordCount {

   /**
    *This method Loops through the text and counts the appearance of a word in each sentence.
    * @param fileNames - is an ArrayList which stores the texts
    * @return - returns the finalMap which holds hold "fileNames" as key and "map as value
    *          The "map" will have the "word" as key and a "list of integers" as value.
    *          The "list of integers" will have the count of the word in each sentence.
    * @throws IOException
    */
  public  LinkedHashMap<String, LinkedHashMap<String, List<Integer>>> count(ArrayList<String> fileNames)
          throws IOException {

     LinkedHashMap<String, LinkedHashMap<String, List<Integer>>> finalMap = new LinkedHashMap<>();

     /*
     This loop will iterate through different files
      */
     for (String fileName : fileNames) {

        /* This will hold the current sentence count
         0 is sentence 1, 1 is sentence 2, so on */
        int sentenceCount = 0;

        /*
         The temporary map to store "word" as key and "list of integers" as value.
		"list of integers" represents the count of word in sentences.
         */
        LinkedHashMap<String, List<Integer>> map = new LinkedHashMap<>();

        /*
        This is the temporary list to hold the count of word in sentences
         */
        ArrayList<Integer> listWord = new ArrayList<>();

        /*
        Reading the file.
         */
        InputStream in = ClassLoader.getSystemResourceAsStream(fileName);
        Scanner scan = new Scanner(in);

        /*
        String to hold file's text.
         */
        String str = "";

        /*
        While loop to store all the text from the file in the string.
         */
        while (scan.hasNextLine()) {
           str = str + scan.nextLine(); // whole text to string
        }
        str = str.toLowerCase(); // Converting the text to Lower Case
        str = str.replaceAll("[!?.]+", " ? "); // Replaces signs for end of sentence with "?" just to make it easier
        String[] wordsArray = str.trim().split("[\\p{Punct}\\s+&&[^?]]"); //Makes every word, space, sign a new element of the String array
        String[] numSentences = str.trim().split("[?]"); //Another array where each sentence is another element of the array.

        /*
        Looping thorough all the words
         */
        for (String word : wordsArray) {

           // Condition will pass if word is not a Q, not a space and not null
           if (!word.equals("?") && !word.matches("\\s+") && !word.equals("")) {

              //Creating a list of integers
              listWord = new ArrayList<Integer>();

              //Adds a new zero for each sentence
              for (int i = 0; i < numSentences.length; i++) {
                 listWord.add(0);
              }
              // Adding the word and the initial list in the map
              map.put(word, listWord);
           }

        }
        /*
        Looping thorough all the words
         */
        for (String word : wordsArray) {
           // Condition will pass if word is not a space and not null
           if (!word.matches("\\s+") && !word.equals("")) {

              // As the word ? is found, it indicates the new line so incrementing the sentence count
              if (word.equals("?")) {
                 sentenceCount = sentenceCount + 1;
              }
              else {
                 // Updating the list of integers. As word is found we are incrementing the value in the list of integer with +1
                 map.get(word).set(sentenceCount, map.get(word).get(sentenceCount) + 1);
              }
           }
        }
        // Putting the filename and prepared map
        finalMap.put(fileName, map);
        scan.close();
        in.close();
     }
     return finalMap;
  }

}


