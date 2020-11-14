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
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaHistorialController implements Initializable {

    conexion con= new conexion();
    Connection cone= con.openConnection();
    
    PreparedStatement pps;
    ResultSet rs;
    
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
        try {
            pps=cone.prepareStatement("SELECT pacientes.nombres, pacientes.peso, pacientes.altura, tipo_sangre.tipoSangre, empleados.nombres, empleados.apellidos FROM consultas_medicas, pacientes,tipo_sangre, empleados WHERE consultas_medicas.idPaciente=pacientes.idPaciente and consultas_medicas.idMedico=empleados.idEmpleado and tipo_sangre.idSangre=pacientes.tipoSangre and noConsulta=?");
            pps.setString(1, txtnoConsulta.getText());
            rs=pps.executeQuery();
            
            if(rs.next()){
                txtPaciente.setText(rs.getString("pacientes.nombres"));
                txtpeso.setText(rs.getString("pacientes.peso"));
                txtaltura.setText(rs.getString("pacientes.altura"));
                txttipoSangre.setText(rs.getString("tipo_sangre.tipoSangre"));
                txtmedico.setText(rs.getString("empleados.nombres"));
                
            }else{
                JOptionPane.showMessageDialog(null, "No existe ninguna consulta con identidad: " + txtPaciente.getText(), "No existe paciente", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaHistorialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
        @FXML
    private void crearHistorial(ActionEvent event) {
        LocalDate fechaCreacion= txtfecha.getValue();
        
        if (txtnoHistorial.getText().isEmpty() || txtfecha.getValue()==null || txtnoConsulta.getText().isEmpty() || txtananmesis.getText().isEmpty()
                || txttratamiento.getText().isEmpty() || txtTemperatura.getText().isEmpty() || txtpresion.getText().isEmpty() || txtpulso.getText().isEmpty()|| txtrespiracion.getText().isEmpty()){
           
           JOptionPane.showMessageDialog(null, "El campo está vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
           
       }else{
            
        
        try {
            pps=cone.prepareStatement("INSERT INTO historial_medico(idHistorial,fechaCreacion,noConsulta,anamnesis,diagnostico,tratamiento) VALUES(?,?,?,?,?,?)");
            pps.setString(1, txtnoHistorial.getText());
            pps.setString(2, String.valueOf(fechaCreacion));
            pps.setString(3, txtnoConsulta.getText());
            pps.setString(4, txtananmesis.getText());
            pps.setString(5, txttratamiento.getText());
            pps.setString(6, txttratamiento.getText());
            pps.executeUpdate();
            
            pps=cone.prepareStatement("INSERT INTO signos_vitales(idHistorial,temperatura,presion,pulso,respiracion) VALUES(?,?,?,?,?)");
            pps.setString(1, txtnoHistorial.getText());
            pps.setString(2, txtTemperatura.getText());
            pps.setString(3, txtpresion.getText());
            pps.setString(4, txtpulso.getText());
            pps.setString(5, txtrespiracion.getText());
            pps.executeUpdate();
             
            JOptionPane.showMessageDialog(null, "Se ha registrado el Historial", "Datos guardados", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(VistaHistorialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    }


    @FXML
    private void cancelar(ActionEvent event) {
    }



    @FXML
    private void eliminarHistorial(ActionEvent event) {
    }
    
}
