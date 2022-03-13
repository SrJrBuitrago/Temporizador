package com.example.temporizador;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;


/**
 * Controlador de la aplicación Temporizador
 *
 * @author SrBlxde (José Ramón)
 * @author LzByte (Lázaro)
 * @see <a href="https://github.com/SrJrBuitrago">Cuenta de Github de José
 * Ramón</a>
 * @see <a href="https://github.com/LzByte">Cuenta de Github de Lázaro</a>
 * @see <a href="https://github.com/SrJrBuitrago/Sliders">Repositorio Sliders
 * </a>
 */

public class Controller implements Initializable {
    /**
     * Componente FXML en el que se van a mostrar las horas al usuario
     */
    @FXML
    public Text tHoras;
    /**
     * Componente FXML en el que se van a mostrar los minutos al usuario
     */
    @FXML
    public Text tMinutos;
    /**
     * Componente FXML en el que se van a mostrar los segundos al usuario
     */
    @FXML
    public Text tSegundos;
    /**
     * Componente FXML el cual tiene la función de que al ser pulsado ejecute
     * un método llamado
     *
     * @see #subirTiempo(ActionEvent) el cual tiene indicado en su propiedad
     * onAction
     */
    @FXML
    public Button bsTiempo;
    /**
     * Componente FXML el cual tiene la función de que al ser pulsado ejecute un
     * método llamado subirTiempo(event)
     * el cual tiene indicado en su propiedad onAction
     *
     * @see #subirTiempo(ActionEvent)
     */
    @FXML
    public Button bIniciar;
    /**
     * Componente FXML el cual tiene la función de que al ser pulsado ejecute un
     * método iniciar(event)
     * el cual tiene indicado en su propiedad onAction
     *
     * @see #iniciar(ActionEvent)
     */
    @FXML
    public Button bDetener;
    /**
     * Componente FXML el cual tiene la función de que al ser pulsado ejecute un
     * método llamado pausa(event)
     * el cual tiene indicado en su propiedad onAction
     * * @see #pausa(ActionEvent)
     */
    @FXML
    public Button bPausa;
    /**
     * Componente FXML el cual tiene la función de obtener datos introducidos
     * por el usuario
     *
     * @see #soloDosHoras(KeyEvent)
     */
    @FXML
    public TextField tfHoras;
    /**
     * Componente FXML el cual tiene la función de obtener datos introducidos
     * por el usuario
     *
     * @see #soloDosMinutos(KeyEvent)
     */
    @FXML
    public TextField tfMinutos;
    /**
     * Componente FXML el cual tiene la función de obtener datos introducidos
     * por el usuario
     *
     * @see #soloDosMinutos(KeyEvent)
     */
    @FXML
    public TextField tfSegundos;
    /**
     * Componente FXML el cual tiene la función de mostrar datos al usuario, en
     * este caso va a mostrar la hora actual
     */
    @FXML
    public Label tReloj;
    /**
     * Objeto de la clase Timeline llamado temporizador, su uso viene a ser
     * restar un segundo a nuestro temporizador
     * cada vez que en este objeto pase un segundo
     */
    public Timeline temporizador;
    /**
     * Objeto de la clase Timeline llamado reloj, va a ser mostrado por pantalla
     * en el programa
     */
    public Timeline reloj;
    /**
     * Variable int la cual va a almacenar el valor de los segundos
     */
    public int segundos;
    /**
     * Variable int la cual va a almacenar el valor de los minutos
     */
    public int minutos;
    /**
     * Variable int la cual va a almacenar el valor de las horas
     */
    public int horas;
    /**
     * Variable booleana que inicializo a false, es utilizada en el método
     * subirTiempo(event)
     *
     * @see #subirTiempo(ActionEvent)
     */
    public boolean camposVacios;
    /**
     * Variable booleana que inicializo a false, es utilizada en el método
     * subirTiempo(event)
     *
     * @see #subirTiempo(ActionEvent)
     */
    public boolean camposSuperiores;
    /**
     * Variable booleana que inicializo a false, es utilizada en el método
     * subirTiempo(event)
     *
     * @see #subirTiempo(ActionEvent)
     */
    public boolean camposACero;
    /**
     * Constante de la clase SimpleDateFormat el cual nos va a permitir dar
     * formato a nuestro temporizador y a nuestro reloj. Es utilizado en el
     * método initialize(url, resourceBundle)
     *
     * @see #initialize(URL, ResourceBundle)
     */
    public final SimpleDateFormat formato = new SimpleDateFormat
            ("HH:mm:ss");

