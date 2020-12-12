/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Conexion.conexion;
import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import modelos.EstadoConsulta;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaConsultasController implements Initializable {
    conexion con= new conexion();
    Connection cone= con.openConnection();
    
    PreparedStatement pps;
    ResultSet rs;
    
    ObservableList<EstadoConsulta> estadoConsulta;

    @FXML
    private TextField txtnoConsulta;
    @FXML
    private DatePicker txtfechaCreacion;
    @FXML
    private DatePicker txtfechaCita;
    @FXML
    private TextField txtidPaciente;
    @FXML
    private TextField txtnombrePac;
    @FXML
    private ComboBox<EstadoConsulta> cmbestadoC;
    @FXML
    private TextArea txtmotivo;
    @FXML
    private TextField txtidMedico;
    @FXML
    private TextField txtMedico;
    @FXML
    private Button btnbuscarPac;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnbuscarMedico;
    @FXML
    private TextField txthoraCita;
    @FXML
    private TableView<?> tablaConsultas;
    @FXML
    private TextField txtBuscar;

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button Close;
    @FXML
    private Button Minimize;
    @FXML
    private Button Return;
    
    private double xOffset = 0; 
    private double yOffset = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        estadoConsulta=FXCollections.observableArrayList();
        EstadoConsulta.cmbEstado(cone, estadoConsulta);
        cmbestadoC.setItems(estadoConsulta);
        
        Tooltip tooltipClose = new Tooltip("Close");
        Close.setTooltip(tooltipClose);
        
        Tooltip tooltipMinimize = new Tooltip("Minimize");
        Minimize.setTooltip(tooltipMinimize);
        
        Tooltip tooltipReturn = new Tooltip("Return");
        Return.setTooltip(tooltipReturn);
        
        
    } 
    
    @FXML
    private void exitButtonOnAction(ActionEvent event){
     ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();      
    }

    @FXML
    private void minimizeButtonOnAction(ActionEvent event){
     ((Stage)(((Button)event.getSource()).getScene().getWindow())).setIconified(true);
    }
    
    @FXML
    void ReturnButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/vistaMenu.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setResizable(false);
                root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void eliminarConsulta(ActionEvent event) {
        
    }

    @FXML
    private void cancelar(ActionEvent event) {
        limpiarDatos();
    }
    
    private void limpiarDatos(){
    
        txtnoConsulta.setText("");
        txtfechaCreacion.setValue(null);
        txtfechaCita.setValue(null);
        txthoraCita.setText("");
        txtidPaciente.setText("");
        txtnombrePac.setText("");
        txtidMedico.setText("");
        txtMedico.setText("");
        txtmotivo.setText("");
        cmbestadoC.setValue(null);
        txtfechaCreacion.requestFocus();
    }

    @FXML
    private void agregarConsulta(ActionEvent event) {
        LocalDate fechaCreacion= txtfechaCreacion.getValue();
        LocalDate fechaCita=txtfechaCita.getValue();
    //    String estadoCita= (String) cmbestadoC.getValue();
        int estadoCon= cmbestadoC.getSelectionModel().getSelectedIndex() + 1;
       
        System.out.println(fechaCreacion);
        
         if (txtfechaCreacion.getValue() == null ) {

            JOptionPane.showMessageDialog(null, "El campo 'Fecha de creación' está vacío, por favor seleccione una fecha.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
            
        }else if(txtfechaCita.getValue() ==null){
            JOptionPane.showMessageDialog(null, "El campo 'Fecha de cita' está vacío, por favor seleccione una fecha.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
        }else if(txthoraCita.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "El campo 'Hora' está vacío, por favor ingrese la hora de la cita.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
        }else if(txtidPaciente.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo 'Identidad' está vacío, por favor ingrese la identidad del paciente.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
        }else if(txtnombrePac.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo 'Nombre' está vacío, por favor ingrese el nombre del paciente.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
        }else if(txtidMedico.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo 'Identidad' está vacío, por favor ingrese la identidad del médico.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
        }else if(txtMedico.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo 'Médico' está vacío, por favor ingrese el nombre del médico.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
        }else if(txtmotivo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo 'Motivo' está vacío, por favor ingrese el motivo.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
        }else if(cmbestadoC.getValue()==null){
            JOptionPane.showMessageDialog(null, "El campo 'Estado de Cita' está vacío, por favor ingrese la hora de la cita.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
        
        try {
            pps=cone.prepareStatement("INSERT INTO consultas_medicas(fechaCreacion,fechaConsulta,horaConsulta,idPaciente,idMedico,motivo,idEstadoConsulta) VALUES(?,?,?,?,?,?,?)");
            pps.setString(1, String.valueOf(fechaCreacion));
            pps.setString(2, String.valueOf(fechaCita));
            pps.setString(3, txthoraCita.getText());
            pps.setString(4, txtidPaciente.getText());
            pps.setString(5, txtidMedico.getText());
            pps.setString(6, txtmotivo.getText());
            pps.setString(7, String.valueOf(estadoCon));
            pps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Se ha registrado la consulta medica", "Datos guardados", JOptionPane.PLAIN_MESSAGE);
            
        } catch (SQLException ex) {
            Logger.getLogger(VistaConsultasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    }

    @FXML
    private void buscarPaciente(ActionEvent event) {
        
        try{
            pps=cone.prepareStatement("SELECT * FROM pacientes where idPaciente=?");
            pps.setString(1, txtidPaciente.getText());
            rs=pps.executeQuery();
            
            if(rs.next()){
                txtnombrePac.setText(rs.getString("nombres"));
            }else{
                JOptionPane.showMessageDialog(null, "No existe ningun paciente con identidad: " + txtidPaciente.getText(), "No existe paciente", JOptionPane.INFORMATION_MESSAGE);
                txtidPaciente.requestFocus();
            }
        }catch (SQLException ex) {
            Logger.getLogger(VistaConsultasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void buscarMedico(ActionEvent event) {
        try{
            String prueba;
            pps=cone.prepareStatement("SELECT * FROM empleados where idEmpleado=?");
            pps.setString(1, txtidMedico.getText());
            rs=pps.executeQuery();
            
            if(rs.next()){
                txtMedico.setText(rs.getString("nombres"));
                prueba= rs.getString("apellidos");
                System.out.println(prueba);
            }else{
                JOptionPane.showMessageDialog(null, "No existe ningun empleado con identidad: " + txtidPaciente.getText(), "No existe empleado", JOptionPane.INFORMATION_MESSAGE);
                txtidMedico.requestFocus();
            }
        }catch (SQLException ex) {
            Logger.getLogger(VistaConsultasController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void txtidPacKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtidEmpKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void buscarConsulta(KeyEvent event) {
    }
    
}
