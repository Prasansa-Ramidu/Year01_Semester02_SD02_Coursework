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
    private static Student[] studentsArray = new Student[100];//Array to store student details
    private static File new_file = new File("Students_Management.txt");//Creating an instance of the File class representing the file "Students_Management.txt"

    //main method
    public static void main(String[] args){
        initializer(students);//Calling initializer method

        initializerArray(studentsArray);//Calling initializerArray method

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
                        load_details_from_file(new_file, students, studentsArray);//Calling load students details from the file method
                        break;
                    case (7):
                        view_details(students);//Calling view student details method
                        break;
                    case (8):
                        additional_students_controls(students);//Calling additional students control method
                        break;
                    case (9):
                        generate_system_summery();//Calling generate system summery method
                        break;
                    case (10):
                        generate_students_report();//Generate students report method
                        break;
                    case (11):
                        exit = true;
                        System.out.println("You have successfully exited from the university intake system");//To exit from the university intake system
                        break;
                    default:
                        System.out.println("Your choice is incorrect.Please enter a given option that been given above");//To represent the user entered an incorrect option
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
            System.out.println("8.To handle additional students details");
            System.out.println("9.Generate system summary");
            System.out.println("10.Generate students report");
            System.out.println("11.To exit from this university intake system");
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
            System.out.println("Enter the student name that you want to register: ");
            String student = input.nextLine();
            System.out.println("Enter the student id to register a student: ");
            String student_id = input.nextLine();

            for (int R = 0;R < students.length;R++){
                if (0 < Integer.parseInt(student_id) && Integer.parseInt(student_id) <= 100){
                    boolean result = containsId(students, student_id);
                    if (students[R][0].equals("empty") && students[R][1].equals("empty") && studentsArray[R].get_student_name().equals("empty") && studentsArray[R].get_student_id().equals("empty")){
                        if (!(result) ){
                            students[R][0] = student;
                            students[R][1] = student_id;

                            studentsArray[R].set_student_name( student );
                            studentsArray[R].set_student_id( student_id );

                            System.out.println("You have successfully register the student.");
                            break;
                        }
                        else {
                            System.out.println("This student id already existing");
                            break;
                        }
                    }
                    else{
                        if (student.equals(students[R][0]) && student_id.equals(students[R][1]) && student.equals( studentsArray[R].get_student_name() ) && student_id.equals( studentsArray[R].get_student_id() )){
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
            if (del_student.equals(students[d][0]) && del_student_id.equals(students[d][1]) && studentsArray[d].get_student_name().equals(students[d][0]) && studentsArray[d].get_student_id().equals(students[d][1])){
                students[d][0] = "empty";
                students[d][1] = "empty";

                studentsArray[d].set_student_name("empty");
                studentsArray[d].set_student_id("empty");
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
            for (int Y = 0;Y < studentsArray.length;Y++){
                if (studentsArray[Y].get_student_name().equals("empty") && studentsArray[Y].get_student_id().equals("empty")){
                    student_write.write("empty : empty : 0 : 0 : 0\n");
                    if (Y == (studentsArray.length - 1)){
                        System.out.println("You have wrote the student details successfully to the file.");
                        break;
                    }
                }
                else {
                    student_write.write(studentsArray[Y].get_student_name() + " : " + studentsArray[Y].get_student_id() + " : " + studentsArray[Y].modules[0].get_module_marks() +  " : " + studentsArray[Y].modules[1].get_module_marks() + " : " + studentsArray[Y].modules[2].get_module_marks()+"\n");
                    if (Y == (studentsArray.length - 1)){
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
    public static void load_details_from_file(File new_file, String[][] students, Student[] studentsArray){
        try {
            Scanner read_file = new Scanner(new_file);
            int x = 0;
            while (read_file.hasNext() || x < studentsArray.length ) {
                String file_line = read_file.nextLine();
                String[] line_parts = file_line.split(" : ");

                studentsArray[x].set_student_name( line_parts[0] );
                studentsArray[x].set_student_id( line_parts[1] );
                studentsArray[x].modules[0].set_module_marks( Double.parseDouble( line_parts[2] ) );
                studentsArray[x].modules[1].set_module_marks( Double.parseDouble( line_parts[3] ) );
                studentsArray[x].modules[2].set_module_marks( Double.parseDouble( line_parts[4] ) );

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

    //initializerArray method
    public static void initializerArray(Student[] studentsArray){
        for (int i = 0; i < studentsArray.length; i++) {
            studentsArray[i] = new Student("empty", "empty");
            for (int x = 0; x < studentsArray[i].modules.length; x++) {
                studentsArray[i].modules[x] = new Module(0);
            }
        }
    }

    //containId method
    public static boolean containsId(String[][] students, String id){
        for (String[] start : students){
            if (start[1].equals(id)){
                return true;
            }
        }
        return false;
    }

    //additional_students_controls method
    public static void additional_students_controls(String[][] students){
        while (true) {
            System.out.println("Enter the student id number: ");
            String student_id = input.nextLine();

            Student requested_student = null;
            for (Student s: studentsArray) {
                if (student_id.equals(s.get_student_id())) {
                    requested_student = s;
                    break;
                }
            }

            if (requested_student == null) {
                System.out.println("Wrong Student id Entered...Please check again!");
                break;
            } else {
                for (int i=1; i<4; i++) {
                    System.out.println("Enter module " + i + " marks: ");
                    while (true) {
                        try {
                            double marks = Double.parseDouble(input.nextLine());
                            if (marks >= 0 && marks <= 100) {
                                Module m = new Module(marks);
                                requested_student.set_module_marks(i - 1, m);
                                break;
                            } else {
                                System.out.println("Marks should be between 0 & 100...Please enter module " + i + " marks again!");
                            }
                        } catch (Exception ex) {
                            System.out.println("Marks are not in expected format...Please enter module " + i + " marks again!");
                        }
                    }
                }
                requested_student.calculate_module_results();
                break;
            }
        }
    }

    //generate_system_summery method
    public static void generate_system_summery(){
        int registered_count = 0;
        for (Student s: studentsArray) {
            if (s.get_student_id().equals("empty")) {
                break;
            } else {
                registered_count++;
            }
        }
        System.out.println("No. of registered students: " + registered_count);
        System.out.println("List of students passed all modules");
        for (Student s: studentsArray) {
            if (s.check_all_modules_passed()) {
                System.out.println("id: " + s.get_student_id() + " === name: " + s.get_student_name());
            }
        }
    }

    //generate_students_report method
    public static void generate_students_report(){
        for (Student s: studentsArray) {
            if (!s.get_student_id().equals("empty")) {
                System.out.println("id: " + s.get_student_id() + " === name: " + s.get_student_name());
                System.out.println("module 1: " + s.get_modules()[0].get_module_marks() + " === module 2: " + s.get_modules()[1].get_module_marks() + " === module 3: " + s.get_modules()[2].get_module_marks());
                System.out.println("total: " + s.get_total_marks() + " === average: " + s.get_average() + " === grade: " + s.get_grade());
                System.out.println("\n");
            }
        }
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
