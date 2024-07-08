
public class Module {
    private int module1Marks;
    private int module2Marks;
    private int module3Marks;

    public Module(){

    }

    public Module(int module1Marks, int module2Marks, int module3Marks) {
        this.module1Marks = module1Marks;
        this.module2Marks = module2Marks;
        this.module3Marks = module3Marks;
    }


    public int getModule1Marks() {
        return module1Marks;
    }
    public void setModule1Marks(int module1Marks) {
        this.module1Marks = module1Marks;
    }
    public int getModule2Marks() {
        return module2Marks;
    }
    public void setModule2Marks(int module2Marks) {
        this.module2Marks = module2Marks;
    }
    public int getModule3Marks() {
        return module3Marks;
    }
    public void setModule3Marks(int module3Marks) {
        this.module3Marks = module3Marks;
    }

    public double averageMarks(){
        double average_marks = (module1Marks + module2Marks + module3Marks)/3;
        return average_marks;
    }
    
    public String gradeOfModule(){
        double average_marks = averageMarks();
        if (average_marks >= 80) {
            return "Distinction";    
        } else if (average_marks >= 70) {
            return "Merit";
        } else if(average_marks >= 40){
            return "Pass";
        } else{
            return "Fail"; 
        }
    }


    
}
