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
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

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
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtcontNeto;
    @FXML
    private TextField txtprecioProd;
    @FXML
    private TextField txtcantidad;

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
    
    @FXML
    private void guardarFactura(ActionEvent event) {
        if (txtFactura.getText().isEmpty() ){
           JOptionPane.showMessageDialog(null, "El campo 'Factura No' está vacío, por favor ingrese el número de factura.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtfechaFactura.getValue() == null ){
           JOptionPane.showMessageDialog(null, "El campo 'Fecha' está vacío, por favor seleccione la fecha.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtidEmp.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Identidad' está vacío, por favor ingrese la identidad del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtempleado.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Empleado' está vacío, por favor ingrese el nombre del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtidPac.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Identidad' está vacío, por favor ingrese la identidad del paciente.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtpaciente.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Paciente' está vacío, por favor ingrese el nombre del paciente.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtcodigoProd.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Código' está vacío, por favor ingrese el código del producto.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtproducto.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Producto' está vacío, por favor ingrese el nombre del producto.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtcontNeto.getText().isEmpty()){   
           JOptionPane.showMessageDialog(null, "El campo 'Contenido Neto' está vacío, por favor ingrese el contenido neto del producto.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtprecioProd.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Precio' está vacío, por favor ingrese el precio.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtcantidad.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Cantidad' está vacío, por favor ingrese el precio.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }

    //EVENTOS KEY-TYPED
    @FXML
    private void txtidEmpKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtFacturaKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtcodProdKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtcantidadKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtidPacKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    
    
}
