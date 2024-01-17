package dataalchemy;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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
            System.out.println("-> Successfully saved cleaned data to: " + cleanedFilePath + "!\n");
            
        } catch (IOException e) {
            System.out.println("-> Data cleaning was unsuccessful. Encountered issues with the file input or output.\n");
            e.printStackTrace();
        }
    }
    
    public void getCleanedDataset(int lines) {
        DataLoading dl = new DataLoading("cleanedhousepricing.csv");
        System.out.println("Cleaned House Pricing Dataset: ");
        dl.getLoadedDataset(lines);
    }
    
    public List<String[]> getCleanedDataset() {
        DataLoading dl = new DataLoading("cleanedhousepricing.csv", false);
        return dl.getLoadedDataset();
    }

}
