/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import modelos.Facturacion;
import Conexion.conexion;
import com.mysql.jdbc.Connection;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javax.swing.JOptionPane;
import javax.swing.event.ChangeListener;
import modelos.DetalleFacturacion;
import modelos.ProductoC;

import modelos.Productos;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaFacturacionController implements Initializable {
    conexion con= new conexion();
    
    Connection cone= con.openConnection();
    PreparedStatement pps;
    ResultSet rs;
    
    @FXML
    private TextField txtidEmp;
    @FXML
    private TextField txtempleado;
    @FXML
    private Button btnbuscarEmp;
   
    @FXML
    private TextField txtFactura;
    @FXML
    private DatePicker txtfechaFactura;
    @FXML
    private TextField txtcodigoProd;
    @FXML
    private TextField txtproducto;
    @FXML
    private Button btnbuscarProd;
    @FXML
    private TextField txtidPac;
    @FXML
    private TextField txtpaciente;
    @FXML
    private Button btnbuscarPac;
    @FXML
    private Button btnGuardar;
     @FXML
    private Button btnAñadir;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtprecioProd;
    @FXML
    private TextField txtcantidad;
    @FXML
    private TextField txtConNeto;
            
    ObservableList <ProductoC>  listadetalle;
    @FXML
    private Button btnAñadirProd;
    @FXML
    private TableView<ProductoC> tabladetalle;
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
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       listadetalle=FXCollections.observableArrayList();
       colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
       colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
       colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
       colConNeto.setCellValueFactory(new PropertyValueFactory("conNeto"));
       colCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        GestionarEvento();
    }    

    
    public void GestionarEvento(){
        
       /*tbltabladetalle.getSelectionModel().selectedItemProperty().addListener( 
               new ChangeListener<DetalleVenta>(){
                   @
           
       });
        
        */
        
        
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
            Logger.getLogger(VistaFacturacionController.class.getName()).log(Level.SEVERE, null, ex);
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
              
            }else{
                JOptionPane.showMessageDialog(null, "No existe ningun Producto con este codigo: " + txtcodigoProd.getText(), "No existe producto", JOptionPane.INFORMATION_MESSAGE);
                txtcodigoProd.requestFocus();
            }
        }catch (SQLException ex) {
            Logger.getLogger(VistaFacturacionController.class.getName()).log(Level.SEVERE, null, ex);
          
        }
            
        }
        
    

    @FXML
    private void buscarPaciente(ActionEvent event) {
         try{
            pps=cone.prepareStatement("SELECT * FROM pacientes where idPaciente=?");
            pps.setString(1, txtidPac.getText());
            rs=pps.executeQuery();
            
            if(rs.next()){
                txtpaciente.setText(rs.getString("nombres"));
            }else{
                JOptionPane.showMessageDialog(null, "No existe ningun paciente con identidad: " + txtidPac.getText(), "No existe paciente", JOptionPane.INFORMATION_MESSAGE);
                txtidPac.requestFocus();
            }
        }catch (SQLException ex) {
            Logger.getLogger(VistaFacturacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
   /* 
    @FXML
    private void guardarFactura(ActionEvent event) {
        if (txtFactura.getText().isEmpty() ){
           JOptionPane.showMessageDialog(null, "El campo 'Factura No' está vacío, por favor ingrese el número de factura.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtfechaFactura.getValue() == null ){
           JOptionPane.showMessageDialog(null, "El campo 'Fecha' está vacío, por favor seleccione la fecha.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtidEmp.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Identidad' está vacío, por favor ingrese la identidad del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtempleado.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Empleado' está vacío, por favor ingrese el nombre del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtidPac.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Identidad' está vacío, por favor ingrese la identidad del paciente.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtpaciente.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Paciente' está vacío, por favor ingrese el nombre del paciente.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtcodigoProd.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Código' está vacío, por favor ingrese el código del producto.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtproducto.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Producto' está vacío, por favor ingrese el nombre del producto.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtcontNeto.getText().isEmpty()){   
           JOptionPane.showMessageDialog(null, "El campo 'Contenido Neto' está vacío, por favor ingrese el contenido neto del producto.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtprecioProd.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Precio' está vacío, por favor ingrese el precio.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtcantidad.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Cantidad' está vacío, por favor ingrese el precio.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }
    }*/

    @FXML
    private void cancelar(ActionEvent event) {
    }

    //EVENTOS KEY-TYPED
    @FXML
    private void txtidEmpKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtFacturaKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtcodProdKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtcantidadKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtidPacKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }
  /*@FXML
    public int AgregarFactura(Connection connection){
        LocalDate fechaFactura= txtfechaFactura.getValue();
 
       
        try{
            
              PreparedStatement pps = cone.prepareStatement("Insert into Facturacion (idFacturacion,fechaFactura,idEmpleado,idProducto) values(?,?,?,?)");
      
             pps.setString(1, txtFactura.getText());
             pps.set(2, String.valueOf(fechaFactura));
             pps.setString(3, txtidEmp.getText());
             pps.setString(4, txtidPac.getText());
             
             pps.executeUpdate();
             
             
            
             pps = cone.prepareStatement("Insert into detalle_Facturacion (idFacturacion,Cantidad,idProducto,) values(?,?,?)");
             
             pps.setString(1, txtFactura.getText());
             pps.setString(2, txtcantidad.getText());
             pps.setString(3, txtcodigoProd.getText());
             
            return pps.executeUpdate();
            
            
          //  JOptionPane.showMessageDialog(null, "Se ha registrado la Factura", "Datos guardados", JOptionPane.PLAIN_MESSAGE);
       
        }catch(SQLException ex){
            
            
           Logger.getLogger(VistaFacturacionController.class.getName()).log(Level.SEVERE, null, ex);
             return 0;
        }
             
        
    }
    */
    
   @FXML
    public void guardarFactura(ActionEvent event){
    /*if (txtFactura.getText().isEmpty() ){
           JOptionPane.showMessageDialog(null, "El campo 'Factura No' está vacío, por favor ingrese el número de factura.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtfechaFactura.getValue() == null ){
           JOptionPane.showMessageDialog(null, "El campo 'Fecha' está vacío, por favor seleccione la fecha.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtidEmp.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Identidad' está vacío, por favor ingrese la identidad del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtempleado.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Empleado' está vacío, por favor ingrese el nombre del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtidPac.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Identidad' está vacío, por favor ingrese la identidad del paciente.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtpaciente.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Paciente' está vacío, por favor ingrese el nombre del paciente.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtcodigoProd.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Código' está vacío, por favor ingrese el código del producto.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtproducto.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Producto' está vacío, por favor ingrese el nombre del producto.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtcontNeto.getText().isEmpty()){   
           JOptionPane.showMessageDialog(null, "El campo 'Contenido Neto' está vacío, por favor ingrese el contenido neto del producto.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtprecioProd.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Precio' está vacío, por favor ingrese el precio.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtcantidad.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Cantidad' está vacío, por favor ingrese el precio.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }*/
   Facturacion a = new   Facturacion(txtFactura.getText(),
                                    Date.valueOf(txtfechaFactura.getValue()),
                                    txtidEmp.getText(),
                                    txtidPac.getText()
   );
      
   
     int resultado;
    
      resultado= a.AgregarFactura(con.getCon());
      
        JOptionPane.showMessageDialog(null, "Se ha registrado la Factura", "Datos guardados", JOptionPane.PLAIN_MESSAGE);

    /*  
    if(resultado==1){
        factura.add(a);
        JOptionPane.showMessageDialog(null, "Se ha registrado la Factura", "Datos guardados", JOptionPane.PLAIN_MESSAGE);
         
    }
    */
}
   
 @FXML   
public void AgregarDetalle(ActionEvent event)  {
    
    DetalleFacturacion a = new DetalleFacturacion(
            String.valueOf(0),
             txtFactura.getText(),
             txtcodigoProd.getText(),
             txtcantidad.getText()
    );
    
    
    int cant;
    
   cant= a.AgregarDetalle(con.getCon());
     /*if(cant==1){
       listadetalle.add(a);*/
     
}
   /* public void guardar(ActionEvent event){
        
      
    }*/
    


    @FXML
    private void añadirProducto(ActionEvent event) {
        
        int codigo = Integer.parseInt(txtcodigoProd.getText());
        String nombre = txtproducto.getText();      
        int conNeto = Integer.parseInt(txtConNeto.getText());
         Double precio = Double.parseDouble(txtprecioProd.getText());
        int cantidad = Integer.parseInt(txtcantidad.getText());
        
        ProductoC pro= new ProductoC(codigo,nombre,conNeto,precio,cantidad);
       // Productos prod = new Productos(codigo,nombre,precio,conNeto,cantidad);
        
        listadetalle.add(pro);
        tabladetalle.setItems(listadetalle);
        
        
    }
}
