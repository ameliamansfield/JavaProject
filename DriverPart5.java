import java.io.*;
import java.util.*;
import java.util.ArrayList;

// you may need some imports here!
public class DriverPart5{


    public static ArrayList<SentencePart5> readTwitterData(String path){

        ArrayList<SentencePart5> sentences = new ArrayList<SentencePart5>();
    
        try{
          //open the csv file for reading
          File file = new File(path);
          try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = null;

              reader.readLine();
   
              //loop through each line in the csv
              while ((line = reader.readLine()) != null){
                // System.out.println("line  "+line);
                sentences.add(SentencePart5.convertLine(line));
              }
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    
        return sentences;
      }



    public static double averageSentiment(ArrayList<SentencePart5> list) {
        double average = 0.0;
        double total = 0.0;
        for(int i = 0; i < 100 && i < list.size(); i++) {
            total += list.get(i).getSentiment();
        }
        average = total/list.size();
            return average;
    }

    public static void main(String[] args) {


    String path = "covid_10K.csv";
    ArrayList<SentencePart5> result = readTwitterData(path);
    double avg = averageSentiment(result);

    for(int i = 1; i < result.size(); ++i){
        if (result.get(i) == null) {
            // System.out.println();
        } else {
            int sentiment = result.get(i).getSentiment();
            // System.out.println(Integer.toString(sentiment));
        }
    }

    // System.out.println(avg);

    // read full csv
    // filter apr
    // filter "result"
    // define a string that is a range that follows the same format on the example 
    // string passed into keep method 

    ArrayList<SentencePart5> april = new ArrayList<>();
    ArrayList<SentencePart5> june = new ArrayList<>();



    String rangeApr = "04 19 2020-04 30 2020";
    String rangeJun = "06 01 2020-06 20 2020";
    ArrayList<SentencePart5> resultsInRange = new ArrayList<SentencePart5>(); 

    // define sent -> inside for loop 
    // if in range, 
    for (int i = 0; i < result.size(); i++) {
        SentencePart5 sent = result.get(i);
        if (sent.keep(rangeApr) == true) {
            april.add(sent);
            }
        else if (sent.keep(rangeJun) == true) {
            //System.out.println("***************** here ********************************");
            june.add(sent);
        }

            //if in april, add to april arraylist
            //if in june, add to june arraylist
            

            // results.length
            // only one for/if, not one for and 2 ifs
        
    }

    double sentimentsApril = averageSentiment(april);
    double sentimentsJune = averageSentiment(june);

    System.out.println("April " + sentimentsApril);
    System.out.println("June " + sentimentsJune);

    // filter june

    }
}

// java.lang.NullPointerException
// 	at SentencePart5.convertLine(SentencePart5.java:136)
// 	at DriverPart5.readTwitterData(DriverPart5.java:23)
// 	at DriverPart5.main(DriverPart5.java:48)