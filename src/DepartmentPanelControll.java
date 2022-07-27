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
import javafx.scene.layout.AnchorPane;

public class DepartmentPanelControll implements Initializable{

    @FXML
    private ListView<Course> coursesView;
    @FXML
    private Label departmentdata;
    @FXML
    private ListView<Professor> professorView;
    @FXML
    private ListView<Student> studentView;
    @FXML
    private ListView<Student> students;
    @FXML
    private AnchorPane details,addwindow;
    @FXML
    private Label text;
    @FXML
    private TextField studentnumber,a,b;
    @FXML
    private Label message;
    @FXML
    private Button add;
    @FXML
    private DatePicker birthdate,birthdate1;
    @FXML
    private ChoiceBox<AcademicRank> rank;
    @FXML
    private ChoiceBox<Professor> prof;
    @FXML
    private Label t1,t2,t3;
    @FXML
    private Label wrongadd;

    ObservableList<Course> clist=FXCollections.observableArrayList();
    ObservableList<Student> slist=FXCollections.observableArrayList();

    private static Department department;
    private Course selectedcourse;
    private Student selectedstudent;
    public int state;

    public static void setDepartment(Department department) {
        DepartmentPanelControll.department=department;
    }
    public static Department getDepartment() {
        return department;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addwindow.setVisible(false);

        departmentdata.setText(" Name : "+department.getName()+"\t Id : "+department.getId());
        professorView.getItems().setAll(department.getFaculty());
        studentView.getItems().setAll(department.getStudents());
        clist.clear();
        for (Course course : Course.getCourses()) {
            if(course.getDepartment()!=null && course.getDepartment().equals(department))
                clist.add(course);
        }
        coursesView.setItems(clist);

        details.setVisible(false);

        coursesView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Course> ov, Course old_val, Course new_val) -> {
            details.setVisible(true);
            selectedcourse = coursesView.getSelectionModel().getSelectedItem();
            if(selectedcourse!=null){
                text.setText("Course Name : "+selectedcourse.getName()+"\nCredits : "+selectedcourse.getCredits()+"\nInstructor : "+selectedcourse.getInstructor());
                reloadstudents();
                students.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Student> olv, Student old_vall, Student new_vall) -> {
                    selectedstudent = students.getSelectionModel().getSelectedItem();
                });
            }
        });

        studentView.setMouseTransparent( true );
        studentView.setFocusTraversable( false );
        professorView.setMouseTransparent( true );
        professorView.setFocusTraversable( false );
        
    }
    public void reloadstudents() {
        slist.clear();
        for (Student student : Student.getStudents()) {
            if(student.getCourses().contains(selectedcourse))
                slist.add(student);
        }
        students.setItems(slist);
    }
    public void addtocourse() {
        if(!studentnumber.getText().isBlank() && Student.searchp(studentnumber.getText())!=null){
            Student.searchp(studentnumber.getText()).takeCourse(selectedcourse);
            studentnumber.clear();
            reloadstudents();
        }
        else 
            message.setText("invalid student number!");
    }
    public void removefromcourse() {
        if(selectedstudent!=null){
            selectedstudent.removeCourse(selectedcourse);
            reloadstudents();
        }
    }
    public void addcourse() throws IOException {
        coursesView.getSelectionModel().clearSelection();
        state=2;
        add.setText("Add Course");
        t1.setText("Name* :");
        t2.setText("Credits :");
        t3.setText("Professor :");
        a.clear();
        b.clear();
        b.setVisible(true);
        birthdate.setVisible(false);
        birthdate1.setVisible(false);
        rank.setVisible(false);
        prof.setVisible(true);
        prof.getItems().setAll(Professor.getProfessors());
        addwindow.setVisible(true);
    }
    public void addprofessor() throws IOException {
        coursesView.getSelectionModel().clearSelection();
        state=1;
        add.setText("Add Professor");
        t1.setText("Name* :");
        t2.setText("Birthdate :");
        t3.setText("Rank :");
        a.clear();
        birthdate.setValue(null);
        birthdate.setVisible(true);
        b.setVisible(false);
        birthdate1.setVisible(false);
        prof.setVisible(false);
        rank.setVisible(true);
        rank.getItems().setAll(AcademicRank.values());
        addwindow.setVisible(true);
    }
    public void addstudent() throws IOException {
        coursesView.getSelectionModel().clearSelection();
        state=3;
        add.setText("Add Student");
        t1.setText("Student \nNumber* :");
        t2.setText("Name :");
        t3.setText("Birthdate :");
        a.clear();
        birthdate1.setValue(null);
        b.clear();
        birthdate.setVisible(false);
        b.setVisible(true);
        prof.setVisible(false);
        rank.setVisible(false);
        birthdate1.setVisible(true);
        addwindow.setVisible(true);
    }

    public void add() {
        switch(state){
            case 1:
            if(a.getText().isEmpty()) wrongadd.setText("please enter name.");
            else {
                Professor professor=new Professor(a.getText());
                professor.setDepartment(DepartmentPanelControll.getDepartment());
                DepartmentPanelControll.getDepartment().addtofaculty(professor);
                professor.setBirthDate(birthdate.getValue());
                professor.setRank(rank.getValue());
                Professor.addtoprofessors(professor);
                addwindow.setVisible(false);
                professorView.getItems().addAll(department.getFaculty());
            }
            break;
            case 2:
            if(a.getText().isEmpty()) wrongadd.setText("please enter the course's name.");
            else {
                Course course=new Course(a.getText());
                if(!b.getText().isEmpty()) course.setCredits(Integer.parseInt(b.getText()));
                course.setDepartment(DepartmentPanelControll.getDepartment());
                if(prof.getValue()!=null){
                    course.setInstructor(prof.getValue());
                    prof.getValue().addcourse(course);
                }
                Course.addtocourses(course);
                addwindow.setVisible(false);
                clist.clear();
                for (Course c : Course.getCourses()) {
                    if(c.getDepartment().equals(department))
                        clist.add(c);
                }
                coursesView.setItems(clist);
            }
            break;
            case 3:
            if(a.getText().isEmpty()) wrongadd.setText("please enter student number.");
            else {
                Student student=new Student(a.getText());
                if(!b.getText().isEmpty()) student.setName(b.getText());
                student.setBirthDate(birthdate1.getValue());
                student.setDepartment(DepartmentPanelControll.getDepartment());
                DepartmentPanelControll.getDepartment().addtostudents(student);
                Student.addtostudents(student);
                addwindow.setVisible(false);
                studentView.getItems().setAll(department.getStudents());
            }
            break;
        }
    }

    public void cancel() {
        addwindow.setVisible(false);
    }

    public void back() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("mainpagee.fxml"));
        App.stage.setScene(new Scene(root));
    }
}


