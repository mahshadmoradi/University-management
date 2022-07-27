import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class GodPanelControll implements Initializable{
    @FXML
    private ListView<Student> studentView;
    @FXML
    private ListView<Professor> professorView;
    @FXML
    private ListView<Department> departmentView;
    @FXML
    private ListView<Course> courseView;
    @FXML
    private AnchorPane addstudent,addprofessor,adddepartment,addcourse;
    @FXML
    private Button remove;
    @FXML
    private ImageView pic;
    @FXML
    private Label data;

    @FXML
    private Label wrongadd,wrongadd1,wrongadd2,wrongadd3;
    @FXML
    private TextField a,b,a1,a2,b2,a3,b3;
    @FXML
    private DatePicker birthdate,birthdate1;
    @FXML
    private ChoiceBox<Department> department,department1,department2;
    @FXML
    private ChoiceBox<AcademicRank> rank;
    @FXML
    private ChoiceBox<Professor> professor;


    int identifier;
    Student selectedStudent;
    Professor selectedpProfessor;
    Department selectedDepartment;
    Course selectedCourse;

    ObservableList<Student> slist=FXCollections.observableArrayList();
    ObservableList<Professor> plist=FXCollections.observableArrayList();
    ObservableList<Department> dlist=FXCollections.observableArrayList();
    ObservableList<Course> clist=FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addstudent.setVisible(false);
        addprofessor.setVisible(false);
        adddepartment.setVisible(false);
        addcourse.setVisible(false);

        remove.setVisible(false);
        pic.setVisible(false);
        slist.setAll(Student.getStudents());
        studentView.setItems(slist);
        plist.setAll(Professor.getProfessors());
        professorView.setItems(plist);
        dlist.setAll(Department.getDepartments());
        departmentView.setItems(dlist);
        clist.setAll(Course.getCourses());
        courseView.setItems(clist);
        
        studentView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Student> ov, Student old_val, Student new_val) -> {
            selectedStudent = studentView.getSelectionModel().getSelectedItem();
            if(selectedStudent!=null){
                addstudent.setVisible(false);
                addprofessor.setVisible(false);
                adddepartment.setVisible(false);
                addcourse.setVisible(false);
                courseView.getSelectionModel().clearSelection();
                departmentView.getSelectionModel().clearSelection();
                professorView.getSelectionModel().clearSelection();
                remove.setVisible(true);
                pic.setVisible(true);
                data.setText("Student Number :  "+selectedStudent.getStudent()+"\n \n Name :  "+selectedStudent.getName()+"\n \n Birthdate :  "+selectedStudent.getBirthDate()+"\n \n Department :  "+selectedStudent.getDepartment());
                data.setVisible(true);
                identifier=0;
            }
            else{
                remove.setVisible(false);
                pic.setVisible(false);
                data.setVisible(false);
            }
        });
        professorView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Professor> ov, Professor old_val, Professor new_val) -> {
            selectedpProfessor = professorView.getSelectionModel().getSelectedItem();
            if(selectedpProfessor!=null){
                addstudent.setVisible(false);
                addprofessor.setVisible(false);
                adddepartment.setVisible(false);
                addcourse.setVisible(false);
                studentView.getSelectionModel().clearSelection();
                courseView.getSelectionModel().clearSelection();
                departmentView.getSelectionModel().clearSelection();
                remove.setVisible(true);
                pic.setVisible(true);
                data.setText("Name :  "+selectedpProfessor.getName()+"\n \n Birthdate :  "+selectedpProfessor.getBirthDate()+"\n \n Department :  "+selectedpProfessor.getDepartment()+"\n \n Rank :  "+selectedpProfessor.getRank());
                data.setVisible(true);
                identifier=1;
            }
            else {
                remove.setVisible(false);
                pic.setVisible(false);
                data.setVisible(false);
            }
        });
        departmentView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Department> ov, Department old_val, Department new_val) -> {
            selectedDepartment = departmentView.getSelectionModel().getSelectedItem();
            if(selectedDepartment!=null){
                addstudent.setVisible(false);
                addprofessor.setVisible(false);
                adddepartment.setVisible(false);
                addcourse.setVisible(false);
                studentView.getSelectionModel().clearSelection();
                courseView.getSelectionModel().clearSelection();
                professorView.getSelectionModel().clearSelection();
                remove.setVisible(true);
                pic.setVisible(true);
                data.setText("Name :  "+selectedDepartment.getName()+"\n \n ID :  "+selectedDepartment.getId());
                data.setVisible(true);
                identifier=2;
            }
            else{
                remove.setVisible(false);
                pic.setVisible(false);
                data.setVisible(false);
            }
        });
        courseView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Course> ov, Course old_val, Course new_val) -> {
            selectedCourse = courseView.getSelectionModel().getSelectedItem();
            if(selectedCourse!=null){
                addstudent.setVisible(false);
                addprofessor.setVisible(false);
                adddepartment.setVisible(false);
                addcourse.setVisible(false);
                studentView.getSelectionModel().clearSelection();
                departmentView.getSelectionModel().clearSelection();
                professorView.getSelectionModel().clearSelection();
                remove.setVisible(true);
                pic.setVisible(true);
                data.setText("Name :  "+selectedCourse.getName()+"\n \n Credits :  "+selectedCourse.getCredits()+"\n \n Department :  "+selectedCourse.getDepartment()+"\n \n Instructor :  "+selectedCourse.getInstructor());
                data.setVisible(true);
                identifier=3;
            }
            else{
                remove.setVisible(false);
                pic.setVisible(false);
                data.setVisible(false);
            }
        });
    }


    public void remove() throws IOException {
        switch(identifier){
            case 0:
            Student.removefromstudents(selectedStudent);
            slist.setAll(Student.getStudents());
            studentView.setItems(slist);
            break;
            case 1:
            Professor.removefromprofessors(selectedpProfessor);
            plist.setAll(Professor.getProfessors());
            professorView.setItems(plist);
            break;
            case 2:
            Department.removefromdepartments(selectedDepartment);
            dlist.setAll(Department.getDepartments());
            departmentView.setItems(dlist);
            break;
            case 3:
            Course.removefromcourses(selectedCourse);
            clist.setAll(Course.getCourses());
            courseView.setItems(clist);
            break;
        } 
    }

    public void addStudent() {
        clear();
        addstudent.setVisible(true);
        department.getItems().setAll(Department.getDepartments());
    }

    public void addProfessor() {
        clear();
        addprofessor.setVisible(true);
        rank.getItems().setAll(AcademicRank.values());
        department1.getItems().setAll(Department.getDepartments());
    }

    public void addDepartment() {
        clear();
        adddepartment.setVisible(true);
    }

    public void addCourse() {
        clear();
        addcourse.setVisible(true);
        department2.getItems().setAll(Department.getDepartments());
        professor.getItems().setAll(Professor.getProfessors());
    }

    public void addstudent() throws IOException{
        if(a.getText().isEmpty()) wrongadd.setText("please enter student number.");
        else if(Student.searchp(a.getText())!=null) wrongadd.setText("the student number already exists");
        else {
            Student student=new Student(a.getText());
            if(!b.getText().isEmpty()) student.setName(b.getText());
            student.setBirthDate(birthdate.getValue());
            if(department.getValue()!=null){
                student.setDepartment(department.getValue());
                department.getValue().addtostudents(student);
            }
            Student.addtostudents(student);
            addstudent.setVisible(false);
            slist.setAll(Student.getStudents());
            studentView.setItems(slist);
        }
    }

    public void addprofessor() throws IOException{
        if(a1.getText().isEmpty()) wrongadd1.setText("please enter name.");
        else {
            Professor professor=new Professor(a1.getText());
            if(department1.getValue()!=null){
                professor.setDepartment(department1.getValue());
                department1.getValue().addtofaculty(professor);
            }
            professor.setBirthDate(birthdate1.getValue());
            professor.setRank(rank.getValue());
            Professor.addtoprofessors(professor);
            addprofessor.setVisible(false);
            plist.setAll(Professor.getProfessors());
            professorView.setItems(plist);
        }
    }

    public void adddepartment() throws IOException{
        if(a2.getText().isEmpty()) wrongadd2.setText("please enter name.");
        else if(Department.finddepartment(a2.getText())!=null) wrongadd.setText("the department already exists");
        else {
            Department department=new Department(a2.getText());
            department.setId(b2.getText());
            Department.addtodepartments(department);
            adddepartment.setVisible(false);
            dlist.setAll(Department.getDepartments());
            departmentView.setItems(dlist);
        }
    }

    public void addcourse() throws IOException{
        if(a3.getText().isEmpty()) wrongadd3.setText("please enter the course's name.");
        else {
            Course course=new Course(a3.getText());
            if(!b3.getText().isEmpty()) course.setCredits(Integer.parseInt(b3.getText()));
            course.setDepartment(department2.getValue());
            if(professor.getValue()!=null){
                course.setInstructor(professor.getValue());
                professor.getValue().addcourse(course);
            }
            Course.addtocourses(course);
            addcourse.setVisible(false);
            clist.setAll(Course.getCourses());
            courseView.setItems(clist);
        }
    }

    public void cancel() {
        addstudent.setVisible(false);
        addprofessor.setVisible(false);
        adddepartment.setVisible(false);
        addcourse.setVisible(false);
    }

    public void back() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("mainpagee.fxml"));
        App.stage.setScene(new Scene(root));
    }

    public void clear() {
        remove.setVisible(false);
        pic.setVisible(false);
        data.setVisible(false);
        addstudent.setVisible(false);
        addprofessor.setVisible(false);
        adddepartment.setVisible(false);
        addcourse.setVisible(false);

        a.clear(); b.clear(); a1.clear(); a2.clear(); b2.clear(); a3.clear(); b3.clear();
        birthdate.setValue(null); birthdate1.setValue(null);
        department.setValue(null); department1.setValue(null); department2.setValue(null);
        rank.setValue(null);
        professor.setValue(null);
        wrongadd.setText(""); wrongadd1.setText(""); wrongadd2.setText(""); wrongadd3.setText("");

        studentView.getSelectionModel().clearSelection();
        courseView.getSelectionModel().clearSelection();
        departmentView.getSelectionModel().clearSelection();
        professorView.getSelectionModel().clearSelection();
    }
}
