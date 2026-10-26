// Modulo: Controladores FXML y gestion de interfaz de aulas

package uni.laboratorio3;

import uni.laboratorio3.App;
import uni.laboratorio3.dao.AulaDAO;
import uni.laboratorio3.entidades.Aula;
import uni.laboratorio3.entidades.EstadoAula;
import uni.laboratorio3.entidades.PlantaAula;
import uni.laboratorio3.entidades.TipoAula;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML private TextField txtNombre;
    @FXML private TextField txtEdificio;
    @FXML private ComboBox<PlantaAula> cbPlanta;
    @FXML private TextField txtCapacidad;
    @FXML private ComboBox<TipoAula> cbTipoAula;
    @FXML private TextField txtResponsable;
    @FXML private ComboBox<EstadoAula> cbEstado;

    private final AulaDAO aulaDAO = new AulaDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarCatalogos();
    }

    private void cargarCatalogos() {
        cbPlanta.setItems(FXCollections.observableArrayList(aulaDAO.listarPlantas()));
        cbTipoAula.setItems(FXCollections.observableArrayList(aulaDAO.listarTipos()));
        cbEstado.setItems(FXCollections.observableArrayList(aulaDAO.listarEstados()));
    }

    @FXML
    private void guardar() {
        String nombre = txtNombre.getText().trim();
        String edificio = txtEdificio.getText().trim();
        PlantaAula plantaSeleccionada = cbPlanta.getValue();
        String capTexto = txtCapacidad.getText().trim();
        TipoAula tipo = cbTipoAula.getValue();
        String responsable = txtResponsable.getText().trim();
        EstadoAula estado = cbEstado.getValue();

        // 1. Validacion de campos vacios
        if (nombre.isEmpty() || edificio.isEmpty() || plantaSeleccionada == null || capTexto.isEmpty() 
                || tipo == null || responsable.isEmpty() || estado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos requeridos", "Todos los campos son obligatorios.");
            return;
        }

        // 2. Validacion de capacidad como entero
        int capacidad;
        try {
            capacidad = Integer.parseInt(capTexto);
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Formato invalido", "La capacidad debe ser un numero entero.");
            return;
        }

        // 3. Validacion de enteros mayores a cero
        if (plantaSeleccionada.getNivel() <= 0 || capacidad <= 0) {
            mostrarAlerta(Alert.AlertType.WARNING, "Valores no permitidos", "Planta y capacidad deben ser enteros mayores que 0.");
            return;
        }

        // 4. Validacion de unicidad (nombre y edificio)
        if (aulaDAO.existeAula(nombre, edificio)) {
            mostrarAlerta(Alert.AlertType.ERROR, "Registro duplicado", 
                "Ya existe el aula '" + nombre + "' registrada en el " + edificio + ".");
            return;
        }

        // 5. Creacion y persistencia de la entidad
        Aula nueva = new Aula();
        nueva.setNombre(nombre);
        nueva.setEdificio(edificio);
        nueva.setPlanta(plantaSeleccionada.getNivel());
        nueva.setCapacidad(capacidad);
        nueva.setTipoAula(tipo);
        nueva.setResponsable(responsable);
        nueva.setEstado(estado);

        if (aulaDAO.guardar(nueva)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Registro exitoso", "El aula ha sido guardada correctamente en la base de datos.");
            limpiar();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error del sistema", "No se pudo persistir el aula en la base de datos.");
        }
    }

    @FXML
    private void limpiar() {
        txtNombre.clear();
        txtEdificio.clear();
        cbPlanta.getSelectionModel().clearSelection();
        txtCapacidad.clear();
        cbTipoAula.getSelectionModel().clearSelection();
        txtResponsable.clear();
        cbEstado.getSelectionModel().clearSelection();
        txtNombre.requestFocus();
    }

    @FXML
    private void cerrarSesion() throws IOException {
        App.setRoot("login");
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
