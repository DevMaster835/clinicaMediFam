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
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaProductosController implements Initializable {
    
    conexion con= new conexion();
    Connection cone= con.openConnection();
    
    PreparedStatement pps;

    @FXML
    private TextField txtfechaVen;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField txtcodigoProd;
    @FXML
    private TextField txtnombreProd;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtExistencia;
    @FXML
    private TextField txtconNeto;
    @FXML
    private TextField txtFabricante;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
        @FXML
    private void agregarProductos(ActionEvent event) {
        
        if (txtcodigoProd.getText().isEmpty() || txtnombreProd.getText().isEmpty() || txtPrecio.getText().isEmpty()
                || txtfechaVen.getText().isEmpty() || txtExistencia.getText().isEmpty() || txtconNeto.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "El campo esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);

        } else{
        
        try{
            pps=cone.prepareStatement("INSERT INTO productos(idProducto,nombre,idPrecioHis,fechaVencimiento,stock,contenidoNeto) VALUES(?,?,?,?,?,?)");
            pps.setString(1, txtcodigoProd.getText());
            pps.setString(2, txtnombreProd.getText());
            pps.setString(3, txtPrecio.getText());
            pps.setString(4, txtfechaVen.getText());
            pps.setString(5, txtExistencia.getText());
            pps.setString(6, txtconNeto.getText());
           // pps.setString(7, txtFabricante.getText());
            pps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Se ha registrado los datos del producto", "Datos guardados", JOptionPane.PLAIN_MESSAGE);
        }catch(SQLException ex){
            Logger.getLogger(VistaPacientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }  
    }

    @FXML
    private void eliminarProductos(ActionEvent event) {
    }

    @FXML
    private void cancelarProductos(ActionEvent event) {
    }


    
}
