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
        String csv_path = "C:\\Users\\Shaurah Sasha\\Downloads\\housepricing.csv";
        String line = "";
        List<String[]> house_pricing_data = new ArrayList<>();
        
        try {
            BufferedReader buffered_reader = new BufferedReader(new FileReader(csv_path));
            
            while((line = buffered_reader.readLine()) != null) {
                String[] columns = line.split(",");
                house_pricing_data.add(columns);
            }  
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for(String[] rows: house_pricing_data) {
            for(String columns: rows) {
                System.out.print(columns + " ");
            }
            System.out.println();
        }
        
        //Data Exploration
    }     
}
