package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class App {

    //entrypoint to app
     public static void main(String[] args) {
         //create an arraylist of words from a given text file
         ArrayList<String> words = readWords("res/words.txt");

         //create a hashmap from the buildhashmap function
         HashMap<String, Integer> wordCounter = buildHashmap(words);

         //write hashmap to an html file
         createHTMLFile(wordCounter);

         //part 2 starts here

         ArrayList<WordFrequency> frequencies = new ArrayList<>();

         //loops through the hashmap, creates new WordFrequency object with hashmap values
         for (String key : wordCounter.keySet()) {
             frequencies.add(new WordFrequency(key, wordCounter.get(key)));
         }

         //sorts hashmap ascending
         Collections.sort(frequencies);

         //write arraylist to html file
         createHTMLFileSorted(frequencies);

         //part 3 starts here

         //read paragraph into an arraylist of seperate words
         ArrayList<String> paragraph = readWords("res/paragraph.txt");
         //build a hashmap out of said arraylist
         HashMap<String, Integer> paragraphMap = buildHashmap(paragraph);

         ArrayList<ParagraphFrequency> paragraphFrequencies = new ArrayList<>();
        //loop through hashmap, making a key-value pair into a ParagraphFrequency object
        for (String key : paragraphMap.keySet()) {
            paragraphFrequencies.add(new ParagraphFrequency(key, paragraphMap.get(key)));
        }

         //sort arraylist of objects
        Collections.sort(paragraphFrequencies);

        //write output to html file
        createParagraphHTML(paragraphFrequencies);
    }

    //readWords is a file that takes a string that points to a file, and returns an arraylist of strings
    //1 create file and arraylist objects
    //2 opens the try statement, creates a buffer reader and checks if its successful or not
    //3 reads a line from the file as a string, regex splits it into an array of individual words
    //4 foreach word in the array, adds the word to the array list in lower case
    //5 reads the next line until there are no more lines to read
    private static ArrayList<String> readWords(String fileName) {
         //1
         File file = new File(fileName);
         ArrayList<String> wordList = new ArrayList<>();

         try {

             FileReader reader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(reader);
             String line = bufferedReader.readLine();

             //2
             while (line != null) {
                 //3
                String[] words = line.split("[ ,.]+");
                //4
                for (String word : words) {
                    wordList.add(word.toLowerCase());
                }
                //5
                line = bufferedReader.readLine();
             }

             bufferedReader.close();

         } catch (IOException e) {
             throw new RuntimeException(e);
         }

        return wordList;
    }

    //buildHashmap takes a list of words and returns a hashmap of said words
    //1 loop through the array
    //2 if the word doesn't exist in the hashmap, add the word plus 1 for count
    //3 if the word does exist, increment the count value by 1
    private static HashMap<String, Integer> buildHashmap(ArrayList<String> words) {
        HashMap<String, Integer> wordCounter = new HashMap<>();

        //1
        for (String word : words) {
            Integer count = wordCounter.get(word);
            //2
            if (count == null) {
                wordCounter.put(word, 1);
            }
            //3
            else {
                wordCounter.put(word, count + 1);
            }
        }

        return wordCounter;
    }

    //takes the hashmap created by the previous functions, and writes it to an html file
    //1 creates a filewriter and a stringbuilder
    //2 appends the boilerplate for the html file
    //3 loop through the String keys
    //4 create a table pair for the key and the value
    //5 close the filewriter
    //6 write the hashmap to console to check values
    private static void createHTMLFile(HashMap<String, Integer> wordCounter) {
        File file = new File("res/words.html");

        try {
            //1
            FileWriter writer = new FileWriter(file);
            StringBuilder builder = new StringBuilder();

            //2
            builder.append("<!DOCTYPE html>");
            builder.append("<html lang=\"en\">");
            builder.append("<head>");
            builder.append("<meta charset=\"UTF-8\">");
            builder.append("<title>Java Assignment</title>");
            builder.append("<link rel=\"stylesheet\" href=\"style.css\">");
            builder.append("</head>");
            builder.append("<body>");


            builder.append("<h1>Word count</h1>");

            builder.append("<table>");
            //3
            for (String key : wordCounter.keySet()) {
                //4
                builder.append("<tr>");
                builder.append("<td>" + key + "</td>");
                builder.append("<td>" + wordCounter.get(key) + "</td>");
                builder.append("</tr>");
            }

            builder.append("</table>");
            builder.append("</body>");
            builder.append("</html>");
            writer.append(builder.toString());
            //5
            writer.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        //6

    }

    //similar to the prevous function:
    //1: create the file with appropriate destination path
    //2: open up a filewriter and stringbuilder, check to make sure no errors occur
    //3: write the needed html boilerplate to the file
    //4: loop through the arraylist, writing the word and its frequency to the file in table format
    //5: write to file, close filewriter
    //6: throw exception, showing stack trace if something went wrong
    private static void createHTMLFileSorted(ArrayList<WordFrequency> frequencies) {
         //1
         File file = new File("res/sortedWords.html");

         //2
         try {
             FileWriter fileWriter = new FileWriter(file);
             StringBuilder builder = new StringBuilder();

             //3
             builder.append("<!DOCTYPE html>");
             builder.append("<html lang=\"en\">");
             builder.append("<head>");
             builder.append("<meta charset=\"UTF-8\">");
             builder.append("<title>Java Assignment</title>");
             builder.append("<link rel=\"stylesheet\" href=\"style.css\">");
             builder.append("</head>");
             builder.append("<body>");


             builder.append("<h1>Word count sorted</h1>");
             builder.append("<table>");
             //4
             for (WordFrequency frequency : frequencies) {
                 builder.append("<tr>");
                 builder.append("<td>" + frequency.getWord() + "</td>");
                 builder.append("<td>" + frequency.getFrequency() + "</td>");
                 builder.append("</tr>");
             }

             builder.append("</table>");
             builder.append("</body>");
             builder.append("</html>");
             //5
             fileWriter.append(builder.toString());
             fileWriter.close();
         }
         //6
         catch (Exception e) {
            throw new RuntimeException();
         }


    }
    //similar to the prevous function:
    //1: create the file with appropriate destination path
    //2: open up a filewriter and stringbuilder, check to make sure no errors occur
    //3: write the needed html boilerplate to the file
    //4: loop through the arraylist, writing the word and its frequency to the file in table format
    //5: write to file, close filewriter
    //6: throw exception, showing stack trace if something went wrong
    public static void createParagraphHTML(ArrayList<ParagraphFrequency> frequencies) {
        //1
        File file = new File("res/sortedParagraphWords.html");

        //2
        try {
            FileWriter fileWriter = new FileWriter(file);
            StringBuilder builder = new StringBuilder();

            //3
            builder.append("<!DOCTYPE html>");
            builder.append("<html lang=\"en\">");
            builder.append("<head>");
            builder.append("<meta charset=\"UTF-8\">");
            builder.append("<title>Java Assignment</title>");
            builder.append("<link rel=\"stylesheet\" href=\"style.css\">");
            builder.append("</head>");
            builder.append("<body>");


            builder.append("<h1>Paragraph sorted</h1>");
            builder.append("<table>");
            //4
            for (ParagraphFrequency frequency : frequencies) {
                builder.append("<tr>");
                builder.append("<td>" + frequency.getWord() + "</td>");
                builder.append("<td>" + frequency.getFrequency() + "</td>");
                builder.append("</tr>");
            }

            builder.append("</table>");
            builder.append("</body>");
            builder.append("</html>");
            //5
            fileWriter.append(builder.toString());
            fileWriter.close();
        }
        //6
        catch (Exception e) {
            throw new RuntimeException();
        }


    }
}
