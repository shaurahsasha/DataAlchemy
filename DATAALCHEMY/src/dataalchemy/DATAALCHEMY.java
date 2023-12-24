/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dataalchemy;
import java.io.*;  
import java.util.*;

/**
 *
 * @author Shaurah Sasha
 */
public class DATAALCHEMY {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Data loading
        String file_path = "C:\\Users\\Shaurah Sasha\\Downloads\\housepricing.csv";
        String line = "";
        List<String[]> house_pricing_data = new ArrayList<>();
        
        try {
            BufferedReader buffered_reader = new BufferedReader(new FileReader(file_path));
            
            while((line = buffered_reader.readLine()) != null) {
                String[] columns = line.split(",");
                house_pricing_data.add(columns);
            }  
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //Data Exploration
        System.out.println("House Pricing Dataset\n");
        int rows_total, cells_total, missing_values;
        rows_total = cells_total = missing_values = 0;
        
        for(String[] rows: house_pricing_data) {
            
            for(String columns: rows) {
                
                if (columns.equals("")) {
                    missing_values++;
                }  
                
                if (rows_total <= 25) {
                    System.out.printf("%-15s ", columns);     
                } 
                cells_total++;
            }
            
            if (rows_total <= 25) {
                    System.out.println();    
            }
            rows_total++;
        }
        System.out.println("\nShowing 25 out of " + rows_total + " rows.");
        System.out.println("This dataset has " + (cells_total/rows_total) + " columns and " + missing_values + " missing values.");

        
        //Data Cleaning [Checking Duplicated Rows] 
        Set house_pricing_set = new HashSet<>(house_pricing_data);
        if(house_pricing_set.size() == house_pricing_data.size()) {
            System.out.println("There are no duplicated rows in this dataset.");
        } else {
            System.out.println("There are duplicated rows in this dataset.");
        }

        //Data Cleaning [Imputing Zero] Simplified
        System.out.println("\n\nHouse Pricing Dataset After Cleaning\n");
        
        List<String[]> house_pricing_cleaned = new ArrayList<>(house_pricing_data);     //Creating a copy of the original dataset 

        for(String[] rows: house_pricing_cleaned) {                                     //Looping through the new dataset to impute with 0
            for(String columns: rows) {  
                if (columns.equals("")) {
                    columns = "0";
                }
                System.out.printf("%-15s ", columns); 
            }
            System.out.println();
        }

        //save&export cleaned data:
        String cleanedFilePath = "cleanedHousepricing.csv";
        
        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(cleanedFilePath));
            
            for(String[] rows: house_pricing_cleaned) {     
                StringBuilder rowBuilder = new StringBuilder();

                for (String column : rows) {
                    if (column.equals("")) {
                        column = "0"; // Impute empty values with "0"
                    }
                    rowBuilder.append(column).append(",");
                }

                if (rowBuilder.length() > 0) {
                    // Remove the last comma before writing the row
                    out.println(rowBuilder.substring(0, rowBuilder.length() - 1));
                }
            }    
                
            out.close();
            System.out.println("Cleaned data has been saved to:" +cleanedFilePath);
        } catch (IOException e) {
            System.out.println("Problem with file output");
        }

    }   


    //Online Meeting at 8pm 18/12/2023
}
