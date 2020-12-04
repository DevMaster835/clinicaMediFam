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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        formatoFecha();
        tablaProductos();
        seleccionar();
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
    
        @FXML
    private void agregarProductos(ActionEvent event) {
        
        if (txtcodigoProd.getText() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de Codigo del Producto esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtnombreProd.getText() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de Nombre esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtPrecio.getText() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de Precio esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtfechaVen.getValue() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de fecha esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtExistencia.getText() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de Existencia esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtconNeto.getText() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de Contenido neto esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else{   
        
        try{
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
            Logger.getLogger(VistaPacientesController.class.getName()).log(Level.SEVERE, null, ex);
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

    //EVENTO KEY TYPED
    @FXML
    private void txtCodigoKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
        
    }

    @FXML
    private void txtNombreKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isAlphabetic(car) && !Character.isSpaceChar(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten letras");
        }
    }

    @FXML
    private void txtPrecioKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car>'.'){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtExistenciaKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtConetidoKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    private void txtFechaVenKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car>'/'){
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
              
              /*if (prod.getId()){
                  return true;*/
              /*}else*/ if(prod.getNombre().toLowerCase().indexOf(lowerCaseFilter)!= -1){
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
    
    
    
}


    

