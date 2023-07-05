package wordCollections;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class collectionData {
    public static ArrayList<newCollection> collectionList;
    
    public collectionData(){
        collectionList = new ArrayList<>();
    }

    public static void collectionCollect() {
        collectionList.clear();
        File folder = new File("src/collections");
        File[] listOfFiles = folder.listFiles();


        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];

            if (file.isFile() && file.getName().endsWith(".txt")) {
                newCollection collectionidv = new newCollection();
                collectionidv.readFile(file);
                collectionList.add(collectionidv);
            }

        }

        selectionSort(collectionList);
    }

    public static void addCollection(String name, String frontInput, String backInput){
        try {
            File myObj = new File("src/collections/" + name + ".txt");
            if (myObj.createNewFile()) {
                BufferedWriter myWriter = new BufferedWriter(new FileWriter(myObj));
                myWriter.write(frontInput + " ~~~~ " + backInput + " ~~~~ " + 10);
                myWriter.close();
            } else {
                JOptionPane.showMessageDialog(null, "File name already exists.");
            }
        } catch (IOException error) {
            System.out.println("An error occurred.");
            error.printStackTrace();
        }
    }

    public static void deleteCollection(int colIndex){
        collectionList.get(colIndex).collectionFile.delete();
        collectionList.remove(colIndex);
    }

    public static ArrayList<newCollection> listCollections (){
        ArrayList<newCollection> list = new ArrayList<>();
        for (int i = 0; i < collectionList.size(); i++) {
            list.add(collectionList.get(i)) ;
        }
        return list;
    }

    static void selectionSort(ArrayList<newCollection> arr) {
        // One by one move boundary of unsorted subarray
        // Time complexity O(n^2)
        for (int i = 0; i < arr.size()-1; i++) {
            // Find the minimum element in unsorted array
            int min_index = i;
            int comp = 0;
            for (int j = i+1; j < arr.size(); j++) {
                comp = compareTo(arr.get(j), arr.get(min_index));
                if (comp < 0){ // if arr.get(j) is "smaller" than arr.get(min_index)
                    min_index = j;
                }
            }
            // Swap the found minimum element with the first element
            Collections.swap(arr, i, min_index);

        }
    }


    private static int compareTo(newCollection col1, newCollection col2) {
        return col1.collectionName.toLowerCase().compareTo(col2.collectionName.toLowerCase());
    }


}
