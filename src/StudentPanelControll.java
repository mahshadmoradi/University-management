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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class StudentPanelControll implements Initializable{

    @FXML
    private ListView<Course> coursesview;
    @FXML
    private TableView<Course> coursetable;
    @FXML
    private TableColumn<Course, String> courseColumn;
    @FXML
    private TableColumn<Course, Integer> credits;
    @FXML
    private TableColumn<Course, Department> department;
    @FXML
    private TableColumn<Course, Professor> instructor;
    @FXML
    private TableView<GradeReport> gradetable;
    @FXML
    private TableColumn<GradeReport, Double> grade;
    @FXML
    private Label studentdata;
    @FXML
    private Label message;


    ObservableList<Course> clist=FXCollections.observableArrayList();

    private static Student student;
    private Course selectedcourse;

    public static void setStudent(Student student) {
        StudentPanelControll.student = student;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        coursetable.setPlaceholder(new Label("you are not enrolled in any course!"));
        gradetable.setPlaceholder(new Label("no grade!"));

        coursetable.setSelectionModel(null);
        gradetable.setSelectionModel(null);

        studentdata.setText(" Student Number : \n   "+student.getStudent()+"\n Name :  \n   "+student.getName()+"\n Birthdate : \n   "+student.getBirthDate()+"\n Department : \n   "+student.getDepartment());
        
        message.setVisible(false);
        clist.addAll(Course.getCourses());
        coursesview.setItems(clist);

        coursesview.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Course> ov, Course old_val, Course new_val) -> {
            selectedcourse = coursesview.getSelectionModel().getSelectedItem();
        });

        courseColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("Name"));
        credits.setCellValueFactory(new PropertyValueFactory<Course, Integer>("Credits"));
        department.setCellValueFactory(new PropertyValueFactory<Course, Department>("department"));
        instructor.setCellValueFactory(new PropertyValueFactory<Course, Professor>("Instructor"));
        grade.setCellValueFactory(new PropertyValueFactory<GradeReport, Double>("grade"));
        coursetable.getItems().addAll(student.getCourses());
        gradetable.getItems().addAll(student.getGradeReport());
    }
    public void addcourse() {
        if(selectedcourse!=null){
        if(student.takeCourse(selectedcourse)) 
        {
            message.setVisible(false);
            //coursesview.getItems().remove(selectedcourse);
            coursetable.getItems().clear();
            coursetable.getItems().addAll(student.getCourses());
        }
        else
        {
            message.setText("you're enrolled in this course!");
            message.setVisible(true);
        }
        //System.out.println(coursetable.getItems().toString());
        }
    }
    public void back() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("mainpagee.fxml"));
        App.stage.setScene(new Scene(root));
    }
}