    /**
     * Método que se ejecuta al iniciar la aplicación.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tfHoras.addEventHandler(KeyEvent.KEY_TYPED, event ->
                soloDosHoras(event));
        tfSegundos.addEventHandler(KeyEvent.KEY_TYPED, event ->
                soloDosSegundos(event));
        tfMinutos.addEventHandler(KeyEvent.KEY_TYPED, event ->
                soloDosMinutos(event));
        tfHoras.addEventHandler(KeyEvent.KEY_TYPED, event ->
                soloNumerosEnteros(event));
        tfMinutos.addEventHandler(KeyEvent.KEY_TYPED, event ->
                soloNumerosEnteros(event));
        tfSegundos.addEventHandler(KeyEvent.KEY_TYPED, event ->
                soloNumerosEnteros(event));

        bPausa.setDisable(true);
        bDetener.setDisable(true);
        bIniciar.setDisable(true);
        bsTiempo.setDisable(false);

        camposVacios = false;
        camposSuperiores = false;
        camposACero = false;

        temporizador = new Timeline();
        temporizador.setCycleCount(Animation.INDEFINITE);
        temporizador.getKeyFrames().add(new KeyFrame(Duration.millis(1000),
                (ActionEvent event) -> {
            segundos--;
            comprobarTiempos();
            formateaNumero(tHoras, horas);
            formateaNumero(tMinutos, minutos);
            formateaNumero(tSegundos, segundos);

        }));

        reloj = new Timeline();
        reloj.setCycleCount(Animation.INDEFINITE);
        reloj.getKeyFrames().add(new KeyFrame(Duration.millis(1000),
                (ActionEvent event) -> {
            tReloj.setText(formato.format(new Date()));
        }));
        reloj.play();
    }

    /**
     * Este método recibe un Action Event que es un onAction en el botón
     * bsTiempo, cuando este se pulsa se llama al método subirTiempo(ActionEvent
     * event) que lo que hace es llamar a los métodos compruebaVacios(),
     * camposSuperiores(), camposVacios() para dar valor true o false a tres
     * variables booleanas que usamos más abajo con un condicional if para
     * comprobar que todas estén en true, si todas son true llamamos al método
     * formateaNumero(Text, int) tres veces, una para cada Text que tenemos.
     * Activamos o desactivamos los botones que necesitamos, en este caso
     * desactivamos todos los botones menos el de iniciar. Y volvemos a poner
     * las variables booleanas a false para su correcto uso después. Si no se
     * cumple ese if tenemos un else el cual pone los tres Text a cero y las
     * variables booleanas a false
     *
     * @param event
     * @see #camposSuperiores()
     * @see #camposVacios()
     * @see #formateaNumero(Text, int)
     */
    public void subirTiempo(ActionEvent event) {
        camposVacios = compruebaVacios();
        camposSuperiores = camposSuperiores();
        camposACero = camposVacios();

        if (camposACero && camposVacios && camposSuperiores) {
            formateaNumero(tHoras, horas);
            formateaNumero(tMinutos, minutos);
            formateaNumero(tSegundos, segundos);

            bIniciar.setDisable(false);
            bsTiempo.setDisable(true);
            tfSegundos.setDisable(true);
            tfMinutos.setDisable(true);
            tfHoras.setDisable(true);

            camposVacios = false;
            camposACero = false;
            camposSuperiores = false;

        } else {
            tHoras.setText("00");
            tSegundos.setText("00");
            tMinutos.setText("00");
            camposVacios = false;
            camposACero = false;
            camposSuperiores = false;
        }
    }

