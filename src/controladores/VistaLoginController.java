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
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaLoginController implements Initializable {
    
    Connection con;
    
    PreparedStatement pps;
    ResultSet rs;

    @FXML
    private Pane panel1;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContra;
    @FXML
    private Button btnIniciar;
    @FXML
    private Button btnSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   

    @FXML
    private void iniciarSesion(ActionEvent event) throws IOException {
       try {
            String usuario = txtUsuario.getText();
            String contraseña = String.valueOf(txtContra.getText());
            String sql = "SELECT * from usuarios where nombreUsuario ='" +usuario+ "' and contraseña='"+contraseña+"' COLLATE Latin1_General_CS_AS";
            Statement st = (Statement) con.createStatement();
            rs = st.executeQuery(sql);
            if(isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor llene todos los campos.", "Ingrese sus datos", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if(rs.next()){
                Parent root = FXMLLoader.load(getClass().getResource("vistaMedicos.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
       }catch (SQLException ex) {
                 Logger.getLogger(VistaEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VistaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private boolean isEmpty(){
        if("".equals(txtUsuario.getText()) || "".equals(txtContra.getText()))
        return true;
        else
            return false;
        }
    
    private void dispose() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @FXML
    private void salirSistema(ActionEvent event) {
    }
    
    public boolean validarContraseñas(String contraseña){
        if(contraseña.length() > 7){
             if(politicasContraseña(contraseña)){
                 return true;
             }
             else{
                 JOptionPane.showMessageDialog(null, "La contraseña no cumple con: \n 1. Debe contener al menos una letra minúscula (a-z)"
                         + "\n 2. Debe contener al menos una letra mayúscula (A-Z) \n 3. Debe contener al menos un número (0-9)", "¡Directrices de contraseña no cumplidas!", JOptionPane.ERROR_MESSAGE);
                 return false;
             }
        }
        else{
            JOptionPane.showMessageDialog(null, "La contraseña es muy corta debe ser de al menos 8 caracteres.", "Longitud de contraseña menor al requerido", JOptionPane.ERROR_MESSAGE); 
           return false; 
        }    
    }
    
      public boolean politicasContraseña(String contraseña){
        boolean tieneNumero = false; 
        boolean tieneMayusculas = false; 
        boolean tieneMinusculas = false;
        char c;
        
        for(int i=0; i<contraseña.length();i++){
            c = contraseña.charAt(i);
            if(Character.isDigit(c)){
                tieneNumero = true;
            }
            else if(Character.isUpperCase(c)){
                tieneMayusculas = true;
            }
            else if(Character.isLowerCase(c)){
                tieneMinusculas = true;
            }
            if(tieneNumero && tieneMayusculas && tieneMinusculas){
                return true;
            }
        }
        return false;
    }

    @FXML
    private void txtUsuario(KeyEvent event) {
    }
    

    }
    

