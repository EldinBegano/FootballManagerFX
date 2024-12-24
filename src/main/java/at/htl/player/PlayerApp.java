package at.htl.player;

import at.htl.player.model.Player;
import at.htl.player.view.PlayerPresenter;
import at.htl.player.view.PlayerView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PlayerApp extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Player model = new Player();
        PlayerView view = new PlayerView();
        Scene scene = new Scene(view);

        PlayerPresenter playerPresenter = new PlayerPresenter(model, view);

        primaryStage.setScene(scene);
        primaryStage.setWidth(500);
        primaryStage.setHeight(800);
        primaryStage.setTitle("Football Manager");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