    /**
     * Este método recibe un ActionEvent que es un onAction del botón bDetener,
     * este método llama al método ponerTodoACero() y para el objeto Timeline
     * temporizador
     *
     * @param event
     * @see #temporizador
     * @see #ponerTodoACero()
     */
    public void parar(ActionEvent event) {
        ponerTodoACero();
        temporizador.stop();
    }

    /**
     * Este método recibe un ActionEvent que es un onAction del botón bPausa,
     * este método pausa el temporizador y activa el botón bIniciar y desactiva
     * los botones bsTiempo y bPausa, los demás botones ya están desactivados.
     *
     * @param event
     * @see #bsTiempo
     * @see #bIniciar
     * @see #bPausa
     * @see #temporizador
     */
    public void pausa(ActionEvent event) {
        bIniciar.setDisable(false);
        bPausa.setDisable(true);
        bsTiempo.setDisable(true);
        temporizador.pause();
    }

    /**
     * Este método recibe un ActionEvent que es un onAction del botón bIniciar,
     * este método inicia el temporizadotr y activa los botones bPausa y
     * bDetener, además desactiva los botones bInciar y bsTiempo
     *
     * @param event
     * @see #bsTiempo
     * @see #bIniciar
     * @see #bPausa
     * @see #bDetener
     * @see #temporizador
     */
    public void iniciar(ActionEvent event) {
        bPausa.setDisable(false);
        bsTiempo.setDisable(true);
        bIniciar.setDisable(true);
        bDetener.setDisable(false);
        temporizador.play();
    }


    /**
     * Este método realiza la comprobación de si el carácter introducido es un
     * dígito y con el método consume() indicamos que el carácter que no sea un
     * dígito va a ser desechado y no se va a utilizar
     *
     * @param keyEvent
     */
    public void soloNumerosEnteros(KeyEvent keyEvent) {
        try {
            char key = keyEvent.getCharacter().charAt(0);

            if (!Character.isDigit(key))
                keyEvent.consume();

        } catch (Exception ex) {
        }
    }

    /**
     * Este método realiza la comprobación de si el texto que contiene el
     * TextField es mayor a dos caracteres, en este caso indicamos que sea mayor
     * que 1 pues como bien sabemos la primera posición es 0
     *
     * @param event
     */
    public void soloDosHoras(KeyEvent event) {
        if (tfHoras.getText().length() > 1) {
            event.consume();
        }
    }

    /**
     * Este método realiza la comprobación de si el texto que contiene el
     * TextField es mayor a dos caracteres, en este caso indicamos que sea mayor
     * que 1 pues como bien sabemos la primera posición es 0
     *
     * @param event
     */
    public void soloDosMinutos(KeyEvent event) {
        if (tfMinutos.getText().length() > 1) {
            event.consume();
        }
    }

    /**
     * Este método realiza la comprobación de si el texto que contiene el
     * TextField es mayor a dos caracteres, en este caso indicamos que sea mayor
     * que 1 pues como bien sabemos la primera posición es 0
     *
     * @param event
     */
    public void soloDosSegundos(KeyEvent event) {
        if (tfSegundos.getText().length() > 1) {
            event.consume();
        }
    }

    /**
     * Este método se encarga de comprobar que cuando los segundos/minutos
     * lleguen a cero se pongan automáticamente en 59 y en el caso de los
     * segundos reste un minuto y en el caso de los minutos reste una hora.
     * Además, también se encarga de controlar que cuando nuestro temporizador
     * llegue a 0 pare el objeto TimeLine temporizador y muestre por pantalla un
     * Alert al usuario indicándole que el tiempo ha finalizado
     */
    public void comprobarTiempos() {

        if (segundos == -1) {
            segundos = 59;
            minutos--;
            tSegundos.setText(String.valueOf(segundos));
            tMinutos.setText(String.valueOf(minutos));
            tHoras.setText(String.valueOf(horas));
        }
        if (minutos == -1) {
            minutos = 59;
            horas--;
            tSegundos.setText(String.valueOf(segundos));
            tMinutos.setText(String.valueOf(minutos));
            tHoras.setText(String.valueOf(horas));
        }
        if (horas == 0 && minutos == 0 & segundos == 0) {
            temporizador.stop();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tiempoooo!!");
            alert.setHeaderText(null);
            alert.setContentText("Tiempo Finalizado");
            alert.show();
            ponerTodoACero();
        }

    }

