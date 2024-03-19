import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Application {
	 private final Connection conn;

	 public Application(Connection conn) {
		  // SQL Connection
		  this.conn = conn;
	 }

	 /**
	  * Get all students from the database
	  */
	 public void getAllStudents(){
		  try {
				Statement stmt = conn.createStatement();
				String SQL = "SELECT * FROM students";
				ResultSet rs = stmt.executeQuery(SQL);
				while (rs.next()) {
					 // Output results
					 System.out.println(rs.getInt("student_id") + " " + rs.getString("first_name") + " " + rs.getString("last_name") + " " + rs.getString("email") + " " + rs.getString("enrollment_date"));
				}
				System.out.println("\n");
		  } catch (SQLException e) {
				throw new RuntimeException(e);
		  }

	 }

	 /**
	  * Add a student to the database
	  * @param first_name: First name of the student
	  * @param last_name: Last name of the student
	  * @param email: Email of the student
	  * @param enrollment_date: Enrollment date of the student
	  */
	 public void addStudent(String first_name, String last_name, String email, String enrollment_date) {
		  try {
				Statement stmt = conn.createStatement();
				String SQL = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES ('" + first_name + "', '" + last_name + "', '" + email + "', '" + enrollment_date + "');";
				stmt.executeUpdate(SQL);

		  } catch (SQLException e) {
				throw new RuntimeException(e);
		  }
		  ;
	 }

	 /**
	  * Update a student's email
	  * @param student_id: ID of the student
	  * @param new_email: New email of the student
	  */
	 public void updateStudentEmail(int student_id, String new_email){
		  try {
				Statement stmt = conn.createStatement();
				String SQL = "update students set email='" + new_email + "' where student_id =" + student_id +";";

				stmt.executeUpdate(SQL);

		  } catch (SQLException e) {
				throw new RuntimeException(e);
		  }
	 }

	 /**
	  * Delete a student from the database
	  * @param student_id: ID of the student
	  */
	 public void deleteStudent(int student_id){
		  try {
				Statement stmt = conn.createStatement();
				String SQL = "delete from students where student_id =" + student_id +";";

				stmt.executeUpdate(SQL);

		  } catch (SQLException e) {
				throw new RuntimeException(e);
		  }
	 }

	 /**
	  * Start the application and run it untill exit is hit
	  */
	 public void startApp (){
		  Scanner scanner = new Scanner(System.in);
		  System.out.println("Welcome to the Student Database Application");
		  while (true) {
				// List of options
				System.out.println("Please select an option:");
				System.out.println("1. View all students");
				System.out.println("2. Add a student");
				System.out.println("3. Update a student's email");
				System.out.println("4. Delete a student");
				System.out.println("5. Exit");

				// Read user input
				int choice = scanner.nextInt();
				switch (choice) {
					 case 1 -> getAllStudents();
					 // Prompt user for data and call the appropriate method
					 case 2 -> {
						  System.out.println("Enter the student's first name:");
						  String first_name = scanner.next();
						  System.out.println("Enter the student's last name:");
						  String last_name = scanner.next();
						  System.out.println("Enter the student's email:");
						  String email = scanner.next();
						  System.out.println("Enter the student's enrollment date (YYYY-MM-DD):");
						  String enrollment_date = scanner.next();
						  addStudent(first_name, last_name, email, enrollment_date);
					 }
					 case 3 -> {
						  System.out.println("Enter the student's ID:");
						  int student_id = scanner.nextInt();
						  System.out.println("Enter the student's new email:");
						  String new_email = scanner.next();
						  updateStudentEmail(student_id, new_email);
					 }
					 case 4 -> {
						  System.out.println("Enter the student's ID:");
						  int student_id2 = scanner.nextInt();
						  deleteStudent(student_id2);
					 }
					 case 5 -> {
						  System.out.println("Goodbye!");
						  try {
								conn.close();
						  } catch (SQLException e) {
								throw new RuntimeException(e);
						  }
						  System.exit(0);
					 }
				}

		  }
	 }

	 public static void main(String[] args) {
		  PostgreSQLJDBCConnection db = new PostgreSQLJDBCConnection();
		  Connection conn = db.startConnection();
		  Application app = new Application(conn);
		  app.startApp();
	 }
}
