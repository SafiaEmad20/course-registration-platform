package com.project.main;

import com.project.bean.Student;
import com.project.useCases.AddBatchUseCase;
import com.project.useCases.AddCourseUseCase;
import com.project.useCases.AdminLoginUseCase;
import com.project.useCases.AllocateStudentUseCase;
import com.project.useCases.DeleteCourseUseCase;
import com.project.useCases.GetBatchwiseStudentsUseCase;
import com.project.useCases.GetCourseDetailsUseCase;
import com.project.useCases.GetSeatAvailabilityUseCase;
import com.project.useCases.RegisterStudentUseCase;
import com.project.useCases.StudentLoginUseCase;
import com.project.useCases.UpdateCourseFee;
import com.project.useCases.UpdateStudentUseCase;
import com.project.useCases.UpdateTotalSeatsUseCase;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("***   Welcome to Student Registration System   ***");
        System.out.println("=====================================================");
        System.out.println("Please select one of the following user types:");
        System.out.println("1. Administrator");
        System.out.println("2. Student");
        System.out.println("3. Exit");

        while (true) {
            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid selection. Please enter a number.");
                sc.nextLine();
                continue;
            }

            int userSelect = sc.nextInt();

            if (userSelect == 3) {
                System.out.println("Thank you!");
                break;
            }

            if (userSelect == 1) {

                System.out.println("1 Login");
                System.out.println("2 Exit");

                int choice = sc.nextInt();

                if (choice == 1) {

                    if (AdminLoginUseCase.main(null)) {

                        boolean adminRunning = true;

                        while (adminRunning) {
                            System.out.println();
                            System.out.println("----------------------------------------");
                            System.out.println("Please select one of the following options");
                            System.out.println("1. Add a new Course");
                            System.out.println("2. Update Fees of course");
                            System.out.println("3. Delete a course or batch");
                            System.out.println("4. Search information about a course");
                            System.out.println("5. Create Batch under a course");
                            System.out.println("6. Allocate students in a Batch under a course");
                            System.out.println("7. Update total seats of a batch");
                            System.out.println("8. View the students of every batch");
                            System.out.println("9. Exit");

                            int x = sc.nextInt();

                            switch (x) {
                                case 1:
                                    AddCourseUseCase.main(null);
                                    break;
                                case 2:
                                    UpdateCourseFee.main(null);
                                    break;
                                case 3:
                                    DeleteCourseUseCase.main(null);
                                    break;
                                case 4:
                                    GetCourseDetailsUseCase.main(null);
                                    break;
                                case 5:
                                    AddBatchUseCase.main(null);
                                    break;
                                case 6:
                                    AllocateStudentUseCase.main(null);
                                    break;
                                case 7:
                                    UpdateTotalSeatsUseCase.main(null);
                                    break;
                                case 8:
                                    GetBatchwiseStudentsUseCase.main(null);
                                    break;
                                case 9:
                                    adminRunning = false;
                                    break;
                                default:
                                    System.out.println("Invalid selection.");
                            }
                        }

                    } else {
                        System.out.println("Login failed. Please try again.");
                    }

                } else if (choice == 2) {
                    System.out.println("Returning to main menu.");
                } else {
                    System.out.println("Invalid selection.");
                }

            } else if (userSelect == 2) {

                boolean studentRunning = true;

                while (studentRunning) {
                    System.out.println("1 Login");
                    System.out.println("2 New User? Register");
                    System.out.println("3 Exit");
                    System.out.println("Enter choice:");

                    int choice = sc.nextInt();

                    if (choice == 1) {

                       Student loggedInStudent = StudentLoginUseCase.login(sc);
                        if (loggedInStudent != null) {
                            boolean loggedIn = true;

                            while (loggedIn) {
                                System.out.println("Please select one of the options below");
                                System.out.println("---------------------------------------");
                                System.out.println("1. Update profile details");
                                System.out.println("2. Show all courses and availability");
                                System.out.println("3. Exit");

                                int y = sc.nextInt();

                                switch (y) {
                                    case 1:
                                        UpdateStudentUseCase.main(null);
                                        break;
                                    case 2:
                                        GetSeatAvailabilityUseCase.main(null);
                                        break;
                                    case 3:
                                        loggedIn = false;
                                        break;
                                    default:
                                        System.out.println("Invalid selection.");
                                }
                            }

                        } else {
                            System.out.println("Login failed. Please try again.");
                        }

                    } else if (choice == 2) {

                        sc.nextLine();
                        RegisterStudentUseCase.register(sc);

                    } else if (choice == 3) {

                        studentRunning = false;

                    } else {
                        System.out.println("Invalid selection.");
                    }
                }

            } else {
                System.out.println("Invalid selection. Please choose 1, 2, or 3.");
            }
        }

        sc.close();
    }
}
