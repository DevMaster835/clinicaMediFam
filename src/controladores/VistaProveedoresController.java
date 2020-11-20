/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Conexion.conexion;
import com.mysql.jdbc.Connection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaProveedoresController implements Initializable {

    conexion con= new conexion();
    Connection cone= con.openConnection();
    
    PreparedStatement pps;

    @FXML
    private TextField txtidProv;
    @FXML
    private TextField txtRTN;
    @FXML
    private TextField txtnombreProv;
    @FXML
    private TextField txtcontacto;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtcorreoProv;
    @FXML
    private TextArea txtdireccionProv;
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

    //

    @FXML
    private void agregarProveedores(ActionEvent event) {
        
        if (txtidProv.getText() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de Identidad del Proveedor esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtRTN.getText() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de RTN esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtnombreProv.getText() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de Nombre esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtcontacto.getText() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de Contacto esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtdireccionProv.getText() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de Direccion esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtTelefono.getText() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de Telefono neto esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else{
        try{
            if(!isEmailValid(txtcorreoProv.getText())){
            return;
            }
            
            if(!validarLongitudTelefono(txtTelefono, 8)){
            return;
            }
            
            if (!validarLongitudMax(txtnombreProv.getText(), 40)) {
            JOptionPane.showMessageDialog(null, "El nombre del proveedor ingresados es muy largo el máximo es de 40 caracteres, usted ingresó " + txtnombreProv.getText().length() + " caracteres.", "Longitud de los nombres del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
            if (!validarLongitudMax(txtcontacto.getText(), 40)) {
            JOptionPane.showMessageDialog(null, "El nombre del contacto ingresado es muy largo el máximo es de 40 caracteres, usted ingresó " + txtcontacto.getText().length() + " caracteres.", "Longitud de los apellidos del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
            if (!validarLongitudMax(txtTelefono.getText(), 8)) {
            JOptionPane.showMessageDialog(null, "El teléfono del proveedor ingresado es muy largo el máximo es de 8 dígitos, usted ingresó " + txtTelefono.getText().length() + " dígitos.", "Longitud del teléfono del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
            if (!validarLongitudMax(txtidProv.getText(), 13)) {
             JOptionPane.showMessageDialog(null, "La identidad del proveedor ingresado es muy largo el máximo es de 13 dígitos, usted ingresó " + txtidProv.getText().length() + " dígitos.", "Longitud del número de identidad del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
            
            pps=cone.prepareStatement("INSERT INTO proveedores(idProveedor,RTN,nombreProveedor,nombreContacto,direccion) VALUES(?,?,?,?,?)");
            pps.setString(1, txtidProv.getText());
            pps.setString(2, txtRTN.getText());
            pps.setString(3, txtnombreProv.getText());
            pps.setString(4, txtcontacto.getText());
            pps.setString(5, txtdireccionProv.getText());
            pps.executeUpdate();
            
            pps=cone.prepareStatement("INSERT INTO telefonos_proveedores(idProveedor,telefono) VALUES(?,?)");
            pps.setString(1, txtidProv.getText());
            pps.setString(2, txtTelefono.getText());
            pps.executeUpdate();
            
            pps=cone.prepareStatement("INSERT INTO correo_proveedores(idProveedor,correo) VALUES(?,?)");
            pps.setString(1, txtidProv.getText());
            pps.setString(2, txtcorreoProv.getText());
            pps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Se ha registrado los datos del proveedor", "Datos guardados", JOptionPane.PLAIN_MESSAGE);     
        }catch(SQLException ex){
            Logger.getLogger(VistaPacientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }

    @FXML
    private void eliminarProveedores(ActionEvent event) {
    }
    
    public static boolean isEmailValid(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);
        
            if(EMAIL_REGEX.matcher(email).matches()) {
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "El correo no es valido");
                   return false;
            }
    }
    
    private boolean validarLongitudTelefono(TextField texto, int longitud){
       if(texto.getText().length() == longitud){
                Pattern pattern = Pattern.compile("[23789]");
                Matcher matcher=pattern.matcher(texto.getText().substring(0,1));
                if(matcher.matches()){ 
                        return true;
                    }else{
                        JOptionPane.showMessageDialog(null, "El número de teléfono debe comenzar con: 2,3,7,8 o 9");
                        return false;
                    } 
       }
        else{
       }
       JOptionPane.showMessageDialog(null, "El número de teléfono debe ser de 8 dígitos", "Longitud del número de telefono",JOptionPane.INFORMATION_MESSAGE);
       return false;
    }
    
    private boolean validarLongitud(TextField texto, int longitud){
       if(texto.getText().length() >= longitud){
           return true;
       }
       else{
           return false;
       }
    }
    
    private boolean validarLongitudMax(String texto, int longitud) {
        if (texto.length() <= longitud) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    private void txtcodigoKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtrtnKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtnombreKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isAlphabetic(car) && !Character.isSpaceChar(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten letras");
        }
    }

    @FXML
    private void txtcontactoKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isAlphabetic(car) && !Character.isSpaceChar(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten letras");
        }
    }

    @FXML
    private void txttelefonoKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }
    
}
