import java.sql.*;
import java.util.Scanner;

public class main {
    //Replace with user's database connection details
    private static final String url = "jdbc:postgresql://localhost:5432/School";
    private static final String user = "postgres";
    private static final String password = "teremi74";
    public static void main(String[] args){
        //main menu for user interaction
        Scanner sc = new Scanner(System.in);
        while(true) {
            printMenu();
            int ans = sc.nextInt();
            if(ans == 0){ //exit the program
                return;
            }else if(ans == 1){ //print all students
                getAllStudents();
            }else if(ans == 2){ //add a new student
                System.out.println("Enter the first name: ");
                String fName = sc.next();
                System.out.println("Enter the last name: ");
                String lName = sc.next();
                System.out.println("Enter the email: ");
                String email = sc.next();
                System.out.println("Enter the enrollment date: ");
                String enDate = sc.next();
                addStudent(fName, lName, email, enDate);
            }else if(ans == 3){ //update a student's data
                System.out.println("Enter the student id: ");
                int id = sc.nextInt();
                System.out.println("Enter the new email: ");
                String email = sc.next();
                updateStudentEmail(id, email);
            }else if(ans == 4){ //delete a student
                System.out.println("Enter the student id: ");
                int id = sc.nextInt();
                deleteStudent(id);
            }else{ //error catching
                System.out.println("INVALID INPUT");
            }
        }
    }

    public static void printMenu(){
        System.out.println("\n********************MAIN MENU********************");
        System.out.println("0. Exit");
        System.out.println("1. Print all students");
        System.out.println("2. Add a student");
        System.out.println("3. Update a student email");
        System.out.println("4. Delete a student");
        System.out.println("*************************************************\n");
    }

    /*The getAllStudents() function runs an SQL query on the database to retrieve all student data and prints it out*/
    public static void getAllStudents(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password); //connect to the database
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students"); //runs query
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()){ //output all students data collected
                System.out.print(resultSet.getInt("student_id") + " ");
                System.out.print(resultSet.getString("first_name") + " ");
                System.out.print(resultSet.getString("last_name") + " ");
                System.out.print(resultSet.getString("email") + " ");
                System.out.println(resultSet.getString("enrollment_date"));
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    /*The addStudent() function takes in data from user input and runs a query on the database to insert a new student into the students table*/
    public static void addStudent(String first_name, String last_name, String email, String enrollment_date){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password); //connect to database
            PreparedStatement statement = connection.prepareStatement("INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)"); // insertion query
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setString(3, email);
            statement.setDate(4, Date.valueOf(enrollment_date)); //setting parameters of the query to the given data
            statement.executeUpdate();
            System.out.println("Student Added");
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    /*The updateStudentEmail() function runs an SQL query on the database to change the given students email*/
    public static void updateStudentEmail(int student_id, String new_email){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password); //connect to database
            PreparedStatement statement = connection.prepareStatement("UPDATE students SET email = ? WHERE student_id = ?"); //update query
            statement.setString(1, new_email);
            statement.setInt(2, student_id); //setting parameters of the query to the given data
            statement.executeUpdate();
            System.out.println("Student Updated");
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    /*The deleteStudent() function runs an SQL query on the database to delete the given student from the database*/
    public static void deleteStudent(int student_id){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password); //connect to database
            PreparedStatement statement = connection.prepareStatement("DELETE FROM students WHERE student_id = ?"); //deletion query
            statement.setInt(1, student_id); //setting parameters of the query to the given data
            statement.executeUpdate();
            System.out.println("Student deleted");
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

}
