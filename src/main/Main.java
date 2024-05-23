package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import panes.GamePanel;
import panes.HomePane;

import javax.script.ScriptEngineManager;

public class Main extends Application {
    private static StackPane root;
    private static Scene scene;
    @Override
    public void start(Stage primaryStage) {

        root = new StackPane();
        scene = new Scene(root, 768, 526);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cinnamon Roll Pony");
        primaryStage.setResizable(false);
        root.getChildren().add(new HomePane());
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    public static void restart() {
        root.getChildren().clear();
        root.getChildren().add(new HomePane());
    }
    public static void main(String[] args) {
        launch(args);
    }


}
