package dataalchemy;
import java.util.*;
import java.io.*;

public class DataTransformation {
    
    private List<String[]> cleanedArray;
    private List<String[]> transformedArray;
    
    public DataTransformation(List<String[]> arrayList) {
        this.cleanedArray = arrayList;
        this.transformedArray = new ArrayList<>(cleanedArray);
        transform();
    }
        
    private void transform() {
        
        oneHotEncoding();
        
        // encoding categorical values
        encodeBoolean(5);       //mainroad
        encodeBoolean(6);       //guestroom
        encodeBoolean(7);       //basement
        encodeBoolean(8);       //hotwaterheating
        encodeBoolean(9);       //airconditioning
        encodeBoolean(11);      //prefarea
        encodeCategory(12);     //furnishingstatus

        // standardising and normalising numerical values 
        standardise(1);         //area
        normalise(2);           //bedrooms
        normalise(3);           //bathrooms
        normalise(4);           //stories
        normalise(10);          //parking      
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
    
    private void encodeBoolean(int index) {
        
        DataLoading dl = new DataLoading("transformedhousepricing.csv", false);
        List<String[]> arrayList = dl.getLoadedDataset();       
        List<String> strArray = getColumnStringArray(arrayList, index);
        String transformFilePath = "transformedhousepricing.csv";
        
        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(transformFilePath));
            
            int j = 0;
            for(String[] rows: arrayList) {      
                StringBuilder rowBuilder = new StringBuilder();
                int i = 0;               
                for(String columns: rows) { 
                    i++;
                    rowBuilder.append(columns).append(",");
                }
                                
                if(j == 0) {
                    rowBuilder.append(strArray.get(j)).append("_yes").append(",");
                    rowBuilder.append(strArray.get(j)).append("_no").append(",");
                } else if (j < strArray.size()){
                    if (strArray.get(j).equals("1")) {
                        rowBuilder.append("1").append(",");
                        rowBuilder.append("0").append(",");
                    } else {
                        rowBuilder.append("0").append(",");
                        rowBuilder.append("1").append(",");
                    }
                }
                j++;
                
                if (rowBuilder.length() > 0) {
                    // Remove the last comma before writing the row
                    out.println(rowBuilder.substring(0, rowBuilder.length() - 1));
                }
                
            }
            out.close();
        } catch (IOException e) {
            System.out.println("-> Encountered problems while normalising the data.\n");
            e.printStackTrace();
        }
    }
    
        private void encodeCategory(int index) {
        
        DataLoading dl = new DataLoading("transformedhousepricing.csv", false);
        List<String[]> arrayList = dl.getLoadedDataset();       
        List<String> strArray = getColumnStringArray(arrayList, index);
        String transformFilePath = "transformedhousepricing.csv";
        
        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(transformFilePath));
            
            int j = 0;
            for(String[] rows: arrayList) {      
                StringBuilder rowBuilder = new StringBuilder();
                int i = 0;               
                for(String columns: rows) { 
                    i++;
                    rowBuilder.append(columns).append(",");
                }
                                
                if(j == 0) {
                    rowBuilder.append("furnished").append(",");
                    rowBuilder.append("semi-furnished").append(",");
                    rowBuilder.append("unfurnished").append(",");
                } else if (j < strArray.size()){
                    if (strArray.get(j).equals("1")) {
                        rowBuilder.append("1").append(",");
                        rowBuilder.append("0").append(",");
                        rowBuilder.append("0").append(",");
                    } else if (strArray.get(j).equals("0")){
                        rowBuilder.append("0").append(",");
                        rowBuilder.append("1").append(",");
                        rowBuilder.append("0").append(",");
                    } else {
                        rowBuilder.append("0").append(",");
                        rowBuilder.append("0").append(",");
                        rowBuilder.append("1").append(",");
                    }
                }
                j++;
                
                if (rowBuilder.length() > 0) {
                    // Remove the last comma before writing the row
                    out.println(rowBuilder.substring(0, rowBuilder.length() - 1));
                }
                
            }
            out.close();
        } catch (IOException e) {
            System.out.println("-> Encountered problems while normalising the data.\n");
            e.printStackTrace();
        }
    }
    
    private void normalise(int index) {
        
        DataLoading dl = new DataLoading("transformedhousepricing.csv", false);
        List<String[]> arrayList = dl.getLoadedDataset();
                
        List<String> strArray = getColumnStringArray(arrayList, index);
        List<Integer> intArray = getColumnIntegerArray(strArray);
        double min = findMin(intArray);
        double max = findMax(intArray);
        
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
    
    private void standardise(int index) {
        
        DataLoading dl = new DataLoading("transformedhousepricing.csv", false);
        List<String[]> arrayList = dl.getLoadedDataset();
                
        List<String> strArray = getColumnStringArray(arrayList, index);
        List<Integer> intArray = getColumnIntegerArray(strArray);
        double mean = findMean(intArray);
        double std = findStd(intArray);
        
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
                        double result = (x - mean) / std;
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
            System.out.println("-> Encountered problems while standardising the data.\n");
            e.printStackTrace();
        }
    }
    
    private List<String> getColumnStringArray(List<String[]> arrayList, int columnIndex) {
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
    
    private List<Integer> getColumnIntegerArray(List<String> strArray) {
        strArray.remove(0);
        List<Integer> intArray = new ArrayList<>();
        for (int i = 0; i < strArray.size(); i++) {
            int j = Integer.parseInt(strArray.get(i));
            intArray.add(j);
        }
        return intArray;
    }
    
    private double findMin(List<Integer> arr) {
        double result = arr.get(0);
        for (int i = 1; i < arr.size(); i ++) {
            result = Math.min(result, arr.get(i));
        }
        return result; 
    }
    
    private double findMax(List<Integer> arr) {             
        double result = arr.get(0);
        for (int i = 1; i < arr.size(); i ++) {
            result = Math.max(result, arr.get(i));
        }
        return result;
    }
    
    private double findMean(List<Integer> arr) {
        double result = 0;
        for (int i = 0; i < arr.size(); i ++) {
            result += arr.get(i);
        }
        return result / arr.size(); 
    }
    
    private double findStd(List<Integer> arr) {
        double mean = findMean(arr);
        double result = 0;
        for (int i = 0; i < arr.size(); i ++) {
            result += Math.pow(arr.get(i) - mean, 2);
        }
        return Math.sqrt(result / arr.size()); 
    }
    
    public void getTransformedDataset(int lines) {
        DataLoading dl = new DataLoading("transformedhousepricing.csv");
        System.out.println("Transformed House Pricing Dataset: ");
        dl.getLoadedDataset(lines);
    }
    
    public List<String[]> getTransformedDataset() {
        DataLoading dl = new DataLoading("transformedhousepricing.csv", false);
        return dl.getLoadedDataset();
    }

}