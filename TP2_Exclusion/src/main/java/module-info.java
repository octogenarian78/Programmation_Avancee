module com.example.tp2_exclusion {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tp2_exclusion to javafx.fxml;
    exports com.example.tp2_exclusion;
}