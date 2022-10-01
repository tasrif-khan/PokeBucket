module com.example.pokebucket {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;


    opens com.main.pokebucket to javafx.fxml;
    exports com.main.pokebucket;
}