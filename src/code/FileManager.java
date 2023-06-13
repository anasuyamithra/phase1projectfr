package code;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.io.FileWriter;
import java.util.Scanner;

public class FileManager {

    private void quicksort(String[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quicksort(arr, low, pivotIndex - 1);
            quicksort(arr, pivotIndex + 1, high);
        }
    }

    private int partition(String[] arr, int low, int high) {
        String pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) < 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
	
    public void displayFileNames() {
        File rootDirectory = new File(".");
        String[] fileNames = rootDirectory.list();
        quicksort(fileNames, 0, fileNames.length - 1);

        System.out.println("\nCurrent file names in ascending order:");
        for (String fileName : fileNames) {
            System.out.println(fileName);
        }
    }
    
//    public String[] getFileNames() {
//        File folder = new File(directory);
//        File[] files = folder.listFiles();
//
//        if (files == null) {
//            return new String[0];
//        }
//
//        String[] fileNames = new String[files.length];
//        for (int i = 0; i < files.length; i++) {
//            fileNames[i] = files[i].getName();
//        }
//
//        quicksort(fileNames, 0, fileNames.length - 1);
//
//        return fileNames;
//    }

    public void addFile(String fileName) throws Exception {
        // Add logic to add the file
        //throw new UnsupportedOperationException("Add file operation not implemented yet.");
    	 File directory = new File(".");
         File[] files = directory.listFiles();

         if (files != null) {
             for (File file : files) {
                 if (file.getName().equalsIgnoreCase(fileName)) {
                     throw new IOException("File already exists: " + fileName);
                 }
             }
         }

         File newFile = new File(fileName);

         if (newFile.createNewFile()) {
             System.out.println("File created successfully: " + fileName);
             System.out.println("Enter content for the file (press Enter on an empty line to finish):");

             try (FileWriter writer = new FileWriter(newFile)) {
                 Scanner scanner = new Scanner(System.in);
                 String line;

                 while (!(line = scanner.nextLine()).isEmpty()) {
                     writer.write(line);
                     writer.write(System.lineSeparator());
                 }

                 System.out.println("Content saved to the file.");
             }
         } else {
             throw new IOException("Failed to create the file: " + fileName);
         }
    }

    public void deleteFile(String fileName) throws Exception {
        File file = new File(fileName);

        if (file.exists()) {
            boolean deleted = file.delete();
            if (!deleted) {
                throw new Exception("Failed to delete the file.");
            }
        } else {
            throw new FileNotFoundException("File not found.");
        }
    }

    public void searchFile(String fileName) {
    	File directory = new File(".");
        String[] fileNames = directory.list();
        
        if (fileNames.length == 0) {
            System.out.println("The directory is empty.");
            return;
        }
        quicksort(fileNames, 0, fileNames.length - 1);
        
        if(binarySearch(fileNames, fileName, 0, fileNames.length - 1)) {
        	System.out.println("File exists.");
        } else {
        	System.out.println("File not found.");
        }
        
    }

    private boolean binarySearch(String[] arr, String key, int low, int high) {
        if (low <= high) {
            int mid = low + (high - low) / 2;
            int result = key.compareTo(arr[mid]);

            if (result == 0) {
                return true;
            } else if (result < 0) {
                return binarySearch(arr, key, low, mid - 1);
            } else {
                return binarySearch(arr, key, mid + 1, high);
            }
        }
        return false;
    }
}
