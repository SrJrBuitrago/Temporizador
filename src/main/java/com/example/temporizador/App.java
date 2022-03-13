
package com.example.temporizador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación Temporizador
 *
 * @author SrBlxde (José Ramón)
 * @author LzByte (Lázaro)
 * @see <a href="https://github.com/SrJrBuitrago">Cuenta de Github de José
 * Ramón</a>
 * @see <a href="https://github.com/LzByte">Cuenta de Github de Lázaro</a>
 * @see <a href="https://github.com/SrJrBuitrago/Sliders">Repositorio Sliders
 * </a>
 */
public class App extends Application {
    private static Scene scene;

    /**
     * Este método recibe un objeto stage y puede lanzar excepciones. Crea un
     * objeto scene y mediante el método loadFXML() carga el fichero .fxml
     * además le damos un ancho y un alto a esa scene. Le damos un título al
     * stage usando el método setTitle de la clase Stage, asignamos nuestra
     * scene al stage mediante el método setScene de la clase Stage. Bloqueamos
     * que nuestro stage sea expandible usando el método setResizable(false) de
     * la clase Stage y mostramos el stage configurado con el método show().
     *
     * @param stage
     * @throws IOException
     * @see Stage
     */
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(loadFXML("principal"), 600, 400);
        stage.setTitle("Temporizador");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(
                fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Este método main llama al método launch() para lanzar nuestra escena
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}