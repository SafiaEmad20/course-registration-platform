
package com.project.useCases;

import java.util.List;

import com.project.Dao.StudentDao;
import com.project.Dao.StudentDaoImpl;
import com.project.bean.Course;
import com.project.bean.Student;
import com.project.exceptions.StudentException;

public class ViewMyCoursesUseCase {

    public static void main(String[] args) {

        StudentDao dao = new StudentDaoImpl();

        Student student = dao.getStudentObj();

        if (student == null) {
            System.out.println("Please login first.");
            return;
        }

        try {
            List<Course> courses =
                    dao.getMyEnrolledCourses(student.getRoll());

            System.out.println("Your enrolled courses:");
            System.out.println("====================================");

            for (Course course : courses) {
                System.out.println(course);
                System.out.println("------------------------------------");
            }

        } catch (StudentException e) {
            System.out.println(e.getMessage());
        }
    }
}
