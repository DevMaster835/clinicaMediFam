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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaFacturacionController implements Initializable {

    @FXML
    private TextField txtidEmp;
    @FXML
    private TextField txtempleado;
    @FXML
    private Button btnbuscarEmp;
    @FXML
    private TextField txtFactura;
    @FXML
    private DatePicker txtfechaFactura;
    @FXML
    private TextField txtcodigoProd;
    @FXML
    private TextField txtproducto;
    @FXML
    private Button btnbuscarProd;
    @FXML
    private TextField txtidPac;
    @FXML
    private TextField txtpaciente;
    @FXML
    private Button btnbuscarPac;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buscarEmpleado(ActionEvent event) {
    }

    @FXML
    private void buscarProducto(ActionEvent event) {
    }

    @FXML
    private void buscarPaciente(ActionEvent event) {
    }
    
}
