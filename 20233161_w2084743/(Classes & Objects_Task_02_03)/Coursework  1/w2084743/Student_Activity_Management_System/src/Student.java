public class Student {
    private String student_id;
    private String student_name;
    public Module[] modules = new Module[3];
    private double total_marks;
    private double average;
    private String grade;

    public Student(String student_id, String student_name){
        this.student_id = student_id;
        this.student_name = student_name;
    }
    public String get_student_id(){
        return student_id;
    }
    public String get_student_name(){
        return student_name;
    }
    public void set_student_id(String student_id){
        this.student_id = student_id;
    }
    public void set_student_name(String student_name){
        this.student_name = student_name;
    }
    public String get_grade() {
        return grade;
    }
    public double get_average() {
        return average;
    }
    public double get_total_marks() {
        return total_marks;
    }

    public Module[] get_modules(){
        return modules;
    }
    public void set_module_marks(double[] module_marks){
        for (int Y = 0;Y < modules.length;Y++){
            modules[Y] = new Module(module_marks[Y]);
        }
    }
    public void set_module_marks(int position, Module module){
        modules[position] = module;
    }
    public void calculate_module_results(){
        for (Module module : modules){
            total_marks += module.get_module_marks();
        }
        average = total_marks / modules.length;
        if (average >= 80){
            grade = "Distinction";
        }
        else if (average >= 70){
            grade = "Merit";
        }
        else if (average >= 40){
            grade = "Pass";
        }
        else {
            grade = "Fail";
        }
    }

    public boolean check_all_modules_passed() {
        boolean all_passed = true;
        for (Module module : modules){
            if (module.get_module_marks() < 40) {
                all_passed = false;
                break;
            }
        }
        return all_passed;
    }
}
