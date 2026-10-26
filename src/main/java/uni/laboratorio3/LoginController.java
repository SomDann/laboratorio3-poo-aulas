package uni.laboratorio3;

import uni.laboratorio3.App;
import uni.laboratorio3.dao.UsuarioDAO;
import uni.laboratorio3.entidades.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;

public class LoginController {

    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtClave;
    @FXML private Label lblMensaje;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private void ingresar() throws IOException {
        String correo = txtCorreo.getText().trim();
        String clave = txtClave.getText().trim();

        if (correo.isEmpty() || clave.isEmpty()) {
            lblMensaje.setText("Por favor complete todos los campos.");
            return;
        }

        Usuario usuario = usuarioDAO.autenticar(correo, clave);
        if (usuario != null) {
            App.setRoot("home");
        } else {
            lblMensaje.setText("Correo o contraseña incorrectos.");
        }
    }
}
