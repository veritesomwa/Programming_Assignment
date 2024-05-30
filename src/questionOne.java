import java.io.*;
import java.util.*;
import java.text.*;

public class questionOne {

    public static void main(String[] args) {
        String inputFile = "employee.txt"; // File name
        List<String> updatedLines = new ArrayList<>(); // List to hold updated file lines

        // Try-with-resources to ensure the reader is closed automatically
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            boolean firstLine = true; // Flag to identify the header line

            // Reading file line by line
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    // Add header line directly to the list
                    updatedLines.add(line);
                    firstLine = false;
                    continue; // Skip processing for header
                }

                // Split the line into components
                String[] parts = line.split(" ");
                if (parts.length != 4) {
                    throw new IOException("Invalid line format: " + line); // Error handling for incorrect line format
                }

                // Parse employee details
                String name = parts[0];
                String surname = parts[1];
                int yearsWorked = Integer.parseInt(parts[2]); // Parse years worked
                double salary = Double.parseDouble(parts[3]); // Parse current salary

                // Determine salary increase percentage based on years worked
                double increase = 0;
                if (yearsWorked < 5) {
                    increase = 0.05; // 5% increase for < 5 years
                } else if (yearsWorked <= 10) {
                    increase = 0.15; // 15% increase for 5-10 years
                } else {
                    increase = 0.30; // 30% increase for > 10 years
                }

                // Calculate new salary
                double newSalary = salary + (salary * increase);

                // Format the new salary to ensure dot as decimal separator
                DecimalFormat decimalFormat = new DecimalFormat("#"); // Define the format
                String formattedSalary = decimalFormat.format(newSalary);

                // Create the updated line for the employee
                String updatedLine = String.format("%s %s %d %s", name, surname, yearsWorked, formattedSalary);
                updatedLines.add(updatedLine); // Add the updated line to the list
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage()); // Handle IO exceptions during file read
            e.printStackTrace();
        }

        // Try-with-resources to ensure the writer is closed automatically
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(inputFile))) {
            // Write all updated lines back to the file
            for (String updatedLine : updatedLines) {
                bw.write(updatedLine);
                bw.newLine(); // Add a new line after each entry
            }
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage()); // Handle IO exceptions during file write
            e.printStackTrace();
        }
    }
}
