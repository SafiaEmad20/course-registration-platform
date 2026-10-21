
package com.project.useCases;

import com.project.Dao.CourseDao;
import com.project.Dao.CourseDaoImpl;
import java.util.Scanner;

public class UpdateCourseFee {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter course name: ");
        String cname = sc.nextLine().trim();

        while (cname.isEmpty()) {
            System.out.println("Course name cannot be empty.");
            System.out.print("Enter course name: ");
            cname = sc.nextLine().trim();
        }

        int batch = readPositiveInt(sc, "Enter batch number: ");
        int fee = readNonNegativeInt(sc, "Enter new fees: ");

        CourseDao dao = new CourseDaoImpl();

        String result = dao.updateFee(cname, batch, fee);
        System.out.println(result);
    }

    private static int readPositiveInt(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            try {
                int value = Integer.parseInt(input);

                if (value > 0) {
                    return value;
                }

                System.out.println("Please enter a number greater than zero.");

            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        }
    }

    private static int readNonNegativeInt(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            try {
                int value = Integer.parseInt(input);

                if (value >= 0) {
                    return value;
                }

                System.out.println("Fees cannot be negative.");

            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        }
    }
}
