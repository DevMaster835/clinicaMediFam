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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;
import modelos.Productos;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaProductosController implements Initializable {
    
   conexion con= new conexion();
    Connection cone= con.openConnection();
    ObservableList<Productos> productos;
    
    PreparedStatement pps;
    
    private double xOffset = 0; 
    private double yOffset = 0;
    
    @FXML
    private Button Close;
    @FXML
    private Button Minimize;
    @FXML
    private Button Return;

    @FXML
    private DatePicker txtfechaVen;
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
    @FXML
    private TableView<Productos> tblProductos;
    @FXML
    private TableColumn<?, ?> colCodigo;
    @FXML
    private TableColumn<?, ?> colProd;
    @FXML
    private TableColumn<?, ?> colPrecio;
    @FXML
    private TableColumn<?, ?> colFechaV;
    @FXML
    private TableColumn<?, ?> colStock;
    @FXML
    private TextField txtBuscar;
    @FXML
    private TableColumn<?, ?> colContenido;
    @FXML
    private Button btnActualizar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        formatoFecha();
        tablaProductos();
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
    
    public void inicializarDatos(){
        txtcodigoProd.setText("");
        txtnombreProd.setText("");
        txtconNeto.setText("");
        txtfechaVen.setValue(null);
        txtExistencia.setText("");
        txtPrecio.setText("");
        txtcodigoProd.requestFocus();
    }
    
    public void tablaProductos(){
        //TABLA PRODUCTOS
        productos=FXCollections.observableArrayList();
        Productos.llenarTabla(cone, productos);
        tblProductos.setItems(productos);
        
        colCodigo.setCellValueFactory(new PropertyValueFactory("id"));
        colProd.setCellValueFactory(new PropertyValueFactory("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        colFechaV.setCellValueFactory(new PropertyValueFactory("fechaVen"));
        colStock.setCellValueFactory(new PropertyValueFactory("stock"));
        colContenido.setCellValueFactory(new PropertyValueFactory("contNeto"));
    }
    
    public void formatoFecha(){
        txtfechaVen.setConverter(new StringConverter<LocalDate>() {
        String pattern = "dd-MM-yyyy";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);      
        {
                txtfechaVen.setPromptText(pattern.toLowerCase());
         
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
    
    public void seleccionar(){
        this.tblProductos.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Productos>(){
            @Override
            public void changed(ObservableValue<? extends Productos> arg0, 
                    Productos valorAnterior, Productos valorSeleccionado) {
                txtcodigoProd.setText(String.valueOf(valorSeleccionado.getId()));
                txtnombreProd.setText(valorSeleccionado.getNombre());
                txtconNeto.setText(String.valueOf(valorSeleccionado.getContNeto()));
                txtfechaVen.setValue(valorSeleccionado.getFechaVen().toLocalDate());
                txtExistencia.setText(String.valueOf(valorSeleccionado.getStock()));
                txtPrecio.setText(String.valueOf(valorSeleccionado.getPrecio()));
            }
      
            }
        
        );
    }
    
    public boolean existeProducto(){
        try {
            Statement st = cone.createStatement();
            String sql = "Select nombre from productos where idProducto = '"+txtcodigoProd.getText()+"'";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                JOptionPane.showMessageDialog(null, " Ya existe"+" el codigo de producto: "+txtcodigoProd.getText(), "Codigo de producto ¡Ya existe!", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
        @FXML
    private void agregarProductos(ActionEvent event) {
        
        if (txtcodigoProd.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de Codigo del Producto esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (!validarLongitud(txtcodigoProd, 3)) {
            JOptionPane.showMessageDialog(null, "El codigo ingresado es muy pequeños el mínimo es de 5 caracteres", "Longitud de codigo", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitudMax(txtcodigoProd.getText(), 10)) {
            JOptionPane.showMessageDialog(null, "El codigo ingresado es muy largo el máximo es de 10 caracteres, usted ingresó " + txtcodigoProd.getText().length() + " caracteres.", "Longitud de codigo de producto", JOptionPane.ERROR_MESSAGE);
        }else if (txtnombreProd.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "El campo de Nombre esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (!validarLongitud(txtnombreProd, 3)) {
            JOptionPane.showMessageDialog(null, "El nombre ingresado es muy pequeños el mínimo es de 3 caracteres", "Longitud de nombre", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitudMax(txtnombreProd.getText(), 25)) {
            JOptionPane.showMessageDialog(null, "El nombre ingresado es muy largo el máximo es de 25 caracteres, usted ingresó " + txtnombreProd.getText().length() + " caracteres.", "Longitud de nombre de producto", JOptionPane.ERROR_MESSAGE);
        }else if (txtconNeto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de Contenido neto esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (!validarLongitud(txtconNeto, 1)) {
            JOptionPane.showMessageDialog(null, "El contenido neto ingresado es muy bajo el mínimo es de 1", "Contenido neto ingresado", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitudMax(txtconNeto.getText(), 3)) {
            JOptionPane.showMessageDialog(null, "El contenido neto ingresado es muy alto el máximo es de 999, usted ingresó " + txtconNeto.getText() + " caracteres.", "Contenido neto ingresado", JOptionPane.ERROR_MESSAGE);
        }else if (txtfechaVen.getValue() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de fecha esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtExistencia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de Existencia esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (!validarLongitud(txtconNeto, 1)) {
            JOptionPane.showMessageDialog(null, "El stock del producto ingresado es muy bajo el mínimo es de 1", "Stock ingresado", JOptionPane.ERROR_MESSAGE);
        }else if (txtPrecio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de Precio esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (!isPriceValid(txtPrecio.getText())) {
        }else{   
        
        try{
            if(existeProducto()){
            return;
            }
            
            pps=cone.prepareStatement("INSERT INTO productos(idProducto,nombre,idPrecioHis,fechaVencimiento,stock,contenidoNeto) VALUES(?,?,?,?,?,?)");
            pps.setString(1, txtcodigoProd.getText());
            pps.setString(2, txtnombreProd.getText());
            pps.setString(3, txtPrecio.getText());
            pps.setString(4, String.valueOf(txtfechaVen.getValue()));
            pps.setString(5, txtExistencia.getText());
            pps.setString(6, txtconNeto.getText());
           // pps.setString(7, txtFabricante.getText());
            pps.executeUpdate();
            
            tablaProductos();
            JOptionPane.showMessageDialog(null, "Se ha registrado los datos del producto", "Datos guardados", JOptionPane.PLAIN_MESSAGE);
            inicializarDatos();
        }catch(SQLException ex){
            Logger.getLogger(VistaProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }  
    }

    @FXML
    private void eliminarProductos(ActionEvent event) {
        try {
            int confirmar= JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar este Producto?");
            if(JOptionPane.OK_OPTION==confirmar){
                
            pps= cone.prepareStatement("DELETE FROM productos WHERE idProducto=?");
            pps.setString(1, txtcodigoProd.getText());
            int res= pps.executeUpdate();
            
                if(res>0){
                    JOptionPane.showMessageDialog(null, "El Producto ha sido eliminado", "Producto eliminado", JOptionPane.PLAIN_MESSAGE);
                    inicializarDatos();
                }else{
                   JOptionPane.showMessageDialog(null, "Error al eliminar Producto ", "Aviso", JOptionPane.INFORMATION_MESSAGE); 
                }
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VistaProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cancelarProductos(ActionEvent event) {
        inicializarDatos();
    }
    
   @FXML
    private void actualizarProductos(ActionEvent event){
        if (txtcodigoProd.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de Codigo del Producto esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (!validarLongitud(txtcodigoProd, 3)) {
            JOptionPane.showMessageDialog(null, "El codigo ingresado es muy pequeños el mínimo es de 5 caracteres", "Longitud de codigo", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitudMax(txtcodigoProd.getText(), 10)) {
            JOptionPane.showMessageDialog(null, "El codigo ingresado es muy largo el máximo es de 10 caracteres, usted ingresó " + txtcodigoProd.getText().length() + " caracteres.", "Longitud de codigo de producto", JOptionPane.ERROR_MESSAGE);
        }else if (txtnombreProd.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "El campo de Nombre esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (!validarLongitud(txtnombreProd, 3)) {
            JOptionPane.showMessageDialog(null, "El nombre ingresado es muy pequeños el mínimo es de 3 caracteres", "Longitud de nombre", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitudMax(txtnombreProd.getText(), 25)) {
            JOptionPane.showMessageDialog(null, "El nombre ingresado es muy largo el máximo es de 25 caracteres, usted ingresó " + txtnombreProd.getText().length() + " caracteres.", "Longitud de nombre de producto", JOptionPane.ERROR_MESSAGE);
        }else if (txtconNeto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de Contenido neto esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (!validarLongitud(txtconNeto, 1)) {
            JOptionPane.showMessageDialog(null, "El contenido neto ingresado es muy bajo el mínimo es de 1", "Contenido neto ingresado", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitudMax(txtconNeto.getText(), 3)) {
            JOptionPane.showMessageDialog(null, "El contenido neto ingresado es muy alto el máximo es de 999, usted ingresó " + txtconNeto.getText() + " caracteres.", "Contenido neto ingresado", JOptionPane.ERROR_MESSAGE);
        }else if (txtfechaVen.getValue() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de fecha esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtExistencia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de Existencia esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (!validarLongitud(txtconNeto, 1)) {
            JOptionPane.showMessageDialog(null, "El stock del producto ingresado es muy bajo el mínimo es de 1", "Stock ingresado", JOptionPane.ERROR_MESSAGE);
        }else if (txtPrecio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de Precio esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (!isPriceValid(txtPrecio.getText())) {
        }else{   
            if(existeProducto()){
            return;
            }
        
        try{
            pps=cone.prepareStatement("UPDATE productos SET nombre=?, idPrecioHis=?, fechaVencimiento=?, stock=?, contenidoNeto=? WHERE idProducto=?");
          
            pps.setString(1, txtnombreProd.getText());
            pps.setString(2, txtPrecio.getText());
            pps.setString(3, String.valueOf(txtfechaVen.getValue()));
            pps.setString(4, txtExistencia.getText());
            pps.setString(5, txtconNeto.getText());
            pps.setString(6, txtcodigoProd.getText());
            pps.executeUpdate();
            
            tablaProductos();
            JOptionPane.showMessageDialog(null, "Se han actualizado los datos del producto", "Datos guardados", JOptionPane.PLAIN_MESSAGE);
            inicializarDatos();
        }catch(SQLException ex){
            Logger.getLogger(VistaProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }  
    }

    //EVENTO KEY TYPED
    @FXML
    private void txtCodigoKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car > '\b'){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
        
    }

    @FXML
    private void txtNombreKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isAlphabetic(car) && !Character.isSpaceChar(car) && car > '\b'){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten letras");
        }
    }

    @FXML
    private void txtPrecioKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car > '\b'){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtExistenciaKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car > '\b'){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtConetidoKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car > '\b'){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    private void txtFechaVenKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car>'/' && car > '\b'){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void buscarProducto(KeyEvent event) {
       FilteredList<Productos> filteredData = new FilteredList<>(productos, p -> true);
      tblProductos.setItems(filteredData); 
      
      txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
          filteredData.setPredicate(prod -> {
              
              if(newValue==null || newValue.isEmpty()){
                  return true;
              }
              
              String lowerCaseFilter= newValue.toLowerCase();
              String lowerCase= String.valueOf(newValue.toLowerCase());
              
               if(prod.getNombre().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                  return true;
              }else{
                  return false;
              }
              
          });
      });
        
      SortedList<Productos> sortedData = new SortedList<>(filteredData);
      sortedData.comparatorProperty().bind(tblProductos.comparatorProperty());
      
      tblProductos.setItems(sortedData);
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
    
    public static boolean isPriceValid(String price) {
        final Pattern EMAIL_REGEX = Pattern.compile("^[0-9]+(\\.[0-9]{1,2})?$", Pattern.CASE_INSENSITIVE);
        if(Integer.parseInt(price) <= 0){        
            JOptionPane.showMessageDialog(null, "El precio no puede ser 0 o menor", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else{
            if(EMAIL_REGEX.matcher(price).matches()) {
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "El precio ingresado no es valido");
                   return false;
            }
           
        }
        return false;
    }
   

    
    
    
}


    

