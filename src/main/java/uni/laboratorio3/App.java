package uni.laboratorio3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 540, 520);
        stage.setTitle("Sistema de Gestión de Aulas");
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        if (fxml.endsWith(".fxml")) {
            fxml = fxml.substring(0, fxml.length() - 5);
        }
        if (fxml.contains("/")) {
            fxml = fxml.substring(fxml.lastIndexOf("/") + 1);
        }

        String ruta = "/com/mycompany/loguinjavafx/" + fxml.toLowerCase() + ".fxml";
        var recurso = App.class.getResource(ruta);

        if (recurso == null) {
            throw new IOException("No se encontro el archivo FXML en la ruta: " + ruta);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(recurso);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}