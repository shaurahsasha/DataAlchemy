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

        //Data Cleaning
    }     
}
