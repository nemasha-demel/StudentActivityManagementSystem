
public class Student{

    private String studentID;
    private String studentName;
    private Module module;

    public Student(){
        this.module = new Module();

    }

    public Student(String studentID, String studentName) {
        super();
        this.studentID = studentID;
        this.studentName = studentName;
    }


    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getGrade(){
        return module.gradeOfModule();
    }


    
  
}