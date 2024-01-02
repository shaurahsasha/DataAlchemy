/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataalchemy;
import java.io.*;
import java.util.*;

/**
 *
 * @author Shaurah Sasha
 */
public class DataLoading {
    
    private final String fileName;
    private String line;
    private List<String[]> arrayList;
    
    public DataLoading(String fileName) {
        this.fileName = fileName;
        this.line = "";
        loadDataset();
    }
    
    private void loadDataset() {
        this.arrayList = new ArrayList<>();
        
        try {
            BufferedReader buffered_reader = new BufferedReader(new FileReader(fileName));
            
            while((line = buffered_reader.readLine()) != null) {
                String[] columns = line.split(",");
                arrayList.add(columns);
            }  
            
            System.out.println("Successfully loaded the dataset into the system!");
            
        } catch (FileNotFoundException e) {
            System.out.println("Data loading was unsuccessful. File was not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Data loading was unsuccessful. Encountered issues with the file input or output.");
            e.printStackTrace();
        }
    }
    
    public List<String[]> getLoadedDataset() {
        return arrayList;
    }
    
    public void getDataset(int lines) {
        int linesCount = 0;
        for(String[] rows: arrayList) { 
            for(String columns: rows) {
                    if (linesCount <= lines) {
                    System.out.printf("%-15s ", columns);     
                } 
            }
            if (linesCount <= lines) {
                System.out.println();    
            }   
            linesCount++;
        }
        System.out.println("Showing " + lines + " rows.");
    }
    
}
