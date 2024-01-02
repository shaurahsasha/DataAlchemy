/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataalchemy;
import java.util.*;

/**
 *
 * @author Shaurah Sasha
 */
public class DataExploration {
    
    private final List<String[]> arrayList;
    private final int[] infoList;
    private int rowsTotal, cellsTotal, missingValues;
    
    public DataExploration(List<String[]> arrayList) {
        this.arrayList = arrayList;
        this.rowsTotal = 0;
        this.cellsTotal = 0;
        this.missingValues = 0;
        this.infoList = new int[3];
        getValues();
    }
    
    //Getters
    public int getRowsTotal() {
        return infoList[0];
    }
    
    public int getColumnsTotal() {
        return infoList[1];
    }
    
    public int getMissingValues() {
        return infoList[2];
    }
    
    private int[] getValues() {
        
        for(String[] rows: this.arrayList) {  
            for(String columns: rows) {   
                if (columns.equals("")) {
                    missingValues++;
                }  
                cellsTotal++;
            }
            rowsTotal++;    
        }
        
        this.infoList[0] = rowsTotal;
        this.infoList[1] = (cellsTotal/rowsTotal);
        this.infoList[2] = missingValues;
        
        return infoList;
    }  
    
    public void getDataset(int lines) {
        System.out.println("House Pricing Dataset: ");
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
    
    @Override
    public String toString() {
        return "This dataset has " + infoList[0] + " rows, " + 
                infoList[1] + " columns and "
                + infoList[2] + " missing values.";
    }
    
}
