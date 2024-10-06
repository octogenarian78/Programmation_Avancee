module com.example.tp1_mobile {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tp1_mobile to javafx.fxml;
    exports com.example.tp1_mobile;
}