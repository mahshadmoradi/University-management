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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ProfessorPanelControll implements Initializable{

    @FXML
    private ListView<Course> coursesview;
    @FXML
    private Label professordata;
    @FXML
    private ListView<Professor> professorView;
    @FXML
    private ListView<Student> students;
    @FXML
    private AnchorPane details;

    @FXML
    private TextField a;
    @FXML
    private TextField b;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Label text;
    @FXML
    private ChoiceBox<Department> department;


    ObservableList<Professor> plist=FXCollections.observableArrayList(Professor.getProfessors());
    ObservableList<Student> slist=FXCollections.observableArrayList();

    private static Professor professor;
    private Course selectedcourse;
    private Student selectedstudent;

    public static void setProfessor(Professor professor) {
        ProfessorPanelControll.professor = professor;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        department.getItems().addAll(Department.getDepartments());
        a.setVisible(false); b.setVisible(false); btn1.setVisible(false); btn2.setVisible(false); text.setVisible(false); department.setVisible(false);

        professordata.setText(" Name : "+professor.getName()+"\t Department : "+professor.getDepartment()+"\n Birthdate : "+professor.getBirthDate()+"\t Rank : "+professor.getRank());
        plist.remove(professor);
        professorView.setItems(plist);
        professorView.setSelectionModel(null);
        
        coursesview.getItems().addAll(professor.getCourses());

        coursesview.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Course> ov, Course old_val, Course new_val) -> {
            selectedcourse = coursesview.getSelectionModel().getSelectedItem();
            refreshslist();
            students.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Student> olv, Student old_vall, Student new_vall) -> {
                selectedstudent = students.getSelectionModel().getSelectedItem();
                if(selectedstudent!=null){
                text.setText("Student Number : "+selectedstudent.getStudent()+"\n\nName : "+selectedstudent.getName()+"\n\nGrade : ");
                btn1.setText("Set Grade");
                btn2.setText("Remove");
                a.setVisible(false); department.setVisible(false); b.clear();
                b.setVisible(true); btn1.setVisible(true); btn2.setVisible(true); text.setVisible(true); 
                }
                else{
                    b.clear(); b.setVisible(false); btn1.setVisible(false); btn2.setVisible(false); text.setVisible(false); 
                }
            });
        });
        

    }
    public void addCourse() throws IOException {
        students.getSelectionModel().clearSelection();
        coursesview.getSelectionModel().clearSelection();
        text.setText("Name* : \n\nDepartment :  \n\nCredits :");
        btn1.setText("Add Course");
        btn2.setText("Cancel");
        
        a.setVisible(true); b.setVisible(true); btn1.setVisible(true); btn2.setVisible(true); text.setVisible(true); department.setVisible(true);
    }
    public void button1() {
        if(btn1.getText().equals("Add Course") && !a.getText().isEmpty())
        {
            Course course=new Course(a.getText());
            if(!b.getText().isEmpty()) course.setCredits(Integer.parseInt(b.getText()));
            course.setDepartment(department.getValue());
            course.setInstructor(professor);
            professor.addcourse(course);
            Course.addtocourses(course);
            coursesview.getItems().clear();
            coursesview.getItems().addAll(professor.getCourses());
            a.setVisible(false); b.setVisible(false); btn1.setVisible(false); btn2.setVisible(false); text.setVisible(false); department.setVisible(false);
            a.clear(); b.clear(); department.getSelectionModel().clearSelection();
        }
        else if(btn1.getText().equals("Set Grade"))
        {
            selectedstudent.finishCourse(selectedcourse, Double.parseDouble(b.getText()));
            b.setVisible(false); btn1.setVisible(false); btn2.setVisible(false); text.setVisible(false); 
            students.getSelectionModel().clearSelection();
            b.clear();
        }
    }
    public void button2() {
        if(btn1.getText().equals("Add Course"))
        {
            a.clear(); b.clear(); department.getSelectionModel().clearSelection();
            a.setVisible(false); b.setVisible(false); btn1.setVisible(false); btn2.setVisible(false); text.setVisible(false); department.setVisible(false);
        }
        else if(btn1.getText().equals("Set Grade"))
        {
            selectedstudent.removeCourse(selectedcourse);
            refreshslist();
            b.setVisible(false); btn1.setVisible(false); btn2.setVisible(false); text.setVisible(false); 
        }
    }
    public void refreshslist(){
        slist.clear();
        for (Student student : Student.getStudents()) {
        if(student.getCourses().contains(selectedcourse))
            slist.add(student);
        }
        students.setItems(slist);
    }
    public void back() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("mainpagee.fxml"));
        App.stage.setScene(new Scene(root));
    }
}


