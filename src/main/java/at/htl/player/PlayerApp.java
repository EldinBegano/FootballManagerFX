package at.htl.player;

import at.htl.player.view.PlayerPresenter;
import at.htl.player.view.PlayerView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PlayerApp extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        PlayerView view = new PlayerView();
        Scene scene = new Scene(view);

        PlayerPresenter playerPresenter = new PlayerPresenter();

        primaryStage.setScene(scene);
        primaryStage.setWidth(400);
        primaryStage.setHeight(600);
        primaryStage.setTitle("Football Manager");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
