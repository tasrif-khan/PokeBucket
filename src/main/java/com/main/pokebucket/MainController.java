package com.main.pokebucket;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainController implements Initializable {

    //Pokedex items
    @FXML private TextField pokemonName;
    @FXML private ComboBox type1;
    @FXML private ComboBox type2;
    @FXML private ComboBox ability;
    @FXML private ComboBox hiddenAbility;
    @FXML private TableView<ModelPokedex> pokedexView;
    @FXML private TableColumn<ModelPokedex, String> keyColumn;
    @FXML private TableColumn<ModelPokedex, String> idColumn;
    @FXML private TableColumn<ModelPokedex, String> nameColumn;
    @FXML private TableColumn<ModelPokedex, String> urlColumn;
    @FXML private TableColumn<ModelPokedex, String> type1Column;
    @FXML private TableColumn<ModelPokedex, String> type2Column;
    @FXML private TableColumn<ModelPokedex, String> ability1Column;
    @FXML private TableColumn<ModelPokedex, String> ability2Column;
    @FXML private TableColumn<ModelPokedex, String> hiddenAbilityColumn;
    @FXML private TableColumn<ModelPokedex, String> egggroup1Column;
    @FXML private TableColumn<ModelPokedex, String> egggroup2Column;
    @FXML private TableColumn<ModelPokedex, String> eggCycleColumn;
    //Personal Tab items
    @FXML private TableView<ModelPersonal> personalView;
    @FXML private TableColumn<ModelPersonal, String> pIdColumn;
    @FXML private TableColumn<ModelPersonal, String> pNameColumn;
    @FXML private TableColumn<ModelPersonal, String> pokeballColumn;
    @FXML private TableColumn<ModelPersonal, String> nicknameColumn;
    @FXML private TableColumn<ModelPersonal, String> shinyColumn;
    @FXML private TableColumn<ModelPersonal, String> methodColumn;
    @FXML private TableColumn<ModelPersonal, String> natureColumn;
    @FXML private TableColumn<ModelPersonal, String> abilityColumn;
    @FXML private TableColumn<ModelPersonal, String> pKeyColumn;
    @FXML private TableColumn<ModelPersonal, String> levelColumn;

    String[] types = {"Any", "Normal", "Fire", "Water", "Grass", "Electric", "Ice", "Fighting",
    "Poison", "Ground", "Flying", "Psychic", "Bug", "Rock", "Ghost", "Dark", "Dragon", "Steel", "Fairy"};

    String[] abilities = {"Any","Overgrow","Thick Fat","Blaze","Tough Claws","Drought","Torrent","Mega Launcher","Shield Dust",
            "Shed Skin","Compound Eyes","Swarm","Adaptability","Keen Eye","No Guard","Run Away","Gluttony","Intimidate",
            "Static","None","Surge Surfer","Sand Veil","Snow Cloak","Poison Point","Cute Charm","Flash Fire","Inner Focus",
            "Chlorophyll","Effect Spore","Pickup","Limber","Fur Coat","Damp","Vital Spirit","Water Absorb","Synchronize",
            "Trace","Guts","Clear Body","Rock Head","Magnet Pull","Oblivious","Shell Armor","Quick Draw","Steadfast",
            "Stench","Poison Touch","Levitate","Cursed Body","Shadow Tag","Insomnia","Hyper Cutter","Soundproof","Frisk",
            "Own Tempo","Lightning Rod","Natural Cure","Early Bird","Parental Bond","Swift Swim","Illuminate","Flame Body",
            "Aerilate","Mold Breaker","Volt Absorb","Immunity","Pressure","Competitive","Defiant","Berserk","Hustle","Sturdy",
            "Speed Boost","Curious Medicine","Serene Grace","Sand Force","Technician","Skill Link","Magma Armor","Weak Armor",
            "Suction Cups","Solar Power","Sand Stream","Pixilate","Truant","Wonder Guard","Magic Bounce","Huge Power","Filter",
            "Pure Power","Plus","Minus","Liquid Ooze","Rough Skin","Strong Jaw","Water Veil","Sheer Force","White Smoke",
            "Battle Armor","Marvel Scale","Forecast","Color Change","Prankster","Refrigerate","Drizzle","Primordial Sea",
            "Desolate Land","Air Lock","Delta Stream","Simple","Rivalry","Anticipation","Honey Gather","Flower Gift","Sticky Hold",
            "Aftermath","Scrappy","Snow Warning","Motor Drive","Leaf Guard","Slow Start","Hydration","Bad Dreams","Multitype",
            "Victory Star","Forewarn","Big Pecks","Unaware","Sand Rush","Healer","Reckless","Rattled","Gorilla Tactics",
            "Wonder Skin","Mummy","Wandering Spirit","Solid Rock","Defeatist","Illusion","Overcoat","Ice Body","Iron Barbs",
            "Telepathy","Mimicry","Iron Fist","Justified","Regenerator","Turboblaze","Teravolt","Download","Battle Bond",
            "Flower Veil","Sap Sipper","Stance Change","Sweet Veil","Contrary","Sniper","Dry Skin","Cheek Pouch","Fairy Aura",
            "Dark Aura","Aura Break","Power Construct","Magician","Battery","Dancer","Schooling","Merciless","Water Bubble",
            "Corrosion","Fluffy","Receiver","Wimp Out","Emergency Exit","Water Compaction","Innards Out","Rks System","Shields Down",
            "Comatose","Disguise","Dazzling","Steelworker","Bulletproof","Electric Surge","Psychic Surge","Grassy Surge","Misty Surge",
            "Full Metal Body","Shadow Shield","Beast Boost","Prism Armor","Neuroforce","Soul-heart","Cotton Down","Ball Fetch",
            "Steam Engine","Ripen","Sand Spit","Gulp Missile","Punk Rock","Tangled Feet","Power Spot","Ice Face","Hunger Switch",
            "Light Metal","Intrepid Sword","Dauntless Shield","Unseen Fist","Transistor","Dragon's Maw","Chilling Neigh","Grim Neigh",
            "Unnerve","As One"};

    String[] hiddenAbilities = {"Any","Chlorophyll","none","Solar Power","Rain Dish","Run Away","Tinted Lens","Sniper","Big Pecks",
            "Hustle","Thick Fat","Unnerve","Lightning Rod","Sand Rush","Slush Rush","Sheer Force","Friend Guard","Unaware",
            "Drought","Snow Warning","Frisk","Infiltrator","Stench","Effect Spore","Damp","Wonder Skin","Sand Force","Rattled",
            "Swift Swim","Defiant","Justified","Magic Guard","Steadfast","Gluttony","Sand Veil","Galvanize","Flame Body",
            "Anticipation","Regenerator","Analytic","Scrappy","Tangled Feet","Ice Body","Poison Touch","Power of Alchemy",
            "Overcoat","Weak Armor","Inner Focus","Aftermath","Harvest","Battle Armor","Rock Head","Unburden","Cloud Nine",
            "Misty Surge","Reckless","Healer","Technician","Dry Skin","Vital Spirit","Moxie","Hydration","Imposter",
            "Quick Feet","Guts","Snow Cloak","Static","Marvel Scale","Multiscale","Leaf Guard","Flash Fire","Iron Fist",
            "Water Absorb","Super Luck","Magic Bounce","Plus","Sap Sipper","Drizzle","Skill Link","Early Bird","Prankster",
            "Telepathy","Immunity","Intimidate","Light Metal","Contrary","Pickpocket","Honey Gather","Cursed Body","Moody",
            "Insomnia","Water Veil","Speed Boost","Rivalry","Compound Eyes","Own Tempo","Heavy Metal","Minus","Volt Absorb",
            "Pressure","Anger Point","Shell Armor","Toxic Boost","Adaptability","Storm Drain","Cute Charm","Protean","Oblivious",
            "Sturdy","Soundproof","Flare Boost","Limber","Keen Eye","Rough Skin","Poison Heal","Overgrow","Blaze","Torrent",
            "Simple","Mold Breaker","Klutz","Zen Mode","Shadow Tag","Serene Grace","Motor Drive","No Guard","Swarm","Clear Body",
            "White Smoke","Truant","Bulletproof","Magician","Huge Power","Gale Wings","Symbiosis","Grass Pelt","Competitive",
            "Aroma Veil","Pixilate","Gooey","Long Reach","Liquid Voice","Pickup","Sweet Veil","Natural Cure","Grassy Surge",
            "Libero","Mirror Armor","Stakeout","Propeller Tail","Steely Spirit","Perish Body","Electric Surge","Ice Scales",
            "Psychic Surge","Stalwart"};

    //Default Queries to populate the table views
    static String default_query = "SELECT key, id, name, url, type1, type2, ability1, ability2, hiddenability, egggroup1, egggroup2, eggcycles FROM pokemon";
    static String personal_default_query = "SELECT pkey, p.key, id, name, nickname, pokeball, shiny, method, nature, ability, level FROM personal_pokemon c INNER JOIN pokemon p ON c.key=p.key";

    ObservableList<String> typeList = FXCollections.observableArrayList(types);
    ObservableList<String> abilitiesList = FXCollections.observableArrayList(abilities);
    ObservableList<String> hiddenAbilitiesList = FXCollections.observableArrayList(hiddenAbilities);

    //connector object used to connect to sqlite db
    Connection con = SqliteConnection.getDBConnection();

    //****MAIN  TAB*****
    //Get search parameters from the following methods
    public String type1ComboBox() {

        String type1Value = type1.getValue().toString();

        if (!type1Value.equalsIgnoreCase("Any")) {

            return "type1 = '" + type1Value + "'";

        }

        else {

            return null;
        }

    }
    public String type2ComboBox() {

        String type2Value = type2.getValue().toString();

        if (!type2Value.equalsIgnoreCase("Any")) {

            return "type2 = '" +  type2Value + "'";
        }

        else {

            return null;
        }


    }
    public String abilityComboBox() {

        String abilityValue = ability.getValue().toString();

        if (!abilityValue.equalsIgnoreCase("Any")) {

            return "(ability1 = '" + abilityValue +"' OR ability2 = '" + abilityValue + "')";

        }
        else {

            return null;
        }

    }
    public String hiddenAbilityComboBox() {

        String hiddenAbilityValue = hiddenAbility.getValue().toString();
        if (!hiddenAbilityValue.equalsIgnoreCase("Any")) {
            return "hiddenability = '" + hiddenAbilityValue + "'";
        }
        else {
            return null;
        }

    }

    // Use search parameters to repopulate the table view for pokedex.
    public void searchQuery() throws SQLException {

        ArrayList searchItems = new ArrayList();
        searchItems.add(type1ComboBox());
        searchItems.add(type2ComboBox());
        searchItems.add(abilityComboBox());
        searchItems.add(hiddenAbilityComboBox());
        searchItems.removeIf(Objects::isNull);
        String newQuery;

        //Only input a search query if there are search parameters
        if (searchItems.isEmpty()) {

            newQuery = default_query;
        }
        else {

            newQuery = default_query + " WHERE ";
        }

        //Concatonate the strings of each search parameter to create a new query
        for (int i = 0; i < searchItems.size(); i++) {

            if (i == (searchItems.size()-1)) {

                newQuery = newQuery + searchItems.get(i);
            }

            else {

                newQuery = newQuery + searchItems.get(i) + " AND ";

            }

        }

        //Refresh the tableview
        populateSQL(newQuery);

    }

    //Separate table view update if user types in the name of the pokemon
    public void getPokemonName() throws SQLException {

        String pokemon = pokemonName.getText();
        //Take any input  user puts in and normalize it so first letter is capitalized
        String searchName = pokemon.substring(0, 1).toUpperCase() + pokemon.substring(1);
        String nameQuery = default_query + " WHERE name LIKE '%" + searchName + "%'";

        populateSQL(nameQuery);
    }

    //Refresh the personal collection.
    public void refresh() throws SQLException {

        populatePersonalSQL(personal_default_query);

    }

    //SQL Population methods to update the pokedex table view
    public void populateSQL(String query) throws SQLException {

        ObservableList<ModelPokedex> pokedexList = FXCollections.observableArrayList();
        //Clear out previous list so it doesnt make a massive pokedex
        pokedexList.clear();
        ResultSet pokedex = null;

        try {

            pokedex = con.createStatement().executeQuery(query);

            while (pokedex.next()) {

                //Add column names to the list
                pokedexList.add(new ModelPokedex(pokedex.getInt("key"), pokedex.getInt("id"), pokedex.getString("name"),
                        pokedex.getString("url"), pokedex.getString("type1"), pokedex.getString("type2"),
                        pokedex.getString("ability1"), pokedex.getString("ability2"), pokedex.getString("hiddenability"),
                        pokedex.getString("egggroup1"), pokedex.getString("egggroup2"), pokedex.getInt("eggcycles")));
            }

        }

        catch (SQLException e) {

            e.printStackTrace();

        }

        //Set the columns in the table view to their id
        keyColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
        keyColumn.setVisible(false);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
        type1Column.setCellValueFactory(new PropertyValueFactory<>("type1"));
        type2Column.setCellValueFactory(new PropertyValueFactory<>("type2"));
        ability1Column.setCellValueFactory(new PropertyValueFactory<>("ability1"));
        ability2Column.setCellValueFactory(new PropertyValueFactory<>("ability2"));
        hiddenAbilityColumn.setCellValueFactory(new PropertyValueFactory<>("hiddenability"));
        egggroup1Column.setCellValueFactory(new PropertyValueFactory<>("egggroup1"));
        egggroup2Column.setCellValueFactory(new PropertyValueFactory<>("egggroup2"));
        eggCycleColumn.setCellValueFactory(new PropertyValueFactory<>("eggcycles"));

        pokedexView.setItems(pokedexList);
        pokedexView.refresh();

    }

    //Changing the scene to add a pokemon
    public int highlightedRow() {
        //See if there is a row higlighted or else just start with the first pokemon (0)
        try {

            ModelPokedex pokemon = pokedexView.getSelectionModel().getSelectedItem();
            int key = pokemon.getKey();
            return key;

        }
        catch (Exception e) {

            return 0;

        }

    }
    public void changeToAddPokemon(ActionEvent event) throws IOException, SQLException {
        //Close the connection from this class
        con.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("add-pokemon.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        AddPokemonController controller = loader.getController();
        controller.setPokemonKey(highlightedRow());
        controller.setAbilityChoices(highlightedRow());

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    //****PERSONAL COLLECTION TAB****
    //Delete the selected row.
    public void deleteRow() throws SQLException {

            ModelPersonal pokemon = personalView.getSelectionModel().getSelectedItem();
            int pokemonToDelete = pokemon.getPkey();
            String delStatement = "DELETE FROM personal_pokemon WHERE pkey = '" + pokemonToDelete + "'";

            //Delete the row and refresh the table view
            con.createStatement().executeUpdate(delStatement);
            populatePersonalSQL(personal_default_query);

    }

    //SQL population method to update the collection table view
    public void populatePersonalSQL(String personalQuery) throws SQLException {
        ObservableList<ModelPersonal> personalList = FXCollections.observableArrayList();
        personalList.clear();
        ResultSet personal = null;

        try {

            personal = con.createStatement().executeQuery(personalQuery);

            while (personal.next()) {

                personalList.add(new ModelPersonal(personal.getString("name"), personal.getInt("id"),personal.getString("nickname"), personal.getString("pokeball"),
                        personal.getString("shiny"), personal.getString("method"), personal.getString("nature"),
                        personal.getString("ability"), personal.getInt("pkey"), personal.getInt("key"), personal.getInt("level")));

            }
        }
        catch (SQLException e) {

            e.printStackTrace();

        }

        //Assign the columns in the pokedex view their ids
        pKeyColumn.setCellValueFactory(new PropertyValueFactory<>("pkey"));
        pKeyColumn.setVisible(false);
        pIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        pNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nicknameColumn.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        pokeballColumn.setCellValueFactory(new PropertyValueFactory<>("pokeball"));
        shinyColumn.setCellValueFactory(new PropertyValueFactory<>("shiny"));
        abilityColumn.setCellValueFactory(new PropertyValueFactory<>("ability"));
        methodColumn.setCellValueFactory(new PropertyValueFactory<>("method"));
        natureColumn.setCellValueFactory(new PropertyValueFactory<>("nature"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));

        //Populate the cells
        personalView.setItems(personalList);
        personalView.refresh();
    }

    //Initilize the window scene
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        type1.getItems().addAll(typeList);
        type1.getSelectionModel().selectFirst();
        type2.getItems().addAll(typeList);
        type2.getSelectionModel().selectFirst();
        ability.getItems().addAll(abilitiesList);
        ability.getSelectionModel().selectFirst();
        hiddenAbility.getItems().addAll(hiddenAbilitiesList);
        hiddenAbility.getSelectionModel().selectFirst();
        try {
            populateSQL(default_query);
            populatePersonalSQL(personal_default_query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}