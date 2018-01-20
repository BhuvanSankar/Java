package week10;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Calculator extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        CalculatorModel model = new CalculatorModel();
        CalculatorView view = new CalculatorView(model);
        new CalculatorController(model, view);

        stage.setScene(new Scene(view.getRootNode(), 250, 300));
        stage.setTitle("Calculator");
        stage.show();
    }

}
