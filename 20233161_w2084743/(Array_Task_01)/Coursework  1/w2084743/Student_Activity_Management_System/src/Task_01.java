import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.lang.NumberFormatException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//class
public class Task_01 {
    private static Scanner input = new Scanner(System.in);//Scanner for user input
    private static final int capacity = 100;//Maximum capacity of students
    private static String[][] students = new String[100][2];//2D-Array to store each student name and student ID
    private static File new_file = new File("Students_Management.txt");//Creating an instance of the File class representing the file "Students_Management.txt"

    //main method
    public static void main(String[] args){
        initializer(students);//Calling initializer method
        create_file(new_file);//Calling create_file method
        try{
            boolean exit = false;
            while (!exit){
                int option = menu();
                switch (option){
                    case (1):
                        available_seats(students);//Calling available seats method
                        break;
                    case (2):
                        register_student(students);//Calling register student method
                        break;
                    case (3):
                        delete_student(students);//Calling delete students method
                        break;
                    case (4):
                        find_student(students);//Calling find student method
                        break;
                    case (5):
                        dump_details_to_file(new_file, students);//Calling dump students details to the file method
                        break;
                    case (6):
                        load_details_from_file(new_file, students);//Calling load students details from the file method
                        break;
                    case (7):
                        view_details(students);//Calling view student details method
                        break;
                    case (8):
                        exit = true;
                        System.out.println("You have successfully exited from the university intake system");
                        break;
                    default:
                        System.out.println("Your choice is incorrect.Please enter a given option that been given above");
                }
            }
        }
        catch (InputMismatchException e){
            System.out.println("Invalid input.please enter a valid input");
        }
    }
    //menu method
    public static int menu(){
        try {
            //Menu:
            System.out.println();
            System.out.println("***MENU***");
            System.out.println("1.Check available seats");
            System.out.println("2.Register student(with ID)");
            System.out.println("3.Delete student");
            System.out.println("4.Find student(with student ID)");
            System.out.println("5.Store student details into a file");
            System.out.println("6.Load student details from the file to the system");
            System.out.println("7.View the list of students based on their names");
            System.out.println("8.To exit from this university intake system");
            System.out.println();
            System.out.println("Please enter your option that you prefer: ");
            String value_01 = input.nextLine();
            int value = Integer.parseInt(value_01);
            return value;
        }
        catch (NumberFormatException e){
            System.out.println("Error occurred you cannot convert string that contain data(text) in to integer or a string that contain double value to integer: " + e);
        }
        return 0;
    }

    //available_seats method
    public static void available_seats(String[][] students){
        for (int K = 0;K < students.length;K++){
            if (students[K][0].equals("empty") && students[K][1].equals("empty")){
                System.out.println("Student slot " + (K + 1) + " is available for the student registration");
            }
            else {
                System.out.println("Student slot " + (K + 1) + " is already been occupied by someone. Please try again!");
            }
        }
    }

    //register_student method
    public static void register_student(String[][] students){
        try {
            System.out.println("Enter the student name that you want register: ");
            String student = input.nextLine();
            System.out.println("Enter the student id to register a student: ");
            String student_id = input.nextLine();
            for (int R = 0;R < students.length;R++){
                if (0 < Integer.parseInt(student_id) && Integer.parseInt(student_id) <= 100){
                    boolean result = containsId(students, student_id);
                    if (students[R][0].equals("empty") && students[R][1].equals("empty")){
                        if (!(result)){
                            students[R][0] = student;
                            students[R][1] = student_id;
                            System.out.println("You have successfully register the student.");
                            break;
                        }
                        else {
                            System.out.println("This student id is already existing");
                            break;
                        }
                    }
                    else{
                        if (student.equals(students[R][0]) && student_id.equals(students[R][1])){
                            System.out.println("Actually this student slot " + (R + 1) + " has been occupied by student name called " + students[R][0] + " And it's student id is " + students[R][1]);
                            break;
                        }
                    }
                }
                else {
                    System.out.println("You have exceed the bound that the student id can get. So please enter a valid bound value for the input.");
                    break;
                }
            }
        }
        catch (NumberFormatException e){
            System.out.println("Error occurred you cannot convert string that contain data(text) in to integer or a string that contain double value to integer: " + e);
        }
    }

