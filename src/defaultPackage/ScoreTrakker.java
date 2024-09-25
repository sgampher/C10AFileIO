package defaultPackage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ScoreTrakker {
    private ArrayList<Student> studentlist = new ArrayList<>();
    private String[] filelist = {"scores.txt", "badscore.txt", "nofile.txt"};

    /*
     * loadDataFile: takes filename as a parameter, then uses filename to specify file to read in student data
     * then stores student objects in the studentlist ArrayList.

     */
    public void loadDataFile(String inFile) throws FileNotFoundException {
        FileReader reader = null;
        Scanner in = null;
        reader = new FileReader(inFile);
        in = new Scanner(reader);
        String name = "";
        String scoreStr = "";
        
        while (in.hasNext()) {
            try {
                name = in.nextLine();
                scoreStr = in.nextLine(); 
                int score = Integer.parseInt(scoreStr); // implementation found in recommended slack resource
                studentlist.add(new Student(name, score)); 
            } catch (NumberFormatException e) {
                System.out.println("Incorrect format for " + name + " not a valid score: " + scoreStr + '\n');
            }
        }

        in.close();
    }
    
    /*
     * printInOrder: sorts and prints the studentlist ArrayList.
     */
    public void printInOrder() {
        Collections.sort(studentlist); // Sort using Comparable (Student class should implement Comparable)
        System.out.println("Student Score List");
        for (Student student : studentlist) {
            System.out.println(student);
        }
    }
    
    /*
     * processFiles: loops through all files in the filelist array.
     */
    public void processFiles() {
    	for (String file : filelist) {
    		try {
	    		studentlist.clear();
	            loadDataFile(file);
	            if (!studentlist.isEmpty()) {
	                printInOrder();
	                System.out.println();
	            }
    		} catch(FileNotFoundException e) {
               System.out.println("Can't open file: " + file);
    		}
         }
    }

    public static void main(String[] args) {
        ScoreTrakker trakker = new ScoreTrakker();
        trakker.processFiles();
    }
}


