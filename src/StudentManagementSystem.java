
import java.io.File;

import java.io.PrintWriter;

import java.util.Scanner;

public class StudentManagementSystem {
    static Scanner sc;
   //To store student objects in an array
    private static Student[] students = new Student[100];
  
    static int no_of_students = 0;


    private static void availableSeats(){
        
        int no_of_seats =  students.length- no_of_students;
        System.out.println("Available seats: "+ no_of_seats);
    }

    private static void registerStudent(){
      
        if (no_of_students==students.length) {
            System.out.println("No available seats");
        } else {
            System.out.print("Enter student's name: ");
            String studentName=sc.next();
           
            System.out.print("Enter student's ID: ");
            String studentId=sc.next();
          
            Student newStudent = new Student(studentId,studentName);
            students[no_of_students++]=newStudent;
           
        }
       
    }

    private static void deleteStudent(String deleteId){
        for (int i = 0; i < no_of_students; i++) {
            if (students[i] != null && students[i].getStudentID().equals(deleteId)) {
                for (int j = i; j < students.length-1; j++) {
                    students[j] = students[j+1];
                }
                no_of_students--;
                storeStudent();
                break;
            } else{
                System.out.println("Student not found");
            }
          }
          
        
    }

    private static void findStudent(String findId){
      for (int i = 0; i < no_of_students; i++) {
        if (students[i] != null && students[i].getStudentID().equals(findId)) {
            System.out.println("Student found: " + students[i].getStudentName() + ", ID: " + students[i].getStudentID());
            return;
        } else{
            System.out.println("Student not found.");
            break;
        }
      }

    
    }

