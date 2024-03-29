package dataalchemy;
import java.io.*;
import java.util.*;

public class DataLoading {
    
    private final String fileName;
    private String line;
    private List<String[]> arrayList;
    
    public DataLoading(String fileName) {
        this.fileName = fileName;
        this.line = "";
        displayLoadDataset();
    }
    
    public DataLoading(String fileName, boolean output) {
        this.fileName = fileName;
        this.line = "";
        
        if(output) {
            displayLoadDataset();
        }
        
        loadDataset();
    }
    
    private void displayLoadDataset() {
        System.out.println("-- Loading the dataset into the system ...");
        this.arrayList = new ArrayList<>();
        
        try {
            BufferedReader buffered_reader = new BufferedReader(new FileReader(fileName));
            
            while((line = buffered_reader.readLine()) != null) {
                String[] columns = line.split(",");
                arrayList.add(columns);
            }  
            
            System.out.println("-> Successfully loaded the dataset " + fileName + " into the system!\n");
            
        } catch (IOException e) {
            System.out.println("-> Data loading was unsuccessful. Encountered issues with the file input or output.\n");
            e.printStackTrace();
        }
    }
    
    private void loadDataset() {
        this.arrayList = new ArrayList<>();
        
        try {
            BufferedReader buffered_reader = new BufferedReader(new FileReader(fileName));
            
            while((line = buffered_reader.readLine()) != null) {
                String[] columns = line.split(",");
                arrayList.add(columns);
            }              
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<String[]> getLoadedDataset() {
        return arrayList;
    }
    
    public void getLoadedDataset(int lines) {
        int linesCount = 0;
        for(String[] rows: arrayList) { 
            for(String columns: rows) {
                    if (linesCount <= lines) {
                    System.out.printf("%-20s ", columns);     
                } 
            }
            if (linesCount <= lines) {
                System.out.println();    
            }   
            linesCount++;
        }
        System.out.println("Showing " + lines + " rows.\n");
    }
    
}
