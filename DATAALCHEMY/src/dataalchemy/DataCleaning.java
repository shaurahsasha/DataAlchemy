/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataalchemy;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 *
 * @author Shaurah Sasha
 */
public class DataCleaning {
    
    private List<String[]> originalArray;
    private List<String[]> cleanedArray;
    private Set setArray;
    
    public DataCleaning(List<String[]> arrayList) {
        this.originalArray = arrayList;
        this.cleanedArray = new ArrayList<>(originalArray);
        findDuplicates();
        imputeZeros();
    }
    
    private void findDuplicates() {
        System.out.println("-- Checking for duplicated rows in the dataset ...");
        this.setArray = new HashSet<>(originalArray);
        if(setArray.size() == originalArray.size()) {
            System.out.println("-> There are no duplicated rows in this dataset.\n");
        } else {
            System.out.println("-> There are duplicated rows in this dataset.\n");
        }
    }
    
    private void imputeZeros() {
        System.out.println("-- Cleaning the dataset ...");
        String cleanedFilePath = "cleanedhousepricing.csv";

        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(cleanedFilePath));

            for(String[] rows: cleanedArray) {      
                StringBuilder rowBuilder = new StringBuilder();
                
                for(String columns: rows) {  
                
                    if (columns.equals("")) {
                        columns = "0";
                    }
                    rowBuilder.append(columns).append(",");
                }
                
                if (rowBuilder.length() > 0) {
                    // Remove the last comma before writing the row
                    out.println(rowBuilder.substring(0, rowBuilder.length() - 1));
                }
            }
            
            out.close();
            System.out.println("-> Successfully saved cleaned data to: " +cleanedFilePath + "!\n");
            
        } catch (IOException e) {
            System.out.println("Problem with file output\n");
        }
    }
    
    public void getCleanedDataset(int lines) {
        DataLoading c = new DataLoading("cleanedhousepricing.csv");
        System.out.println("Cleaned House Pricing Dataset: ");
        c.getDataset(lines);
    }
    
    public List<String[]> getCleanedDataset() {
        DataLoading c = new DataLoading("cleanedhousepricing.csv", false);
        return c.getLoadedDataset();
    }

}
