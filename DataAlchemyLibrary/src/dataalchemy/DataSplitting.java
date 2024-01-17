package dataalchemy;
import java.io.*;
import java.util.*;

public class DataSplitting {
 
    private List<String[]> transformedArray;
    private List<String[]> shuffledArray;
    private List<String[]> trainingSet;
    private List<String[]> testingSet;

    public DataSplitting(List<String[]> transformedArray) {
        this.transformedArray = transformedArray;
        shuffle();
        split70_30();
        //split70_15_15();
    }
    
    private void shuffle() {
        
        DataLoading dl = new DataLoading("transformedhousepricing.csv", false);
        List<String[]> arrayList = dl.getLoadedDataset();
        
        System.out.println("-- Shuffling the dataset ...");
        String shuffledFilePath = "shuffledhousepricing.csv";

        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(shuffledFilePath));
            
            int n = arrayList.size();
            Random random = new Random();
            for (int i = 0; i < n; i++) {

                if(i == 0) {
                    continue;
                }

                int change = random.nextInt(n - i) + i;
                String[] temp = arrayList.get(i);
                arrayList.set(i, arrayList.get(change)) ;
                arrayList.set(change, temp);
            }            
            
            for(String[] rows: arrayList) {      
                StringBuilder rowBuilder = new StringBuilder();

                for(String columns: rows) {  
                    rowBuilder.append(columns).append(",");
                }
                
                if (rowBuilder.length() > 0) {
                    // Remove the last comma before writing the row
                    out.println(rowBuilder.substring(0, rowBuilder.length() - 1));
                }
            }
            out.close();
            System.out.println("-> Successfully saved shuffled data to: " + shuffledFilePath + "!\n");
            
        } catch (IOException e) {
            System.out.println("-> Data splitting was unsuccessful. Encountered issues with the file input or output.\n");
        }
    }
    
    private void split70_30() {
        
        DataLoading dl = new DataLoading("shuffledhousepricing.csv", false);
        List<String[]> arrayList = dl.getLoadedDataset();
        
        System.out.println("-- Splitting the dataset 70:30 ...");
        String trainingSetFilePath = "traininghousepricing.csv";
        String testingSetFilePath = "testinghousepricing.csv";


        try {
            PrintWriter trainingOutputStream = new PrintWriter(new FileOutputStream(trainingSetFilePath));
            PrintWriter testingOutputStream = new PrintWriter(new FileOutputStream(testingSetFilePath));       
            
            int rowCounter = 0;
            for(String[] rows: arrayList) {      
                StringBuilder rowBuilder = new StringBuilder();
                
                if(rowCounter == 0) {
                    
                    for(String columns: rows) {  
                            rowBuilder.append(columns).append(",");
                    }
                    if (rowBuilder.length() > 0) {
                        // Remove the last comma before writing the row
                        trainingOutputStream.println(rowBuilder.substring(0, rowBuilder.length() - 1));
                        testingOutputStream.println(rowBuilder.substring(0, rowBuilder.length() - 1));
                    }
                } else if (rowCounter <= (int)arrayList.size()*0.7) {
                    
                    for(String columns: rows) {  
                            rowBuilder.append(columns).append(",");
                    }
                    if (rowBuilder.length() > 0) {
                        // Remove the last comma before writing the row
                        trainingOutputStream.println(rowBuilder.substring(0, rowBuilder.length() - 1));
                    }
                    
                } else {
                    for(String columns: rows) {  
                        rowBuilder.append(columns).append(",");
                    }

                    if (rowBuilder.length() > 0) {
                        // Remove the last comma before writing the row
                        testingOutputStream.println(rowBuilder.substring(0, rowBuilder.length() - 1));
                    }
                }
                
                rowCounter++;
            }
            trainingOutputStream.close();
            testingOutputStream.close();
            System.out.println("-> Successfully saved the training set to: " + trainingSetFilePath + "!");
            System.out.println("-> Successfully saved the testing set to: " + testingSetFilePath + "!\n");

        } catch (IOException e) {
            System.out.println("-> Data splitting was unsuccessful. Encountered issues with the file input or output.\n");
        }
    }
    
     private void split70_15_15() {
        
        DataLoading dl = new DataLoading("shuffledhousepricing.csv", false);
        List<String[]> arrayList = dl.getLoadedDataset();
        
        System.out.println("-- Splitting the dataset to 70:15:15 ...");
        String trainingSetFilePath = "traininghousepricing.csv";
        String testingSetFilePath = "testinghousepricing.csv";
        String validationSetFilePath = "validationhousepricing.csv";


        try {
            PrintWriter trainingOutputStream = new PrintWriter(new FileOutputStream(trainingSetFilePath));
            PrintWriter testingOutputStream = new PrintWriter(new FileOutputStream(testingSetFilePath));
            PrintWriter validationOutputStream = new PrintWriter(new FileOutputStream(validationSetFilePath));       

            
            int rowCounter = 0;
            for(String[] rows: arrayList) {      
                StringBuilder rowBuilder = new StringBuilder();
                
                if(rowCounter == 0) {
                    
                    for(String columns: rows) {  
                            rowBuilder.append(columns).append(",");
                    }
                    if (rowBuilder.length() > 0) {
                        // Remove the last comma before writing the row
                        trainingOutputStream.println(rowBuilder.substring(0, rowBuilder.length() - 1));
                        testingOutputStream.println(rowBuilder.substring(0, rowBuilder.length() - 1));
                        validationOutputStream.println(rowBuilder.substring(0, rowBuilder.length() - 1));
                    }
                } else if (rowCounter <= (int)arrayList.size()*0.7) {
                    
                    for(String columns: rows) {  
                            rowBuilder.append(columns).append(",");
                    }
                    if (rowBuilder.length() > 0) {
                        // Remove the last comma before writing the row
                        trainingOutputStream.println(rowBuilder.substring(0, rowBuilder.length() - 1));
                    }
                    
                } else if (rowCounter > (int)arrayList.size()*0.7 && rowCounter <= (int)arrayList.size()*0.85) {
                    for(String columns: rows) {  
                        rowBuilder.append(columns).append(",");
                    }

                    if (rowBuilder.length() > 0) {
                        // Remove the last comma before writing the row
                        testingOutputStream.println(rowBuilder.substring(0, rowBuilder.length() - 1));
                    }
                } else if  (rowCounter > (int)arrayList.size()*0.85){
                    for(String columns: rows) {  
                        rowBuilder.append(columns).append(",");
                    }

                    if (rowBuilder.length() > 0) {
                        // Remove the last comma before writing the row
                        validationOutputStream.println(rowBuilder.substring(0, rowBuilder.length() - 1));
                    }
                }
                
                rowCounter++;
            }
            trainingOutputStream.close();
            testingOutputStream.close();
            validationOutputStream.close();
            System.out.println("-> Successfully saved the training set to: " + trainingSetFilePath + "!");
            System.out.println("-> Successfully saved the testing set to: " + testingSetFilePath + "!");
            System.out.println("-> Successfully saved the validation set to: " + validationSetFilePath + "!\n");

        } catch (IOException e) {
            System.out.println("-> Data splitting was unsuccessful. Encountered issues with the file input or output.\n");
        }
    }
    
    public void getShuffledDataset(int lines) {
        DataLoading dl = new DataLoading("shuffledhousepricing.csv");
        System.out.println("Shuffled House Pricing Dataset: ");
        dl.getLoadedDataset(lines);
    }

}
