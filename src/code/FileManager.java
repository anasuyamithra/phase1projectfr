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
        Arrays.sort(fileNames);

        System.out.println("\nCurrent file names in ascending order:");
        for (String fileName : fileNames) {
            System.out.println(fileName);
        }
    }

    public void addFile(String fileName) throws Exception {
        // Add logic to add the file
        //throw new UnsupportedOperationException("Add file operation not implemented yet.");
    	File file = new File(fileName);

        if (file.createNewFile()) {
            System.out.println("File created successfully: " + fileName);
            System.out.println("Enter content for the file (press Enter on an empty line to finish):");

            try (FileWriter writer = new FileWriter(file)) {
                Scanner scanner = new Scanner(System.in);
                String line;

                while (!(line = scanner.nextLine()).isEmpty()) {
                    writer.write(line);
                    writer.write(System.lineSeparator());
                }
                
                System.out.println("Content saved to the file.");
            }
        } else {
            throw new IOException("Failed to create the file.");
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
