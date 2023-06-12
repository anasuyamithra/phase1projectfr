package code;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserInterface {
    private FileManager fileManager;

    public UserInterface() {
        this.fileManager = new FileManager();
    }

    public void displayOptions() {
        System.out.println("Select an option:");
        System.out.println("1. Get current file names in ascending order");
        System.out.println("2. File Management");
        System.out.println("3. Exit");
    }

    public void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            displayOptions();
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    fileManager.displayFileNames();
                    break;
                case 2:
                    handleFileManagement();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting the application...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private void handleFileManagement() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            displayFileManagementOptions();
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (option) {
                case 1:
                    addFile(scanner);
                    break;
                case 2:
                    deleteFile(scanner);
                    break;
                case 3:
                    searchFile(scanner);
                    break;
                case 4:
                    exit = true;
                    System.out.println("Returning to the main context...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

       scanner.close();
    }

    private void displayFileManagementOptions() {
        System.out.println("\nFile Management Options:");
        System.out.println("1. Add a file");
        System.out.println("2. Delete a file");
        System.out.println("3. Search for a file");
        System.out.println("4. Back to main context");
    }

    private void addFile(Scanner scanner) {
        System.out.print("Enter the file name: ");
        String fileName = scanner.nextLine();

        try {
            fileManager.addFile(fileName);
            System.out.println("File added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding the file: " + e.getMessage());
        }
    }

    private void deleteFile(Scanner scanner) {
        System.out.print("Enter the file name to delete: ");
        String fileName = scanner.nextLine();

        try {
            fileManager.deleteFile(fileName);
            System.out.println("File deleted successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error deleting the file: " + e.getMessage());
        }
    }

    private void searchFile(Scanner scanner) {
        System.out.print("Enter the file name to search: ");
        String fileName = scanner.nextLine();

        try {
            boolean found = fileManager.searchFile(fileName);
            if (found) {
                System.out.println("File found.");
            } else {
                System.out.println("File not found.");
            }
        } catch (Exception e) {
            System.out.println("Error searching for the file: " + e.getMessage());
        }
    }
}
