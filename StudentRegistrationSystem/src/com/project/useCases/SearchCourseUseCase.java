package com.project.useCases;

import java.util.List;
import java.util.Scanner;

import com.project.Dao.CourseDao;
import com.project.Dao.CourseDaoImpl;
import com.project.bean.Course;

public class SearchCourseUseCase {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter course name or keyword:");
        String keyword = sc.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("Please enter a course name.");
            return;
        }

        CourseDao dao = new CourseDaoImpl();

        try {
            List<Course> courseList = dao.searchCourses(keyword);

            System.out.println("Matching courses:");
            System.out.println("================================================");

            courseList.forEach(course -> {
                System.out.println(course);
                System.out.println("-----------------------------------------------");
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}