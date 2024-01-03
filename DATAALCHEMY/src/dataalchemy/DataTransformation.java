/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataalchemy;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

/**
 *
 * @author Shaurah Sasha
 */
public class DataTransformation {
    
    private List<String[]> cleanedArray;
    private List<String[]> transformedArray;
    
    public DataTransformation(List<String[]> arrayList) {
        this.cleanedArray = arrayList;
        this.transformedArray = new ArrayList<>(cleanedArray);
        oneHotEncoding();
        normalization();
    }
    
    private void oneHotEncoding() {
        
        System.out.println("-- Transforming the data ...");
        
        String transformedFilePath = "transformedhousepricing.csv";
        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(transformedFilePath));

            for(String[] rows: transformedArray) {      
                StringBuilder rowBuilder = new StringBuilder();
                
                for(String columns: rows) {  
                
                    if (columns.equals("yes")) {
                        columns = "1";
                    } else if (columns.equals("no")) {
                        columns = "0";
                    }
                    
                    if (columns.equals("furnished")) {
                        columns = "1";
                    } else if (columns.equals("semi-furnished")) {
                        columns = "0";
                    } else if (columns.equals("unfurnished")) {
                        columns = "-1";
                    }
                    
                    rowBuilder.append(columns).append(",");
                }
                
                if (rowBuilder.length() > 0) {
                    // Remove the last comma before writing the row
                    out.println(rowBuilder.substring(0, rowBuilder.length() - 1));
                }
            }
            out.close(); 
            System.out.println("-> Successfully saved transformed data to: " + transformedFilePath + "!\n");

        } catch (IOException e) {
            System.out.println("-> Encountered problems while encoding the data.\n");
            e.printStackTrace();
        }
    }
    
    private void normalization() {
        
        /**
         * Area
         */
        int indexArea = 1;
        normalization(indexArea);
        
        /**
         * Bedrooms
         */
        int indexBedrooms = 2;
        normalization(indexBedrooms);
        
        /**
         * Bathrooms
         */
        int indexBathrooms = 3;
        normalization(indexBathrooms);
        
        /**
         * Stories
         */
        int indexStories = 4;
        normalization(indexStories);
        
        /**
         * Parking
         */
        int indexParkings = 10;
        normalization(indexParkings);            
    }
    
    private void normalization(int index) {
        
        DataLoading c = new DataLoading("transformedhousepricing.csv", false);
        List<String[]> arrayList = c.getLoadedDataset();
                
        List<String> strArray = getColumnArray(arrayList, index);
        List<Integer> intArray = strToInteger(strArray);
        int min = findMin(intArray);
        int max = findMax(intArray);
        
        String transformedFilePath = "transformedhousepricing.csv";
        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(transformedFilePath));
            
            int j = 0;
            for(String[] rows: arrayList) {      
                StringBuilder rowBuilder = new StringBuilder();
                int i = 0;               
                for(String columns: rows) {  
                    
                    if(j != 0) {
                        if(i == index) {
                        int x = Integer.parseInt(columns);
                        double result = (x - min) / (double)(max - min);
                        String strResult = String.format("%.10f",result);
                        columns = strResult;
                        }
                    }
                    i++;
                    rowBuilder.append(columns).append(",");
                }
                
                if (rowBuilder.length() > 0) {
                    // Remove the last comma before writing the row
                    out.println(rowBuilder.substring(0, rowBuilder.length() - 1));
                }
                j++;
            }
            out.close();
        } catch (IOException e) {
            System.out.println("-> Encountered problems while normalising the data.\n");
            e.printStackTrace();
        }
    }
    
    private List<String> getColumnArray(List<String[]> arrayList, int columnIndex) {
        List<String> columnArray = new ArrayList<>();
        for(String[] rows: arrayList) {
            int i = 0;
            for(String columns: rows) {  
                if(i == columnIndex) {
                    columnArray.add(columns);
                    break;
                }
                i++;
            }
        }
        return columnArray;
    }
    
    private List<Integer> strToInteger(List<String> strArray) {
        strArray.remove(0);
        List<Integer> intArray = new ArrayList<>();
        for (int i = 0; i < strArray.size(); i++) {
            int j = Integer.parseInt(strArray.get(i));
            intArray.add(j);
        }
        return intArray;
    }
    
    private int findMax(List<Integer> arr) {             
        int result = arr.get(0);
        for (int i = 1; i < arr.size(); i ++) {
            result = Math.max(result, arr.get(i));
        }
        return result;
    }
    
    private int findMin(List<Integer> arr) {
        int result = arr.get(0);
        for (int i = 1; i < arr.size(); i ++) {
            result = Math.min(result, arr.get(i));
        }
        return result; 
    }
    
    public void getTransformedDataset(int lines) {
        DataLoading c = new DataLoading("transformedhousepricing.csv");
        System.out.println("Transformed House Pricing Dataset: ");
        c.getDataset(lines);
    }
    
    public List<String[]> getTransformedDataset() {
        DataLoading c = new DataLoading("transformedhousepricing.csv", false);
        return c.getLoadedDataset();
    }

    
}