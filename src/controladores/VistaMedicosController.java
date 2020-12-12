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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import modelos.Especialidades;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaMedicosController implements Initializable {

    @FXML
    private TextField txtidMedico;
    @FXML
    private TextField txtlicencia;
    @FXML
    private TextField txtexperiencia;
    @FXML
    private Button Close;
    @FXML
    private Button Minimize;
    @FXML
    private Button Return;
    @FXML
    private ComboBox<Especialidades> cmbEspecialidad;

    private double xOffset = 0; 
    private double yOffset = 0;
    /**
     * Initializes the controller class.
     */
    conexion con= new conexion();
    Connection cone= con.openConnection();
    
    ObservableList<Especialidades> comboEspecialidad;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtMedico;
    @FXML
    private Button btnBuscar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        llenarComboBox();
    }    

    @FXML
    private void exitButtonOnAction(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
    }

    @FXML
    private void minimizeButtonOnAction(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).setIconified(true);
    }

    @FXML
    private void ReturnButton(ActionEvent event) throws IOException {
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
    
    public void llenarComboBox(){
        comboEspecialidad= FXCollections.observableArrayList();
        Especialidades.llenarEspecialidad(cone, comboEspecialidad);
        cmbEspecialidad.setItems(comboEspecialidad);
        
    }

    @FXML
    private void guardarMedicos(ActionEvent event) {
        int especialidad= cmbEspecialidad.getSelectionModel().getSelectedIndex()+1;
        
        if(txtidMedico.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo Identidad está vacío, por favor ingrese la identidad del médico.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
        }else if(cmbEspecialidad.getValue()==null){
            JOptionPane.showMessageDialog(null, "El campo especialidad está vacío, por favor ingrese la especialidad del médico.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
        }else if(txtlicencia.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo licencia está vacío, por favor ingrese la licencia del médico.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
        }else if(txtexperiencia.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo años de experiencia está vacío, por favor ingrese años de experiencia del médico.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
        }else{
            
            try {
                PreparedStatement pps= cone.prepareStatement("INSERT INTO medicos (idEmpleado, idEspecialidad, licenciaMedica, añosExperiencia) VALUES(?,?,?,?)");
                pps.setString(1, txtidMedico.getText());
                pps.setString(2, String.valueOf(especialidad));
                pps.setString(3, txtlicencia.getText());
                pps.setString(4, txtexperiencia.getText());
                pps.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Se ha registrado los datos del Médico", "Datos guardados", JOptionPane.PLAIN_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(VistaMedicosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }

    @FXML
    private void buscarMedico(ActionEvent event) {
        try{
           PreparedStatement pps=cone.prepareStatement("SELECT * FROM empleados where idEmpleado=?");
            pps.setString(1, txtidMedico.getText());
           ResultSet rs=pps.executeQuery();
            
            if(rs.next()){
                txtMedico.setText(rs.getString("nombres"));
            }else{
                JOptionPane.showMessageDialog(null, "No existe ningun médico con identidad: " + txtidMedico.getText(), "No existe médico", JOptionPane.INFORMATION_MESSAGE);
                txtidMedico.requestFocus();
            }
        }catch (SQLException ex) {
            Logger.getLogger(VistaConsultasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
