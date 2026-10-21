package com.project.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.project.bean.Student;
import com.project.exceptions.StudentException;
import com.project.utility.DBUtil;
import com.project.bean.Course;
import org.mindrot.jbcrypt.BCrypt;
public class StudentDaoImpl implements StudentDao{
	
	static int studentloggedin;

	@Override
	public String registerStudent(Student student) {

		String message = "Registration failed";
		
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps =
			conn.prepareStatement("insert into student(name,email,password,coursename) values(?,?,?,?)");
			
			ps.setString(1, student.getName());
			ps.setString(2, student.getEmail());
			String hashedPassword = BCrypt.hashpw(
            student.getPassword(),
            BCrypt.gensalt(12)
             );
			ps.setString(3, hashedPassword);
			ps.setString(4, student.getCourse());
			
			int x = ps.executeUpdate();
			
			if (x>0) {
				
				message = "Student registration successful."; 	
				
			}
			
		} catch (SQLException e) {
			
			message=e.getMessage();
			
		}
		
		
		
		return message;
	}

	@Override
	public String updateStudent(Student student) {
		String message = "Update failed";
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = 
					conn.prepareStatement("UPDATE student SET name = ? , password = ? , coursename = ? where roll = ?");

			ps.setString(1, student.getName());
			ps.setString(2, student.getPassword());
			ps.setString(3, student.getCourse());
			ps.setInt(4, studentloggedin);
			
			int x = ps.executeUpdate();
			
			if (x>0) {
				
				message = "Student details updated successfully."; 	
				
			}
			
				
		} catch (SQLException e) {
			 
			message = e.getMessage();
		}
		
		
		return message;
	}


@Override
public Student loginStudent(String username, String password) throws StudentException {

    Student student = null;

    try (Connection conn = DBUtil.provideConnection()) {

        PreparedStatement ps = conn.prepareStatement(
            "SELECT * FROM student WHERE email = ?"
        );

        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            int r = rs.getInt("roll");
            String n = rs.getString("name");
            String e = rs.getString("email");
            String hashedPassword = rs.getString("password");
            String c = rs.getString("coursename");

            if (BCrypt.checkpw(password, hashedPassword)) {

                studentloggedin = r;

                student = new Student(r, n, e, hashedPassword, c);

            } else {
                throw new StudentException("Invalid Username or password.. ");
            }

        } else {
            throw new StudentException("Invalid Username or password.. ");
        }

    } catch (SQLException e) {
        throw new StudentException(e.getMessage());
    }

    return student;
}


	@Override
	public Student getStudentObj() {

		Student student = null;
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			
			PreparedStatement ps= conn.prepareStatement("select * from student where roll = ?");			
			
			ps.setInt(1, studentloggedin);
			
			
			ResultSet rs= ps.executeQuery();
			
			
				if(rs.next()) {
				
				int r = rs.getInt("roll");
				String n= rs.getString("name");
				String e= rs.getString("email");
				String p= rs.getString("password");
				String c = rs.getString("coursename");
				
				
			student=new Student(r, n, e, p, c);	
			
		}
				
		}catch (SQLException e) {
			
			student = null;
		}
		
		return student;
		
	}

	@Override
	public List<Student> getAllStudents() throws StudentException {
		
		List<Student> studentList = new ArrayList<>();
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("Select * from student");
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int roll = rs.getInt("roll");
				String n = rs.getString("name");
				
				
				Student student = new Student();
				student.setName(n);
				student.setRoll(roll);
				
				studentList.add(student);
			
			}
			
		} catch (SQLException e) {
			throw new StudentException(e.getMessage());
		}
		
		if (studentList.isEmpty()) {
			throw new StudentException("No student data found");
		}
		
		return studentList;
		
	}


@Override
public List<Course> getMyEnrolledCourses(int roll)
        throws StudentException {

    List<Course> courses = new ArrayList<>();

    String sql = "SELECT c.* " +
                 "FROM course c " +
                 "JOIN course_student cs ON c.cid = cs.cid " +
                 "WHERE cs.roll = ?";

    try (Connection conn = DBUtil.provideConnection()) {

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, roll);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            int cid = rs.getInt("cid");
            String cname = rs.getString("cname");
            int batch = rs.getInt("batch");
            int fee = rs.getInt("fee");
            int totalSeats = rs.getInt("totalseats");

            Course course = new Course(
                    cid, cname, batch, fee, totalSeats);

            courses.add(course);
        }

    } catch (SQLException e) {
        throw new StudentException(e.getMessage());
    }

    if (courses.isEmpty()) {
        throw new StudentException(
                "You are not enrolled in any courses yet.");
    }

    return courses;
}

	
}
