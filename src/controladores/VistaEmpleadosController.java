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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaEmpleadosController implements Initializable {
    
    conexion con= new conexion();
    Connection cone= con.openConnection();
    
   
    PreparedStatement pps;

    @FXML
    private TextField txtFechaEmp;
    @FXML
    private TextField txtidEmpleado;
    @FXML
    private TextField txtNombreEmp;
    @FXML
    private TextField txtApellidoEmp;
    private TextField txtNacEmp;
    @FXML
    private TextField txtTelEmp;
    @FXML
    private TextField txtCorreoEmp;
    @FXML
    private TextArea txtDireccionEmp;
    @FXML
    private ToggleGroup grupoGen;
    @FXML
    private ComboBox<?> cmbTipoEmp;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnGuardar;
    @FXML
    private RadioButton rdbM;
    @FXML
    private RadioButton rdbF;
    @FXML
    private ComboBox<?> cmbtipoCorreo;
    @FXML
    private ComboBox<?> cmbNacionalidad;

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void start(Stage primaryStage){
        //cargarCombobox();
    }
    
    public boolean existeEmpleado(){
        try {
            Statement st = cone.createStatement();
            String sql = "Select nombres from empleados where idEmpleado = '"+txtidEmpleado.getText()+"'";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                JOptionPane.showMessageDialog(null, " Ya existe "+" el número de identidad: "+txtidEmpleado.getText(), "Número de identidad ¡Ya existe!", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
     @FXML
    private void guardarEmpleados(ActionEvent event) {
        int genero=0;
        
        if(rdbM.isSelected()==true){
            genero=1;
        }else if(rdbF.isSelected()==true){
            genero=2;
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione el género del empleado", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }
        
        String tipoEmp= (String) cmbTipoEmp.getValue();
        String nacionalidad= (String) cmbNacionalidad.getValue();
        String tipoCorreo= (String) cmbtipoCorreo.getValue();
        
        if (txtidEmpleado.getText().isEmpty() || txtNombreEmp.getText().isEmpty() || txtApellidoEmp.getText().isEmpty() || txtFechaEmp.getText().isEmpty() || genero==0 || txtCorreoEmp.getText().isEmpty()|| cmbNacionalidad.getValue()==null 
                || txtDireccionEmp.getText().isEmpty() || txtTelEmp.getText().isEmpty() || cmbTipoEmp.getValue()==null){
            
            JOptionPane.showMessageDialog(null, "El campo está vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else{
        try{
            if(existeEmpleado()){
            return;
        }
            pps= cone.prepareStatement("INSERT INTO empleados(idEmpleado,nombres,apellidos,fechaNacimiento,idGenero,idNacionalidad, direccion, tipoEmpleado) VALUES (?,?,?,?,?,?,?,?)");
            pps.setString(1, txtidEmpleado.getText());
            pps.setString(2, txtNombreEmp.getText());
            pps.setString(3, txtApellidoEmp.getText());
            pps.setString(4, txtFechaEmp.getText());
            pps.setString(5, String.valueOf(genero));
            pps.setString(6, nacionalidad);
            pps.setString(7, txtDireccionEmp.getText());
            pps.setString(8, tipoEmp);
            pps.executeUpdate();
            
            
            pps=cone.prepareStatement("INSERT INTO telefonos_empleados(idEmpleado,telefono) VALUES(?,?)");
            pps.setString(1, txtidEmpleado.getText());
            pps.setString(2, txtTelEmp.getText());
            pps.executeUpdate();
            
            pps=cone.prepareStatement("INSERT INTO correo_empleados(idEmpleado, correo, tipoCorreo) VALUES(?,?,?)");
            pps.setString(1, txtidEmpleado.getText());
            pps.setString(2, txtCorreoEmp.getText());
            pps.setString(3, tipoCorreo);
            pps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Se ha registrado los datos del Empleado", "Datos guardados", JOptionPane.PLAIN_MESSAGE);
        }catch (SQLException ex) {
            Logger.getLogger(VistaEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        
    }

    @FXML
    private void eliminarEmpleados(ActionEvent event) {
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }
 
    private boolean validarLongitud(TextField texto, int longitud){
       if(texto.getText().length() >= longitud){
           return true;
       }
       else{
           return false;
       }
    }
 
    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {
            char a=evt.getKeyChar();
            if (evt.getKeyChar() == 8 || evt.getKeyChar() == 127 || 
                 evt.getKeyChar() == 0 || evt.getKeyChar() == 3 || evt.getKeyChar() == 22 
                 || evt.getKeyChar() == 26 || evt.getKeyChar() == 24) {
        return;
        }
        if(txtTelEmp.getText().length() >=8){
            evt.consume();
            
            JOptionPane.showMessageDialog(null, "Número máximo de dígitos admitidos");
        }
     
        if(Character.isLetter(a) || !Character.isLetterOrDigit(a)){
            evt.consume();
            JOptionPane.showMessageDialog(null, "Sólo números");
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
    
    
    private boolean validarEmail(TextField texto, String txtCorreoEmp){
        if(texto.getText() == txtCorreoEmp){
                Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                Matcher matcher=pattern.matcher(texto.getText().substring(0,1));
                if(matcher.matches()){ 
                        return true;
                    }else{
                        JOptionPane.showMessageDialog(null, "El correo no es valido");
                        return false;
                    } 
       }
        else{
       }
       JOptionPane.showMessageDialog(null, "El número de teléfono debe ser de 8 dígitos", "Longitud del número de telefono",JOptionPane.INFORMATION_MESSAGE);
       return false;
    }


    @FXML
    private void txtTelefonoKeyTyped(ActionEvent event) {
    }
}
