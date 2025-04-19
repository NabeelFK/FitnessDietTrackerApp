module com.example.fitnesstrackergp_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;
    requires java.desktop;


    opens com.example.fitnesstrackergp_gui to javafx.fxml;
    exports com.example.fitnesstrackergp_gui;
}