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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaPacientesController implements Initializable {
    
    conexion con= new conexion();
    Connection cone= con.openConnection();
    
    PreparedStatement pps;

    @FXML
    private TextField txtidPaciente;
    @FXML
    private TextField txtNombrePaciente;
    @FXML
    private TextField txtApellidoPaciente;
    @FXML
    private TextField txtTelPaciente;
    @FXML
    private TextField txtCorreoPaciente;
    @FXML
    private TextArea txtDireccionPaciente;
    @FXML
    private TextField txtPesoPac;
    @FXML
    private TextField txtAlturaPac;
    @FXML
    private ComboBox<?> cmbTipoSangre;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnEliminar;
    @FXML
    private RadioButton rdbM;
    @FXML
    private ToggleGroup grupoGen;
    @FXML
    private RadioButton rdbF;
    @FXML
    private TextField txtFechaPac;
    @FXML
    private ComboBox<?> cmbNacionalidad;
    @FXML
    private ComboBox<?> cmbCorreo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void limpiarDatos(){
        txtidPaciente.setText("");
        txtNombrePaciente.setText("");
        txtApellidoPaciente.setText("");
        txtFechaPac.setText("");
        cmbNacionalidad.setValue(null);
        txtDireccionPaciente.setText("");
        txtPesoPac.setText("");
        txtAlturaPac.setText("");
        txtTelPaciente.setText("");
        
        
        
        
    }
    
    public boolean existePaciente(){
        try {
            Statement st = cone.createStatement();
            String sql = "Select nombres from pacientes where idPaciente = '"+txtidPaciente.getText()+"'";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                JOptionPane.showMessageDialog(null, " Ya existe"+" el número de identidad: "+txtidPaciente.getText(), "Número de identidad ¡Ya existe!", JOptionPane.INFORMATION_MESSAGE);
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
    private void guardarPacientes(ActionEvent event) {
        int genero=0;
        
        if(rdbM.isSelected()==true){
            genero=1;
        }else if(rdbF.isSelected()==true){
            genero=2;
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione el género del paciente", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }
        
        System.out.println(genero);
        
        String nacionalidad= (String) cmbNacionalidad.getValue();
        String tipoCorreo= (String) cmbCorreo.getValue();
        String tipoSangre=(String) cmbTipoSangre.getValue();
        
        
        if (txtidPaciente.getText().isEmpty() || txtNombrePaciente.getText().isEmpty() || txtApellidoPaciente.getText().isEmpty() || genero==0
                || txtFechaPac.getText().isEmpty() || txtDireccionPaciente.getText().isEmpty() || txtPesoPac.getText().isEmpty()|| txtAlturaPac.getText().isEmpty() || cmbTipoSangre.getValue() == null || cmbNacionalidad.getValue() == null || cmbCorreo.getValue() == null ) {

            JOptionPane.showMessageDialog(null, "El campo esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else{ 
        try{
            if(existePaciente()){
            return;
        }
            pps=cone.prepareStatement("INSERT INTO pacientes(idPaciente,nombres,apellidos,fechaNacimiento,idGenero,idNacionalidad,direccion,peso,altura,tipoSangre) VALUES(?,?,?,?,?,?,?,?,?,?)");
            pps.setString(1, txtidPaciente.getText());
            pps.setString(2, txtNombrePaciente.getText());
            pps.setString(3, txtApellidoPaciente.getText());
            pps.setString(4, txtFechaPac.getText());
            pps.setString(5, String.valueOf(genero));
            pps.setString(6, nacionalidad);
            pps.setString(7, txtDireccionPaciente.getText());
            pps.setString(8, txtPesoPac.getText());
            pps.setString(9, txtAlturaPac.getText());
            pps.setString(10, tipoSangre);
            pps.executeUpdate();
            
            pps=cone.prepareStatement("INSERT INTO telefonos_pacientes(idPaciente,telefono) VALUES(?,?)");
            pps.setString(1, txtidPaciente.getText());
            pps.setString(2, txtTelPaciente.getText());
            pps.executeUpdate();
            
            pps=cone.prepareStatement("INSERT INTO correo_pacientes(idPaciente,correo,tipoCorreo) VALUES(?,?,?)");
            pps.setString(1, txtidPaciente.getText());
            pps.setString(2, txtCorreoPaciente.getText());
            pps.setString(3, tipoCorreo);
            pps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Se ha registrado los datos del paciente", "Datos guardados", JOptionPane.PLAIN_MESSAGE);
        }catch(SQLException ex){
            Logger.getLogger(VistaPacientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }

    @FXML
    private void eliminarPacientes(ActionEvent event) {
    }
    
}
