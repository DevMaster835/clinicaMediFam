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
import java.sql.SQLException;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import modelos.Correos;
import modelos.Pacientes;
import modelos.Proveedores;
import modelos.Telefonos;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaProveedoresController implements Initializable {

    conexion con= new conexion();
    Connection cone= con.openConnection();
    
    PreparedStatement pps;
    ObservableList<Proveedores> proveedores;
    ObservableList<Telefonos> listaContacto;
    ObservableList<Correos> listacorreo;
    
    private double xOffset = 0; 
    private double yOffset = 0;
    
    @FXML
    private Button Close;
    @FXML
    private Button Minimize;
    @FXML
    private Button Return;

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
    @FXML
    private TableView<Telefonos> tablaTelefonos;
    @FXML
    private TableColumn<?, ?> idTel;
    @FXML
    private Button btnAgregarTelefono;
    @FXML
    private TableView<Correos> tablaCorreos;
    @FXML
    private TableColumn<?, ?> idCorreo;
    @FXML
    private TableColumn<?, ?> colCorreo;
    @FXML
    private Button btnAgregarCorreo;
    @FXML
    private TextField txtBuscar;
    @FXML
    private TableView<Proveedores> tblProveedores;
    @FXML
    private TableColumn<?, ?> colCodigo;
    @FXML
    private TableColumn<?, ?> colRTN;
    @FXML
    private TableColumn<?, ?> colProv;
    @FXML
    private TableColumn<?, ?> colContacto;
    @FXML
    private TableColumn<?, ?> colDireccion;
    @FXML
    private TableColumn<?, ?> colProvTel;
    @FXML
    private TableColumn<?, ?> colTelProv;
    @FXML
    private TableColumn<?, ?> colProveedor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        tablaProveedores();
        tablasContacto();
        seleccionar();
        
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
    
    public void tablaProveedores(){
        //TABLA PROVEEDORES
        proveedores=FXCollections.observableArrayList();
        Proveedores.llenarTabla(cone, proveedores);
        tblProveedores.setItems(proveedores);
        
        colCodigo.setCellValueFactory(new PropertyValueFactory("id"));
        colRTN.setCellValueFactory(new PropertyValueFactory("rtn"));
        colProveedor.setCellValueFactory(new PropertyValueFactory("proveedor"));
        colContacto.setCellValueFactory(new PropertyValueFactory("contacto"));
        colDireccion.setCellValueFactory(new PropertyValueFactory("direccion"));
    }
    
    public void tablasContacto(){
        //TABLA DE TELEFONOS
         listaContacto= FXCollections.observableArrayList();
         idTel.setCellValueFactory(new PropertyValueFactory("id"));
         colProvTel.setCellValueFactory(new PropertyValueFactory("nombre"));
         colTelProv.setCellValueFactory(new PropertyValueFactory("numero"));
         
         //TABLA DE CORREOS
         listacorreo= FXCollections.observableArrayList();
         idCorreo.setCellValueFactory(new PropertyValueFactory("id"));
         colProv.setCellValueFactory(new PropertyValueFactory("nombre"));
         colCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
    }
    
    public void seleccionar(){
        this.tblProveedores.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Proveedores>(){
            @Override
            public void changed(ObservableValue<? extends Proveedores> arg0, 
                    Proveedores valorAnterior, Proveedores valorSeleccionado) {
                txtidProv.setText(valorSeleccionado.getId());
                txtRTN.setText(valorSeleccionado.getRtn());
                txtnombreProv.setText(valorSeleccionado.getProveedor());
                txtcontacto.setText(valorSeleccionado.getContacto());
                txtdireccionProv.setText(valorSeleccionado.getDireccion());               
            }
      
            }
        
        );
    }
    
    public void inicializarDatos(){
        txtidProv.setText("");
        txtRTN.setText("");
        txtnombreProv.setText("");
        txtcontacto.setText("");
        txtdireccionProv.setText("");
    }

    //

    @FXML
    private void agregarProveedores(ActionEvent event) {
        
        if (txtidProv.getText() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de Identidad del Proveedor esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtRTN.getText() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de RTN esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtnombreProv.getText() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de Nombre esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtcontacto.getText() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de Contacto esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtdireccionProv.getText() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de Direccion esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        //}else if (txtTelefono.getText() == null ) {
        //    JOptionPane.showMessageDialog(null, "El campo de Telefono neto esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else{
        try{
           /* if(!isEmailValid(txtcorreoProv.getText())){
            return;
            }*/
            
           /* if(!validarLongitudTelefono(txtTelefono, 8)){
            return;
            }*/
            
            if (!validarLongitudMax(txtnombreProv.getText(), 40)) {
            JOptionPane.showMessageDialog(null, "El nombre del proveedor ingresados es muy largo el máximo es de 40 caracteres, usted ingresó " + txtnombreProv.getText().length() + " caracteres.", "Longitud de los nombres del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
            if (!validarLongitudMax(txtcontacto.getText(), 40)) {
            JOptionPane.showMessageDialog(null, "El nombre del contacto ingresado es muy largo el máximo es de 40 caracteres, usted ingresó " + txtcontacto.getText().length() + " caracteres.", "Longitud de los apellidos del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
           /* if (!validarLongitudMax(txtTelefono.getText(), 8)) {
            JOptionPane.showMessageDialog(null, "El teléfono del proveedor ingresado es muy largo el máximo es de 8 dígitos, usted ingresó " + txtTelefono.getText().length() + " dígitos.", "Longitud del teléfono del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
            }*/
            if (!validarLongitudMax(txtidProv.getText(), 13)) {
             JOptionPane.showMessageDialog(null, "La identidad del proveedor ingresado es muy largo el máximo es de 13 dígitos, usted ingresó " + txtidProv.getText().length() + " dígitos.", "Longitud del número de identidad del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
            
            pps=cone.prepareStatement("INSERT INTO proveedores(idProveedor,RTN,nombreProveedor,nombreContacto,direccion) VALUES(?,?,?,?,?)");
            pps.setString(1, txtidProv.getText());
            pps.setString(2, txtRTN.getText());
            pps.setString(3, txtnombreProv.getText());
            pps.setString(4, txtcontacto.getText());
            pps.setString(5, txtdireccionProv.getText());
            pps.executeUpdate();
            
            //GUARDAR TELEFONOS
            for(int i=0;i<tablaTelefonos.getItems().size();i++){
                pps=cone.prepareStatement("INSERT INTO telefonos_proveedores(idProveedor,telefono) VALUES(?,?)");
                pps.setString(1, txtidProv.getText());
                pps.setString(2, String.valueOf(tablaTelefonos.getItems().get(i).getNumero()));
                pps.executeUpdate();
            }
            
            //GUARDAR CORREOS
             for(int j=0;j<tablaCorreos.getItems().size();j++){
                pps=cone.prepareStatement("INSERT INTO correo_proveedores(idProveedor,correo) VALUES(?,?)");
                pps.setString(1, txtidProv.getText());
                pps.setString(2, String.valueOf(tablaCorreos.getItems().get(j).getCorreo()));
                pps.executeUpdate();
             }
            tablaProveedores();
            
            JOptionPane.showMessageDialog(null, "Se ha registrado los datos del proveedor", "Datos guardados", JOptionPane.PLAIN_MESSAGE);  
            inicializarDatos();
        }catch(SQLException ex){
            Logger.getLogger(VistaPacientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        inicializarDatos();
    }

    @FXML
    private void eliminarProveedores(ActionEvent event) {
         try {
            int confirmar= JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar este Proveedor");
            if(JOptionPane.OK_OPTION==confirmar){
                
            pps= cone.prepareStatement("DELETE FROM proveedores WHERE idProveedor=?");
            pps.setString(1, txtidProv.getText());
            int res= pps.executeUpdate();
            
                if(res>0){
                    JOptionPane.showMessageDialog(null, "El Proveedor ha sido eliminado", "Provedor eliminado", JOptionPane.PLAIN_MESSAGE);
                    inicializarDatos();
                }else{
                   JOptionPane.showMessageDialog(null, "Error al eliminar Provedor ", "Aviso", JOptionPane.INFORMATION_MESSAGE); 
                }
            }
 
        } catch (SQLException ex) {
            Logger.getLogger(VistaProveedoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    @FXML
    private void txtcodigoKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtrtnKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtnombreKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isAlphabetic(car) && !Character.isSpaceChar(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten letras");
        }
    }

    @FXML
    private void txtcontactoKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isAlphabetic(car) && !Character.isSpaceChar(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten letras");
        }
    }

    @FXML
    private void txttelefonoKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void agregarTelefono(ActionEvent event) {
        String numero= txtTelefono.getText();
        String idE= this.txtidProv.getText();
        String nombreE= txtnombreProv.getText();
        
        Telefonos ic= new Telefonos(idE,nombreE,numero);
        
        if(!this.listaContacto.contains(ic)){
            this.listaContacto.add(ic);
            this.tablaTelefonos.setItems(listaContacto);
            txtTelefono.setText("");
            txtTelefono.requestFocus();
        }else{
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El número de télefono ya existe");
            alert.showAndWait();
            txtTelefono.requestFocus();
        }
    }
        

    @FXML
    private void agregarCorreo(ActionEvent event) {
        String idE= this.txtidProv.getText();
        String nombreE= txtnombreProv.getText();
        String correo= txtcorreoProv.getText();
        
        Correos c= new Correos(idE,nombreE,correo);
        
        if(!this.listacorreo.contains(c)){
            this.listacorreo.add(c);
            this.tablaCorreos.setItems(listacorreo);
            txtcorreoProv.setText("");
            txtcorreoProv.requestFocus();
        }else{
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El correo ya existe");
            alert.showAndWait();
            txtcorreoProv.requestFocus();
        }
    }

    @FXML
    private void buscarProveedor(KeyEvent event) {
        FilteredList<Proveedores> filteredData = new FilteredList<>(proveedores, p -> true);
      tblProveedores.setItems(filteredData); 
      
      txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
          filteredData.setPredicate(proveedor -> {
              
              if(newValue==null || newValue.isEmpty()){
                  return true;
              }
              
              String lowerCaseFilter= newValue.toLowerCase();
              
              if(proveedor.getId().toLowerCase().indexOf(lowerCaseFilter) != -1){
                  return true;
              }else if(proveedor.getProveedor().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                  return true;
              }else{
                  return false;
              }

          });
      });
        
      SortedList<Proveedores> sortedData = new SortedList<>(filteredData);
      sortedData.comparatorProperty().bind(tblProveedores.comparatorProperty());
      
      tblProveedores.setItems(sortedData);
        
    }
    
}
