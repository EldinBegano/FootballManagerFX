package at.htl.player.view;

import at.htl.player.model.Player;
import com.sun.javafx.scene.control.DoubleField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class PlayerView extends VBox {
    //Create your own Player
    final TextField name = new TextField();
    final TextField position = new TextField();
    final DoubleField value = new DoubleField();
    final Button buy = new Button("Buy");
    final Button sell = new Button("Sell");
    final Button simulate = new Button("Simulate");

    final TableView<Player> tableView = new TableView<>();

    public PlayerView() {
        GridPane pane = content();
        this.getChildren().add(tableView);
        this.setSpacing(40);
        this.getChildren().add(pane);
    }

    private GridPane content(){
        GridPane gridPane = new GridPane(10,10);

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
}