    /**
     * Este método recibe un Text y un int básicamente comprueba comprueba con
     * un condicional if que el int sea menor que diez, si es así, settea en el
     * Text la siguiente cadena ("0" + int) para que cuando en el Text se tenga
     * que representar un número menor que diez aparezca un cero a su izquierda.
     *
     * @param text
     * @param tiempo
     */
    public void formateaNumero(Text text, int tiempo) {

        if (tiempo < 10) {
            text.setText("0" + tiempo);
        } else {
            text.setText("" + tiempo);
        }
    }

    /**
     * Este método tiene una función que es poner los campos y los botones tal y
     * como cuando se inicia la app
     */
    public void ponerTodoACero() {
        tfSegundos.setText("");
        tfSegundos.setDisable(false);
        tfMinutos.setDisable(false);
        tfMinutos.setText("");
        tfHoras.setDisable(false);
        tfHoras.setText("");
        tHoras.setText("00");
        tMinutos.setText("00");
        tSegundos.setText("00");
        tHoras.setDisable(false);
        tMinutos.setDisable(false);
        tSegundos.setDisable(false);
        bDetener.setDisable(true);
        bsTiempo.setDisable(false);
        bIniciar.setDisable(true);
        bPausa.setDisable(true);
    }

    /**
     * Comprueba que las horas, segundos y minutos no sean igual a cero. Este
     * método se usa en el método subirTiempo(event) para comprobar que el
     * usuario no ha metido ceros en los TextField.
     *
     * @return false/true
     * @see #subirTiempo(ActionEvent)
     */
    public boolean camposVacios() {
        if (horas == 0 && minutos == 0 && segundos == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AVISO");
            alert.setHeaderText(null);
            alert.setContentText("Los valores no pueden estar a 0");
            alert.showAndWait();
            return false;
        } else {
            return true;
        }
    }

    /**
     * Este método se encarga de comprobar que los TextField no estén vacíos, si
     * no lo están, las variables segundos, minutos y horas cogen el valor de su
     * TextField correspondiente
     *
     * @return true/false
     */
    public boolean compruebaVacios() {
        if (!tfSegundos.getText().isEmpty() && !tfMinutos.getText().isEmpty() &&
                !tfHoras.getText().isEmpty()) {
            segundos = Integer.parseInt(tfSegundos.getText());
            minutos = Integer.parseInt(tfMinutos.getText());
            horas = Integer.parseInt(tfHoras.getText());
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AVISO");
            alert.setHeaderText(null);
            alert.setContentText("Debe rellenar todos los campos");
            alert.showAndWait();
            return false;
        }
    }

    /**
     * Este método se encarga de comprobar que los segundos no puedan ser
     * mayores a 59, que los minutos no puedan ser mayores a 59 y que las horas
     * no sean mayores de 23. Esto es comprobado mediante condicionales if/else
     * if cuando estos if/else if se cumplen se muestra un Alert al usuario
     * indicando el parámetro y que es superior
     *
     * @return false/true
     */
    public boolean camposSuperiores() {
        if (segundos > 59) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AVISO");
            alert.setHeaderText(null);
            alert.setContentText("El valor máximo para los segundos es 59");
            alert.showAndWait();
            return false;
        } else if (minutos > 59) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AVISO");
            alert.setHeaderText(null);
            alert.setContentText("El valor máximo para los minutos es 59");
            alert.showAndWait();
            return false;
        } else if (horas > 23) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AVISO");
            alert.setHeaderText(null);
            alert.setContentText("El valor máximo para las horas es 23");
            alert.showAndWait();
            return false;
        }
        return true;
    }

}
