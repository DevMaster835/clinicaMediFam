/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaHistorialController implements Initializable {

    @FXML
    private TextField txtnoHistorial;
    @FXML
    private TextField txtnoConsulta;
    @FXML
    private DatePicker txtfecha;
    @FXML
    private TextField txtPaciente;
    @FXML
    private TextField txtedad;
    @FXML
    private TextField txtpeso;
    @FXML
    private TextField txtaltura;
    @FXML
    private TextField txttipoSangre;
    @FXML
    private TextField txtTemperatura;
    @FXML
    private TextField txtpresion;
    @FXML
    private TextField txtpulso;
    @FXML
    private TextField txtrespiracion;
    @FXML
    private TextField txtmedico;
    @FXML
    private TextArea txtananmesis;
    @FXML
    private TextArea txttratamiento;
    @FXML
    private Button btnbConsulta;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnEliminar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buscarConsulta(ActionEvent event) {
    }

    @FXML
    private void agregarConsulta(ActionEvent event) {
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }

    @FXML
    private void eliminarConsulta(ActionEvent event) {
    }
    
}
