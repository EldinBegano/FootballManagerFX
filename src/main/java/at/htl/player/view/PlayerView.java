package at.htl.player.view;

import at.htl.player.model.Player;
import com.sun.javafx.scene.control.DoubleField;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class PlayerView extends VBox {
    final TextField name = new TextField();
    final TextField position = new TextField();
    final DoubleField value = new DoubleField();
    final Button buy = new Button("Buy");
    final Button sell = new Button("Sell");
    final Button simulate = new Button("Simulate");

    final TableView<Player> tableView = new TableView<>();
    private String selectedTeam;

    final ComboBox<String> comboBox = new ComboBox<>();
    final Button confirm = new Button("Confirm");

    private final Stage stage;

    // Constructor
    public PlayerView(Stage stage) {
        this.stage = stage;
        showTeams();
    }

    private void showTeams() {
        //Bilder mit AI gemacht
        Map<String, String> teamImages = getImages();
        URL imageUrl = getClass().getResource("/images/bayern.png");
        if (imageUrl == null) {
            System.out.println("Image not found!");
            return;
        }
        Image preview = new Image(imageUrl.toString());
        ImageView imageView = new ImageView(preview);

        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        comboBox.getItems().addAll(
                "Bayer 04 Leverkusen",
                "Bayern München",
                "Borussia Dortmund",
                "Borussia Mönchengladbach",
                "1. FC Köln",
                "1. FSV Mainz 05",
                "1. FC Union Berlin",
                "RB Leipzig",
                "SC Freiburg",
                "VfB Stuttgart",
                "VfL Bochum",
                "VfL Wolfsburg",
                "Werder Bremen",
                "FC Augsburg",
                "Eintracht Frankfurt",
                "Heidenheim 1846",
                "Holstein Kiel"
        );

        comboBox.setValue("Bayern München");
        selectedTeam = "Bayern München";

        comboBox.valueProperty().addListener((_, _, newValue) -> {
            selectedTeam = newValue;
            updateImageView(newValue, imageView, teamImages);
        });

        confirm.setOnAction(_ -> onTeamSelected());

        GridPane teamGrid = new GridPane(10, 10);
        teamGrid.add(imageView, 0, 0, 2, 1);
        teamGrid.add(new Label("Select Team:"), 0, 1);
        teamGrid.add(comboBox, 1, 1);
        teamGrid.add(confirm, 0, 2, 2, 1);

        this.getChildren().add(teamGrid);
    }

    //Extrahierte Methode
    private static Map<String, String> getImages() {
        Map<String, String> teamImages = new HashMap<>();
        teamImages.put("Bayer 04 Leverkusen", "/images/bayer.png");
        teamImages.put("Bayern München", "/images/bayern.png");
        teamImages.put("Borussia Dortmund", "/images/dortmund.png");
        teamImages.put("Borussia Mönchengladbach", "/images/gladbach.png");
        teamImages.put("1. FC Köln", "/images/cologne.png");
        teamImages.put("1. FSV Mainz 05", "/images/mainz.png");
        teamImages.put("1. FC Union Berlin", "/images/union.png");
        teamImages.put("RB Leipzig", "/images/leipzig.png");
        teamImages.put("SC Freiburg", "/images/freiburg.png");
        teamImages.put("VfB Stuttgart", "/images/stuttgart.png");
        teamImages.put("VfL Bochum", "/images/bochum.png");
        teamImages.put("VfL Wolfsburg", "/images/wolfsburg.png");
        teamImages.put("Werder Bremen", "/images/bremen.png");
        teamImages.put("FC Augsburg", "/images/augsburg.png");
        teamImages.put("Eintracht Frankfurt", "/images/frankfurt.png");
        teamImages.put("Heidenheim 1846", "/images/heidenheim.png");
        teamImages.put("Holstein Kiel", "/images/kiel.png");
        return teamImages;
    }

    private void updateImageView(String teamName, ImageView imageView, Map<String, String> teamImages) {
        String imagePath = teamImages.get(teamName);
        if (imagePath != null) {
            URL imageUrl = getClass().getResource(imagePath);
            if (imageUrl != null) {
                Image image = new Image(imageUrl.toString());
                imageView.setImage(image);

                double maxWidth = 100;
                double maxHeight = 100;

                imageView.setFitWidth(maxWidth);
                imageView.setFitHeight(maxHeight);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);
            } else {
                System.out.println("Image not found for team: " + teamName);
            }
        }
    }

    private void onTeamSelected() {
        PlayerPresenter presenter = new PlayerPresenter(new Player(), this);
        System.out.println(selectedTeam);
        stage.setHeight(800);
        stage.setWidth(500);
        //Löschen der Auswahl
        this.getChildren().clear();
        showYourTeam();
    }

    private void showYourTeam() {
        GridPane playerForm = content();
        initTableColumns();
        this.getChildren().add(tableView);
        this.getChildren().add(playerForm);
    }

    private void initTableColumns() {
        //TableView ist ein dreck
        TableColumn<Player, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Player, String> positionColumn = new TableColumn<>("Position");
        positionColumn.setCellValueFactory(cellData -> cellData.getValue().positionProperty());

        TableColumn<Player, Double> valueColumn = new TableColumn<>("Value (in Million)");
        valueColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty().asObject());

        /*
        Ich hol mir die width vom tableview mit dem listener, diese ändert sich wenn man das fenster bewegt
        obs ist die property die sich ändert(width)
        oldwidth ist die alte width, newwidth ist die neue
        */
        tableView.widthProperty().addListener((_, _, newWidth) -> {
            double width = newWidth.doubleValue();
            double columnWidth = width / 3;
            nameColumn.setPrefWidth(columnWidth);
            positionColumn.setPrefWidth(columnWidth);
            valueColumn.setPrefWidth(columnWidth);
        });

        tableView.getColumns().addAll(nameColumn, positionColumn, valueColumn);
    }

    private GridPane content() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label nameLabel = new Label("Name:");
        Label positionLabel = new Label("Position:");
        Label valueLabel = new Label("Value:");

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(name, 1, 0);

        gridPane.add(positionLabel, 0, 1);
        gridPane.add(position, 1, 1);

        gridPane.add(valueLabel, 0, 2);
        gridPane.add(value, 1, 2);

        gridPane.add(buy, 0, 3);
        gridPane.add(sell, 1, 3);
        gridPane.add(simulate, 0, 4, 2, 1);

        return gridPane;
    }

    public TextField getName() {
        return name;
    }

    public TextField getPosition() {
        return position;
    }

    public DoubleField getValue() {
        return value;
    }

    public Button getBuy() {
        return buy;
    }

    public Button getSell() {
        return sell;
    }

    public Button getSimulate() {
        return simulate;
    }

    public TableView<Player> getTableView() {
        return tableView;
    }

    public String getSelectedTeam() {
        return selectedTeam;
    }
}
