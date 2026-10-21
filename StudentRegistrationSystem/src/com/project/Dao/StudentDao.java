package com.project.Dao;

import java.util.List;

import com.project.bean.Course;
import com.project.bean.Student;
import com.project.exceptions.StudentException;
import com.project.bean.Course;

public interface StudentDao {

	
		public String registerStudent(Student student);
		
		public Student loginStudent(String username, String password)throws StudentException;
		
		public String updateStudent(Student student);
		
		public Student getStudentObj();
		
		public List<Student> getAllStudents() throws StudentException;
		public List<Course> getMyEnrolledCourses(int roll)
        throws StudentException;
		
}
