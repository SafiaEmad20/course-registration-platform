
package com.project.useCases;

import com.project.Dao.CourseDao;
import com.project.Dao.CourseDaoImpl;
import com.project.bean.Course;
import java.util.Scanner;

public class AddCourseUseCase {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter course name");
        String cname = sc.next();

        CourseDao dao = new CourseDaoImpl();
        int lastbatch = dao.getlastbatch(cname);

        System.out.println("Last batch for " + cname + " course was " + lastbatch + ".");
        System.out.println("New batch number " + (lastbatch + 1) + " for " + cname + " will be created.");

        int batch = lastbatch + 1;
        int fee;
        int totalseats;

        while (true) {
            System.out.println("Enter fee for this batch");

            if (sc.hasNextInt()) {
                fee = sc.nextInt();

                if (fee > 0) {
                    break;
                }

                System.out.println("Fee must be greater than zero.");
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.next();
            }
        }

        while (true) {
            System.out.println("Enter total number of seats");

            if (sc.hasNextInt()) {
                totalseats = sc.nextInt();

                if (totalseats > 0) {
                    break;
                }

                System.out.println("Total seats must be greater than zero.");
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.next();
            }
        }

        Course course = new Course();
        course.setCname(cname);
        course.setBatch(batch);
        course.setFee(fee);
        course.setTotalseats(totalseats);

        String result = dao.addCourse(course);

        System.out.println(result);
    }
}
