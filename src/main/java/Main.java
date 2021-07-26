import javafx.application.Application;
import javafx.stage.Stage;
import view.ChooseFileView;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ChooseFileView newTable = new ChooseFileView();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
