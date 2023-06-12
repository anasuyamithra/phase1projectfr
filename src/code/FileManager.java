package code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

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
        throw new UnsupportedOperationException("Add file operation not implemented yet.");
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
