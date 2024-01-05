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
public class DataAlchemy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /**
         * Data Loading
         */
        System.out.println("1.  Data Loading (0.5 marks)\n");
        DataLoading a = new DataLoading("housepricing.csv");
        System.out.println();

        /**
         * Data Exploration
         */
        System.out.println("2.  Data Exploration (0.5 marks)\n");
        DataExploration b = new DataExploration(a.getLoadedDataset());
        b.getDataset(10);
        System.out.println(b);
        System.out.println();
        
        /**
         * Data Cleaning
         */
        System.out.println("3.  Data Cleaning (2 marks)\n");
        DataCleaning c = new DataCleaning(a.getLoadedDataset());
        c.getCleanedDataset(10);
        System.out.println();
        
        /**
         * Data Transformation and Scaling
         */
        System.out.println("4.  Data Transformation and Scaling (2 marks)\n");
        DataTransformation d = new DataTransformation(c.getCleanedDataset());
        d.getTransformedDataset(10);
        
        /**
         * Data Splitting
         */
        System.out.println("6.  Data Splitting (1 mark)\n");
        DataSplitting e = new DataSplitting(d.getTransformedDataset());
        e.getShuffledDataset(10);

    }     
}
