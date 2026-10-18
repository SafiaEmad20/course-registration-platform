package com.project.useCases;

import java.util.Scanner;

import com.project.Dao.CourseDao;
import com.project.Dao.CourseDaoImpl;
import com.project.exceptions.StudentException;

public class JoinWaitingListUseCase {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the Course ID of the full batch you want to wait for:");
		int cid = sc.nextInt();

		CourseDao dao = new CourseDaoImpl();

		try {
			System.out.println(dao.joinWaitingList(cid));
		} catch (StudentException e) {
			System.out.println(e.getMessage());
		}
	}
}