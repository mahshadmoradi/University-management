import java.util.ArrayList;
import java.util.List;

public class Department {
    private String Name;
    private String Id;
    private List<Student> students=new ArrayList<>();
    private List<Professor> faculty=new ArrayList<>();
    private static List<Department> departments=new ArrayList<>();

    Department(String Name){
        this.Name=Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getName() {
        return Name;
    }
    public void setId(String id) {
        Id = id;
    }
    public String getId() {
        return Id;
    }
    public List<Student> getStudents() {
        return students;
    }
    public List<Professor> getFaculty() {
        return faculty;
    }
    @Override  
    public boolean equals(Object obj) {  
    if (obj == null)  return false;  
    if (obj == this)  return true;   
    if(this.getName()==((Department)obj).getName() && this.getId()==((Department)obj).getId()) return true;
    return false;
    }  
    public static List<Department> getDepartments() {
        return departments;
    }
    public static Department finddepartment(String name) {
        for (Department department : Department.getDepartments()) {
            if(department.getName()==name) return department;
        }
        return null;
    }
    public static void addtodepartments(Department department) {
        departments.add(department);
    }
    public static void removefromdepartments(Department department) {
        departments.remove(department);
    }
    public void addtostudents(Student student) {
        students.add(student);
    }
    public void addtofaculty(Professor professor) {
        faculty.add(professor);
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.Name;
    }
    public static Department search(String name) {
        for (Department department : departments) 
            if(department.getName().equals(name)) return department;
        return null;
    }
}
