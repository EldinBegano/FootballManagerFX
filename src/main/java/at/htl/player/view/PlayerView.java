package at.htl.player.view;

import at.htl.player.model.Player;
import com.sun.javafx.scene.control.DoubleField;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

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

    // Constructor
    public PlayerView() {
        showTeams();
    }

    private void showTeams() {
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

        comboBox.setValue("Bayern Munich");
        selectedTeam = "Bayern Munich";

        confirm.setOnAction(e -> onTeamSelected());

        GridPane teamGrid = new GridPane(10, 10);
        teamGrid.add(new Label("Select Team:"), 0, 0);
        teamGrid.add(comboBox, 1, 0);
        teamGrid.add(confirm, 0, 1, 2, 1);

        this.getChildren().add(teamGrid);
    }

    private void onTeamSelected() {
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
        tableView.widthProperty().addListener((obs, oldWidth, newWidth) -> {
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
