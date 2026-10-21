
package com.project.useCases;

import java.util.Scanner;

import com.project.Dao.StudentDao;
import com.project.Dao.StudentDaoImpl;
import com.project.bean.Student;
import com.project.exceptions.StudentException;

public class StudentLoginUseCase {

    public static Student login() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Username:");
        String uname = sc.next();

        System.out.println("Enter Password:");
        String pass = sc.next();

        StudentDao dao = new StudentDaoImpl();

        try {
            Student student = dao.loginStudent(uname, pass);

            System.out.println("Welcome Student: " + student.getName());

            return student;

        } catch (StudentException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
