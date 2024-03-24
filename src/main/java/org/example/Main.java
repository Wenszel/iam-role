package org.example;

import org.example.model.IAMRole;
import org.json.JSONException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws JSONException {
        String json = loadJsonFromUserInput();
        if (json != null) {
            processJson(json);
        }
    }

    private static String loadJsonFromUserInput() {
        Scanner scanner = new Scanner(System.in);
        String json = null;

        while (json == null) {
            printDataLoadingOptions();
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine();

            System.out.print("Enter the file path: ");
            String filePath;
            JSONReader jsonReader;
            switch (choice) {
                case 1:
                    System.out.println("e.g. json/correct_asterisk.json");
                    filePath = scanner.nextLine();
                    jsonReader = new JSONResourceReader();
                    break;
                case 2:
                    System.out.println("e.g. /users/username/desktop/file.json");
                    filePath = scanner.nextLine();
                    jsonReader = new JSONReader();
                    break;
                default:
                    System.out.println("Invalid choice.");
                    return null;
            }

            try {
                json = jsonReader.readJSON(filePath);
            } catch (IllegalArgumentException e) {
                System.out.println("File not found: " + filePath);
            }
        }
        scanner.close();
        return json;
    }

    private static void printDataLoadingOptions() {
        System.out.println("Choose data loading mode:");
        System.out.println("1. Resources (predefined JSON files in the resources directory)");
        System.out.println("2. File path (provide the path to the JSON file)");
        System.out.print("Your choice: ");
    }

    private static void processJson(String json) {
        try {
            IAMRoleParser iamRoleParser = new IAMRoleParser();
            IAMRoleVerifier iamRoleVerifier = new IAMRoleVerifier();
            IAMRole iamRole = iamRoleParser.parse(json);
            System.out.println("Output: " + iamRoleVerifier.verify(iamRole));
        } catch (JSONException e) {
            System.out.println("Invalid JSON");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}