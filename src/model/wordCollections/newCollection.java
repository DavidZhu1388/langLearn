package model.wordCollections;

import javax.swing.*;
import java.io.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class newCollection {

    public String collectionName;
    public ArrayList<String> front;
    public ArrayList<String> back;
    public ArrayList<Integer> familiarCount;
    public File collectionFile;
    public String filePath = "";


    public newCollection (){
        collectionName = "";
        front = new ArrayList<>();
        back = new ArrayList<>();
        familiarCount = new ArrayList<>();
    }

    public void readFile (File collectedFile){
        // reads the file and turns it to an object with arraylists front and back

        //------------------------------------------------------------------// get the collection's name - ".txt"
        StringBuffer buf = new StringBuffer(collectedFile.getName());

        buf.replace(buf.length()-4, buf.length(),""); // remove ".txt" part

        collectionName = buf.toString();

        //------------------------------------------------------------------//

        collectionFile = collectedFile;
        filePath = "src/collections/" + collectionName + ".txt";

        String input;

        if (collectionFile.exists() && collectionFile.length() != 0) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(collectionFile));
                input = reader.readLine(); // reads "front ~~~~ back ~~~~ familiarityCount"

                while (input != null) { // while not at the end of file
                    String[] parts= input.split(" ~~~~ "); // split into parts
                    front.add(parts[0]); // add parts[0] into the front arraylist
                    back.add(parts[1]); // add parts[1] into the back arraylist
                    familiarCount.add(Integer.parseInt(parts[2])); // add parts[2] into the familiarCount arraylist
                    input = reader.readLine(); // repeat if necessary
                }

            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("File " + collectionFile + " is not found or is empty");
        }

    }

    public boolean editCollectionName(String nameChanged){
        collectionName = nameChanged;

        // Replace the file path with path of the directory
        File rename = new File("src/collections/"+nameChanged+".txt");

        // store the return value of the method
        boolean isChanged = collectionFile.renameTo(rename);

        // if the method return true then if block is executed
        if (rename.exists()) {
            if (!isChanged) {
                System.out.println("Operation Failed");
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Collection name already taken, please try a new one");
        }
        return isChanged;

    }

    public void editCollectionWord(int wordIndex, String newFront, String newBack) throws IOException {
        front.set(wordIndex, newFront);
        back.set(wordIndex, newBack);
        int famCount = familiarCount.get(wordIndex);

        File inputFile = new File(filePath);
        File tempFile = new File("src/collections/myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;
        int count = 0;

        while ((currentLine = reader.readLine()) != null) {
            if (count == wordIndex) {
                writer.write(newFront + " ~~~~ " + newBack + " ~~~~ " + famCount);
                writer.newLine();
                count++;
            } else {
                writer.write(currentLine);
                writer.newLine();
                count++;
            }
        }
        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);

    }

    public void addCollectionWord(String newFront, String newBack){
        front.add(newFront);
        back.add(newBack);

        try {
            BufferedWriter myWriter = new BufferedWriter(new FileWriter(filePath));
            for (int i = 0; i < front.size()-1; i++) {
                myWriter.write( front.get(i) + " ~~~~ " + back.get(i) + " ~~~~ " + familiarCount.get(i));
                myWriter.newLine();
            }
            myWriter.write( newFront + " ~~~~ " + newBack + " ~~~~ " + 10);
            myWriter.close();

        } catch (IOException error) {
            System.out.println("An error occurred.");
            error.printStackTrace();
        }

    }

    public void deleteCollectionWord(int wordIndex) throws IOException {

        front.remove(wordIndex);
        back.remove(wordIndex);

        File inputFile = new File(filePath);
        File tempFile = new File("src/collections/myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;
        int count = 0;

        while ((currentLine = reader.readLine()) != null) {
            if (count == wordIndex) {
                count++;
            } else {
                writer.write(currentLine);
                writer.newLine();
                count++;
            }
        }
        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);

    }

    public void editFamiliarCount(int wordIndex, int value) throws IOException {
        int newCount = familiarCount.get(wordIndex)+value;

        File inputFile = new File(filePath);
        File tempFile = new File("src/collections/myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;
        int count = 0;

        while ((currentLine = reader.readLine()) != null) {
            if (count == wordIndex) {
                writer.write(front.get(wordIndex) + " ~~~~ " + back.get(wordIndex) + " ~~~~ " + newCount);
                writer.newLine();
                count++;
            } else {
                writer.write(currentLine);
                writer.newLine();
                count++;
            }
        }
        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }
}
