import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class Student {

    private String Student;
    private String Name;
    private LocalDate BirthDate;
    private Department department;
    private static List<Student> students=new ArrayList<>();
    private List<Course> courses=new ArrayList<>();
    private List<GradeReport> gradeReport=new ArrayList<>();

    public Student(String Student){
        this.Student=Student;
    }
    public String getStudent() {
        return Student;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getName() {
        return Name;
    }
    public void setBirthDate(LocalDate birthDate) {
        BirthDate = birthDate;
    }
    public LocalDate getBirthDate() {
        return BirthDate;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public Department getDepartment() {
        return department;
    }
    public List<Course> getCourses() {
        return courses;
    }
    public boolean takeCourse(Course course) {
        if(!courses.contains(course)){
            courses.add(course);
            gradeReport.add(new GradeReport(this, course));
            return true;
        }
        return false;
    }
    public void finishCourse(Course course,double score) {
        if(courses.contains(course) && 0<score && score<20){
            for (GradeReport gradereport : gradeReport) {
                if(gradereport.getCourse().equals(course))
                    { gradereport.setGrade(score); break; }
            }
        }
    }
    public void removeCourse(Course course){
        courses.remove(course);
        for (int i = 0; i < gradeReport.size(); i++) {
            if(gradeReport.get(i).getCourse().equals(course)){
                gradeReport.remove(gradeReport.get(i));
                break;
            }
        }
        
    }
    public List<GradeReport> getGradeReport() {
        return gradeReport;
    }
    public static List<Student> getStudents() {
        return students;
    }
    public static void addtostudents(Student student) {
        students.add(student);
    }
    public static void removefromstudents(Student student) {
        students.remove(student);
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.Student;
    }
    public static Student search(String Student, String name) {
        for (Student st : students) 
            if(st.getStudent().equals(Student) && st.getName().equals(name)) return st;
        return null;
    }
    public static Student searchp(String Student) {
        for (Student st : students) 
            if(st.getStudent().equals(Student)) return st;
        return null;
    }
}
