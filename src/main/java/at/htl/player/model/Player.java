package at.htl.player.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player {

    //Die wichtigsten Sachen eines Spielers
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty position = new SimpleStringProperty();
    private final DoubleProperty value = new SimpleDoubleProperty();
    private final StringProperty club = new SimpleStringProperty(); //Test

    public Player(String name, String position, Double value, String club) {
       this.name.set(name);
       this.position.set(position);
       this.value.set(value);
       this.club.set(club);
    }

    public Player(String name, String position, Double value) {
        this.name.set(name);
        this.position.set(position);
        this.value.set(value);
    }

    public Player() {
    }


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getPosition() {
        return position.get();
    }

    public StringProperty positionProperty() {
        return position;
    }

    public double getValue() {
        return value.get();
    }

    public DoubleProperty valueProperty() {
        return value;
    }

    public String getClub() {
        return club.get();
    }

    public StringProperty clubProperty() {
        return club;
    }

    @Override
    public String toString() {
        return String.format("Name: %s", name);
    }
}