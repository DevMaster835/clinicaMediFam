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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    /*
    private void cargarCombobox(){
        try {
            //  conexion con= new conexion();
            // Connection cone= con.openConnection();

            String query ="SELECT * FROM nacionalidades";
            
            PreparedStatement ps=cone.prepareCall(query);
            ResultSet rs=ps.executeQuery();
            
          while(rs.next()){
              cmbTipoEmp.setId(rs.getString("nacionalidad"));
              
          }
            ps.close();
            rs.close();
            

 
        } catch (SQLException ex) {
            Logger.getLogger(VistaEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }

*/

    
}
