import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application {

    public static AnchorPane scene;
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage=primaryStage;
        Image icon=new Image("department-icon.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setResizable(false);
        primaryStage.setTitle("University Management System");
        AnchorPane root = FXMLLoader.load(getClass().getResource("mainpagee.fxml"));
        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
