package com.example.temporizador;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Controlador de la aplicación Temporizador
 * @author SrBlxde (José Ramón)
 * @author LzByte (Lázaro)
 * @see <a href="https://github.com/SrJrBuitrago"></a>
 * @see <a href="https://github.com/LzByte"></a>
 */

public class Controller implements Initializable {
    @FXML
    private Text tHoras;
    @FXML
    private Text tMinutos;
    @FXML
    private Text tSegundos;
    @FXML
    private Button bsValores;
    @FXML
    private Button bIniciar;
    @FXML
    private Button bDetener;
    @FXML
    private Button bReiniciar;
    @FXML
    private Button bPausa;
    @FXML
    private TextField tfHoras;
    @FXML
    private TextField tfMinutos;
    @FXML
    private TextField tfSegundos;
    @FXML
    private AnchorPane aPane;
    private Timeline cronometro;
    private Timeline reloj;

    private int segundos;
    private int minutos;
    private int horas;

    private final SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        tfHoras.addEventHandler(KeyEvent.KEY_TYPED, event -> soloNumerosEnteros(event));
        tfMinutos.addEventHandler(KeyEvent.KEY_TYPED, event -> soloNumerosEnteros(event));
        tfSegundos.addEventHandler(KeyEvent.KEY_TYPED, event -> soloNumerosEnteros(event));
        bReiniciar.disabledProperty();
    }

        public void subirTiempo(ActionEvent event){
        segundos = Integer.parseInt(tfSegundos.getText());
        horas = Integer.parseInt(tfHoras.getText());
        minutos = Integer.parseInt(tfMinutos.getText());
        if (segundos>59){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AVISO");
            alert.setHeaderText(null);
            alert.setContentText("El valor máximo para los segundos es 59");
            alert.showAndWait();
             } else if (minutos > 59) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AVISO");
            alert.setHeaderText(null);
            alert.setContentText("El valor máximo para los minutos es 59");
            alert.showAndWait();
        }else if(horas>23){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AVISO");
            alert.setHeaderText(null);
            alert.setContentText("El valor máximo para las horas es 24");
            alert.showAndWait();

        }else {
            tHoras.setText(String.valueOf(horas));
            tMinutos.setText(String.valueOf(minutos));
            tSegundos.setText(String.valueOf(segundos));
        }
        }
        public void reiniciar(ActionEvent event){
        tfSegundos.setText("");
            tfMinutos.setText("");
            tfHoras.setText("");
            tHoras.setText("00");
            tMinutos.setText("00");
            tSegundos.setText("00");
        }
        public void parar(ActionEvent event){
            tfSegundos.setText("");
            tfMinutos.setText("");
            tfHoras.setText("");
        }
        //eRealizar los metodos que faltan, meterlos en su action del boton correspondiente y hacer el crono
        public void pausa(ActionEvent event){

        }
        public void iniciar(ActionEvent event){

        }
    /**
     Este método recibe un parámetro y realiza la comprobación de si el carácter introducido es un dígito y con el método
     consume() indicamos que el carácter que no sea un dígito no va a ser manejado por ningun controlador
     @param keyEvent
     */
        private void soloNumerosEnteros (KeyEvent keyEvent){
            try {
                char key = keyEvent.getCharacter().charAt(0);

                if (!Character.isDigit(key))
                    keyEvent.consume();

            } catch (Exception ex) {
            }
        }
}
