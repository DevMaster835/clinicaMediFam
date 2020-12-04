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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;
import modelos.Correos;
import modelos.Empleados;
import modelos.Telefonos;
import modelos.Nacionalidades;
import modelos.tipoCorreoE;
import modelos.tipoEmpleados;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaEmpleadosController implements Initializable {
    
    conexion con= new conexion();
    Connection cone= con.openConnection();
    Empleados emp;
    
    
    
   
    PreparedStatement pps;
    ObservableList<Empleados> listaEmpleados;
    ObservableList<Nacionalidades> listaNacionalidades;
    ObservableList<tipoEmpleados> listaTipoEmpleados;
    ObservableList<tipoCorreoE> listaTipoCorreo;
    ObservableList<Telefonos> listaContacto;
    ObservableList<Correos> listaCorreo;

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
    private ComboBox<tipoEmpleados> cmbTipoEmp;
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
    private ComboBox<tipoCorreoE> cmbtipoCorreo;
    @FXML
    private ComboBox<Nacionalidades> cmbNacionalidad;
    @FXML
    private RadioButton rdbId;
    @FXML
    private ToggleGroup grupoBusqueda;
    @FXML
    private RadioButton rdbNom;
    @FXML
    private TableView<Empleados> tblEmpleados;
    @FXML
    private TableColumn<Empleados, String>id;
    @FXML
    private TableColumn<Empleados, String> nombre;
    @FXML
    private DatePicker txtFechaNaci;
    @FXML
    private TableColumn<Empleados, String> apellidos;
    @FXML
    private TableColumn<Empleados, String> genero;
    @FXML
    private TableColumn<Empleados, Nacionalidades> colNacionalidad;
    @FXML
    private TableColumn<Empleados, String> colDireccion;
    @FXML
    private TableColumn<Empleados, tipoEmpleados> colTipoEmp;
    @FXML
    private TableColumn<Empleados, Date> fechaNac;
    private TableColumn<Empleados, String> direccion;
    private TableColumn<Empleados, String> tipoEmpleado;
    @FXML
    private TableView<Telefonos> tablaTelefonos;
    @FXML
    private TableView<Correos> tablaCorreos;
    @FXML
    private TableColumn<?, ?> idTel;
    @FXML
    private TableColumn<?, ?> colEmpTel;
    @FXML
    private TableColumn<?, ?> colTel;
    @FXML
    private TableColumn<?, ?> idCorreo;
    @FXML
    private TableColumn<?, ?> colEmpCo;
    @FXML
    private TableColumn<?, ?> colCorreo;
    @FXML
    private TableColumn<?, ?> colTipoC;
    @FXML
    private Button btnAgregarTelefono;
    @FXML
    private Button btnAgregarCorreo;
    @FXML
    private Button btnActualizar;
    @FXML
    private TextField txtBuscar;
    @FXML
    private Button Close;
    @FXML
    private Button Minimize;
    @FXML
    private Button Return;

    private double xOffset = 0; 
    private double yOffset = 0;
    

    /**
     * Initializes the controller class.
     * @param url
     */
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
         //Inicializar
         listaNacionalidades= FXCollections.observableArrayList();
         listaTipoEmpleados= FXCollections.observableArrayList();
         listaTipoCorreo= FXCollections.observableArrayList();
         
         //llenar lista
         Nacionalidades.llenarTabla(cone, listaNacionalidades);
         tipoEmpleados.cmbTipoEmpleado(cone, listaTipoEmpleados);
         tipoCorreoE.cmbTipoCorreoE(cone, listaTipoCorreo);
         
         //Enlazar listas
         cmbNacionalidad.setItems(listaNacionalidades);
         cmbTipoEmp.setItems(listaTipoEmpleados);
         cmbtipoCorreo.setItems(listaTipoCorreo);

         //llenar combobox
         cmbNacionalidad.setItems(listaNacionalidades);
         cmbTipoEmp.setItems(listaTipoEmpleados);
         
         formatoFecha();
         tablaEmpleados();
         tablaContacto();
         gestionarEventos();
       
    }
    
    public void start(Stage primaryStage){
        //cargarCombobox();
         
             
        
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
    
    public void formatoFecha(){
        //formato fecha
        txtFechaNaci.setConverter(new StringConverter<LocalDate>() {
        String pattern = "dd-MM-yyyy";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);      
        {
                txtFechaNaci.setPromptText(pattern.toLowerCase());
         
        }

             @Override
             public String toString(LocalDate date) {
                 if (date != null) {
                    return dateFormatter.format(date);
                }else {
                    return "";
                }
             }

             @Override
             public LocalDate fromString(String string) {
                 if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                }else {
                    return null;
                }
             }
        
        });
    }
    
    public void tablaEmpleados(){
        listaEmpleados= FXCollections.observableArrayList();
        Empleados.llenarTabla(cone, listaEmpleados);
        tblEmpleados.setItems(listaEmpleados);
        
         id.setCellValueFactory(new PropertyValueFactory("idEmp"));
         nombre.setCellValueFactory(new PropertyValueFactory("nombres"));
         apellidos.setCellValueFactory(new PropertyValueFactory("apellidos"));
         fechaNac.setCellValueFactory(new PropertyValueFactory("fechaNac"));
         genero.setCellValueFactory(new PropertyValueFactory("idGenero"));
         colNacionalidad.setCellValueFactory(new PropertyValueFactory("nac"));
         colDireccion.setCellValueFactory(new PropertyValueFactory("direccion"));
         colTipoEmp.setCellValueFactory(new PropertyValueFactory("tipoE"));
    }
    
    public void tablaContacto(){
        //TABLA DE TELEFONOS
         listaContacto= FXCollections.observableArrayList();
         idTel.setCellValueFactory(new PropertyValueFactory("id"));
         this.colEmpTel.setCellValueFactory(new PropertyValueFactory("nombre"));
         this.colTel.setCellValueFactory(new PropertyValueFactory("numero"));
         
         //TABLA DE CORREOS
         listaCorreo= FXCollections.observableArrayList();
         idCorreo.setCellValueFactory(new PropertyValueFactory("id"));
         colEmpCo.setCellValueFactory(new PropertyValueFactory("nombre"));
         colCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
         colTipoC.setCellValueFactory(new PropertyValueFactory("tipoCorreo"));
    }
    
    public void gestionarEventos(){
         
        this.tblEmpleados.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Empleados>(){
            @Override
            public void changed(ObservableValue<? extends Empleados> arg0,
                    Empleados valorAnterior, Empleados valorSeleccionado) {
                
                txtidEmpleado.setText(String.valueOf(valorSeleccionado.getIdEmp()));
                txtNombreEmp.setText(String.valueOf(valorSeleccionado.getNombres()));
                txtApellidoEmp.setText(String.valueOf(valorSeleccionado.getApellidos()));
                if(valorSeleccionado.getIdGenero().equals("Masculino")){
                    rdbM.setSelected(true);
                }else{
                    rdbF.setSelected(true);
                }
                txtFechaNaci.setValue(valorSeleccionado.getFechaNac().toLocalDate());
                cmbNacionalidad.setValue(valorSeleccionado.getNac());
                cmbTipoEmp.setValue(valorSeleccionado.getTipoE());
                txtDireccionEmp.setText(valorSeleccionado.getDireccion());
                
                //System.out.println("Seleccionó un registro");
            }
                    
                
        }
        );
    }
    
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
        if(identidad.length() < 13){
             JOptionPane.showMessageDialog(null, "El número de identidad debe de tener 13 dígitos, ha ingresado solamente "+identidad.length()+" dígitos.", "Número de identidad invalido", JOptionPane.ERROR_MESSAGE);
        }
        if(identidad.length() == 13){
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
    
    public void limpiarDatos(){
        txtidEmpleado.setText("");
        txtNombreEmp.setText("");
        txtApellidoEmp.setText("");
        txtCorreoEmp.setText("");
        txtDireccionEmp.setText("");
        txtFechaNaci.setValue(null);
        txtTelEmp.setText("");
        txtidEmpleado.requestFocus();
        
     //   btnGuardar.setDisable(false);
    }

    //METODOS GUARDAR
     @FXML
    private void guardarEmpleados(ActionEvent event) {
    int tipoEmp= cmbTipoEmp.getSelectionModel().getSelectedIndex() + 1;
    int nacionalidad= cmbNacionalidad.getSelectionModel().getSelectedIndex() + 1;
    int tipoCorreo= cmbtipoCorreo.getSelectionModel().getSelectedIndex() + 1;
     //   LocalDate fechaNac= txtFechaNaci.getValue();
        int genero=0;
        
        if(rdbM.isSelected()==true){
            genero=1;
        }else if(rdbF.isSelected()==true){
            genero=2;
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione el género del empleado", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }
        
        if (txtidEmpleado.getText().isEmpty()){
            
             JOptionPane.showMessageDialog(null, "El campo 'Identidad' está vacío, por favor ingrese la identidad del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
                }else if (txtNombreEmp.getText().isEmpty() ){
               JOptionPane.showMessageDialog(null, "El campo 'Nombres' está vacío, por favor ingrese el nombre del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE); 
                }else if (txtApellidoEmp.getText().isEmpty() ){
               JOptionPane.showMessageDialog(null, "El campo 'Apellidos' está vacío, por favor ingrese los apellidos del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE); 
              //  }else if (txtCorreoEmp.getText().isEmpty() ){
               JOptionPane.showMessageDialog(null, "El campo 'Correo' está vacío, por favor ingrese el correo electrónico del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE); 
                }else if (txtDireccionEmp.getText().isEmpty() ){
               JOptionPane.showMessageDialog(null, "El campo 'Dirección' está vacío, por favor ingrese la dirección del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE); 
                }else if (txtFechaNaci.getValue()==null ){
               JOptionPane.showMessageDialog(null, "El campo 'Fecha nacimiento' está vacío, por favor ingrese la fecha de nacimiento del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE); 
               // }else if (txtTelEmp.getText().isEmpty() ){
              // JOptionPane.showMessageDialog(null, "El campo 'Teléfono' está vacío, por favor ingrese el teléfono del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE); 
                
        }else{
            

        try{
            if(existeEmpleado()){
            return;
            }
          /*  if(!validarLongitudTelefono(txtTelEmp, 8)){
            return;
            }*/
        
          /* if(!isEmailValid(txtCorreoEmp.getText())){
            return;
            }*/
           
           if (!validarLongitudMax(txtNombreEmp.getText(), 40)) {
            JOptionPane.showMessageDialog(null, "Los nombres del empleado ingresados son muy largos el máximo es de 40 caracteres, usted ingresó " + txtNombreEmp.getText().length() + " caracteres.", "Longitud de los nombres del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
            if (!validarLongitudMax(txtApellidoEmp.getText(), 40)) {
            JOptionPane.showMessageDialog(null, "Los apellidos del empleado ingresados son muy largos el máximo es de 40 caracteres, usted ingresó " + txtApellidoEmp.getText().length() + " caracteres.", "Longitud de los apellidos del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
           /* if (!validarLongitudMax(txtTelEmp.getText(), 8)) {
            JOptionPane.showMessageDialog(null, "El teléfono del empleado ingresado es muy largo el máximo es de 8 dígitos, usted ingresó " + txtTelEmp.getText().length() + " dígitos.", "Longitud del teléfono del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
            }*/
            if (!validarLongitudMax(txtidEmpleado.getText(), 13)) {
             JOptionPane.showMessageDialog(null, "La identidad del empleado ingresado es muy largo el máximo es de 13 dígitos, usted ingresó " + txtidEmpleado.getText().length() + " dígitos.", "Longitud del número de identidad del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
           
            if(!validarIdentidad(txtidEmpleado.getText())){
            return;
            }
            
            pps= cone.prepareStatement("INSERT INTO empleados(idEmpleado,nombres,apellidos,fechaNacimiento,idGenero,idNacionalidad, direccion, tipoEmpleado) VALUES (?,?,?,?,?,?,?,?)");
            pps.setString(1, txtidEmpleado.getText());
            pps.setString(2, txtNombreEmp.getText());
            pps.setString(3, txtApellidoEmp.getText());
            pps.setString(4, txtFechaNaci.getValue().toString()/*String.valueOf(fechaNac)*/);
            pps.setString(5, String.valueOf(genero));
            pps.setString(6, String.valueOf(nacionalidad));
            pps.setString(7, txtDireccionEmp.getText());
            pps.setString(8, String.valueOf(tipoEmp));
            pps.executeUpdate();
            
            //GUARDAR TELEFONOS
            for(int i=0;i<tablaTelefonos.getItems().size();i++){
                pps=cone.prepareStatement("INSERT INTO telefonos_empleados(idEmpleado,telefono) VALUES(?,?)");
                pps.setString(1, txtidEmpleado.getText());
                pps.setString(2, String.valueOf(tablaTelefonos.getItems().get(i).getNumero()));
                pps.executeUpdate();                  
            }
            
            //GUARDAR CORREOS
            for(int j=0;j<tablaCorreos.getItems().size();j++){
                int tipoco=0;
                if(tablaCorreos.getItems().get(j).getTipoCorreo().equals("Personal")){
                    tipoco=1;
                }else if(tablaCorreos.getItems().get(j).getTipoCorreo().equals("Empresa")){
                    tipoco=2;
                }else if(tipoco==0){
                  JOptionPane.showMessageDialog(null, "Seleccione el tipo de correo", "Error", JOptionPane.PLAIN_MESSAGE);  
                }
                
                pps=cone.prepareStatement("INSERT INTO correo_empleados(idEmpleado, correo, tipoCorreo) VALUES(?,?,?)");
                pps.setString(1, txtidEmpleado.getText());
                pps.setString(2, String.valueOf(tablaCorreos.getItems().get(j).getCorreo()));
                pps.setString(3, String.valueOf(tipoco));
                pps.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Se ha registrado los datos del Empleado", "Datos guardados", JOptionPane.PLAIN_MESSAGE);
            limpiarDatos();
        }catch (SQLException ex) {
            Logger.getLogger(VistaEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        
    }
    
    @FXML
    private void actualizarEmpleado(ActionEvent event) {
        int tipoEmp= cmbTipoEmp.getSelectionModel().getSelectedIndex() + 1;
        int nacionalidad= cmbNacionalidad.getSelectionModel().getSelectedIndex() + 1;
        int tipoCorreo= cmbtipoCorreo.getSelectionModel().getSelectedIndex() + 1;
        int genero=0;
        if(rdbM.isSelected()==true){
            genero=1;
        }else if(rdbF.isSelected()==true){
            genero=2;
        }else{
           JOptionPane.showMessageDialog(null, "Seleccione el género del empleado", "¡Error!", JOptionPane.ERROR_MESSAGE); 
        }
        try {
            PreparedStatement pps=cone.prepareStatement("UPDATE empleados SET nombres=?, apellidos=?, fechaNacimiento=?, idGenero=?, idNacionalidad=?, direccion=?, tipoEmpleado=? WHERE idEmpleado=?");
            pps.setString(1, txtNombreEmp.getText());
            pps.setString(2, txtApellidoEmp.getText());
            pps.setString(3, String.valueOf(txtFechaNaci.getValue()));
            pps.setString(4, String.valueOf(genero));
            pps.setString(5,  String.valueOf(nacionalidad));
            pps.setString(6, txtDireccionEmp.getText());
            pps.setString(7, String.valueOf(tipoEmp));
            pps.setString(8, txtidEmpleado.getText());
            
            JOptionPane.showMessageDialog(null, "Se ha actulizado los datos del Empleado", "Datos guardados", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(VistaEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void eliminarEmpleados(ActionEvent event) {
        try {
            int confirmar= JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar este Empleado?");
            if(JOptionPane.OK_OPTION==confirmar){
                
            pps= cone.prepareStatement("DELETE FROM empleados WHERE idEmpleado=?");
            pps.setString(1, txtidEmpleado.getText());
            int res= pps.executeUpdate();
            
                if(res>0){
                    JOptionPane.showMessageDialog(null, "El empleado ha sido eliminado", "Empleado eliminado", JOptionPane.PLAIN_MESSAGE);
                    limpiarDatos();
                    
                }else{
                   JOptionPane.showMessageDialog(null, "Error al eliminar el Empleado", "Aviso", JOptionPane.INFORMATION_MESSAGE); 
                }       
            }
   
        } catch (SQLException ex) {
            Logger.getLogger(VistaEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        limpiarDatos();
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


    @FXML
    private void agregarTelefono(ActionEvent event) {
        int numero= Integer.parseInt(this.txtTelEmp.getText());
        String idE= this.txtidEmpleado.getText();
        String nombreE= txtNombreEmp.getText() + " " + this.txtApellidoEmp.getText();
        
        Telefonos ic= new Telefonos(idE,nombreE,numero);
        
        if(!this.listaContacto.contains(ic)){
            this.listaContacto.add(ic);
            this.tablaTelefonos.setItems(listaContacto);
            txtTelEmp.setText("");
            txtTelEmp.requestFocus();
        }else{
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El número de télefono ya existe");
            alert.showAndWait();
            txtTelEmp.requestFocus();
        }
        
        System.out.println(tablaTelefonos.getItems().size());
        for(int i=0;i<tablaTelefonos.getItems().size();i++){
                
            System.out.println(String.valueOf(tablaTelefonos.getItems().get(i).getNumero()));
               
            }
    }

    @FXML
    private void agregarCorreo(ActionEvent event) {
        String idE= this.txtidEmpleado.getText();
        String nombreE= txtNombreEmp.getText() + " " + this.txtApellidoEmp.getText();
        String correo= txtCorreoEmp.getText();
        String tipoC= String.valueOf(cmbtipoCorreo.getValue());
        
        Correos c= new Correos(idE,nombreE,correo,tipoC);
        
        if(!this.listaCorreo.contains(c)){
            this.listaCorreo.add(c);
            this.tablaCorreos.setItems(listaCorreo);
            txtCorreoEmp.setText("");
            cmbtipoCorreo.setValue(null);
            txtCorreoEmp.requestFocus();
        }else{
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El correo ya existe");
            alert.showAndWait();
            txtCorreoEmp.requestFocus();
        }
    }

    @FXML
    private void buscarEmpleado(KeyEvent event) {
      FilteredList<Empleados> filteredData = new FilteredList<>(listaEmpleados, p -> true);
      tblEmpleados.setItems(filteredData); 
      
      txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
          filteredData.setPredicate(empleado -> {
              
              if(newValue==null || newValue.isEmpty()){
                  return true;
              }
              
              String lowerCaseFilter= newValue.toLowerCase();
              
              if(empleado.getIdEmp().toLowerCase().indexOf(lowerCaseFilter) != -1){
                  return true;
              }else if(empleado.getNombres().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                  return true;
              }else{
                  return false;
              }
              
          });
      });
        
      SortedList<Empleados> sortedData = new SortedList<>(filteredData);
      sortedData.comparatorProperty().bind(tblEmpleados.comparatorProperty());
      
      tblEmpleados.setItems(sortedData);
    }
  

}
