package at.htl.player.model;

public class Player {

    //Die wichtigsten Sachen eines Spielers
    private String name;
    private String position;
    private Double value;
    private String club; //Test

    public Player(String name, String position, Double value, String club) {
        this.name = name;
        this.position = position;
        this.value = value;
        this.club = club;
    }

    public Player(String name, String position, Double value) {
        this.name = name;
        this.position = position;
        this.value = value;
    }

    public Player() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    @Override
    public String toString() {
        return String.format("Player: %s\n" +
                "Position: %s\n" +
                "Value: %f"
                , name
                , position
                , value);
    }
}