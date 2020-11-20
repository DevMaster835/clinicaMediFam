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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private ComboBox<String> cmbNacionalidad;
    


    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         /*   ObservableList<String> items = FXCollections.observableArrayList();
            items.addAll("item-1", "item-2", "item-3", "item-4", "item-5");

            ComboBox<String> cbx = new ComboBox<>(items);
            StackPane pane = new StackPane(cbx);*/
    }
    
    public void start(Stage primaryStage){
        //cargarCombobox();
         
             
        
    }
    
    //String email;
    
    public boolean existeEmpleado(){
        try {
            Statement st = cone.createStatement();
            String sql = "Select nombres from empleados where idEmpleado = '"+txtidEmpleado.getText()+"'";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                JOptionPane.showMessageDialog(null, " Ya existe "+" el número de identidad: "+txtidEmpleado.getText(), "Número de identidad ¡Ya existe!", JOptionPane.INFORMATION_MESSAGE);
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
    
    public boolean validarIdentidad(String identidad){
        String id = identidad.substring(0, 1);
        if(identidad.length() < 5){
             JOptionPane.showMessageDialog(null, "El número de identidad debe de tener 13 dígitos, ha ingresado solamente "+identidad.length()+" dígitos.", "Número de identidad invalido", JOptionPane.ERROR_MESSAGE);
        }
        if(identidad.length() == 5){
             if("0".equals(id)){
                 return true;
             }
             else if("1".equals(id)){
                 return true;
             }
             else{
                 JOptionPane.showMessageDialog(null, "El número de identidad sólo puede comenzar con 0 o 1 ", "Error en campo identidad", JOptionPane.ERROR_MESSAGE);
                 return false;
             }
        }
        else{
           return false; 
        }    
    }
    
    
    
    private boolean validarLongitud(TextField texto, int longitud){
       if(texto.getText().length() >= longitud){
           return true;
       }
       else{
           return false;
       }
    }
    
    private boolean validarLongitudMax(String texto, int longitud) {
        if (texto.length() <= longitud) {
            return true;
        } else {
            return false;
        }
    }
    
    


    //METODOS GUARDAR
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
        
        if (txtidEmpleado.getText().isEmpty()){
            
             JOptionPane.showMessageDialog(null, "El campo 'Identidad' está vacío, por favor ingrese la identidad del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
                }else if (txtNombreEmp.getText().isEmpty() ){
               JOptionPane.showMessageDialog(null, "El campo 'Nombres' está vacío, por favor ingrese el nombre del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE); 
                }else if (txtApellidoEmp.getText().isEmpty() ){
               JOptionPane.showMessageDialog(null, "El campo 'Apellidos' está vacío, por favor ingrese los apellidos del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE); 
                }else if (txtCorreoEmp.getText().isEmpty() ){
               JOptionPane.showMessageDialog(null, "El campo 'Correo' está vacío, por favor ingrese el correo electrónico del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE); 
                }else if (txtDireccionEmp.getText().isEmpty() ){
               JOptionPane.showMessageDialog(null, "El campo 'Dirección' está vacío, por favor ingrese la dirección del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE); 
                }else if (txtFechaEmp.getText().isEmpty() ){
               JOptionPane.showMessageDialog(null, "El campo 'Fecha nacimiento' está vacío, por favor ingrese la fecha de nacimiento del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE); 
                }else if (txtTelEmp.getText().isEmpty() ){
               JOptionPane.showMessageDialog(null, "El campo 'Teléfono' está vacío, por favor ingrese el teléfono del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE); 
                }else if (txtNombreEmp.getText().isEmpty() ){
               JOptionPane.showMessageDialog(null, "El campo 'Nombres' está vacío, por favor ingrese el nombre del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE); 
        }else{
            

        try{
            if(existeEmpleado()){
            return;
            }
            if(!validarLongitudTelefono(txtTelEmp, 8)){
            return;
            }
        
           if(!isEmailValid(txtCorreoEmp.getText())){
            return;
            }
           
           if (!validarLongitudMax(txtNombreEmp.getText(), 40)) {
            JOptionPane.showMessageDialog(null, "Los nombres del empleado ingresados son muy largos el máximo es de 40 caracteres, usted ingresó " + txtNombreEmp.getText().length() + " caracteres.", "Longitud de los nombres del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
            if (!validarLongitudMax(txtApellidoEmp.getText(), 40)) {
            JOptionPane.showMessageDialog(null, "Los apellidos del empleado ingresados son muy largos el máximo es de 40 caracteres, usted ingresó " + txtApellidoEmp.getText().length() + " caracteres.", "Longitud de los apellidos del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
            if (!validarLongitudMax(txtTelEmp.getText(), 8)) {
            JOptionPane.showMessageDialog(null, "El teléfono del empleado ingresado es muy largo el máximo es de 8 dígitos, usted ingresó " + txtTelEmp.getText().length() + " dígitos.", "Longitud del teléfono del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
            if (!validarLongitudMax(txtidEmpleado.getText(), 13)) {
             JOptionPane.showMessageDialog(null, "El salario del empleado ingresado es muy largo el máximo es de 13 dígitos, usted ingresó " + txtidEmpleado.getText().length() + " dígitos.", "Longitud del número de identidad del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
           
            if(validarIdentidad(txtidEmpleado.getText())){
            return;
            }
            

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
 
    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {
            char a=evt.getKeyChar();
            if (evt.getKeyChar() == 8 || evt.getKeyChar() == 127 || 
                 evt.getKeyChar() == 0 || evt.getKeyChar() == 3 || evt.getKeyChar() == 22 
                 || evt.getKeyChar() == 26 || evt.getKeyChar() == 24) {
        return;
        }
        if(txtTelEmp.getText().length() >=8){
            evt.consume();
            
            JOptionPane.showMessageDialog(null, "Número máximo de dígitos admitidos");
        }
     
        if(Character.isLetter(a) || !Character.isLetterOrDigit(a)){
            evt.consume();
            JOptionPane.showMessageDialog(null, "Sólo números");
        }
    }
    
    private boolean validarLongitudTelefono(TextField texto, int longitud){
       if(texto.getText().length() == longitud){
                Pattern pattern = Pattern.compile("[23789]");
                Matcher matcher=pattern.matcher(texto.getText().substring(0,1));
                if(matcher.matches()){ 
                        return true;
                    }else{
                        JOptionPane.showMessageDialog(null, "El número de teléfono debe comenzar con: 2,3,7,8 o 9");
                        return false;
                    } 
       }
        else{
       }
       JOptionPane.showMessageDialog(null, "El número de teléfono debe ser de 8 dígitos", "Longitud del número de telefono",JOptionPane.INFORMATION_MESSAGE);
       return false;
    }
    
    
    public static boolean isEmailValid(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);
     if(EMAIL_REGEX.matcher(email).matches()) {
         return true;
     }else{
         JOptionPane.showMessageDialog(null, "El correo no es valido");
         return false;
         
     }
    }

    @FXML
    private void txtNombreEmpKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isAlphabetic(car) && !Character.isSpaceChar(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten letras");
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
    private void txtApellidoEmpKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isAlphabetic(car) && !Character.isSpaceChar(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten letras");
        }
    }

    @FXML
    private void txtTelefonoKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

}