    private static void storeStudent(){
        try {
             File output = new File("studentList.txt");
             PrintWriter writer = new PrintWriter(output);
            

             for (int i = 0; i < no_of_students; i++) {
                writer.println("Name: "+students[i].getStudentName() + ", Student ID: "+ students[i].getStudentID());
               
              }
              System.out.println("Stored Siccessfully.");
              writer.close();
        

        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void loadStudent(){
        try {
            File input = new File("studentList.txt");
            Scanner in = new Scanner(input);
            while (in.hasNextLine()) {
               String studentDetails = in.nextLine();
               System.out.println(studentDetails); 
              
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void viewStudentList(){
        for (int i = 0; i < students.length-1; i++) {
            for (int j= 0;j < students.length-i-1; j++)
            {
                if(students[j] != null && students[j + 1] != null && (int)students[j].getStudentName().charAt(0)>(int)students[j+1].getStudentName().charAt(0)){
                    Student temp = students[j];
                    students[j]= students[j+1];
                    students[j+1] = temp;
    
                }
            }
           
        }
        for (int m = 0; m < students.length; m++) {
            if (students[m] != null) {
                System.out.println(students[m].getStudentName());
            }
            
        }
    }

   

    private static void moduleMarks(String id){
        boolean studentFound = false;

        for (int i = 0; i < no_of_students; i++) {
            if (students[i] != null && students[i].getStudentID().equals(id)) {
                System.out.print("Enter Module 1 marks: ");
                int mark1 = sc.nextInt();
                System.out.print("Enter Module 2 marks: ");
                int mark2 = sc.nextInt();
                System.out.print("Enter Module 3 marks: ");
                int mark3 = sc.nextInt();

                Module module = new Module(mark1,mark2,mark3);
                students[i].setModule(module);
                System.out.println("Saved successfully.");
                studentFound = true;
                break; // Once the student is found, we can exit the loop
            }
            
        }
        if (studentFound == false) {
            System.out.println("Student not found.");
        }
        
       

    }

    private static void viewSummary(){
        System.out.println("Total student registrations: " + no_of_students);
       
        int module1PassCount = 0;
        int module2PassCount = 0;
        int module3PassCount = 0;

        for (int i = 0; i < no_of_students; i++) {
            if (students[i] != null) {
                Module module = students[i].getModule();
                if (module != null) {
                    if (module.getModule1Marks() > 40) {
                        module1PassCount++;
                    }
                    if (module.getModule2Marks() > 40) {
                        module2PassCount++;
                    }
                    if (module.getModule3Marks() > 40) {
                        module3PassCount++;
                    }
                }
            }
        }

        System.out.println("Total students who scored more than 40 in Module 1: " + module1PassCount);
        System.out.println("Total students who scored more than 40 in Module 2: " + module2PassCount);
        System.out.println("Total students who scored more than 40 in Module 3: " + module3PassCount);
    }

    private static void completeReport(){
        System.out.println("Complete Report:");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-15s %-20s %-15s %-15s %-15s %-15s %-15s\n",
                "Student ID", "Student Name", "Module 1", "Module 2", "Module 3", "Total", "Average", "Grade");
        System.out.println("------------------------------------------------------------");

        for (int i = 0; i < no_of_students; i++) {
            if (students[i] != null) {
                Student student = students[i];
                Module module = student.getModule();

                if (module != null) {
                    int module1Marks = module.getModule1Marks();
                    int module2Marks = module.getModule2Marks();
                    int module3Marks = module.getModule3Marks();

                    int totalMarks = module1Marks + module2Marks + module3Marks;
                    double averageMarks = module.averageMarks();
                    String grade = module.gradeOfModule();

                    System.out.printf("%-15s %-20s %-15d %-15d %-15d %-15d %-15.2f %-15s\n",
                            student.getStudentID(), student.getStudentName(),
                            module1Marks, module2Marks, module3Marks,
                            totalMarks, averageMarks, grade);
                }
            }
        }

    }

    private static void showMenu(){
        sc = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("");
            System.out.println("-------------------------------------");
            System.out.println("1. Check available seats");
            System.out.println("2. Register Student with ID");
            System.out.println("3. Delete student.");
            System.out.println("4. Find student (with student ID)");
            System.out.println("5. Store student details");
            System.out.println("6. Load student details");
            System.out.println("7. View the list of students");
            System.out.println("8. View more");
            System.out.println("9. Exit");
    
            System.out.println("-------------------------------------");
            System.out.println("");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("");
                    availableSeats();
                    break;

                case 2:
                    System.out.println("");
                    registerStudent();
                    break;

                case 3:
                    System.out.println("");
                    System.out.print("Enter Student ID to delete: ");
                    String deleteId=sc.next();
                    deleteStudent(deleteId);
                    break;

                case 4:
                    System.out.println("");
                    System.out.print("Enter Student ID to find: ");
                    String findId = sc.next();
                    findStudent(findId);
                    break;

                case 5:
                    System.out.println("");
                    storeStudent();
                    break;

                case 6:
                    System.out.println("");
                    sc.nextLine(); 
                    
                    
                    loadStudent();
                    break;

                case 7:
              
                    viewStudentList();
                    break;

                case 8:
                    System.out.println("");
                    showSubMenu();
                    break;

                default:
                    break;
            }
        } while (choice != 9);
        
        sc.close();
    }

    private static void showSubMenu(){
        char option = 0;

        do {
            System.out.println("a. To enter module marks:");
            System.out.println("b. Generate a summary of the system which includes");
            System.out.println("c. Generate complete report with list of students includes");
            System.out.println("d. exit");
          
            System.out.print("Enter your option: ");
            option = sc.next().charAt(0);
    
                        switch (option) {
                            case 'a':
                                System.out.println("");
                                System.out.print("Enter student ID: ");
                                String stdID = sc.next();
                                moduleMarks(stdID);
                                break;
    
                            case 'b':
                                System.out.println("");
                                viewSummary();
                                break;
    
                            case 'c':
                                System.out.println("");
                                completeReport();
                                break;
    
                            default:
                                break;
                        }
        } while (option != 'd');
       
    }




    public static void main(String[] args) {
       
        System.out.println("-------------------------------------");
        System.out.println("Welcome to Student Management System.");
        showMenu();  
       
    }

    
}
