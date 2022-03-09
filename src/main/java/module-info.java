module com.example.temporizador {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.temporizador to javafx.fxml;
    exports com.example.temporizador;
}