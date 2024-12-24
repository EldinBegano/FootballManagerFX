package at.htl.player.view;

import at.htl.player.model.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.text.Position;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class PlayerPresenter {

    private final Player model;
    private final PlayerView view;
    ObservableList<Player> list = FXCollections.observableArrayList();

    public PlayerPresenter(Player model, PlayerView view) {
        this.model = model;
        this.view = view;
        bindViewToModel();
        bindListToView();
        buttonFunctions();
        loadPlayersFromCSV("test.csv");
    }

    private void loadPlayersFromCSV(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.lines()
                    .skip(1)
                    .map(line -> line.split(";"))
                    .filter(parts -> parts.length == 3)
                    .forEach(parts -> {
                        try {
                            String name = parts[0].trim();
                            String position = parts[1].trim();
                            double value = Double.parseDouble(parts[2].trim().replace(" million €", ""));
                            list.add(new Player(name, position, value));
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid value format for player: " + parts[0].trim());
                        }
                    });
        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }
    }



    private void buttonFunctions() {
        view.getBuy().setOnAction(action -> buyPlayer());
        view.getSell().setOnAction(action -> sellPlayer());
        view.getSimulate().setOnAction(action -> simulateGame());
    }

    private void simulateGame() {
    }

    private void sellPlayer() {
    }

    private void buyPlayer() {
        List<String> errorList = new LinkedList<>();
        try {
            if(model.getName().isEmpty()){
                errorList.add("Name is empty");
            }
            if(model.getPosition().isEmpty()){
                errorList.add("Position is empty");
            }
            if (model.getValue() < 0) {
                errorList.add("Value is less than 0");
            }
            if (!errorList.isEmpty()) {
                throw new RuntimeException("Invalid input.");
            }
            list.add(new Player(model));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void bindListToView() {
        view.getTableView().setItems(list);
    }

    private void bindViewToModel() {
        view.getName().textProperty().bindBidirectional(model.nameProperty());
        view.getPosition().textProperty().bindBidirectional(model.positionProperty());
        view.getValue().valueProperty().bindBidirectional(model.valueProperty());
    }


}