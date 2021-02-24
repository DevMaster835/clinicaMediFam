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
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import modelos.ProductoC;


/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaCompraController implements Initializable {
    conexion con= new conexion();
    
    Connection cone= con.openConnection();
    PreparedStatement pps;
    ResultSet rs;
    
    @FXML
    private TextField txtidEmp;
    @FXML
    private TextField txtempleado;
    @FXML
    private TextField txtcodigoProd;
    @FXML
    private TextField txtproducto;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtprecioProd;
    @FXML
    private TextField txtcantidad;
    @FXML
    private TextField txtConNeto;
            
    ObservableList <ProductoC>  listadetalle;
   
    
     private double xOffset = 0; 
     private double yOffset = 0;
    
    @FXML
    private TableColumn<?, ?> colCodigo;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colPrecio;
    @FXML
    private TableColumn<?, ?> colConNeto;
    @FXML
    private TableColumn<?, ?> colCantidad;
    @FXML
    private TableColumn<?, ?> colSubtototal;
    @FXML
    private TableView<ProductoC> tablaProductos;
    private ComboBox<String> cmbConceptos;
    
    ObservableList<String> items =FXCollections.observableArrayList();
    @FXML
    private Tab paneProducto;
    private Tab paneServicio;
    @FXML
    private Tab paneDatos;
    @FXML
    private Button Close;
    @FXML
    private Button Minimize;
    @FXML
    private Button Return;
    
    String idEmpl= VistaLoginController.idEmp;
    @FXML
    private Button btnbuscarEmp;
    @FXML
    private TextField txtidPr;
    @FXML
    private TextField txtproveedor;
    @FXML
    private TextField txtRTN;
    @FXML
    private TextField txtContacto;
    @FXML
    private TextField txtcompras;
    @FXML
    private TextField txtfechaCompras;
    @FXML
    private Button btnbuscarProv;
    private Button txtidDetalle;
    @FXML
    private Button btnbuscarProd2;
  
    @FXML
    private Button btnAñadirProd;
    @FXML
    private Label lbidemp;
    @FXML
    private Label lbidprov;
    @FXML
    private Label lbcodprod;
    @FXML
    private Label lbcantidad;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicializarAlertas();
       txtfechaCompras.setText(mostrarFecha());
       tablaProductos();
      
       
       txtempleado.setText(VistaLoginController.nombresEmp + " " + VistaLoginController.apellidosEmp);
       txtidEmp.setText(idEmpl);

    }
    
    public void inicializarAlertas(){
        lbidprov.setVisible(false);
        lbidemp.setVisible(false);
        lbcodprod.setVisible(false);
        lbcantidad.setVisible(false);
        lbcodprod.setVisible(false);
        lbcantidad.setVisible(false);
    }
    
    public void noCompra(){
        try {
            pps=cone.prepareStatement("SELECT COUNT(idCompra) + 1 as ID FROM compras;");
            rs=pps.executeQuery();
            
            if(rs.next()){
                txtcompras.setText(rs.getString("ID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaCompraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tablaProductos(){
        
       listadetalle=FXCollections.observableArrayList();
       colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
       colNombre.setCellValueFactory(new PropertyValueFactory("producto"));
       colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
       colConNeto.setCellValueFactory(new PropertyValueFactory("contNeto"));
       colCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
       colSubtototal.setCellValueFactory(new PropertyValueFactory("subtotal"));
    }
    
    
   
    
    public void limpiarProductos(){
        txtcodigoProd.setText("");
        txtproducto.setText("");
        txtConNeto.setText("");
        txtprecioProd.setText("");
        txtcantidad.setText("");
    }
    public void limpiarEmpleado(){
        txtidEmp.setText("");
        txtempleado.setText("");
        
    }
   public void limpiarProvedor(){
       txtcompras.setText("");
       txtidPr.setText("");
        txtproveedor.setText("");
        txtRTN.setText("");
        txtContacto.setText("");
       
    }
   
   private String mostrarFecha(){
        int dia,mes,año;
        dia= Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        mes=Calendar.getInstance().get(Calendar.MONTH)+ 1;
        año= Calendar.getInstance().get(Calendar.YEAR);
        
        return dia + "-" + mes + "-" + año ;
    }
    
    private String fecha(){
        int dia,mes,año;
        dia= Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        mes=Calendar.getInstance().get(Calendar.MONTH)+ 1;
        año= Calendar.getInstance().get(Calendar.YEAR);
        
        return año + "-" + mes + "-" + dia ;
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
    
    @FXML
    private void buscarEmpleado(ActionEvent event) {
        
          try{
            pps=cone.prepareStatement("SELECT * FROM empleados where idEmpleado=?");
            pps.setString(1, txtidEmp.getText());
            rs=pps.executeQuery();
            
            if(rs.next()){
                txtempleado.setText(rs.getString("nombres"));
               
                
            }else{
                JOptionPane.showMessageDialog(null, "No existe ningun Empleado con esta identidad: " + txtidEmp.getText(), "No existe empleado", JOptionPane.INFORMATION_MESSAGE);
                txtcodigoProd.requestFocus();
            }
        }catch (SQLException ex) {
            Logger.getLogger(VistaCompraController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void buscarProducto(ActionEvent event) {
         try{
            pps=cone.prepareStatement("SELECT * FROM productos where idProducto=?");
            pps.setString(1, txtcodigoProd.getText());
            rs=pps.executeQuery();
            
            if(rs.next()){
                txtproducto.setText(rs.getString("nombre"));
                txtConNeto.setText(rs.getString("contenidoNeto"));
                txtprecioProd.setText(rs.getString("idPrecioHis"));
              
            }else{
                JOptionPane.showMessageDialog(null, "No existe ningun Producto con este codigo: " + txtcodigoProd.getText(), "No existe producto", JOptionPane.INFORMATION_MESSAGE);
                txtcodigoProd.requestFocus();
            }
        }catch (SQLException ex) {
            Logger.getLogger(VistaCompraController.class.getName()).log(Level.SEVERE, null, ex);
          
        }
            
    }
    
    @FXML
    private void buscarProveedor(ActionEvent event) {
         try{
            pps=cone.prepareStatement("SELECT * FROM proveedores where idProveedor=?");
            pps.setString(1, txtidPr.getText());
            rs=pps.executeQuery();
            
            if(rs.next()){
                txtproveedor.setText(rs.getString("nombreProveedor"));
                txtContacto.setText(rs.getString("nombreContacto"));
                txtRTN.setText(rs.getString("RTN"));
                
              
            }else{
                JOptionPane.showMessageDialog(null, "No existe ningun Proveedor con esta identidad: " + txtidPr.getText(), "No existe proveedor", JOptionPane.INFORMATION_MESSAGE);
                txtcodigoProd.requestFocus();
            }
        }catch (SQLException ex) {
            Logger.getLogger(VistaCompraController.class.getName()).log(Level.SEVERE, null, ex);
          
        }
            
    }
    
    

  
    @FXML
    private void guardarCompra(ActionEvent event) {
      /*  if (txtcompras.getText().isEmpty() ){
           JOptionPane.showMessageDialog(null, "El campo 'N° de Compra' está vacío, por favor ingrese el número de Compra.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else*/ if(txtfechaCompras.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Fecha' está vacío, por favor seleccione la fecha.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
   /*    }else if(txtidEmp.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Identidad' está vacío, por favor ingrese la identidad del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }*/
       }else if(txtidEmp.getText().isEmpty()){
          JOptionPane.showMessageDialog(null, "El campo 'Identidad' está vacío, por favor ingrese la identidad del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       
       }else if(txtempleado.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Empleado' está vacío, por favor ingrese el nombre del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtidPr.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Identidad' está vacío, por favor ingrese la identidad del Proveedor.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtproveedor.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Nombre del Proveedor' está vacío, por favor ingrese el nombre del Nombre del Proveedor.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtRTN.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'RTN' está vacío, por favor ingrese el nombre del RTN.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       } else if (tablaProductos.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar al menos un Producto", "¡Error!", JOptionPane.ERROR_MESSAGE);
       }else{

       try{
           noCompra();
            pps=cone.prepareStatement("INSERT INTO compras ( fechaCompra, idEmpleado, idProveedor) VALUES(?,?,?)");         
               pps.setString(1, fecha());
               pps.setString(2, txtidEmp.getText());
               pps.setString(3, txtidPr.getText());
               
               pps.executeUpdate();
               
              for(int i=0;i<tablaProductos.getItems().size();i++){
                  
                     pps =cone.prepareStatement("INSERT INTO detalle_compras(idCompra,idProducto,cantidad) VALUES(?,?,?)");
                    // pps.setString(1, txtidDetalle.getText());
                     pps.setString(1, txtcompras.getText());
                     pps.setString(2, String.valueOf(tablaProductos.getItems().get(i).getCodigo()));
                     pps.setString(3, String.valueOf(tablaProductos.getItems().get(i).getCantidad()));
                    
                     pps.executeUpdate();
                     
                      PreparedStatement p1=cone.prepareStatement("UPDATE productos,detalle_compras SET stock= (stock+" + tablaProductos.getItems().get(i).getCantidad()  + ") "
                                            + " WHERE productos.idProducto=? and detalle_compras.idCompra=?");
                                p1.setString(1, String.valueOf(tablaProductos.getItems().get(i).getCodigo()));
                                p1.setString(2, txtcompras.getText());
                                p1.executeUpdate();
              }
       
              
             Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Confirmación");
                alert.setContentText("La Compra se ha guardado exitosamente");
                alert.showAndWait();
       
         }catch (SQLException ex) {
              
            Logger.getLogger(VistaCompraController.class.getName()).log(Level.SEVERE, null, ex);
            tablaProductos();
            limpiarProductos();
            limpiarEmpleado();
            limpiarProvedor();
            
        }
       }
    }
    
    

    @FXML
    private void cancelar(ActionEvent event) {
       
        limpiarProductos();
        limpiarProductos();
        limpiarEmpleado();
        limpiarProvedor();
        tablaProductos.getItems().clear();
       
        
    }

    //EVENTOS KEY-TYPED
  

    
    @FXML
    private void txtcodProdKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            lbcodprod.setVisible(true);
            lbcodprod.setText("Sólo se permiten números");
        }else{
            lbcodprod.setVisible(false);
        }
    }

    @FXML
    private void txtcantidadKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            lbcantidad.setVisible(true);
            lbcantidad.setText("Sólo se permiten números");
        }else{
            lbcantidad.setVisible(false);
        }    }

    @FXML
    private void txtidPacKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            lbidprov.setVisible(true);
            lbidprov.setText("Sólo se permiten números");
        }else{
            lbidprov.setVisible(false);
        }
    }
 
    @FXML
    private void añadirProducto(ActionEvent event) {
        
       
        
       if(txtcodigoProd.getText().isEmpty()){
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El Codigo del Producto no puede ir vacío");
            alert.showAndWait();
            txtcodigoProd.requestFocus();
            
        }else if(txtproducto.getText().isEmpty()){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El Nombre del Producto no puede ir vacío");
            alert.showAndWait();
             txtproducto.requestFocus();
            
        }else if (txtprecioProd.getText().isEmpty()){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El Precio del Producto no puede ir vacío");
            alert.showAndWait();
            txtprecioProd.requestFocus();
            
        }else if(txtConNeto.getText().isEmpty()){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El Contenido Neto del Producto no puede ir vacío");
            alert.showAndWait();
             txtConNeto.requestFocus();
            
        }else if(txtcantidad.getText().isEmpty()){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("La Cantidad del Producto no puede ir vacío");
            alert.showAndWait();
             txtcantidad.requestFocus();
        }
        
         int codigo = Integer.parseInt(txtcodigoProd.getText());
        String nombre = txtproducto.getText();      
        Double precio = Double.parseDouble(txtprecioProd.getText());
        int conNeto = Integer.parseInt(txtConNeto.getText());
        int cantidad = Integer.parseInt(txtcantidad.getText());
        double subtotal= 0;
        
        subtotal+= (precio*cantidad);
        ProductoC pro= new ProductoC(codigo,nombre,precio,conNeto,cantidad,subtotal);
       // Productos prod = new Productos(codigo,nombre,precio,conNeto,cantidad);
        
        listadetalle.add(pro);
        tablaProductos.setItems(listadetalle);
        limpiarProductos();
        
        
    }

    private void txtidEmpKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

  
    @FXML
    private void txtcompraKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }
    
    





}
