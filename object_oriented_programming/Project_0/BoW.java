import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.function.IntPredicate;
import java.nio.file.Files;
import java.util.Scanner;

public class BoW {
    //NOTE: I used a hashmap because I wanted to preserve the frequency per document in
    //case  I repurpose this for a personal project
        
    private HashMap<String,ArrayList<Integer>> wordMap = new HashMap<String,ArrayList<Integer>>();
    private ArrayList<File> FileList = new ArrayList<File>();
    private Scanner prompt = new Scanner(System.in);

    private void validateInput(File fileName){
        if (fileName.isFile()) {
            if (!FileList.contains(fileName)){
                FileList.add(fileName);
                expand(fileName);
            } else {
                System.out.println(fileName.toString()+ " has already been added, please try different one");
            }
        } else {
            System.out.println(fileName.toString()+" is not a file name, please try a different one");
        }
    }
   private void mainMenu(){
        System.out.println("Please select an operation from the list below:");
        System.out.println("\t\t1.Expand\n\t\t2.Print term frequencies\n\t\t3.Print inverse document frequencies\n\t\t4.Exit\n");
        String userChoice=prompt.nextLine().strip();
        if (userChoice.equals("1")||userChoice.toLowerCase().equals("expand")) {
            System.out.println("Please enter a File to be added to the Bag of Words");
            validateInput(new File (prompt.nextLine()));
        }
        else if (userChoice.equals("2")||userChoice.toLowerCase().contains("term")){
            if (FileList.isEmpty())
                System.out.println("term frequencies are not currently available");
            else
                printTermFrequencies();
        }
        else if (userChoice.equals("3")||userChoice.toLowerCase().contains("inverse")){
                 if (FileList.isEmpty())
                       System.out.println("term frequencies are not currently available");
                   else
                       printInverseDocumentFrequencies();
           }
           else if (userChoice.equals("4")||userChoice.toLowerCase().equals("exit"))
               System.exit(0);
           else
               System.out.println("Not a valid selection, please try again");
    }

    public void expand(File fileName){
        if (!wordMap.isEmpty())
            wordMap.forEach( (word,counterList) -> counterList.add(0));
            try {
                for (String word : Files.readString(fileName.toPath()).split("[^\\w\']")) {//god regexes are beautiful
                    if (!word.isBlank()) {
                        wordMap.putIfAbsent(word,new ArrayList<Integer>(Collections.nCopies(FileList.size(),0)));
                        wordMap.get(word).set( FileList.size()-1 , wordMap.get(word).get(FileList.size()-1)+1 );//this is why I hate java
                    }
                }
            } catch (IOException e) {
                System.out.println("An I/O exception has occured. please try again");
            }
     }
    public void printTermFrequencies(){
       int sum;
       System.out.println("Here are the term Frequencies so far");
       for (String word : wordMap.keySet()) {
           sum=wordMap.get(word).stream().mapToInt(Integer::intValue).sum();
           System.out.println(word + " : " + String.valueOf(sum));
       }
   }
    public void printInverseDocumentFrequencies() {
        long appearanceCount=0;
        IntPredicate notZero= (x) -> x > 0;
        System.out.println("Here are the inverse Document Frequencies so far");
        for (String word : wordMap.keySet()){
            appearanceCount=wordMap.get(word).stream().mapToInt(i -> i).filter(notZero).count();
            System.out.println(word + " : "+ String.valueOf(Math.log(Double.valueOf(FileList.size())/Double.valueOf(appearanceCount))));
        }
    }
    public void mainLoop(){
        if (FileList.isEmpty()) {
          System.out.println("Please enter a file name to initialize the bag of words");
          validateInput(new File(prompt.nextLine()));
        }
        do {
            mainMenu();
        } while (true);
    }

    BoW(String fileName) {
        validateInput(new File(fileName));
        mainLoop();
    }
    BoW(){
        mainLoop();
    }
    public static void main(String [] args){
        new BoW();
    }
    
}
