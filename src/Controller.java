import javafx.fxml.FXML;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Controller implements Initializable{
    @FXML
    private AnchorPane mainpane;
    @FXML
    private TextField a,b;
    @FXML
    private Label aa,bb,m;

    public static int k;

    public void god() throws IOException {
        AnchorPane p = FXMLLoader.load(getClass().getResource("godpanel.fxml"));
        mainpane.getChildren().clear();
        mainpane.getChildren().add(p);
    }
    public void student() throws IOException {
        k=1;
        AnchorPane p = FXMLLoader.load(getClass().getResource("login.fxml"));
        mainpane.getChildren().clear();
        mainpane.getChildren().add(p);
    }
    public void professor() throws IOException {
        k=2;
        AnchorPane p = FXMLLoader.load(getClass().getResource("login.fxml"));
        mainpane.getChildren().clear();
        mainpane.getChildren().add(p);
    }
    public void department() throws IOException {
        k=3;
        AnchorPane p = FXMLLoader.load(getClass().getResource("login.fxml"));
        mainpane.getChildren().clear();
        mainpane.getChildren().add(p);
    }
    public void back() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("mainpagee.fxml"));
        App.stage.setScene(new Scene(root));
    }
    public void login() throws IOException {
        switch(k){
            case 1:
            if(Student.search(a.getText(),b.getText())==null)
            m.setText("student not found!");
            else
            {
            StudentPanelControll.setStudent(Student.search(a.getText(),b.getText()));
            AnchorPane pane = FXMLLoader.load(getClass().getResource("studentpanel.fxml"));
            mainpane.getChildren().clear();
            mainpane.getChildren().add(pane);
            }
            break;
            case 2:
            if(Professor.search(a.getText(), b.getText())==null)
            m.setText("professor not found!");
            else
            {
            ProfessorPanelControll.setProfessor(Professor.search(a.getText(),b.getText()));
            AnchorPane pane = FXMLLoader.load(getClass().getResource("professorpanel.fxml"));
            mainpane.getChildren().clear();
            mainpane.getChildren().add(pane);
            }
            break;
            case 3:
            if(Department.search(a.getText())==null)
            m.setText("department not found!");
            else
            {
            DepartmentPanelControll.setDepartment(Department.search(a.getText()));
            AnchorPane pane = FXMLLoader.load(getClass().getResource("departmentpanel.fxml"));
            mainpane.getChildren().clear();
            mainpane.getChildren().add(pane);
            }
            break;
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            switch(k){
                case 1:
                aa.setText("Student Number :");
                bb.setText("          Name :");
                break;
                case 2:
                aa.setText("          Name :");
                bb.setText("    Department :");
                break;
                case 3:
                aa.setText("    Department :");
                bb.setVisible(false);
                b.setVisible(false);
                break;
            }
        } catch (Exception e) {}
    }
}
