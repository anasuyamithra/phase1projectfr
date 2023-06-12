package code;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.io.FileWriter;
import java.util.Scanner;

public class FileManager {
    public void displayFileNames() {
        File rootDirectory = new File(".");
        String[] fileNames = rootDirectory.list();
        for (int i = 0; i < fileNames.length - 1; i++) {
            int minIndex = i;
            
            for (int j = i + 1; j < fileNames.length; j++) {
                if (fileNames[j].compareToIgnoreCase(fileNames[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            
            String temp = fileNames[minIndex];
            fileNames[minIndex] = fileNames[i];
            fileNames[i] = temp;
        }

        System.out.println("\nCurrent file names in ascending order:");
        for (String fileName : fileNames) {
            System.out.println(fileName);
        }
    }

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

    public boolean searchFile(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }
}
