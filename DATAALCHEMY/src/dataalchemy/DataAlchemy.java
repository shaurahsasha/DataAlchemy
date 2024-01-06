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
         * Data Visualization
         */
        System.out.println("5.  Data Visualisation (1 mark)\n");
        
        /**
         * Data Splitting, Save and Export Preprocessed Data
         */
        System.out.println("6.  Data Splitting (1 mark)");
        System.out.println("7.  Save and Export Pre-processed Data (1 mark)\n");
        DataSplitting f = new DataSplitting(d.getTransformedDataset());
        f.getShuffledDataset(10);
                
        /**
         * User Interface
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        } catch (InstantiationException e) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        } catch (IllegalAccessException e) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        } catch (javax.swing.UnsupportedLookAndFeelException e) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserInterface().setVisible(true);
            }
        });

    }     
}
