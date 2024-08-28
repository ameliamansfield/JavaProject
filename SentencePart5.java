import java.util.ArrayList;
import java.util.Properties;
import org.ejml.simple.SimpleMatrix;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.*;
import java.util.*;


public class SentencePart5{
  private String text;
  private String author;
  private String timestamp;


  public SentencePart5(String text, String author, String timestamp){
    this.text = text;
    this.author = author;
    this.timestamp = timestamp;
  }

  public void setText(String text){
    this.text = text;
  }

  public String getText(){
    return text;
  }

  public void setAuthor(String author){
    this.author = author;
  }

  public String getAuthor(){
    return author;
  }

  public void setTimestamp(String timestamp){
    this.timestamp = timestamp;
  }

  public String getTimestamp(){
    return timestamp;
  }

  public String toString(){
    return "{author:" + author + ", sentence:\"" + text + "\", timestamp:\"" + timestamp + "\"}";
  }

  public String[] splitSentence() {
    String[] pieces = this.text.split(" ");
    return pieces;
    }

  public String[] splitSentenceBigram() { 
    return null; 
    }

  public boolean keep(String temporalRange) {

    try {
        DateFormat formatter = new SimpleDateFormat("MM dd yyyy");
        Date date = formatter.parse(this.timestamp);
        // System.out.println("current: "+ date);
        Timestamp timeStampDate = new Timestamp(date.getTime());

        String[] range = temporalRange.split("-");

        Date beginningRange = formatter.parse(range[0]);
        Date endRange = formatter.parse(range[1]);
        // System.out.println("beginning: "+ beginningRange);
        // System.out.println("end: "+ endRange);
        Timestamp beginning = new Timestamp(beginningRange.getTime());
        Timestamp end = new Timestamp(endRange.getTime());

        if (timeStampDate.after(beginning) && timeStampDate.before(end)) {
            return true;
        } else {
            return false;
        }

      } catch (ParseException e) {
        System.out.println("Exception :" + e);
        return false;
      }
      

      //getTime() --> makes numbers out of timestamp

  // String[dates] = temporalRange.split("-")



      //if seconds are in range return true
    // if else -> if in range, return true; if not in range return false 
    //return true/false; 
    //compare saved timestamp to range

  }

public static SentencePart5 convertLine(String line){
      String[] pieces = new String[8];
      String basket = "";
      int ctr = 0;
      boolean startQuote = false;
      for(int i = 0; i < line.length(); i++) {
        if (line.charAt(i) == ',' && startQuote == false) {
          pieces[ctr] = basket;
          basket = "";
          ctr++;
        } else if (line.charAt(i) == '"') {
          startQuote = !startQuote;
        } else{
          basket = basket + line.charAt(i);
        }
      }
      pieces[ctr] = basket;

    //   if (pieces.length < 8) {
    //     return null;
    //   }
      String date = pieces[2];
      String username = pieces[4];
      String tweet = pieces[7];
  
      //clean the date from "4/19/2020 0:00" to "April 19 2020"
      //System.out.println(username);
      String [] datePieces = date.split(" ");
      String first = datePieces[0];
      datePieces = first.split("/");
      String month = datePieces[0];

      date = "0" + month + " " + datePieces[1] + " 20" + datePieces[2];
  
      String author = username;
      String text = tweet;
      return new SentencePart5(text, author, date);
    }
    public int getSentiment(){
      try{
          Properties props = new Properties();
          props.setProperty("annotators", "tokenize, ssplit, pos, parse, sentiment");
          StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
          Annotation annotation = pipeline.process(this.text);
          CoreMap sentence = annotation.get(CoreAnnotations.SentencesAnnotation.class).get(0);
          Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
          return RNNCoreAnnotations.getPredictedClass(tree);
      } catch (Exception e){
          return 0;
      }
  }
}