package com.main.pokebucket;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.SearchableComboBox;


public class AddPokemonController implements Initializable {

    String[] pokeballTypes = {"PokeBall", "GreatBall", "UltraBall", "MasterBall", "SafariBall", "LevelBall", "LureBall", "MoonBall",
            "FriendBall", "LoveBall", "HeavyBall", "FastBall", "SportBall", "PremierBall", "RepeatBall", "TimerBall", "NestBall", "NetBall", "DiveBall",
            "LuxuryBall", "HealBall", "QuickBall", "DuskBall", "CherishBall", "ParkBall", "ParkBall", "DreamBall", "BeastBall"};

    String[] natureList = {"Adamant","Bashful","Bold","Brave","Calm","Careful","Docile","Gentle","Hardy","Hasty","Impish",
            "Jolly","Lax","Lonely","Mild","Modest","Naive","Naughty","Quiet","Quirky","Rash","Relaxed","Sassy","Serious","Timid"};

    @FXML SearchableComboBox<ModelPokedex> pokemonNameBox = new SearchableComboBox<>();
    @FXML private ComboBox pokeballChoice;
    @FXML private ComboBox natureChoice;
    @FXML private ComboBox abilityChoice;
    @FXML private ComboBox methodChoice;
    @FXML private TextField nicknameInput;
    @FXML private CheckBox isShiny;
    @FXML private Spinner<Integer> mySpinner;
    @FXML private Label statusLabel;

    private int pokemonKey;
    int currentLevel;
    Connection con = SqliteConnection.getDBConnection();

    public ArrayList getPokemonList() throws SQLException {

        ArrayList<String> pokemonList = new ArrayList<>();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT name FROM pokemon");
        while (resultSet.next()) {
            pokemonList.add(resultSet.getString("name"));
        }
        return pokemonList;

    }

    public void changeToPokeDex(ActionEvent event) throws IOException, SQLException {
        con.close();
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("pokebucket.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    public void updatePokemonKey() throws SQLException {

        pokemonKey = pokemonNameBox.getSelectionModel().getSelectedIndex();
        setAbilityChoices(pokemonKey);

    }

    public void setAbilityChoices(int key) throws SQLException {
        ArrayList<String> abilityChoices = new ArrayList<>();
        ResultSet resultSet = con.createStatement().executeQuery
                ("SELECT ability1, ability2, hiddenability FROM pokemon WHERE key ='" + key + "'");
        while (resultSet.next()) {
            abilityChoices.add(resultSet.getString("ability1"));
            abilityChoices.add(resultSet.getString("ability2"));
            abilityChoices.add(resultSet.getString("hiddenability"));
            while(abilityChoices.remove("none")){}

        }
        abilityChoice.getItems().clear();
        abilityChoice.getItems().addAll(abilityChoices);
        abilityChoice.getSelectionModel().selectFirst();

    }

    public void setPokemonKey(int key) throws SQLException {
        pokemonKey = key;
        pokemonNameBox.getSelectionModel().clearAndSelect(pokemonKey);

    }

    public int generatePkey() throws SQLException {
        ResultSet maxPKey = con.createStatement().executeQuery("SELECT MAX(pkey) as pkey FROM personal_pokemon");
        System.out.println(maxPKey.getInt("pkey"));
        return maxPKey.getInt("pkey") + 1;


    }

    public void newPokemonButton() throws SQLException, InterruptedException {
        try {
            String nickname;
            if (nicknameInput.getText() == null || nicknameInput.getText().trim().isEmpty()) {
                nickname = "(none)";
            } else {
                nickname = nicknameInput.getText();
            }
            int pkey = generatePkey();
            int key = pokemonKey;
            String pokeball = pokeballChoice.getValue().toString();
            String shiny = String.valueOf(isShiny.isSelected());
            String method = methodChoice.getValue().toString();
            String nature = natureChoice.getValue().toString();
            String ability = abilityChoice.getValue().toString();
            currentLevel = mySpinner.getValue();
            String q = String.format("INSERT INTO personal_pokemon (pkey,key,nickname,pokeball,shiny,method,nature,ability,level) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s')", pkey, key, nickname, pokeball, shiny, method, nature, ability, currentLevel);
            System.out.println(q);
            con.createStatement().executeUpdate(q);
            statusLabel.setText("Pokemon was added sucessfully");

        }
        catch (Exception e) {
            statusLabel.setText("An error has occured");
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {


        try {
            pokemonNameBox.getItems().addAll(getPokemonList());
            pokeballChoice.getItems().addAll(pokeballTypes);
            pokeballChoice.getSelectionModel().selectFirst();
            natureChoice.getItems().addAll(natureList);
            natureChoice.getSelectionModel().selectFirst();
            methodChoice.getItems().add("test");
            methodChoice.getSelectionModel().selectFirst();
            SpinnerValueFactory<Integer> valueFactory =
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
            valueFactory.setValue(1);
            mySpinner.setValueFactory(valueFactory);
            statusLabel.setText("Add pokemon to save");


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
