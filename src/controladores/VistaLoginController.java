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
import org.apache.commons.codec.digest.DigestUtils;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaLoginController implements Initializable {
    
    conexion con= new conexion();
    Connection cone= con.openConnection();

    
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    } 
    
    public void start(Stage primaryStage){
        //cargarCombobox();
    }
    
    private boolean validacionSimbolosContraseña(String texto){
          for (int i = 0; i < texto.length(); i++) {
              if(texto.contains(" ") || texto.contains("|"))
                  return true;
          }
          return false;
      }

    @FXML
    private void iniciarSesion(ActionEvent event) throws IOException {
       try {
            String usuario = txtUsuario.getText();
            String contraseña = String.valueOf(txtContra.getText());
            String contraseñaEncriptada=DigestUtils.md5Hex(contraseña);
            String sql = "SELECT * from usuarios where nombreUsuario ='" +usuario+ "' and contraseña='"+contraseñaEncriptada+"' ";

            pps=cone.prepareStatement(sql);
         //   pps.setString(1, usuario);
          //  pps.setString(2, contraseña);
            System.out.println(contraseñaEncriptada);
            
            rs = pps.executeQuery();
          /*  if(isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor llene todos los campos.", "Ingrese sus datos", JOptionPane.INFORMATION_MESSAGE);
                return;
            }*/
            if(!validarContraseñas(contraseña)){
            
                return;
            }
            if(validacionSimbolosContraseña(contraseña)){
            JOptionPane.showMessageDialog(null, "La contraseña no puede contener espacios o barras largas (|) ");
            return;
            }
            
            if((txtUsuario.getText().equals(""))){
            javax.swing.JOptionPane.showMessageDialog(null,"Debe ingresar el nombre de usuario del empleado.","Nombre  de usuario del empleado requerido",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            txtUsuario.requestFocus();
            return;
            }
            if((txtContra.getText().equals(""))){
            javax.swing.JOptionPane.showMessageDialog(null,"Debe ingresar la contraseña del usuario del empleado.","Contraseña de usuario del empleado requerido",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            txtContra.requestFocus();
            return;
            }
            if(rs.next()){
                Parent root = FXMLLoader.load(getClass().getResource("/vistas/vistaMenu.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
                
            }else{
               JOptionPane.showMessageDialog(null, "La contraseña es incorrecta, intente nuevamente" //REVISAR PARA INDICAR SI USUARIO O CONTRASEÑA ES INCORRECTO
                         , "Ingrese la contraseña correcta", JOptionPane.ERROR_MESSAGE);
               txtContra.requestFocus();
            }
            
       }catch (SQLException ex) {
                 Logger.getLogger(VistaLoginController.class.getName()).log(Level.SEVERE, null, ex);
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
             if(!politicasContraseñaNumeros(contraseña) && !politicasContraseñaMayusculas(contraseña) && !politicasContraseñaMinusculas(contraseña)){
                 JOptionPane.showMessageDialog(null, "La contraseña no cumple con: \n 1. Debe contener al menos una letra minúscula (a-z)"
                         + "\n 2. Debe contener al menos una letra mayúscula (A-Z) \n 3. Debe contener al menos un número (0-9)", "¡Directrices de contraseña no cumplidas!", JOptionPane.ERROR_MESSAGE);
                 return false;
             }
             else if(politicasContraseñaNumeros(contraseña) && !politicasContraseñaMayusculas(contraseña) && !politicasContraseñaMinusculas(contraseña)){
                 JOptionPane.showMessageDialog(null, "La contraseña no cumple con: \n 1. Debe contener al menos una letra minúscula (a-z)"
                         + "\n 2. Debe contener al menos una letra mayúscula (A-Z)", "¡Directrices de contraseña no cumplidas!", JOptionPane.ERROR_MESSAGE);
                 return false;
             }
             else if(politicasContraseñaNumeros(contraseña) && politicasContraseñaMayusculas(contraseña) && !politicasContraseñaMinusculas(contraseña)){
                 JOptionPane.showMessageDialog(null, "La contraseña no cumple con: \n 1. Debe contener al menos una letra minúscula (a-z)"
                         , "¡Directrices de contraseña no cumplidas!", JOptionPane.ERROR_MESSAGE);
                 return false;
             } else if(politicasContraseñaNumeros(contraseña) && !politicasContraseñaMayusculas(contraseña) && politicasContraseñaMinusculas(contraseña)){
                 JOptionPane.showMessageDialog(null, "La contraseña no cumple con: \n 1. Debe contener al menos una letra mayúscula (A-Z)"
                         , "¡Directrices de contraseña no cumplidas!", JOptionPane.ERROR_MESSAGE);
                 return false;
             }
             else if(politicasContraseñaNumeros(contraseña) && politicasContraseñaMayusculas(contraseña) && politicasContraseñaMinusculas(contraseña)){
                 return true;
             }       
        }
        else if(contraseña.length() > 1){
            JOptionPane.showMessageDialog(null, "La contraseña es muy corta debe ser de al menos 8 caracteres.", "Longitud de contraseña menor al requerido", JOptionPane.ERROR_MESSAGE);
            txtContra.requestFocus();
           return false; 
        }    
        return true;
    }
    
    public boolean politicasContraseñaNumeros(String contraseña){
        boolean tieneNumero = false; 
        char c;
        
        for(int i=0; i<contraseña.length();i++){
            c = contraseña.charAt(i);
            if(Character.isDigit(c)){
                tieneNumero = true;
            }
            if(tieneNumero){
                return true;      
            }
        }
        return false;      
      }
    
      
      public boolean politicasContraseñaMayusculas(String contraseña){ 
        boolean tieneMayusculas = false; 
        char c;
        
        for(int i=0; i<contraseña.length();i++){
            c = contraseña.charAt(i);
            if(Character.isUpperCase(c)){
                tieneMayusculas = true;
            }
            if(tieneMayusculas){
                return true;
            }
        }
        return false;
    }
      
      public boolean politicasContraseñaMinusculas(String contraseña){
        boolean tieneMinusculas = false;
        char c;
        
        for(int i=0; i<contraseña.length();i++){
            c = contraseña.charAt(i);           
            if(Character.isLowerCase(c)){
                tieneMinusculas = true;
            }
            if(tieneMinusculas){
                return true;
            }
        }
        return false;
    }
      /*
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
*/
    @FXML
    private void txtUsuarioKeyTyped(KeyEvent event) {
        if(txtUsuario.getText().length() >=15){
            event.consume();
            JOptionPane.showMessageDialog(null, "Número máximo de caracteres admitidos.");
        }
       // char a=event.getKeyChar();
    }

    @FXML
    private void txtContraseñaKeyTyped(KeyEvent event) {
       // txtContra.setText(txtContra.getText().trim());
        if(txtContra.getText().length() >=15){
            event.consume();
            JOptionPane.showMessageDialog(null, "Número máximo de caracteres admitidos.");
        }
    }
    
 
    

    

    

    }
    

