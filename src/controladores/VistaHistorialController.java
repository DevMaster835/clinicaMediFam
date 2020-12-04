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
import javafx.scene.input.KeyEvent;
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
    @FXML
    private Button Close;
    @FXML
    private Button Minimize;
    @FXML
    private Button Return;

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
        
        if (txtnoHistorial.getText().isEmpty() ){
           JOptionPane.showMessageDialog(null, "El campo 'N° Historial' está vacío, por favor ingrese el número de historial    .", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtnoConsulta.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'N° Consulta' está vacío, por favor ingrese el número de consulta.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtPaciente.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Paciente' está vacío, por favor ingrese el nombre del paciente.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtedad.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Edad' está vacío, por favor ingrese la edad del paciente.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtpeso.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Peso' está vacío, por favor ingrese el peso del paciente.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txttipoSangre.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Tipo Sangre' está vacío, por favor ingrese el tipo de sangre del paciente.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtaltura.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Altura' está vacío, por favor ingrese la altura del paciente.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtananmesis.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Ananmesis' está vacío, por favor ingrese la ananmesis.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txttratamiento.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Tratamiento' está vacío, por favor ingrese el tratamiento.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtTemperatura.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Temperatura' está vacío, por favor ingrese la temperatura del paciente.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtpresion.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Presión Arterial' está vacío, por favor ingrese la presión arterial del paciente.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtpulso.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Pulso' está vacío, por favor ingrese el pulso del paciente.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtrespiracion.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Respiración' está vacío, por favor ingrese la respiración del paciente.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtfecha.getValue() == null){
           JOptionPane.showMessageDialog(null, "El campo 'Fecha' está vacío, por favor seleccione una fecha.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtmedico.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Médico' está vacío, por favor ingrese el nombre del médico.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
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

    //EVENTOS KEY TYPED
    @FXML
    private void txtHistorialKeyTyped(KeyEvent event) {
         char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtConsultaKeyTyped(KeyEvent event) {
         char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtTempKeyTyped(KeyEvent event) {
         char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car>'.'){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtpresionKeyTyped(KeyEvent event) {
         char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)&& car>'/'){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtpulsoKeyTyped(KeyEvent event) {
       char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car>'.'){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtrespKeyTyped(KeyEvent event) {
         char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void exitButtonOnAction(ActionEvent event) {
    }

    @FXML
    private void minimizeButtonOnAction(ActionEvent event) {
    }

    @FXML
    private void ReturnButton(ActionEvent event) {
    }
    
}
