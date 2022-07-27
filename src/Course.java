import java.util.ArrayList;
import java.util.List;

public class Course {

    private String Name;
    private Integer Credits;
    private Department department;
    private Professor Instructor;
    private static List<Course> courses=new ArrayList<>();

    public Course(String Name){
        this.Name=Name;
        Credits=3;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getName() {
        return Name;
    }
    public void setCredits(int credits) {
        if(0<credits && credits<=6) Credits = credits;
    }
    public int getCredits() {
        return Credits;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public Department getDepartment() {
        return department;
    }
    public void setInstructor(Professor instructor) {
        Instructor = instructor;
    }
    public Professor getInstructor() {
        return Instructor;
    }
    @Override  
    public boolean equals(Object obj) {  
    if (obj == null)  return false;  
    if (obj == this)  return true;   
    if(this.getName()==((Course)obj).getName() && this.getCredits()==((Course)obj).getCredits()
    && this.getDepartment()==((Course)obj).getDepartment() && this.getInstructor()==((Course)obj).getInstructor()) return true;
    return false;
    }
    public static List<Course> getCourses() {
        return courses;
    }
    public static void addtocourses(Course course) {
        courses.add(course);
    }
    public static void removefromcourses(Course course) {
        courses.remove(course);
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.Name;
    }
}