    //delete_student method
    public static void delete_student(String[][] students){
        System.out.println("Enter the student name that you want to delete: ");
        String del_student = input.nextLine();
        System.out.println("Enter the student id that you want to delete: ");
        String del_student_id = input.nextLine();
        int iterator = 0;
        for (int d = 0;d < students.length;d++) {
            if (del_student.equals(students[d][0]) && del_student_id.equals(students[d][1])){
                students[d][0] = "empty";
                students[d][1] = "empty";
                System.out.println("You have successfully deleted the student slot " + (d + 1) + " contain details");
                break;
            }
            else {
                iterator = iterator + 1;
            }
        }
        if ( iterator == students.length ) {
            System.out.println("You entered name " + del_student + " and you entered student id " + del_student_id + " actually are invalid inputs and these are not exist in the array.");
        }
    }

    //find_student method
    public static void find_student(String[][] students){
        try {
            System.out.println("Enter the student id that belong to the student that you want to find: ");
            String student_to_find_id = input.nextLine();
            int iterator = 0;
            for (int Z = 0;Z < students.length;Z++){
                if (0 < Integer.parseInt(student_to_find_id) && Integer.parseInt(student_to_find_id) <= 100){
                    if (!(students[Z][0].equals("empty") && students[Z][1].equals("empty") ) && student_to_find_id.equals(students[Z][1])){
                        System.out.println();
                        System.out.println("Student slot " + (Z + 1) + " finds the student that you looking for and his/her name is " + students[Z][0]);
                        System.out.println("Student find successfully completed");
                        System.out.println();
                        break;
                    }
                    else {
                        iterator = iterator + 1;
                    }
                }
                else {
                    System.out.println("You just entered id called " + student_to_find_id + " is an invalid input and it is not exist in the array.");
                    break;
                }
            }
            if (iterator == students.length) {
                System.out.println("No student with this id represent in the array.");
            }
        }
        catch (NumberFormatException e){
            System.out.println("Error occurred you cannot convert a string that contain data(text) to Integer or a string that contain double value to Integer: " + e);
        }
    }

    //dump_details_to_file method
    public static void dump_details_to_file(File new_file, String[][] students){
        try {
            FileWriter student_write = new FileWriter(new_file);
            for (int Y = 0;Y < students.length;Y++){
                if (students[Y][0].equals("empty") && students[Y][1].equals("empty")){
                    student_write.write("empty : empty\n");
                    if (Y == (students.length - 1)){
                        System.out.println("You have wrote the student details successfully to the file.");
                        break;
                    }
                }
                else {
                    student_write.write(students[Y][0] + " : " + students[Y][1] + "\n");
                    if (Y == (students.length - 1)){
                        System.out.println("You have wrote the student details successfully to the file.");
                        break;
                    }
                }
            }
            student_write.close();
        }
        catch (IOException e){
            System.out.println("Error occurred while dumping data to the file: " + e);
        }
    }

    //load_details_from_file method
    public static void load_details_from_file(File new_file, String[][] students){
        try {
            Scanner read_file = new Scanner(new_file);
            int x = 0;
            while (read_file.hasNext() || x < students.length ) {
                String file_line = read_file.nextLine();
                String[] line_parts = file_line.split(" : ");
                students[x][0] = line_parts[0];
                students[x][1] = line_parts[1];
                x = x + 1;
                System.out.println(file_line);
            }
            System.out.println("\nYou have successfully got the student details from the file.");
            read_file.close();
        }
        catch (NoSuchElementException e){
            System.out.println("Error occurred No data been found at the start when you select the load data from the file option: " + e);
        }
        catch (IOException e){
            System.out.println("Error occurred while loading data from the file : " + e);
        }
    }

    //view_details method
    public static void view_details(String[][] students){
        Arrays.sort(students, Comparator.comparing(I -> I[0]));
        for (String[] intaker : students){
            System.out.println(Arrays.toString(intaker));
        }
    }

    //initializer method
    public static void initializer(String[][] students){
        for (int i = 0;i < students.length;i++){
            for (int j = 0;j < students[i].length;j++){
                students[i][j] = "empty";
            }
        }
    }

    public static boolean containsId(String[][] students, String id){
        for (String[] start : students){
            if (start[1].equals(id)){
                return true;
            }
        }
        return false;
    }

    //create_file method
    public static void create_file(File new_file){
        try {
            boolean file_created = new_file.createNewFile();
            if (new_file.exists()){
                System.out.println("This " + new_file.getName() + " file is already existing");
            }
            else {
                if (file_created){
                    System.out.println(new_file.getName() + "file created Successfully");
                }
                else {
                    System.out.println("While creating " + new_file.getName() + " error occurred.");
                }
            }
        }
        catch (IOException e){
            System.out.println("Error occurred while creating the file: " + e);
        }
    }
}
