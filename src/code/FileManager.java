package code;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class FileManager {
    private TreeNode root;

    private static class TreeNode {
        String fileName;
        TreeNode left;
        TreeNode right;

        TreeNode(String fileName) {
            this.fileName = fileName;
        }
    }

    public void displayFileNames() {
    	System.out.println("Inside file manager display function");
        inOrderTraversal(root);
    }

    private void inOrderTraversal(TreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.fileName);
            inOrderTraversal(node.right);
        } else {
        	System.out.println("Directory is empty. Please add some files.");
        }
    }

    public void addFile(String fileName) throws IOException {
        File newFile = new File(fileName);

        if (newFile.createNewFile()) {
            System.out.println("File created successfully: " + fileName);
            insertNode(fileName);
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


    private void insertNode(String fileName) {
        if (root == null) {
            root = new TreeNode(fileName);
        } else {
            insertNode(root, fileName);
        }
    }

    private void insertNode(TreeNode node, String fileName) {
        if (fileName.compareTo(node.fileName) < 0) {
            if (node.left == null) {
                node.left = new TreeNode(fileName);
            } else {
                insertNode(node.left, fileName);
            }
        } else {
            if (node.right == null) {
                node.right = new TreeNode(fileName);
            } else {
                insertNode(node.right, fileName);
            }
        }
    }

    public void deleteFile(String fileName) throws FileNotFoundException {
        root = deleteNode(root, fileName);
    }

    private TreeNode deleteNode(TreeNode node, String fileName) throws FileNotFoundException {
        if (node == null) {
            throw new FileNotFoundException("File not found.");
        }

        if (fileName.compareTo(node.fileName) < 0) {
            node.left = deleteNode(node.left, fileName);
        } else if (fileName.compareTo(node.fileName) > 0) {
            node.right = deleteNode(node.right, fileName);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            node.fileName = findMinimum(node.right).fileName;
            node.right = deleteNode(node.right, node.fileName);
        }

        return node;
    }

    private TreeNode findMinimum(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void searchFile(String fileName) {
        if (searchFile(root, fileName)) {
            System.out.println("File exists.");
        } else {
            System.out.println("File not found.");
        }
    }

    private boolean searchFile(TreeNode node, String fileName) {
        if (node == null) {
            return false;
        }

        if (fileName.compareTo(node.fileName) == 0) {
            return true;
        } else if (fileName.compareTo(node.fileName) < 0) {
            return searchFile(node.left, fileName);
        } else {
            return searchFile(node.right, fileName);
        }
    }
}
