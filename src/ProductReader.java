import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
// FOR WRITING PRODUCT
public class ProductReader {
    public static void main(String[] args) {
        // Initialize an ArrayList to store product data
        ArrayList<String> products = new ArrayList<>();

        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter product data in a specific format
        System.out.println("Enter product data using the format: ID, Name, Description, Cost");
        System.out.println("Example: 000001, Pipeweed, Long Bottom Leaf, 600.0");
        System.out.println("Enter 'exit' to finish.");

        while (true) {
            String productData = "";

            // Get ID
            String id = SafeInput.getNonZeroLenString(scanner, "Enter ID");
            productData += id + ", ";

            // Get Name
            String name = SafeInput.getNonZeroLenString(scanner, "Enter Name");
            productData += name + ", ";

            // Get Description
            String description = SafeInput.getNonZeroLenString(scanner, "Enter Description");
            productData += description + ", ";

            // Get Cost
            double cost = SafeInput.getDouble(scanner, "Enter Cost");
            productData += cost;

            // Add the formatted product data to the ArrayList
            products.add(productData);

            // Check if the user wants to add another product
            if (!SafeInput.getYNConfirm(scanner, "Do you want to add another product?")) {
                break;
            }
        }

        // Get a filename from the user with a valid format using regular expression
        String filename = SafeInput.getRegExString(scanner, "Enter a filename to save the data", "[a-zA-Z0-9]+\\.txt");

        // Write product data to the specified file
        try (FileWriter writer = new FileWriter(filename)) {
            // Loop through the list of products and write each product's data to the file
            for (String product : products) {
                writer.write(product + System.lineSeparator());
            }
            System.out.println("Data saved to " + filename);
        } catch (IOException e) {
            // Handle any errors that might occur during file writing
            System.out.println("Error writing to file.");
        } finally {
            // Close the scanner to prevent resource leak
            scanner.close();
        }
    }
}
