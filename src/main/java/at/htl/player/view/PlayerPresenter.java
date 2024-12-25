package at.htl.player.view;

import at.htl.player.model.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PlayerPresenter {

    private final Player model;
    private final PlayerView view;
    private ObservableList<Player> list = FXCollections.observableArrayList();
    private String team;
    private static final Map<String, String> teamFileMap = new HashMap<>();

    public PlayerPresenter(Player model, PlayerView view) {
        this.model = model;
        this.view = view;
        bindViewToModel();
        bindListToView();
        buttonFunctions();
        team = view.getSelectedTeam();
        System.out.println(team);
        getCSV();
        loadPlayersForSelectedTeam();
        System.out.println(teamFileMap.get(team));
    }

    private static void getCSV() {
        teamFileMap.put("Bayer 04 Leverkusen", "teams/bayer.csv");
        teamFileMap.put("Bayern München", "teams/bayern.csv");
        teamFileMap.put("Borussia Dortmund", "teams/dortmund.csv");
        teamFileMap.put("Borussia Mönchengladbach", "teams/gladbach.csv");
        teamFileMap.put("1. FC Köln", "teams/cologne.csv");
        teamFileMap.put("1. FSV Mainz 05", "teams/mainz.csv");
        teamFileMap.put("1. FC Union Berlin", "teams/union.csv");
        teamFileMap.put("RB Leipzig", "teams/leipzig.csv");
        teamFileMap.put("SC Freiburg", "teams/freiburg.csv");
        teamFileMap.put("VfB Stuttgart", "teams/stuttgart.csv");
        teamFileMap.put("VfL Bochum", "teams/bochum.csv");
        teamFileMap.put("VfL Wolfsburg", "teams/wolfsburg.csv");
        teamFileMap.put("Werder Bremen", "teams/bremen.csv");
        teamFileMap.put("FC Augsburg", "teams/augsburg.csv");
        teamFileMap.put("Eintracht Frankfurt", "teams/frankfurt.csv");
        teamFileMap.put("Heidenheim 1846", "teams/heidenheim.csv");
        teamFileMap.put("Holstein Kiel", "teams/kiel.csv");
    }

    private void loadPlayersForSelectedTeam() {
        String filePath = teamFileMap.get(team);

        if (filePath != null) {
            loadPlayersFromCSV(filePath);
        } else {
            System.err.println("No CSV file found for selected team: " + team);
        }
    }

    private void loadPlayersFromCSV(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.lines()
                    .skip(1)  // Skip the header line
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
        view.getBuy().setOnAction(_ -> buyPlayer());
        view.getSell().setOnAction(_ -> sellPlayer());
        view.getSimulate().setOnAction(_ -> simulateGame());
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