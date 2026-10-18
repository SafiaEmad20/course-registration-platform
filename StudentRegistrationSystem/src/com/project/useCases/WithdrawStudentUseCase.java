package com.project.useCases;

import java.util.Scanner;

import com.project.Dao.CourseDao;
import com.project.Dao.CourseDaoImpl;
import com.project.exceptions.StudentException;

public class WithdrawStudentUseCase {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the Course ID you want to withdraw from:");
		int cid = sc.nextInt();

		CourseDao dao = new CourseDaoImpl();

		try {
			System.out.println(dao.withdrawStudent(cid));
		} catch (StudentException e) {
			System.out.println(e.getMessage());
		}
	}
}