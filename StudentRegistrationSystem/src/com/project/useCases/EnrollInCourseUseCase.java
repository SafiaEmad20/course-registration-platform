
package com.project.useCases;

import java.util.List;
import java.util.Scanner;

import com.project.Dao.CourseDao;
import com.project.Dao.CourseDaoImpl;
import com.project.Dao.StudentDao;
import com.project.Dao.StudentDaoImpl;
import com.project.bean.Course;
import com.project.bean.Student;

public class EnrollInCourseUseCase {

    public static void main(String[] args) {

        StudentDao studentDao = new StudentDaoImpl();
        CourseDao courseDao = new CourseDaoImpl();

        // Get the currently logged-in student
        Student student = studentDao.getStudentObj();

        if (student == null) {
            System.out.println("Please login first.");
            return;
        }

        // Show all courses and batches
        try {
            List<Course> courseList = courseDao.getAllCourses();

            System.out.println("Available courses and batches:");
            System.out.println("==============================================");

            for (Course course : courseList) {
                System.out.println(
                    "Course ID: " + course.getCid()
                    + " | Name: " + course.getCname()
                    + " | Batch: " + course.getBatch()
                    + " | Fee: " + course.getFee()
                );
                System.out.println("----------------------------------------------");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        // Let the student choose a course
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the Course ID you want to enroll in:");
        int cid = sc.nextInt();

        try {
            String result =
                courseDao.allocateStudent(student.getRoll(), cid);

            System.out.println(result);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
