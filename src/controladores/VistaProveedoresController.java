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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import modelos.Correos;
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
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnModificarTel;
    @FXML
    private Button btnModificarCorreo;

    /**
     * Initializes the controller class.
     */
    
    ObservableList<Correos> listaCorreo;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        tablaProveedores();
        tablasContacto();
        seleccionar();
        seleccionarTelefono();
        seleccionarCorreo();
        
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
                if(valorSeleccionado!=null){
                txtidProv.setText(valorSeleccionado.getId());
                txtRTN.setText(valorSeleccionado.getRtn());
                txtnombreProv.setText(valorSeleccionado.getProveedor());
                txtcontacto.setText(valorSeleccionado.getContacto());
                txtdireccionProv.setText(valorSeleccionado.getDireccion());               
           try {
            tablaTelefonos.getItems().clear();
            pps=cone.prepareStatement("SELECT prov.idProveedor, prov.nombreContacto, tel.idTelefono, tel.telefono FROM proveedores prov INNER JOIN telefonos_proveedores tel ON prov.idProveedor=tel.idProveedor and prov.idProveedor=?");
            pps.setString(1, txtidProv.getText());
            
           ResultSet rs = pps.executeQuery();

           while(rs.next()){
               String num= rs.getString("tel.telefono");
               System.out.println(rs.getString("tel.idTelefono"));
               Telefonos ic = new Telefonos(txtidProv.getText(), txtnombreProv.getText(), num);

                if(!listaContacto.contains(ic)) {
                        listaContacto.add(ic);
                        tablaTelefonos.setItems(listaContacto);
                }
           }
           
           tablaCorreos.getItems().clear();
           PreparedStatement ps=cone.prepareStatement("SELECT prov.idProveedor, prov.nombreContacto, co.idCorreo, co.correo FROM proveedores prov INNER JOIN correo_proveedores co ON prov.idProveedor=co.idProveedor and prov.idProveedor=?");
           ps.setString(1, txtidProv.getText());
            
           ResultSet rrs = ps.executeQuery();

           while(rrs.next()){
               String email= rrs.getString("co.correo"); 
               Correos co = new Correos(txtidProv.getText(), txtnombreProv.getText(), email);

                if(!listacorreo.contains(co)) {
                        listacorreo.add(co);
                        tablaCorreos.setItems(listacorreo);
                }
           }
           
        }catch (SQLException ex) {
            Logger.getLogger(VistaEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
                btnGuardar.setDisable(true);
                btnActualizar.setDisable(false);
                btnEliminar.setDisable(false);
                btnAgregarTelefono.setDisable(true);
                btnModificarTel.setDisable(false);
                btnAgregarCorreo.setDisable(true);
                btnModificarCorreo.setDisable(false);
            }
            }
            } 
        );
    }
    
    public void seleccionarTelefono(){
        tablaTelefonos.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Telefonos>() {
             @Override
            public void changed(ObservableValue<? extends Telefonos> arg0,
                    Telefonos valorAnterior, Telefonos valorSeleccionado) {
                
                if(valorSeleccionado!=null){
                  txtTelefono.setText(valorSeleccionado.getNumero());
                }  
            }
            }
        );
    }
    
    public void seleccionarCorreo(){
        tablaCorreos.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Correos>() {
             @Override
            public void changed(ObservableValue<? extends Correos> arg0,
                    Correos valorAnterior, Correos valorSeleccionado) {
                
                if(valorSeleccionado!=null){
                  txtcorreoProv.setText(valorSeleccionado.getCorreo());
                }  
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
    
     public static boolean isValidAdd(String address) {
        final Pattern ADD_REGEX = Pattern.compile("[a-zA-Z\\,\\t\\h]+|(^$)", Pattern.CASE_INSENSITIVE);
        if(ADD_REGEX.matcher(address).matches()) {
            return true;
        }else{
         JOptionPane.showMessageDialog(null, "La direccion no es valida");
         return false;        
        }
      }
     
     public boolean validarRTN(String identidad) {
        String id = identidad.substring(0, 1);
        if (identidad.length() <= 13) {
            JOptionPane.showMessageDialog(null, "El RTN debe de tener al menos 14 dígitos, ha ingresado solamente " + identidad.length() + " dígitos.", "Número de identidad invalido", JOptionPane.ERROR_MESSAGE);
        }
        if (identidad.length() >= 14) {
            if ("0".equals(id)) {
                return true;
            } else if ("1".equals(id)) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "El número de identidad sólo puede comenzar con 0 o 1 ", "Error en campo identidad", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            return false;
        }
    }

    //

    @FXML
    private void agregarProveedores(ActionEvent event) {
        
        if (txtidProv.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de Codigo del Proveedor esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitud(txtidProv, 3)) {
            JOptionPane.showMessageDialog(null, "El codigo ingresado es muy pequeños el mínimo es de 5 caracteres", "Longitud de codigo", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitudMax(txtidProv.getText(), 10)) {
            JOptionPane.showMessageDialog(null, "El codigo de proveedor ingresado es muy largo el máximo es de 10 caracteres, usted ingresó " + txtidProv.getText().length() + " caracteres.", "Longitud de codigo de proveedor", JOptionPane.ERROR_MESSAGE);
        } else if (txtRTN.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de RTN esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (!validarRTN(txtRTN.getText())) {
        } else if (!validarLongitudMax(txtRTN.getText(), 16)) {
            JOptionPane.showMessageDialog(null, "El RTN del proveedor ingresado es muy largo el máximo es de 16 caracteres, usted ingresó " + txtRTN.getText().length() + " caracteres.", "Longitud de RTN del proveedor", JOptionPane.ERROR_MESSAGE);
        } else if (txtnombreProv.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de Nombre del proveedor esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitud(txtnombreProv, 3)) {
            JOptionPane.showMessageDialog(null, "El nombre ingresado es muy pequeño el mínimo es de 3 caracteres", "Longitud de nombre de proveedor", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitudMax(txtnombreProv.getText(), 25)) {
            JOptionPane.showMessageDialog(null, "El nombre ingresado es muy largo el máximo es de 25 caracteres, usted ingresó " + txtnombreProv.getText().length() + " caracteres.", "Longitud de los nombres del proveedor", JOptionPane.ERROR_MESSAGE);
        }  else if (txtcontacto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de Contacto esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (!validarLongitud(txtcontacto, 3)) {
            JOptionPane.showMessageDialog(null, "El contacto ingresado es muy pequeño el mínimo es de 3 caracteres", "Longitud de nombre de contacto de proveedor", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitudMax(txtcontacto.getText(), 25)) {
            JOptionPane.showMessageDialog(null, "El contacto ingresado es muy largo el máximo es de 25 caracteres, usted ingresó " + txtcontacto.getText().length() + " caracteres.", "Longitud de los nombres del contacto del proveedor", JOptionPane.ERROR_MESSAGE);
        } else if (txtdireccionProv.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de Direccion esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (!isValidAdd(txtcorreoProv.getText())) {
        } else {
        try{
           
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
        
        if(!Character.isDigit(car) && car > '\b'){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtrtnKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car > '\b'){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtnombreKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isAlphabetic(car) && !Character.isSpaceChar(car) && car > '\b'){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten letras");
        }
    }

    @FXML
    private void txtcontactoKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isAlphabetic(car) && !Character.isSpaceChar(car) && car > '\b'){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten letras");
        }
    }

    @FXML
    private void txttelefonoKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car > '\b'){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }
    
    @FXML
    private void txtcorreoKeyTyped(KeyEvent event) {
        char car = event.getCharacter().charAt(0);
        if ((Character.isSpaceChar(car) && car > '\b')) {
            event.consume();
            JOptionPane.showMessageDialog(null, "No se permiten espacios");
        }
    }

    @FXML
    private void agregarTelefono(ActionEvent event) {
        String numero= txtTelefono.getText();
        String idE= this.txtidProv.getText();
        String nombreE= txtnombreProv.getText();
        
        if (txtidProv.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("La identidad del empleado no puede ir vacío");
            alert.showAndWait();
            txtidProv.requestFocus();
        } else if (txtnombreProv.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El nombre del empleado no puede ir vacío");
            alert.showAndWait();
            txtnombreProv.requestFocus();
        } else if (txtTelefono.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El teléfono del empleado no puede ir vacío");
            alert.showAndWait();
            txtTelefono.requestFocus();
        }
        if (!validarLongitudTelefono(txtTelefono, 8)) {
            return;
        }
        if (!validarLongitudMax(txtTelefono.getText(), 8)) {
            JOptionPane.showMessageDialog(null, "El teléfono del empleado ingresado es muy largo el máximo es de 8 dígitos, usted ingresó " + txtTelefono.getText().length() + " dígitos.", "Longitud del teléfono del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (existeTelefono()) {
            return;
        }
        
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
        
        if (txtidProv.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El codigo del proveedor no puede ir vacío");
            alert.showAndWait();
            txtidProv.requestFocus();
        } else if (txtnombreProv.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El nombre del proveedor no puede ir vacío");
            alert.showAndWait();
            txtnombreProv.requestFocus();
        } else if (txtcorreoProv.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El correo del proveedor no puede ir vacío");
            alert.showAndWait();
            txtcorreoProv.requestFocus();
        } else if (!isEmailValid(txtcorreoProv.getText())) {
        }else {
            
            if (existeCorreo()) {
                    return;
            }
        
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
    }

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
    
    public boolean existeTelefono() {
        try {
            Statement st = cone.createStatement();
            String sql = "Select telefono from telefonos_proveedores where telefono = '" + txtTelefono.getText() + "'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, " Ya existe " + " el número de teléfono: " + txtTelefono.getText(), "Número de teléfono ¡Ya existe!", JOptionPane.ERROR_MESSAGE);
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaProveedoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean existeCorreo() {
        try {
            Statement st = cone.createStatement();
            String sql = "Select correo from correo_proveedores where correo = '" + txtcorreoProv.getText() + "'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, " Ya existe " + " la dirección de correo: " + txtcorreoProv.getText(), "Dirección de correo ¡Ya existe!", JOptionPane.ERROR_MESSAGE);
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaProveedoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    public boolean existeProveedor(){
        try {
            Statement st = cone.createStatement();
            String sql = "Select nombres from proveedores where idProveedor = '"+txtidProv.getText()+"'";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                JOptionPane.showMessageDialog(null, " Ya existe"+" el codigo de producto: "+txtidProv.getText(), "Codigo de proveedor ¡Ya existe!", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaProveedoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @FXML
    private void actualizarProveedor(ActionEvent event) {
        if (txtidProv.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de Codigo del Proveedor esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitud(txtidProv, 3)) {
            JOptionPane.showMessageDialog(null, "El codigo ingresado es muy pequeños el mínimo es de 5 caracteres", "Longitud de codigo", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitudMax(txtidProv.getText(), 10)) {
            JOptionPane.showMessageDialog(null, "El codigo de proveedor ingresado es muy largo el máximo es de 10 caracteres, usted ingresó " + txtidProv.getText().length() + " caracteres.", "Longitud de codigo de proveedor", JOptionPane.ERROR_MESSAGE);
        } else if (txtRTN.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de RTN esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (!validarRTN(txtRTN.getText())) {
        } else if (!validarLongitudMax(txtRTN.getText(), 16)) {
            JOptionPane.showMessageDialog(null, "El RTN del proveedor ingresado es muy largo el máximo es de 16 caracteres, usted ingresó " + txtRTN.getText().length() + " caracteres.", "Longitud de RTN del proveedor", JOptionPane.ERROR_MESSAGE);
        } else if (txtnombreProv.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de Nombre del proveedor esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitud(txtnombreProv, 3)) {
            JOptionPane.showMessageDialog(null, "El nombre ingresado es muy pequeño el mínimo es de 3 caracteres", "Longitud de nombre de proveedor", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitudMax(txtnombreProv.getText(), 25)) {
            JOptionPane.showMessageDialog(null, "El nombre ingresado es muy largo el máximo es de 25 caracteres, usted ingresó " + txtnombreProv.getText().length() + " caracteres.", "Longitud de los nombres del proveedor", JOptionPane.ERROR_MESSAGE);
        }  else if (txtcontacto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de Contacto esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (!validarLongitud(txtcontacto, 3)) {
            JOptionPane.showMessageDialog(null, "El contacto ingresado es muy pequeño el mínimo es de 3 caracteres", "Longitud de nombre de contacto de proveedor", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitudMax(txtcontacto.getText(), 25)) {
            JOptionPane.showMessageDialog(null, "El contacto ingresado es muy largo el máximo es de 25 caracteres, usted ingresó " + txtcontacto.getText().length() + " caracteres.", "Longitud de los nombres del contacto del proveedor", JOptionPane.ERROR_MESSAGE);
        } else if (txtdireccionProv.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de Direccion esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (!isValidAdd(txtdireccionProv.getText())) {
        }
        
        else{
            /*if(existeProveedor()){
            return;
            } */
        
        try {
            
            PreparedStatement ps = cone.prepareStatement("UPDATE proveedores SET RTN=?, nombreProveedor=?, nombreContacto=?, direccion=? WHERE idProveedor=?");
            ps.setString(1, txtRTN.getText());
            ps.setString(2, txtnombreProv.getText());
            ps.setString(3, txtcontacto.getText());
            ps.setString(4, txtdireccionProv.getText());
            ps.setString(5, txtidProv.getText());
            
           
            //MODIFICAR TELEFONOS
            for (int i = 0; i < tablaTelefonos.getItems().size(); i++) {
                  PreparedStatement  ps1 = cone.prepareStatement("UPDATE telefonos_proveedores SET telefono=? WHERE idProveedor=?");                  
                    ps1.setString(1, String.valueOf(tablaTelefonos.getItems().get(i).getNumero()));
                    ps1.setString(2, txtidProv.getText());
                    ps1.executeUpdate();
            }
            int mod = ps.executeUpdate();

            if (mod >= 1) {
                tablaProveedores();
                JOptionPane.showMessageDialog(null, "Se han actualizado los datos", "¡Confirmación!", JOptionPane.INFORMATION_MESSAGE);               
                inicializarDatos();
            }

        } catch (SQLException ex) {
            Logger.getLogger(VistaProveedoresController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    }

    @FXML
    private void modificarTelefono(ActionEvent event) {
        String numero= txtTelefono.getText();
        String idE= this.txtidProv.getText();
        String nombreE= txtnombreProv.getText();
        
        if (txtidProv.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("La identidad del empleado no puede ir vacío");
            alert.showAndWait();
            txtidProv.requestFocus();
        } else if (txtnombreProv.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El nombre del empleado no puede ir vacío");
            alert.showAndWait();
            txtnombreProv.requestFocus();
        } else if (txtTelefono.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El teléfono del empleado no puede ir vacío");
            alert.showAndWait();
            txtTelefono.requestFocus();
        }
        if (!validarLongitudTelefono(txtTelefono, 8)) {
            return;
        }
        if (!validarLongitudMax(txtTelefono.getText(), 8)) {
            JOptionPane.showMessageDialog(null, "El teléfono del empleado ingresado es muy largo el máximo es de 8 dígitos, usted ingresó " + txtTelefono.getText().length() + " dígitos.", "Longitud del teléfono del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (existeTelefono()) {
            return;
        }
        
        Telefonos ic= new Telefonos(idE,nombreE,numero);
        listaContacto.set(tablaTelefonos.getSelectionModel().getSelectedIndex(), ic);
    }
    
    @FXML
    private void buscarProveedores(KeyEvent event) {
        FilteredList<Proveedores> filteredData = new FilteredList<>(proveedores, p -> true);
        tblProveedores.setItems(filteredData);

        txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(proveedor -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if ( proveedor.getId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (proveedor.getProveedor().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Proveedores> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblProveedores.comparatorProperty());

        tblProveedores.setItems(sortedData);
        
    }

    @FXML
    private void modificarCorreo(ActionEvent event) {
        String idE = txtidProv.getText();
        String nombreE = txtnombreProv.getText();
        String correo = txtcorreoProv.getText();

        if (txtidProv.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("La identidad del paciente no puede ir vacío");
            alert.showAndWait();
            txtcorreoProv.requestFocus();
        } else if (txtnombreProv.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El nombre del empleado no puede ir vacío");
            alert.showAndWait();
            txtnombreProv.requestFocus();
        } else if (txtcorreoProv.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El correo del empleado no puede ir vacío");
            alert.showAndWait();
            txtnombreProv.requestFocus();
        } else if (!isEmailValid(txtcorreoProv.getText())) {
        
        } else {
            Correos c= new Correos(idE,nombreE,correo);
            listacorreo.set(tablaCorreos.getSelectionModel().getSelectedIndex(), c);
        }
    }

    
    
}
