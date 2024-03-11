import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        LinkedList<Student> students = new LinkedList<Student>();
        Scanner scanner = new Scanner(System.in);

        // Loop until the user is done inputting new students
        boolean hasAnotherStudent = false;
        while (true) {
            // Check if the user has another student to enter
            while (true){
                System.out.println("Would you like to input the data for a new student? Y or N");
                var input = scanner.nextLine();

                if (input.equalsIgnoreCase("y")){
                    hasAnotherStudent = true;
                    break;
                } else if (input.equalsIgnoreCase("n")) {
                    hasAnotherStudent = false;
                    break;
                } else {
                    System.out.println("INVALID RESPONSE");
                }
            }

            // Exit if there is not another student to enter
            if (!hasAnotherStudent) break;

            // Create the student using the name that was input
            System.out.println();
            System.out.println("Input this new student's NAME.");
            var student = new Student(scanner.nextLine());

            // Set the student's address from the console
            System.out.println("Input " + student.getName() + "'s ADDRESS.");
            student.setAddress(scanner.nextLine());

            // Validate and set the GPA from the console
            while (true){
                System.out.println("Input " + student.getName() + "'s current GPA.");
                try {
                    student.setGPA(Double.parseDouble(scanner.nextLine()));
                    System.out.println();
                    break;
                } catch (NumberFormatException e){
                    System.out.println("INVALID RESPONSE");
                }
            }

            // Add the student to the list
            students.add(student);
        }

        // Sort the list in ascending order by name
        students.sort(Comparator.comparing(Student::getName));

        // Write the list to a file
        var filePath = "C:\\temp\\student_list.txt";
        try {
            // Write to file
            var file = new File(filePath);
            file.createNewFile();

            var fileWriter = new FileWriter(filePath);
            var printWriter = new PrintWriter(fileWriter);

            for (Student entry : students) {
                printWriter.println(entry.toString());
            }

            printWriter.close();
            fileWriter.close();

            System.out.println("Saved student list to file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save to file.");
        }

        // Close the scanner
        scanner.close();

    }
}