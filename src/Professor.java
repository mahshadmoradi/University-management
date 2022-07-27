import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Professor {

    private String Name;
    private LocalDate BirthDate;
    private Department department;
    private AcademicRank rank;
    private static List<Professor> professors=new ArrayList<>();
    private List<Course> courses=new ArrayList<>();

    public Professor(String Name){
        this.Name=Name;
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
     public void setRank(AcademicRank rank) {
        this.rank = rank;
    }
    public AcademicRank getRank() {
        return rank;
    }
    @Override  
    public boolean equals(Object obj) {  
    if (obj == null)  return false;  
    if (obj == this)  return true;   
    if(this.getName()==((Professor)obj).getName() && this.getBirthDate()==((Professor)obj).getBirthDate()
    && this.getDepartment()==((Professor)obj).getDepartment() && this.getRank()==((Professor)obj).getRank()) return true;
    return false;
    }
    public List<Course> getCourses() {
        return courses;
    }
    public static List<Professor> getProfessors() {
        return professors;
    }
    public static void addtoprofessors(Professor professor) {
        professors.add(professor);
    }
    public static void removefromprofessors(Professor professor) {
        professors.remove(professor);
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.Name;
    }
    public static Professor search(String name, String department) {
        for (Professor professor : professors) 
            if(professor.getName().equals(name) && professor.getDepartment().getName().equals(department)) return professor;
        return null;
    }
    public boolean addcourse(Course course) {
        if(!courses.contains(course))
        {
            courses.add(course);
            return true;
        }
        return false;
    }
}
