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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

    @FXML
    private void agregarProveedores(ActionEvent event) {
        
        try{
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

    @FXML
    private void cancelar(ActionEvent event) {
    }

    @FXML
    private void eliminarProveedores(ActionEvent event) {
    }
    
}
